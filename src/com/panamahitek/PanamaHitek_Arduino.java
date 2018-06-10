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
import jssc.SerialPort;
import static jssc.SerialPort.PURGE_RXCLEAR;
import static jssc.SerialPort.PURGE_TXCLEAR;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class PanamaHitek_Arduino {

    private SerialPort serialPort;
    private SerialPortEventListener events = null;
    private String connection = "";
    private String portName = "";
    private static int BYTESIZE = 8;
    private static int STOPBITS = 1;
    private static int PARITY = 0;
    private static int TIMEOUT = 2000;
    private String message = "";
    private boolean messageAvailable = false;
    private boolean availableInUse = false;

    public PanamaHitek_Arduino() {
        PanamaHitek();
    }

    /**
     * Los créditos que se imprimen en cada ejecución de la libreria
     */
    private void PanamaHitek() {
//        System.out.println("PanamaHitek_Arduino Library, version 2.8.0");
//        System.out.println("==============================================");
//        System.out.println("Library created by Antony Garcia Gonzalez");
//        System.out.println("Student of Panama's Tecnological University and the creator of panamahitek.com ");
//        System.out.println("You can find all the information about this library at http://panamahitek.com");
//        System.out.println();

    }

    /**
     * Método para establecer la paridad en la conexión con el Puerto Serie. La
     * paridad por defecto es "Sin Paridad"
     *
     * @param input_Parity 0 = Sin Paridad, 1 = Paridad Impar, 2 = Paridad Par,
     * 3 = Paridad Marcada, 4 = Paridad Espaciada
     *
     * @since v2.6.0
     */
    public void setParity(int input_Parity) {

        if ((input_Parity >= 0) && (input_Parity <= 4)) {
            PARITY = input_Parity;
        } else {
            PARITY = 0;
            System.out.println("La paridad solamente puede ser: \n"
                    + "0 = Sin Paridad\n"
                    + "1 = Paridad Impar\n"
                    + "2 = Paridad Par\n"
                    + "3 = Paridad Marcada\n"
                    + "4 = Paridad Espaciada\n"
                    + "Se conserva la paridad por defecto (0- Sin Paridad)");
        }

    }

    /**
     * Método para establecer el ByteSize Se aceptan valores de entrada entre 5
     * y 8.
     *
     * @param Bytes Valor tipo entero para establecer el ByteSize
     * @since v2.6.0
     */
    public void setByteSize(int Bytes) {

        if ((Bytes >= 5) && (Bytes <= 8)) {
            BYTESIZE = Bytes;
        } else {
            BYTESIZE = 8;
            System.out.println("Sólo se aceptan valores entre 5 y 8 para el ByteSize "
                    + "\nSe conserva el valor por defecto (8 Bytes)");
        }
    }

    /**
     * Método para establecer el StopBit
     *
     * @param Bits Se establecen los StopBits 1 = 1 StopBit 2 = 2 StopBits 3 =
     * 1.5 StopBits
     * @since v2.6.0
     */
    public void setStopBits(int Bits) {

        if ((Bits >= 1) && (Bits <= 3)) {
            STOPBITS = Bits;
        } else {
            STOPBITS = 1;
            System.out.println("Sólo se aceptan valores entre 1 y 3 para StopBit (3 es para 1.5 StopBits)."
                    + "\nSe conserva el valor por defecto (1 Bit)");
        }
    }

    /**
     * Método para establecer el TimeOut
     *
     * @param time Valor tipo entero, dado en milisegundos
     * @since v2.6.0
     */
    public void setTimeOut(int time) {
        TIMEOUT = time;
    }

    /**
     * Método para iniciar la conexión con Arduino, solamente para transmisión
     * de información de la computadora al Arduino por medio del Puerto Serie
     *
     * @since v1.0.0
     *
     * @param PORT_NAME Nombre del puerto en el que se encuentra conectado el
     * Arduino
     * @param DATA_RATE Velocidad de transmisión de datos en baudios por segundo
     * @throws ArduinoException Se pueden dar varias excepciones: - Si se
     * intenta iniciar la comunicación con Arduino más de una vez - Si no se
     * encuentra ningún dispositivo conectado al Puerto Serie - Si no se
     * encuentra un Arduino conectado en el puerto establecido - Si el puerto
     * seleccionado está siendo usado por alguna otra aplicación
     *
     */
    public void arduinoTX(String PORT_NAME, int DATA_RATE) throws ArduinoException {
        if (connection.equals("")) {
            connection = "TX";
        } else {
            throw new ArduinoException(portName, "arduinoTX()", ArduinoException.TYPE_RXTX_EXCEPTION);
        }

        try {
            this.portName = PORT_NAME;
            serialPort = new SerialPort(PORT_NAME);
            serialPort.openPort();
            serialPort.setParams(DATA_RATE, BYTESIZE, STOPBITS, PARITY);
            flushSerialPort();
        } catch (SerialPortException ex) {
            throw new ArduinoException(portName, "arduinoTX()", ArduinoException.TYPE_PORT_NOT_OPENED);
        }

    }

    /**
     * Método para iniciar la conexión con Arduino,tanto para enviar como para
     * recibir información desde el Arduino por medio del Puerto Serie
     *
     * @since v1.0.0
     *
     * @param PORT_NAME Nombre del puerto en el que se encuentra conectado el
     * Arduino
     * @param DATA_RATE Velocidad de transmisión de datos en baudios por segundo
     * @param events Instancia de la clase SerialPortEventListener para detectar
     * cuando sea que se recibe información en el Puerto Serie
     * @throws ArduinoException Se pueden dar varias excepciones: - Si se
     * intenta iniciar la comunicación con Arduino más de una vez - Si no se
     * encuentra ningún dispositivo conectado al Puerto Serie - Si no se
     * encuentra un Arduino conectado en el puerto establecido - Si el puerto
     * seleccionado está siendo usado por alguna otra aplicación
     *
     *
     *
     */
    public void arduinoRXTX(String PORT_NAME, int DATA_RATE, SerialPortEventListener events) throws ArduinoException {
        if (connection.equals("")) {
            connection = "RXTX";
        } else {
            throw new ArduinoException(portName, "arduinoRXTX()", ArduinoException.TYPE_RXTX_EXCEPTION);
        }
        try {
            this.portName = PORT_NAME;
            serialPort = new SerialPort(PORT_NAME);
            serialPort.openPort();
            serialPort.setParams(DATA_RATE, BYTESIZE, STOPBITS, PARITY);
            serialPort.addEventListener(events);
            flushSerialPort();
        } catch (SerialPortException ex) {
            throw new ArduinoException(portName, "arduinoRXTX()", ArduinoException.TYPE_PORT_NOT_OPENED);
        }

    }

    /**
     * Método para iniciar la conexión con Arduino, solamente para recepción de
     * información de en la computadora desde el Arduino por medio del Puerto
     * Serie
     *
     * @since v1.0.0
     *
     * @param PORT_NAME Nombre del puerto en el que se encuentra conectado el
     * Arduino
     * @param DATA_RATE Velocidad de transmisión de datos en baudios por segundo
     * @param events Instancia de la clase SerialPortEventListener para detectar
     * cuando sea que se recibe información en el Puerto Serie
     * @throws ArduinoException, SerialPortException Se pueden dar varias
     * excepciones: - Si se intenta iniciar la comunicación con Arduino más de
     * una vez - Si no se encuentra ningún dispositivo conectado al Puerto Serie
     * - Si no se encuentra un Arduino conectado en el puerto establecido - Si
     * el puerto seleccionado está siendo usado por alguna otra aplicación
     */
    public void arduinoRX(String PORT_NAME, int DATA_RATE, SerialPortEventListener events) throws ArduinoException, SerialPortException {

        if (connection.equals("")) {
            connection = "RX";
        } else {
            throw new ArduinoException(portName, "arduinoRX()", ArduinoException.TYPE_RXTX_EXCEPTION);
        }
        try {
            this.portName = PORT_NAME;
            serialPort = new SerialPort(PORT_NAME);
            serialPort.openPort();
            serialPort.setParams(DATA_RATE, BYTESIZE, STOPBITS, PARITY);
            serialPort.addEventListener(events);
            flushSerialPort();
        } catch (SerialPortException ex) {
            throw new ArduinoException(portName, "arduinoRX()", ArduinoException.TYPE_PORT_NOT_OPENED);
        }
    }

    /**
     * Método para el envío de información desde Java hacia Arduino.
     *
     * @since v1.0.0
     *
     * @param data Una cadena de caracteres con el String que se desea enviar.
     * @throws ArduinoException, SerialPortException Se puede producir un error
     * si no se ha iniciado la conexión con Aruino o si se intenta enviar datos
     * cuando se ha iniciado la conexión por medio de ArduinoRX().
     *
     */
    public void sendData(String data) throws ArduinoException, SerialPortException {
        if (connection.equals("RX")) {
            throw new ArduinoException(portName, "sendData()", ArduinoException.TYPE_WRONG_SEND_DATA_CONNECTION);
        } else if (connection.equals("")) {
            throw new ArduinoException(portName, "sendData()", ArduinoException.TYPE_SEND_DATA);
        } else if (connection.equals("TX") || connection.equals("RXTX")) {
            serialPort.writeBytes(data.getBytes());
        }
    }

    /**
     * Se envía información desde Java hacia Arduino en forma de Bytes, a
     * diferencia del método sendData(String Data) donde la información se envía
     * como una cadena de caracteres (String).
     *
     * @param data El Byte que se desea enviar (Un valor entre 0 y 255)
     * @throws ArduinoException, SerialPortException Se puede producir una
     * excepción si no se ha iniciado la conexión con Arduino o si dicha
     * conexión se ha iniciado a través del método ArduinoRX().
     *
     *
     * @since v2.6.0
     */
    public void sendByte(int data) throws ArduinoException, SerialPortException {

        if (connection.equals("RX")) {
            throw new ArduinoException(portName, "sendByte()", ArduinoException.TYPE_SEND_DATA);
        } else if (connection.equals("")) {
            throw new ArduinoException(portName, "sendByte()", ArduinoException.TYPE_NO_ARDUINO_CONNECTION);
        } else if (connection.equals("TX") || connection.equals("RXTX")) {
            serialPort.writeByte((byte) data);
        }

    }

    /**
     * Método para recibir datos desde Arduino. Se reciben los datos tal como
     * son enviados desde Arduino, caracter por caracter.
     *
     * @return Un entero entre 0 y 255 con el caracter que se haya recibido
     * @throws ArduinoException, SerialPortException Se puede producir una
     * excepción si no se ha iniciado la conexión con Arduino o si la misma ha
     * sido iniciada utilizando el método ArduinoTX().
     *
     *
     * @since v1.0.0
     */
    public int receiveData() throws ArduinoException, SerialPortException {

        if (connection.equals("TX")) {
            throw new ArduinoException(portName, "receiveData()", ArduinoException.TYPE_RECEIVE_DATA);
        } else if (connection.equals("")) {
            throw new ArduinoException(portName, "receiveData()", ArduinoException.TYPE_NO_ARDUINO_CONNECTION);
        } else {
            return (int) serialPort.readByte();
        }
    }

    /**
     * Devuelve un valor true cuando se produce un salto de línea en el envío de
     * información desde Arduino hacia la computadora. Se debe tomar en cuenta
     * que la separación entre un mensaje y otro depende del uso de
     * Serial.println() en Arduino, ya que este método busca los saltos de línea
     * en los mensajes para luego llevar a cabo la impresión. Si se utiliza
     * Serial.print() la librería no reconocerá el mensaje que se esté enviando.
     *
     * @since v2.6.0
     *
     * @return Una variable tipo boolean que será TRUE cuando se reciba un salto
     * de línea en la recepción de datos desde Arduino.
     */
    public boolean isMessageAvailable() throws SerialPortException, ArduinoException {
        availableInUse = true;
        serialRead();
        return messageAvailable;
    }

    /**
     * Método para hacer la lectura de datos enviados desde Arduino con
     * Serial.println(). Se busca cuando se produce un salto de línea y se
     * imprime la información.
     */
    private void serialRead() throws ArduinoException, SerialPortException {
        int entrada;

        if (!messageAvailable) {
            entrada = receiveData();
            if (entrada > 0) {
                if (entrada != 13) {
                    if (entrada != 10) {
                        message = message + (char) entrada;
                    } else {
                        messageAvailable = true;
                    }
                }
            }

        }

    }

    /**
     * Este método se utiliza dentro de una estructura condicional. Cuando el
     * método isMessageAvailable() devuelve un valor TRUE, este método imprime
     * el mensaje que haya sido almacenado en el buffer.
     *
     * @since v2.0.0
     *
     * @return Un String con el mensaje que se haya recibido
     */
    public String printMessage() throws SerialPortException, ArduinoException {
        String salida = "No hay datos disponibles";
        if (!availableInUse) {
            serialRead();
        }
        if (isMessageAvailable()) {
            salida = message;
            message = "";
            messageAvailable = false;
        }
        return salida;
    }

    /**
     *
     *
     * Devuelve la cantidad de puertos activos. Esto dependerá de cuantos
     * dispositivos dispositivos estén conectado a a la computadora a través del
     * Puerto Serie
     *
     * @since v2.6.0
     *
     * @return Una número entero positivo mayor o igual a cero con la cantidad
     * de puertos que se encontraron disponibles.
     */
    public int getPortsAvailable() {
        return SerialPortList.getPortNames().length;
    }

    /**
     * Nombra los puertos disponibles en el Puerto Serie
     *
     * @return Una lista con los puertos disponibles
     *
     * @since v2.6.0
     */
    public List<String> getSerialPorts() {
        List<String> ports = new ArrayList<String>();

        String[] portNames = SerialPortList.getPortNames();
        for (int i = 0; i < portNames.length; i++) {
            ports.add(portNames[i]);
        }

        return ports;
    }

    /**
     * Método para finalizar la conexión con Arduino en el puerto serie.
     *
     * @throws ArduinoException Se puede producir una excepción cuando se
     * intenta cerrar la conexión con Arduino sin que la misma se haya iniciado
     * antes
     *
     * @since v2.0.0
     */
    public void killArduinoConnection() throws ArduinoException {

        if (connection.equals("")) {
            throw new ArduinoException(this.portName, "killArduinoConnection()", ArduinoException.TYPE_KILL_ARDUINO_CONNECTION);
        } else {
            try {
                serialPort.closePort();
            } catch (SerialPortException ex) {
                throw new ArduinoException(portName, "killArduinoConnection()", ArduinoException.TYPE_CLOSE_PORT);
            }
            connection = "";
            System.out.println("Conexión con Arduino Finalizada");
        }
    }

    /**
     * Se limpian los buffer de entrada y salida del puerto serie
     *
     * @throws SerialPortException
     *
     * @since 2.8.0
     */
    public void flushSerialPort() throws SerialPortException {
        serialPort.purgePort(PURGE_RXCLEAR);
        serialPort.purgePort(PURGE_TXCLEAR);
    }

    /**
     *
     * @returnCantidad de bytes disponibles para ser leídos en el buffer de
     * entrada del puerto serie
     * @throws SerialPortException
     * @since 2.8.0
     */
    public int getInputBytesAvailable() throws SerialPortException {
        return serialPort.getInputBufferBytesCount();
    }

    /**
     *
     * @return El event listener que se agrega al serialPort cuando se
     * implementan los métodos arduinoRX() y ardunoRXTX()
     *
     * @since 2.8.0
     */
    public SerialPortEventListener getEventListener() throws ArduinoException {
        if (events != null) {
            return events;
        } else {
            throw new ArduinoException(portName, "getEventListener()", ArduinoException.TYPE_NO_EVENT_LISTENER);
        }

    }
}
