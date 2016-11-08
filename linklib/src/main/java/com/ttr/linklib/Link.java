package com.ttr.linklib;

public class Link {

    private String middlewareURL = null;

    public Link(String middlewareURL) {
        this.middlewareURL = middlewareURL;
    }

    public boolean sendData(String dataToSend) {
        int response = RestService.Post(middlewareURL, dataToSend);
       //qui controllo che il codice di risposta sia esattamente uguale a 200 (tutto ok) o diverso (se c'è un'eccezione sarà 0)
        return response == 200;
    }
}