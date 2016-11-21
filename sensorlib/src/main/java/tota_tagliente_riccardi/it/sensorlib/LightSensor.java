package tota_tagliente_riccardi.it.sensorlib;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.IBinder;

/**
 * Created by Luke on 09/11/2016.
 */

public class LightSensor extends SensorHandler {

    public LightSensor(Context context) {
        super("light_sensor", Sensor.TYPE_LIGHT,context);
    }
    @Override
    public IBinder onBind(Intent i)
    {
        return null;
    }

}
