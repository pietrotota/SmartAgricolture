package com.ttr.linklib;

import tota_tagliente_riccardi.it.sensorlib.Sensor;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class Link implements Runnable {

    private String middlewareURL = null;
    private JSONService jsonService;  //istanza del JSONService per la memorizzazione dei dati dei sensori
    private long timeout,executionTime; //timer per il thread
    private Sensor<Float>[] sensors;    //array dei sensori
    private boolean run=false;
    //passo l'istanza del jsonService cosicchè i dati siano accessibili al di fuori del thread
    public Link(String middlewareURL,JSONService jsonService) {
        this.middlewareURL = middlewareURL;
        this.jsonService=jsonService;
    }
    public void setTimeout(long timeoutSec)
    {
        this.timeout=timeoutSec*1000;   //setto timeout in millisecondi
    }
    public void setRun(boolean run)
    {
        this.run=run;
    }
    public void setSensors(Sensor<Float>[] sensors)
    {
        this.sensors=sensors;
    }
    public boolean sendData(String dataToSend) {
        int response = RestService.Post(middlewareURL, dataToSend);
       //qui controllo che il codice di risposta sia esattamente uguale a 200 (tutto ok) o se diverso (se c'è un'eccezione sarà 0)
        return response == 200;
    }
    //implementazione del metodo run del thread
    @Override
    public void run() {
        executionTime=System.currentTimeMillis();
        while(run)
        {
            long temp=System.currentTimeMillis();
            if(temp-executionTime>timeout)
            {
                executionTime=temp;
                for(Sensor sensor:sensors)
                {
                    jsonService.addData(sensor.getSensorName(),sensor.getData());   //aggiunta dei dati rilevati dai sensori nel file JSON
                }
            }
        }
    }
}