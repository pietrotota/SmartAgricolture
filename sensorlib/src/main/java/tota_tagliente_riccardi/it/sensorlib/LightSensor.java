package tota_tagliente_riccardi.it.sensorlib;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

/**
 * Created by Luke on 09/11/2016.
 */

public class LightSensor extends SensorHandler<Float> {


    float cLux=0;//luminosità espressa in lux
    Sensor mTemp= mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);;
    @Override
    public Float getData() {
        return cLux;
    }

    @Override
    //aggiorna il dato col valore più corrente quando il dato è disponibile sul sensore, ovvero quando cambia la luminosità ad esempio
    public void onSensorChanged(SensorEvent sensorEvent) {

        cLux=sensorEvent.values[0];
    }

    @Override

    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}
