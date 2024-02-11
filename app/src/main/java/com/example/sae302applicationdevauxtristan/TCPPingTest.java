package com.example.sae302applicationdevauxtristan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.net.InetAddress;

public class TCPPingTest extends AppCompatActivity {

    private EditText ipAddressEditText;
    private Button manualPingButton;
    private Button loopbackPingButton;
    private Button googlePingButton;
    private TextView pingResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp_ping_test);

        ipAddressEditText = findViewById(R.id.ipAddressEditText);
        manualPingButton = findViewById(R.id.manualPingButton);
        loopbackPingButton = findViewById(R.id.loopbackPingButton);
        googlePingButton = findViewById(R.id.googlePingButton);
        //pingResultTextView = findViewById(R.id.pingResultTextView);

        manualPingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ipAddress = ipAddressEditText.getText().toString();
                if (!ipAddress.isEmpty()) {
                    pingIpAddress(ipAddress);
                }
            }
        });

        loopbackPingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pingIpAddress("127.0.0.1");
            }
        });

        googlePingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pingIpAddress("8.8.8.8");
            }
        });
    }

    private void pingIpAddress(final String ipAddress) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InetAddress inetAddress = InetAddress.getByName(ipAddress);
                    boolean reachable = inetAddress.isReachable(5000); // Timeout in milliseconds
                    if (reachable) {
                        updatePingResult("Ping successful to " + ipAddress);
                    } else {
                        updatePingResult("Ping failed to " + ipAddress);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    updatePingResult("Error: " + e.getMessage());
                }
            }
        }).start();
    }

    private void updatePingResult(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                pingResultTextView.setText(result);
            }
        });
    }
}
