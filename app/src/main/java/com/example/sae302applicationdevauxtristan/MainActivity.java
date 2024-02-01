package com.example.sae302applicationdevauxtristan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button serverButton;
    private Button clientButton;
    private TextView ipTextView;
    private TextView portTextView;
    private static final int SERVER_PORT = 12345; // Example port number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serverButton = findViewById(R.id.serveurButton);
        clientButton = findViewById(R.id.clientButton);
        ipTextView = findViewById(R.id.ipTextView);
        portTextView = findViewById(R.id.portTextView);

        serverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startServerActivity();
            }
        });

        clientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startClientActivity();
            }
        });
    }

    private void startServerActivity() {
        Intent serverIntent = new Intent(this, TCPServerActivity.class);
        startActivity(serverIntent);
        displayServerInfo();
    }

    private void startClientActivity() {
        Intent clientIntent = new Intent(this, TCPClientActivity.class);
        startActivity(clientIntent);
    }

    private void displayServerInfo() {
        try {
            String ip = getIPAddress(true);
            ipTextView.setText("Adresse IP : " + ip);
            portTextView.setText("Port de communication : " + SERVER_PORT);
        } catch (Exception e) {
            ipTextView.setText("Impossibilité d'obtenir une adresse IP (erreur 2103)");
        }
    }

    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) { } // for now eat exceptions
        return "";
    }
}
