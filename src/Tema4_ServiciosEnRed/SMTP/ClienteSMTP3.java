package Tema4_ServiciosEnRed.SMTP;

import java.io.IOException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import org.apache.commons.net.smtp.*;

public class ClienteSMTP3 {
	public static void main(String[] args) throws 
			NoSuchAlgorithmException, UnrecoverableKeyException,
			KeyStoreException, InvalidKeyException, InvalidKeySpecException {

		// se crea cliente SMTP seguro
		AuthenticatingSMTPClient authenticatingSMTPClient = new AuthenticatingSMTPClient();

		// datos del usuario y del servidor
		String server = "localhost"; // Mercury corre en tu máquina
		int puerto = 25; // Puerto SMTP predeterminado
		String username = "postmaster"; // Usuario creado en Mercury
		String password = ""; // Contraseña del usuario en Mercury
		String remitente = "postmaster@localhost"; // Debe coincidir con el usuario Mercury
		String destino1 = "davidmartinpulgar9@gmail.com"; // Correo de destino externo


		try {
			int respuesta;

			// Creación de la clave para establecer un canal seguro
			KeyManagerFactory keyManagerFactory = KeyManagerFactory
					.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			keyManagerFactory.init(null, null);
			KeyManager keyManager = keyManagerFactory.getKeyManagers()[0];

			// nos conectamos al servidor SMTP
			authenticatingSMTPClient.connect(server, puerto);
			System.out.println("1 - " + authenticatingSMTPClient.getReplyString());
			// se establece la clave para la comunicación segura
			authenticatingSMTPClient.setKeyManager(keyManager);

			respuesta = authenticatingSMTPClient.getReplyCode();
			if (!SMTPReply.isPositiveCompletion(respuesta)) {
				authenticatingSMTPClient.disconnect();
				System.err.println("CONEXIÓN RECHAZADA.");
				System.exit(1);
			}

			// se envía el commando EHLO
			authenticatingSMTPClient.ehlo(server);// necesario
			System.out.println("2 - " + authenticatingSMTPClient.getReplyString());

			// NECESITA NEGOCIACIÓN TLS - MODO NO IMPLICITO
			// Se ejecuta el comando STARTTLS y se comprueba si es true
			if (authenticatingSMTPClient.execTLS()) {
				System.out.println("3 - " + authenticatingSMTPClient.getReplyString());

				// se realiza la autenticación con el servidor
				if (authenticatingSMTPClient.auth(AuthenticatingSMTPClient.AUTH_METHOD.LOGIN, username, password)) {
					System.out.println("4 - " + authenticatingSMTPClient.getReplyString());
					destino1 = "davidmartinpulgar9@gmail.com";
					String asunto = "Prueba de SMTPClient con GMAIL";
					String mensaje = "Hola. \nEnviando saludos.\nUsando  GMAIL.\nChao.";
					// se crea la cabecera
					SimpleSMTPHeader simpleSMTPHeader = new SimpleSMTPHeader(remitente, destino1, asunto);

					// el nombre de usuario y el email de origen coinciden
					authenticatingSMTPClient.setSender(remitente);
					authenticatingSMTPClient.addRecipient(destino1);
					System.out.println("5 - " + authenticatingSMTPClient.getReplyString());

					// se envia DATA
					Writer writer = authenticatingSMTPClient.sendMessageData();
					if (writer == null) { // fallo
						System.out.println("FALLO AL ENVIAR DATA.");
						System.exit(1);
					}

					writer.write(simpleSMTPHeader.toString()); // cabecera
					writer.write(mensaje);// luego mensaje
					writer.close();
					System.out.println("6 - " + authenticatingSMTPClient.getReplyString());

					boolean exito = authenticatingSMTPClient.completePendingCommand();
					System.out.println("7 - " + authenticatingSMTPClient.getReplyString());

					if (!exito) { // fallo
						System.out.println("FALLO AL FINALIZAR TRANSACCIÓN.");
						System.exit(1);
					} else
						System.out.println("MENSAJE ENVIADO CON EXITO......");

				} else
					System.out.println("USUARIO NO AUTENTICADO.");
			} else
				System.out.println("FALLO AL EJECUTAR  STARTTLS.");

		} catch (IOException e) {
			System.err.println("Could not connect to server.");
			e.printStackTrace();
			System.exit(1);
		}
		try {
			authenticatingSMTPClient.disconnect();
		} catch (IOException f) {
			f.printStackTrace();
		}

		System.out.println("Fin de env�o.");
		System.exit(0);
	}// main
}// ..ClienteSMTP3
