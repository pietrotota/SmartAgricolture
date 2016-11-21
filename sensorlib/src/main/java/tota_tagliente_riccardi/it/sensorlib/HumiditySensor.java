package tota_tagliente_riccardi.it.sensorlib;

import android.content.Intent;
import android.hardware.Sensor;
import android.os.IBinder;
import android.content.Context;
/**
 * Created by Luke on 09/11/2016.
 */

public class HumiditySensor extends SensorHandler {

    public HumiditySensor(Context context) {
        super("humidity_sensor", Sensor.TYPE_RELATIVE_HUMIDITY,context);
    }
    @Override
    public IBinder onBind(Intent i)
    {
        return null;
    }

}
