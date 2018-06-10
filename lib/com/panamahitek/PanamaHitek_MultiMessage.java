/**
 * Este código ha sido construido por Antony García González y el Equipo
 * Creativo de Panama Hitek.
 *
 * Está protegido bajo la licencia LGPL v 2.1, cuya copia se puede encontrar en
 * el siguiente enlace: http://www.gnu.org/licenses/lgpl.txt
 *
 * Para su funcionamiento utiliza el código de la librería JSSC (anteriormente RXTX) que ha
 * permanecido intacto sin modificación alguna de parte de nuestro equipo
 * creativo. Agradecemos al creador de la librería JSSC, Alexey Sokolov por esta 
 * herramienta tan poderosa y eficaz que ha hecho posible el mejoramiento de nuestra 
 * librería.
 *
 * Esta librería es de código abierto y ha sido diseñada para que los usuarios,
 * desde principiantes hasta expertos puedan contar con las herramientas
 * apropiadas para el desarrollo de sus proyectos, de una forma sencilla y
 * agradable.
 *
 * Se espera que se en cualquier uso de este código se reconozca su procedencia.
 * Este algoritmo fue diseñado en la República de Panamá por Antony García
 * Gónzález, estudiante de la Universidad de Panamá en la carrera de
 * Licenciatura en Ingeniería Electromecánica, desde el año 2013 hasta el
 * presente. Su diseñador forma parte del Equipo Creativo de Panama Hitek, una
 * organización sin fines de lucro dedicada a la enseñanza del desarrollo de
 * software y hardware a través de su sitio web oficial http://panamahitek.com
 *
 * Solamente deseamos que se reconozca esta compilación de código como un
 * trabajo hecho por panameños para Panamá y el mundo.
 *
 * Si desea contactarnos escríbanos a creativeteam@panamahitek.com
 */
package com.panamahitek;

import java.util.ArrayList;
import java.util.List;
import jssc.SerialPortException;

/**
 * @author Antony García González, de Proyecto Panama Hitek. Visita http://panamahitek.com
 */
public class PanamaHitek_MultiMessage {

    //Variables 
    private static int mensajes = 0;
    private static int lecturas = 0;
    private static List<String> inputBuffer;
    private PanamaHitek_Arduino ino;

    /*Esta clase ha sido diseñada para hacer lectura de múltiples datos, por ejemplo
     de sensores conectados a Arduino sin tener que llevar a cabo complicadas secuencias lógicas
     para discernir entre una lectura y otra.
     */
    public PanamaHitek_MultiMessage(int messages, PanamaHitek_Arduino InputObject) {
        this.ino = InputObject;
        mensajes = messages;
        inputBuffer = new ArrayList<String>();

    }

    /**
     * Este método revisa constantemente si se ha terminado de leer la cantidad
     * de mensajes establecida en la creación del objeto de la clase
     * PanamaHitek_MultiMessage.
     *
     * @return TRUE si se ha terminado de leer datos, FALSE si aún no se
     * completa la lectura.
     */
    public boolean dataReceptionCompleted() throws ArduinoException, SerialPortException {
        String str = "";
        int i = 0;

        if (ino.getInputBytesAvailable() > 0) {
            while (i != mensajes) {
                if (ino.getInputBytesAvailable() > 0) {
                    int n = ino.receiveData();
                    if (n != 10 && n != 13) {
                        str += (char) n;
                    } else {
                        str += n;
                    }
                }
                if (str.contains("1310")) {
                    i++;
                    inputBuffer.add(str.replaceAll("1310", ""));
                    str = "";
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para obtener la información leída.
     *
     * @param index Indica el índice que se desea leer. Este está dado por el
     * orden en que se imprimen los datos en el Serial.println() de Arduino
     * @return un String con la información solicitada
     */
    public String getMessage(int index) {

        String Output = inputBuffer.get(index);
        return Output;
    }

    /**
     *
     * @return Este método devuelve una lista con los mensajes recibidos en
     * determinada lectura
     */
    public List<String> getMessageList() {
        return inputBuffer.subList(0, mensajes);
    }

    /**
     * Este método se encarga de limpiar el buffer y restablecer las variables
     * para prepararse para una nueva lectura
     */
    public void flushBuffer() {
        for (int i = 0; i < mensajes; i++) {
            inputBuffer.remove(0);
        }
    }
}