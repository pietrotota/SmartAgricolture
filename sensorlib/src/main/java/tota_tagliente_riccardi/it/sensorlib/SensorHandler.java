package tota_tagliente_riccardi.it.sensorlib;

import android.app.Activity;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import  android.hardware.Sensor;
import android.hardware.SensorListener;
import  android.hardware.SensorManager;

import static android.content.Context.SENSOR_SERVICE;

public abstract class SensorHandler extends Activity implements SensorEventListener {

    Sensor s;

    protected float data;

    private String sensorName = "";
    private int type=0;

    protected SensorManager mSensorManager;

    public SensorHandler(int type) {
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        s = mSensorManager.getDefaultSensor(type);
    }

    public SensorHandler(String name, int type) {
        setSensorName(name);
        setSensorType(type);
    }
    public void setSensorType(int type) {this.type=type;}
    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getSensorName() {
        return sensorName;
    }

    public float getData() {
        return data;
    } // Valore letto dal sensore


    // metodi che registrano e cancellano la registrazione dal sensore, senza di questi non si dovrebbe riuscire a recuperare il dato
    protected void onCreate() {
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        s = mSensorManager.getDefaultSensor(type);


    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener((SensorListener) this);
    }

    @Override
    //aggiorna il dato col valore più corrente quando il dato è disponibile sul sensore, ovvero quando cambia la luminosità ad esempio
    public void onSensorChanged(SensorEvent sensorEvent) {

        data = sensorEvent.values[0];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}
