package tota_tagliente_riccardi.it.sensorlib;


/*
    Interfaccia da usare per la creazione dei sensori
    ovviamente T sarà il tipo di dato letto dal sensore
 */

public abstract class Sensor<T> {
    private String sensorName="";
    public void setSensorName(String sensorName)
    {
        this.sensorName=sensorName;
    }
    public String getSensorName()
    {
        return sensorName;
    }
    public abstract T getData(); // Valore letto dal sensore

}
