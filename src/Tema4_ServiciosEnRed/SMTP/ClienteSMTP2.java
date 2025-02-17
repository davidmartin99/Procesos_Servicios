package Tema4_ServiciosEnRed.SMTP;

import java.io.IOException;
import java.io.Writer;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SMTPSClient;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

public class ClienteSMTP2 {

	public static void main(String[] args) {
		SMTPClient clienteSMTP = new SMTPClient();
		try {
			int respuesta;
			clienteSMTP.connect("localhost");
			System.out.print(clienteSMTP.getReplyString());
			respuesta = clienteSMTP.getReplyCode();

			if (!SMTPReply.isPositiveCompletion(respuesta)) {
				clienteSMTP.disconnect();
				System.err.println("SMTP server refused connection.");
				System.exit(1);
			}

			clienteSMTP.login();

			String remitente = "postmaster@localhost.es";
			String destino1 = "destino1@servidor1.com";
			String destino2 = "destino2@servidor2.com";
			String asunto = "Prueba de SMTPClient";
			String mensaje = "Hola. \nEnviando saludos.\nBye.";

			// se crea la cabecera
			SimpleSMTPHeader simpleSMTPHeader = new SimpleSMTPHeader(remitente, destino1, asunto);
			simpleSMTPHeader.addCC(destino2);

			// establecer el correo de origen
			clienteSMTP.setSender(remitente);

			// añadir correos destino
			clienteSMTP.addRecipient(destino1);// hay que añadir los dos
			clienteSMTP.addRecipient(destino2);

			// se envia DATA
			Writer writer = clienteSMTP.sendMessageData();
			if (writer == null) { // fallo
				System.out.println("FALLO AL ENVIAR DATA.");
				System.exit(1);
			}

			System.out.println(simpleSMTPHeader.toString());
			writer.write(simpleSMTPHeader.toString()); // primero escribo cabecera
			writer.write(mensaje);// luego el mensaje
			writer.close();

			if (!clienteSMTP.completePendingCommand()) { // fallo
				System.out.println("FALLO AL FINALIZAR LA TRANSACCIÓN.");
				System.exit(1);
			}

			clienteSMTP.logout();
			clienteSMTP.disconnect();

		} catch (IOException e) {
			System.err.println("NO SE PUEDE CONECTAR AL SERVIDOR.");
			e.printStackTrace();
			System.exit(2);
		}

		System.exit(0);
	}
}// ..