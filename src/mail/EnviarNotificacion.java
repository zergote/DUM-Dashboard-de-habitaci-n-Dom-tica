/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import static com.oracle.jrockit.jfr.Transition.To;
import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.security.auth.Subject;
import javax.swing.JOptionPane;

/**
 *
 * @author xyges
 */
public class EnviarNotificacion {

    public static String Username = "christianyg@gmail.com";
    public static String PassWord = "19870332";
    String Mensage = "Se ha detectado un intruso a las 2:00PM";
    String To = "xyges@hotmail.com";
    String Subject = "Deteccion de intruso";

    public void EnviarMail(String toRe, String SubjectRe, String mensajeRe) {
        this.To = toRe;
        this.Subject = SubjectRe;
        this.Mensage = mensajeRe;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(Username, PassWord);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(To));
            message.setSubject(Subject);
            message.setText(Mensage);

            Transport.send(message);
            System.out.println("Notificacion enviada");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
