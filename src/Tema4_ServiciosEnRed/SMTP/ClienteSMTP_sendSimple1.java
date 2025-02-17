package Tema4_ServiciosEnRed.SMTP;

import java.io.IOException;
import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;

public class ClienteSMTP_sendSimple1 {
    public static void main(String[] args) {
        SMTPClient clienteSMTP = new SMTPClient();
        try {
            int respuesta;
            clienteSMTP.connect("localhost", 25); // Ensure correct port is used
            System.out.print(clienteSMTP.getReplyString());
            respuesta = clienteSMTP.getReplyCode();

            if (!SMTPReply.isPositiveCompletion(respuesta)) {
                clienteSMTP.disconnect();
                System.err.println("CONEXION RECHAZADA.");
                System.exit(1);
            }

            // Authenticate if required
            // clienteSMTP.login("username", "password");

            // Set the sender, recipient, and message body
            String remitente = "postmaster@localhost";
            String destinatario = "davidmartinpulgar9@gmail.com";
            String mensaje = "Hola, Mensaje de prueba. Saludos.";

            // Send the message
            if (clienteSMTP.sendSimpleMessage(remitente, destinatario, mensaje)) {
                System.out.println("Mensaje enviado a " + destinatario);
            } else {
                System.out.println("No se ha podido enviar el mensaje");
            }

            clienteSMTP.logout();
            clienteSMTP.disconnect();
        } catch (IOException e) {
            System.err.println("NO SE PUEDE CONECTAR AL SERVIDOR.");
            e.printStackTrace();
            System.exit(2);
        }
    }
}

