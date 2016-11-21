package tota_tagliente_riccardi.it.sensorlib;

import android.app.Service;
import android.content.Context;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import  android.hardware.Sensor;
import  android.hardware.SensorManager;

public abstract class SensorHandler extends Service implements SensorEventListener {

    Sensor s;

    private float data=55;
    private Context context;

    private String sensorName = "";
    private int type;

    protected SensorManager mSensorManager;

    public SensorHandler(String name, int type,Context context) {
        setSensorName(name);
        setSensorType(type);
        setContext(context);
    }
    public void setSensorType(int type) {this.type=type;}
    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }
    public void setContext(Context context) {this.context=context;}
    public void changeData(float data)
    {
        this.data=data;
    }

    public String getSensorName() {
        return sensorName;
    }

    public float getData() {
        return data;
    } // Valore letto dal sensore


    // metodi che registrano e cancellano la registrazione dal sensore, senza di questi non si dovrebbe riuscire a recuperare il dato
    public void onCreate() {
        mSensorManager =(SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
        s = mSensorManager.getDefaultSensor(type);
        mSensorManager.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);


    }
    @Override
    //aggiorna il dato col valore più corrente quando il dato è disponibile sul sensore, ovvero quando cambia la luminosità ad esempio
    public void onSensorChanged(SensorEvent sensorEvent) {

        changeData(sensorEvent.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}
