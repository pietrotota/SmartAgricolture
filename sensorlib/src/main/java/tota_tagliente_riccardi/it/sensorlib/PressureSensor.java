package tota_tagliente_riccardi.it.sensorlib;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.IBinder;

/**
 * Created by Luke on 09/11/2016.
 */

public class PressureSensor extends SensorHandler {
    public PressureSensor(Context context) {
        super("pressure_sensor", Sensor.TYPE_PRESSURE,context);
    }
    @Override
    public IBinder onBind(Intent i)
    {
        return null;
    }
}
