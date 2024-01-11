package com.example.sae302applicationdevauxtristan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private TCPServer tcpServer;
    private boolean isServerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Référence au bouton "En tant que serveur !"
        Button tcpButton = findViewById(R.id.serveurButton);

        tcpServer = new TCPServer();

        tcpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isServerRunning) {
                    tcpServer.stopServer();
                    isServerRunning = false;
                    tcpButton.setText("En tant que serveur !");
                } else {
                    tcpServer.startServer();
                    isServerRunning = true;
                    tcpButton.setText("Arrêter le serveur !");
                    // Changer de vue vers activity_tcp_serveur.xml
                    Intent intent = new Intent(MainActivity.this, TCPServerActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tcpServer != null) {
            tcpServer.stopServer();
        }
    }

    // Classe TCPServer
    private class TCPServer {

        private ServerSocket serverSocket;
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private boolean isRunning = false;
        private int port = 8080; // Port utilisé pour la connexion

        public void startServer() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        serverSocket = new ServerSocket(port);
                        isRunning = true;

                        while (isRunning) {
                            System.out.println("Attente de connexion...");
                            clientSocket = serverSocket.accept(); // Attente de connexion du client

                            out = new PrintWriter(clientSocket.getOutputStream(), true);
                            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                            String inputLine;
                            while ((inputLine = in.readLine()) != null) {
                                System.out.println("Message reçu : " + inputLine);
                                out.println("Message reçu : " + inputLine); // Répond au client
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        public void stopServer() {
            try {
                isRunning = false;
                if (in != null) in.close();
                if (out != null) out.close();
                if (clientSocket != null) clientSocket.close();
                if (serverSocket != null) serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
