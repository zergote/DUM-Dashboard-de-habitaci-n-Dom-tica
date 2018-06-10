package stream;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import com.panamahitek.PanamaHitek_MultiMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortException;

public class StreamData implements Runnable {

    PanamaHitek_Arduino teensy;
    PanamaHitek_MultiMessage multiTeensy;
    Boolean ejecucion;
    lockThread lock;
    public int temperatura;
    public int humedad;
    public int fuego;
    public int intruso;
    public int usuario;
    public int humo;
    public int luces;
    public int radioFre;
    public int estadoActualTemp;
    public int estadoActualHume;
    int array[];
    Thread hilo;

    public StreamData(PanamaHitek_Arduino teensy, lockThread lock) {
        this.teensy = teensy;
        this.lock = lock;
        ejecucion = true;
        //String de datos
        temperatura = 29;
        humedad = 65;
        fuego = 1; // 0 Precencia de fuego
        intruso = 0;
        usuario = 0;
        humo = 1;
        luces = 0;
        radioFre = 0;
        estadoActualHume = 0;
        estadoActualTemp = 0;
        //Activacion de hilo
        hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        while (lock.ejecucionStream) {
            if (lock.activacionDelIf) {
                try {
                    if (teensy.isMessageAvailable()) {
                        String recibido = teensy.printMessage();
                        for (int i = 0; i <= recibido.length(); i++) {
                            if (i == 0) {
                                luces = recibido.charAt(i) - 123;
                            }
                            if (i == 1) {
                                radioFre = recibido.charAt(i) - 123;
                            }
                            if (i == 2) {
                                fuego = recibido.charAt(i) - 123;
                            }
                            if (i == 3) {
                                humo = recibido.charAt(i) - 123;
                            }
                            if (i == 4) {
                                intruso = recibido.charAt(i) - 123;
                            }
                            if (i == 5) {
                                temperatura = recibido.charAt(i);
                            }
                            if (i == 6) {
                                humedad = recibido.charAt(i);
                            }
                            if (i == 7) {
                                estadoActualTemp = recibido.charAt(i);
                            }
                            if (i == 8) {
                                estadoActualHume = recibido.charAt(i);
                            }
                        }
                    }
                } catch (ArduinoException ex) {
                    Logger.getLogger(StreamData.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SerialPortException ex) {
                    Logger.getLogger(StreamData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
