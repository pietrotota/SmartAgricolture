package com.ttr.linklib;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class RestService {

    public static void Post(String urlstring, String data)
    {
        /*
        try {
            //constants
            URL url = new URL(urlstring);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
            OutputStream os = new BufferedOutputStream(conn.getOutputStream());
            os.write(data.getBytes());
            //clean up
            os.flush();

            //do somehting with response
            is = conn.getInputStream();
            String contentAsString = readIt(is,len);
        }
        catch (Exception e)
        {

        }
        finally {
            //clean up
            os.close();
            is.close();
            conn.disconnect();
        }
        */


    }

}
