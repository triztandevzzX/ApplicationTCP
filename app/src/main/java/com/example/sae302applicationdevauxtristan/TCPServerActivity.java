import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TCPServerActivity extends AppCompatActivity {

    private TextView adresseIPTextView;
    private TextView portTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp_server);

        // Initialiser les TextViews
        adresseIPTextView = findViewById(R.id.adresseIPTextView);
        portTextView = findViewById(R.id.portTextView);

        // Récupérer l'adresse IP du téléphone
        String adresseIP = getDeviceIPAddress();

        // Dynamiquement déterminer le port (à remplacer par ta logique)
        int port = determinePort();

        // Afficher l'adresse IP et le port en permanence
        adresseIPTextView.setText("Adresse IP: " + adresseIP);
        portTextView.setText("Port: " + port);
    }

    // Méthode pour obtenir l'adresse IP du téléphone
    private String getDeviceIPAddress() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();
        return String.format("%d.%d.%d.%d",
                (ipAddress & 0xff),
                (ipAddress >> 8 & 0xff),
                (ipAddress >> 16 & 0xff),
                (ipAddress >> 24 & 0xff));
    }

    // Méthode pour déterminer dynamiquement le port (à remplacer par ta logique)
    private int determinePort() {
        // Ajoute ici ta logique pour déterminer le port
        return 1234; // Exemple de port, remplace-le par ta logique
    }
}
