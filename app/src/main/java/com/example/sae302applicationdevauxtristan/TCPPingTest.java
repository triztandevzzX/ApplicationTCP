package com.example.sae302applicationdevauxtristan;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.InetAddress;

public class TCPPingTest extends AppCompatActivity {

    private ImageView pingImageView;
    private TextView titleTextView;
    private EditText ipAddressEditText;
    private Button pingButton;
    private TextView pingResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp_ping_test);

        // Initialiser les éléments de l'interface
        initViews();
    }

    // Initialiser les éléments de l'interface
    private void initViews() {
        pingImageView = findViewById(R.id.pingImageView);
        titleTextView = findViewById(R.id.titleTextView);
        ipAddressEditText = findViewById(R.id.ipAddressEditText);
        pingButton = findViewById(R.id.pingButton);
        pingResultTextView = findViewById(R.id.pingResultTextView);

        // Ajouter un effet de clic au bouton
        pingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPingTest();
            }
        });

        // Animation de clic pour le bouton
        pingButton.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    v.setScaleX(0.95f);
                    v.setScaleY(0.95f);
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    v.setScaleX(1f);
                    v.setScaleY(1f);
                    break;
                }
            }
            return false;
        });
    }

    // Méthode pour lancer le test de ping
    private void startPingTest() {
        Log.d("PingTest", "startPingTest() called");

        String ipAddress = ipAddressEditText.getText().toString().trim();

        if (!ipAddress.isEmpty()) {
            new PingTask().execute(ipAddress);
        } else {
            Toast.makeText(this, "Veuillez entrer une adresse IP à tester", Toast.LENGTH_SHORT).show();
        }
    }

    // AsyncTask pour effectuer le test de ping en arrière-plan
    private class PingTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String ipAddress = params[0];
            String result;

            try {
                InetAddress inetAddress = InetAddress.getByName(ipAddress);
                boolean isReachable = inetAddress.isReachable(5000); // Timeout de 5 secondes

                if (isReachable) {
                    result = "L'adresse IP est atteignable.";
                } else {
                    result = "L'adresse IP n'est pas atteignable.";
                }
            } catch (IOException e) {
                result = "Erreur lors du test de ping : " + e.getMessage();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            // Mettre à jour l'affichage des résultats
            pingResultTextView.setText(result);
        }
    }
}
