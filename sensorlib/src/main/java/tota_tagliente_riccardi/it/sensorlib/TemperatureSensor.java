package tota_tagliente_riccardi.it.sensorlib;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class TemperatureSensor extends SensorHandler {
    public TemperatureSensor() {
        super("temperature_sensor", Sensor.TYPE_AMBIENT_TEMPERATURE);
    }

}
