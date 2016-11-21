package tota_tagliente_riccardi.it.sensorlib;

import android.content.Intent;
import android.hardware.Sensor;
import android.os.IBinder;
import android.content.Context;

public class TemperatureSensor extends SensorHandler {
    public TemperatureSensor(Context context) {
        super("temperature_sensor", Sensor.TYPE_AMBIENT_TEMPERATURE,context);
    }
    @Override
    public IBinder onBind(Intent i)
    {
        return null;
    }

}
