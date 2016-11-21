package tota_tagliente_riccardi.it.smartagricolture;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.ttr.linklib.Link;
import com.ttr.linklib.SensorData;

public class MainActivity extends Activity implements SensorEventListener{

    private Button btnConnect;
    private TextView txtLog;
    private TextView txtIndirizzo;
    private SensorManager mSensorManager;
    private SensorData[] sensorData={new SensorData(),new SensorData(),new SensorData(),new SensorData()};
    private Sensor[] s=new Sensor[4];
    private Link link;

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
        //registrazione dei sensori e relativi listener
        mSensorManager =(SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
        s[0] = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        s[1] = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        s[2] = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        s[3] = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mSensorManager.registerListener(this, s[0], SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, s[1], SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, s[2], SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, s[3], SensorManager.SENSOR_DELAY_NORMAL);


    }
    @Override
    //aggiorna il dato col valore più corrente quando il dato è disponibile sul sensore, ovvero quando cambia la luminosità ad esempio
    public void onSensorChanged(SensorEvent sensorEvent) {

        if(sensorEvent.sensor.getType()==Sensor.TYPE_AMBIENT_TEMPERATURE) {
            sensorData[0].setSensorData(sensorEvent.values[0]);
            sensorData[0].setSensorName("temperature");
        }
        else if(sensorEvent.sensor.getType()==Sensor.TYPE_LIGHT) {
            sensorData[1].setSensorData(sensorEvent.values[0]);
            sensorData[1].setSensorName("light");
        }
        else if(sensorEvent.sensor.getType()==Sensor.TYPE_RELATIVE_HUMIDITY) {
            sensorData[2].setSensorData(sensorEvent.values[0]);
            sensorData[2].setSensorName("umidity");
        }
        else if(sensorEvent.sensor.getType()==Sensor.TYPE_PRESSURE) {
            sensorData[3].setSensorData(sensorEvent.values[0]);
            sensorData[3].setSensorName("pressure");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

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

        link = new Link(txtIndirizzo.getText().toString());
        link.setSensorData(sensorData);
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
