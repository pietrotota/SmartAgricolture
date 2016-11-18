package tota_tagliente_riccardi.it.smartagricolture;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ttr.linklib.JSONService;
import com.ttr.linklib.Link;

import java.util.concurrent.Executor;

import tota_tagliente_riccardi.it.sensorlib.*;

public class MainActivity extends Activity {

    Button btnConnect;
    TextView txtLog;
    TextView txtIndirizzo;

    Link link;

    // Pattern singleton
    // Serve a permettere l'accesso dall'esterno, da qualsiasi parte, all'istanza della classe
    public static MainActivity singleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singleton = this;

        // Inizializza gli elementi grafici
        btnConnect = (Button) findViewById(R.id.btnConnect);

        txtIndirizzo = (TextView) findViewById(R.id.txtIndirizzo);
        txtLog = (TextView) findViewById(R.id.txtLog);

        // Definisci la logica al click del pulsante
        btnConnect.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OnBtnConnectClick();
                    }
                }
        );

    }

    void OnBtnConnectClick() {

        if(btnConnect.getText() == "Disconnetti")
        {
            btnConnect.setText("Connetti");
            link.stop();
            return;
        }

        // ----------------

        if (link != null) {
            link.stop();
        }

        Log.d("Link << ", "Creazione in corso...");

        link = new Link(txtIndirizzo.getText().toString()){
            public void onDataCreation(final JSONService json){
                // All'invio di ogni pacchetto, facciamo il log

                // TODO: sta cosa mi sta scassando la minchia
                //MainActivity.singleton.LogLine(json.toString());
            }
        };

        // Registra i sensori

        link.registerSensor(new TemperatureSensor());
        link.registerSensor(new HumiditySensor());
        link.registerSensor(new LightSensor());
        link.registerSensor(new PressureSensor());

        // avvia il thread

        Log.d("Link << ", "Avvio...");

        new Thread(link).start();

        btnConnect.setText("Disconnetti");
    }

    public void LogLine(String text) {
        Log.d("Log << ", text);
        txtLog.setText(txtLog.getText() + "\n" + text);
    }
}
