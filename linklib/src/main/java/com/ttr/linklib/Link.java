package com.ttr.linklib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tota_tagliente_riccardi.it.sensorlib.SensorHandler;

/*

 Scenario di Utilizzo:

 Link link = new Link("http://.....");

 // registra i sensori da utilizzare

 link.registerSensor( 'tsensor', new TemperatureSensor );
 link.registerSensor( 'brightness', new BrightnessSensor );

 // avvia il processo di lettura e invio dei dati

 link.run();

 */

public class Link implements Runnable {

    private String middlewareURL = null;
    private long timeout, executionTime; //timer per il thread

    private List<SensorHandler> sensors; // lista dei sensori registrati

    private boolean run = false; // condizione di esecuzione del Thread

    public Link(String middlewareURL) {
        this.middlewareURL = middlewareURL;
        this.sensors = new ArrayList<SensorHandler>();
        // mettiamo di default 60 secondi
        this.setTimeout(60);
    }

    public void setTimeout(long timeoutSec) {
        this.timeout = timeoutSec * 1000;   //setto timeout in millisecondi
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public void start() {
        if (this.run) return;
        this.run = true;
        this.run();
    }

    public void stop() {
        this.run = false;
    }

    // Registrarli tutti insieme passando un array
    public void setSensors(SensorHandler[] sensorsArray) {
        for (SensorHandler sensor : sensorsArray)
            this.sensors.add(sensor);
    }

    // Registrandoli uno ad uno
    public void registerSensor(SensorHandler sensor) {
        this.sensors.add(sensor);
    }

    public void sendData(String dataToSend) {

        // TODO: dobbiamo stare attenti al formato che si aspetta il middleware

        HttpCall httpCall = new HttpCall(this.middlewareURL, HttpCall.POST);
        HttpRequest request = new HttpRequest()
        {
            @Override
            public void onResponse(String response) {
                super.onResponse(response);

                // Vediamo qui se ritornare qualcosa

            }
        };

        // Parametri
        HashMap<String,String> paramsPost = new HashMap<>();
        paramsPost.put("data", dataToSend);
        httpCall.setParams(paramsPost);

        // esegui la richiesta
        request.execute(httpCall);
    }

    //implementazione del metodo run del thread
    @Override
    public void run() {
        executionTime = System.currentTimeMillis();
        while (run) {
            long temp = System.currentTimeMillis();
            if (temp - executionTime > timeout) {

                // crea l'oggetto json per la raccolta delle informazioni
                JSONService messageData = new JSONService();

                executionTime = temp;
                for (SensorHandler sensor : this.sensors) {
                    messageData.addData(sensor.getSensorName(), sensor.getData());   //aggiunta dei dati rilevati dai sensori nel file JSON
                }

                // se vogliamo visualizzare il json dall'esterno
                this.onDataCreation(messageData);

                // invia il json
                sendData(messageData.toString());
            }
        }
    }

    // Se vogliamo accedervi dall'esterno, basta ridefinire questa in fase di creazione dell'oggetto
    // cosi sappiamo quando il json viene creato se no rischiamo di rileggere sempre lo stesso oggetto
    // perchè non teniamo conto di quando viene modificato
    public void onDataCreation(JSONService json) {

    }
}