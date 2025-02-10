package Tema4_ServiciosEnRed.SMTP;

import java.io.IOException;
import org.apache.commons.net.smtp.*;

public class ClienteSMTP1_sendSimple {
	public static void main(String[] args) {
		SMTPClient clienteSMTP = new SMTPClient();
		try {
			int respuesta;
			// NOS CONECTAMOS AL PUERTO 25
			clienteSMTP.connect("localhost");
			System.out.print(clienteSMTP.getReplyString());
			respuesta = clienteSMTP.getReplyCode();

			if (!SMTPReply.isPositiveCompletion(respuesta)) {
				clienteSMTP.disconnect();
				System.err.println("CONEXION RECHAZADA.");
				System.exit(1);
			}

			// REALIZAR ACCIONES, por ejemplo enviar un correo
			clienteSMTP.login();
			String destinatario = "davidmartinpulgar9@gmail.com";
			String mensaje = "Hola, \n Mensaje de prueba. \n Saludos.";
			String remitente = "yo@localhost.com";

			if (clienteSMTP.sendSimpleMessage(remitente, destinatario, mensaje))
				System.out.println("Mensaje enviado a " + destinatario);
			else
				System.out.println("No se ha podido enviar el mensaje");

			clienteSMTP.logout();

			// NOS DESCONECTAMOS
			clienteSMTP.disconnect();

		} catch (IOException e) {
			System.err.println("NO SE PUEDE CONECTAR AL SERVIDOR.");
			e.printStackTrace();
			System.exit(2);
		}

	}// main
}// ..
