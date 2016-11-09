package tota_tagliente_riccardi.it.sensorlib;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class TemperatureSensor extends SensorHandler<Float> {

    float cTemp=0;
 
    Sensor mTemp= mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);;

    // In questo modo recupero il nome di default del sensore
    String sensorName= mTemp.getName();


    @Override
    public Float getData() {
        return cTemp;
    }

    @Override
    //aggiorna il dato col valore più corrente quando il dato è disponibile sul sensore, ovvero quando cambia la luminosità ad esempio
    public void onSensorChanged(SensorEvent sensorEvent) {

        cTemp=sensorEvent.values[0];
    }

    @Override

    public void onAccuracyChanged(Sensor sensor, int i) {

    }



}
