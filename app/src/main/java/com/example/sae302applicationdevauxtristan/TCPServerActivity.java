package com.example.sae302applicationdevauxtristan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServerActivity extends AppCompatActivity {
    private TextView serverStatusTextView;
    private ServerSocket serverSocket;
    private ExecutorService executorService = Executors.newFixedThreadPool(4); // For handling client threads
    private static final int SERVER_PORT = 443; // Example server port

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp_server);

        serverStatusTextView = findViewById(R.id.serverStatusTextView);
        startServer();
    }

    private void startServer() {
        executorService.submit(() -> {
            try {
                serverSocket = new ServerSocket(SERVER_PORT);
                runOnUiThread(() -> serverStatusTextView.setText("Le serveur utilise le port " + SERVER_PORT));

                while (!serverSocket.isClosed()) {
                    Socket clientSocket = serverSocket.accept();
                    handleClient(clientSocket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void handleClient(Socket clientSocket) {
        executorService.submit(() -> {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    // Echo the received message back to the client
                    out.println(inputLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
    }
}
