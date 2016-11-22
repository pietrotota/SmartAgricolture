package com.ttr.linklib;

import android.util.Log;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/*

 Scenario di Utilizzo:

 Link link = new Link("http://.....");


 // avvia il processo di lettura e invio dei dati

 link.run();

 */

public class Link implements Runnable {

    private String middlewareURL = null;
    private SensorData[] sensorsData=new SensorData[4];
    private long timeout=10, executionTime; //timer per il thread

    private boolean running = false; // condizione di esecuzione del Thread
    public void setSensorData(SensorData[]sensorsData) {this.sensorsData=sensorsData;}
    public Link(String middlewareURL) {
        this.middlewareURL = middlewareURL;
        this.running = true;
    }

    public void setTimeout(int timeoutSec) {
        this.timeout = timeoutSec * 1000;   //setto timeout in millisecondi
    }

    public void setRun(boolean run) {
        this.running = run;
    }

    public void stop() {
        this.running = false;
    }

    public void sendData(String dataToSend) {

        //Log.d("Debug << ", "Nuovo pacchetto in invio...");
        URL url;
        HttpURLConnection connection;
        try
        {
            url=new URL(this.middlewareURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setChunkedStreamingMode(0);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/json");
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream ());
            wr.writeBytes (dataToSend.toString());
            wr.flush ();
            wr.close ();
        }
        catch (Exception e)
        {

        }
    }

    //implementazione del metodo run del thread
    @Override
    public void run() {

        Log.d("Link << ", "Thread avviato");

        Log.d("Thread << ", "Time to execute = " + timeout/1000);

        executionTime = System.currentTimeMillis();
        while (running) {
            long temp = System.currentTimeMillis();
            if (temp - executionTime > timeout) {

                Log.d("Link << ", "Generazione pacchetto...");

                // crea l'oggetto json per la raccolta delle informazioni
                JSONService messageData = new JSONService();

                executionTime = temp;
                for (SensorData sensor: sensorsData) {
                    if(!sensor.toString().startsWith("null"))
                    messageData.addData(sensor.getSensorName(), sensor.getSensorData());   //aggiunta dei dati rilevati dai sensori nel file JSON
                }

                Log.d("Link << ", "Pacchetto creato");
                Log.d("Data << ", messageData.toString());

                // invia il json
                sendData(messageData.toString());

                Log.d("Thread << ", "Time to execute = " + timeout/1000);
            }
        }

        Log.d("Link << ", "Thread fermato");
    }
}