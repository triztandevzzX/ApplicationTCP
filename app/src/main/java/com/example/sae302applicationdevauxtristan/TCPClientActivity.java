
package com.example.sae302applicationdevauxtristan;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        Button downloadButton = findViewById(R.id.downloadButton);
        downloadButton.setOnClickListener(view -> downloadFile());

        new Thread(this::connectToServer).start();
        loadAndDisplayJsonContent();
    }

    private void loadAndDisplayJsonContent() {
        String filename = "fichier_json_1.json";
        try {
            InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String jsonContent = new String(buffer, "UTF-8");

            // Affiche le contenu JSON dans le TextView
            TextView fileNameTextView = findViewById(R.id.file_name);
            fileNameTextView.setText(jsonContent);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void downloadFile() {
        String filename = "fichier_json_1_sauvegarde.json";
        String content = ((TextView) findViewById(R.id.file_name)).getText().toString();

        try {
            FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();

            // Affiche un message de confirmation
            Toast.makeText(this, "Fichier téléchargé avec succès", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Erreur lors du téléchargement", Toast.LENGTH_SHORT).show();
        }
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
