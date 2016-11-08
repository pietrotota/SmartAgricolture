package com.ttr.linklib;

import org.json.JSONException;
import org.json.JSONObject;

/*
Classe per formulare le richieste JSON che saranno passate tramite HTTP come stringhe di dati
 */
public class JSONService {
    private JSONObject jsonObject;
    public JSONService() {
        jsonObject = new JSONObject();
    }
    //metodo per aggiungere i dati nel formato key:value
    public void addData(String key,Object value)
    {
        try {
            jsonObject.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //metodo per ottenere i dati fornendo la key
    public Object getData(String key)
    {
        try {
            return jsonObject.get(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    //metodo che ritorna in formato stringa il file JSON creato
    public String toString()
    {
        return jsonObject.toString();
    }
}
