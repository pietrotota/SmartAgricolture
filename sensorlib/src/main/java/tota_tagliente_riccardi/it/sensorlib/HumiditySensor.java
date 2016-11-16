package tota_tagliente_riccardi.it.sensorlib;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

/**
 * Created by Luke on 09/11/2016.
 */

public class HumiditySensor extends SensorHandler {

    public HumiditySensor() {
        super("humidity_sensor", Sensor.TYPE_RELATIVE_HUMIDITY);
    }

}
