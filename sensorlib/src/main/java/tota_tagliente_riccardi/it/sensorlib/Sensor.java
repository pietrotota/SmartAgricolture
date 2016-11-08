package tota_tagliente_riccardi.it.sensorlib;


/*
    Interfaccia da usare per la creazione dei sensori
    ovviamente T sarà il tipo di dato letto dal sensore
 */

public interface Sensor<T> {

    T getData(); // Valore letto dal sensore

}
