package com.ttr.linklib;

import java.util.ArrayList;

/**
 * Created by pietro on 21/11/16.
 */

public class Message
{
    private String notification="echo";
    private Parameters parameters;
    public Message(String id)
    {
        this.parameters=new Parameters(id);
    }
    public void addData(String sensorName,float sensorData)
    {
        SensorData s=new SensorData();
        s.setSensorName(sensorName);
        s.setSensorData(sensorData);
        parameters.addSensor(s);
    }

}
class Parameters {
    private String command = "send_data";
    private String id;
    private Sensors sensors_data=new Sensors();
    private long timestamp;
    public Parameters(String id)
    {this.id=id; this.timestamp=System.currentTimeMillis();}
    public void addSensor(SensorData s) {
        sensors_data.setValue(s);
    }
}
class Sensors
{
    private Float light_sensor;
    private Float temperature_sensor;
    private Float pressure_sensor;
    private Float umidity_sensor;
    public void setValue(SensorData s)
    {
            if(s.getSensorName().equals("light_sensor"))
             light_sensor=new Float(s.getSensorData());
            else if(s.getSensorName().equals("pressure_sensor"))
                pressure_sensor=new Float(s.getSensorData());
            else if(s.getSensorName().equals("temperature_sensor"))
                temperature_sensor=new Float(s.getSensorData());
            else if(s.getSensorName().equals("umidity_sensor"))
                umidity_sensor=new Float(s.getSensorData());
    }

}