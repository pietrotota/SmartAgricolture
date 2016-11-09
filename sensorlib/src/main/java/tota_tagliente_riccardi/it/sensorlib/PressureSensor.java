package tota_tagliente_riccardi.it.sensorlib;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

/**
 * Created by Luke on 09/11/2016.
 */

public class PressureSensor extends SensorHandler {

    float cPress=0;
    Sensor mTemp= mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);;
    @Override
    public Float getData() {
        return cPress;
    }

    @Override
    //aggiorna il dato col valore corrente quando il dato è disponibile sul sensore, ovvero quando cambia la luminosità ad esempio
    public void onSensorChanged(SensorEvent sensorEvent) {

        cPress=sensorEvent.values[0];
    }

    @Override

    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
