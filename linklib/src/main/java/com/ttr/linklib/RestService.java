package com.ttr.linklib;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class RestService {

    public static int Post(String urlstring, String data)
    {
        HttpURLConnection conn;
        OutputStream os;
        InputStream is;

        try {
            //constants
            URL url = new URL(urlstring);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout( 10000 );
            conn.setConnectTimeout( 15000  );
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(data.getBytes().length);

            //make some HTTP header nicety
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");

            //open
            conn.connect();

            //setup send
            os = new BufferedOutputStream(conn.getOutputStream());
            os.write(data.getBytes());
            //clean up
            os.flush();

            //do somehting with response
            is = conn.getInputStream();
            byte [] response=null;
            int responseCode=conn.getResponseCode();//qui richiedo il codice di risposta
            os.close();
            is.close();
            conn.disconnect();
            return responseCode;
        }
        catch (Exception e)
        {
            return 0; //qui gestiamo le eccezioni che ci vengono mandate, ritorna l'errore
        }                       // nella sezione di codice in cui richiamiamo questo metodo verificheremo la risposta del server
            }                   // o una eventuale eccezione che si è verificata a livello applicativo

}
