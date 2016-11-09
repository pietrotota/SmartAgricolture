package tota_tagliente_riccardi.it.sensorlib;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

/**
 * Created by Luke on 09/11/2016.
 */

public class HumiditySensor extends SensorHandler {

    float cHum=0;
    Sensor mTemp= mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);;
    @Override
    public Float getData() {
        return cHum;
    }

    @Override
    //aggiorna il dato col valore più corrente quando il dato è disponibile sul sensore, ovvero quando cambia la luminosità ad esempio
    public void onSensorChanged(SensorEvent sensorEvent) {

        cHum=sensorEvent.values[0];
    }

    @Override

    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
