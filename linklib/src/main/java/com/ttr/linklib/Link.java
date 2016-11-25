package com.ttr.linklib;

import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;

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
    public void setSensorData(SensorData[]sensorsData)
    {
        this.sensorsData=sensorsData;
    }
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
            //apre la connessione, crea il pacchetto HTTP specificando il content type ecc ed invia i dati all'url specificato
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
                Message messageData = new Message(this.getDeviceName());
                executionTime = temp;
                for (SensorData sensor: sensorsData) {
                    if(sensor.isPresent())
                        //aggiunta dei dati rilevati dai sensori nel file JSON
                        messageData.addData(sensor.getSensorName(), sensor.getSensorData());
                }
                Gson gson = new Gson();
                String json = gson.toJson(messageData);
                Log.d("Link << ", "Pacchetto creato");
                Log.d("Data << ", json);

                // invia il json
                sendData(json);

                Log.d("Thread << ", "Time to execute = " + timeout/1000);
            }
        }

        Log.d("Link << ", "Thread fermato");
    }
    //serie di funzioni per ottenere il modello del dispositivo da inserire nel campo id del json da inviare al middleware
    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
}