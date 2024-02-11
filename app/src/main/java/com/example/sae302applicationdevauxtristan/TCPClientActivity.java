package com.example.sae302applicationdevauxtristan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClientActivity extends AppCompatActivity {
    private EditText serverIpEditText;
    private EditText messageEditText;
    private Button sendButton;
    private TextView messagesTextView;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private static final String SERVER_IP = "192.168.1.100"; // Exemple d'adresse IP du serveur
    private static final int SERVER_PORT = 443; // Numéro de port du serveur

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp_client);

        serverIpEditText = findViewById(R.id.serverIpEditText);
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);
        //messagesTextView = findViewById(R.id.messagesTextView);

        sendButton.setOnClickListener(view -> sendMessage());

        new Thread(this::connectToServer).start();
    }

    private void connectToServer() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Lire les messages du serveur et les afficher
            while (true) {
                final String message = in.readLine();
                if (message != null) {
                    runOnUiThread(() -> messagesTextView.append("Server: " + message + "\n"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        String message = messageEditText.getText().toString();
        if (!message.isEmpty() && out != null) {
            out.println(message);
            messagesTextView.append("Client: " + message + "\n");
            messageEditText.setText("");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (socket != null) {
                socket.close();
            }
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
