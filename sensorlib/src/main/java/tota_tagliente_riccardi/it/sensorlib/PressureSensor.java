package tota_tagliente_riccardi.it.sensorlib;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

/**
 * Created by Luke on 09/11/2016.
 */

public class PressureSensor extends SensorHandler {
    public PressureSensor() {
        super("pressure_sensor", Sensor.TYPE_PRESSURE);
    }
}
