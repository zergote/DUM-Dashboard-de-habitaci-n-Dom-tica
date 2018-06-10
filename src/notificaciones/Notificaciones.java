package notificaciones;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import mail.EnviarNotificacion;
import stream.StreamData;
import stream.lockThread;

public class Notificaciones implements Runnable {

    StreamData datos;
    EnviarNotificacion servicioEmail;
    Thread hilo;
    lockThread lock;
    Date date;
    DateFormat horaYFecha;
    public Boolean senalFuego, senalHumo, senalIntruso, senalAcceso;
    public Boolean BDFuego, BDHumo, BDIntruso, BDAcceso;
    public String correoElectronico;

    public Notificaciones(StreamData datos, lockThread lock) {
        date = new Date();
        this.datos = datos;
        horaYFecha = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        this.lock = lock;
        servicioEmail = new EnviarNotificacion();
        hilo = new Thread(this);
        hilo.start();
        senalIntruso = senalFuego = senalIntruso = senalAcceso = false;
        BDFuego = BDHumo = BDIntruso = BDAcceso = true;
        correoElectronico = "xyges@hotmail.com";
    }

    @Override
    public void run() {
        while (lock.ejecucionStream) {
            if (lock.activacionDelIf) {
                try {
                    if (datos.fuego == 0 && senalFuego == false) {
                        senalFuego = true;
                        BDFuego = false;
                        servicioEmail.EnviarMail(correoElectronico, "Presencia de fuego en el area", "Ha ocurrido un incendio en el area a las " + horaYFecha.format(date));
                    } else {
                        senalFuego = false;
                    }
                } catch (Exception e) {
                }

                try {
                    if (datos.humo == 0) {
                        senalHumo = true;
                        BDHumo = false;
                        servicioEmail.EnviarMail(correoElectronico, "Presencia de Humo/Gas en el area", "Se ha producido una fuga de gas o una fuente de humo a las" + horaYFecha.format(date));
                    } else {
                        senalHumo = false;
                    }
                } catch (Exception e) {
                }

                try {
                    if (senalIntruso = false) {
                        senalIntruso = true;
                        BDHumo = false;
                        servicioEmail.EnviarMail(correoElectronico, "Se ha detectado un intruso en el area", "Una persona no autorizada esta en el area " + horaYFecha.format(date));
                    } else {
                        senalIntruso = false;
                    }
                } catch (Exception e) {
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Notificaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
