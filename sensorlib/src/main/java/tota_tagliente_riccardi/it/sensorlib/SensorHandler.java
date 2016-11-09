package tota_tagliente_riccardi.it.sensorlib;


/*
    Interfaccia da usare per la creazione dei sensori
    ovviamente T sarà il tipo di dato letto dal sensore
 */

import android.app.Activity;
import android.hardware.SensorEventListener;
import  android.hardware.Sensor;
import android.hardware.SensorListener;
import  android.hardware.SensorManager;

import static android.content.Context.SENSOR_SERVICE;

public abstract class SensorHandler<T> extends Activity implements SensorEventListener{

    public SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


    //Che sono sti metodi e a cosa servono???
    private String sensorName="";
    public void setSensorName(String sensorName)
    {
        this.sensorName=sensorName;
    }
    public String getSensorName()
    {
        return sensorName;
    }
    public abstract T getData(); // Valore letto dal sensore


    // metodi che registrano e cancellano la registrazione dal sensore, senza di questi non si dovrebbe riuscire a recuperare il dato
    protected void onResume() {
        super.onResume();


   }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener((SensorListener) this);
    }


}
