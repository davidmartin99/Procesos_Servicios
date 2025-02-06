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

public class ClienteSMTP3_NOTLS {
	public static void main(String[] args) throws 
			NoSuchAlgorithmException, UnrecoverableKeyException,
			KeyStoreException, InvalidKeyException, InvalidKeySpecException {

		AuthenticatingSMTPClient authenticatingSMTPClient = new AuthenticatingSMTPClient("SSL");

		// datos del usuario y del servidor
		String server = "smtpserver";
		String username = "usuario1";
		String password = "password";
		int puerto = 25;
		String remitente = "usuario1@servidor1.com";

		try {
			int respuesta;
			// Creacion de la clave para establecer un canal seguro
			KeyManagerFactory keyManagerFactory = KeyManagerFactory
					.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			keyManagerFactory.init(null, null);
			KeyManager keyManager = keyManagerFactory.getKeyManagers()[0];

			// nos conectamos
			authenticatingSMTPClient.connect(server, puerto);
			System.out.println("1 - " + authenticatingSMTPClient.getReplyString());

			authenticatingSMTPClient.login(server);		

			// se establece la clave
			authenticatingSMTPClient.setKeyManager(keyManager);

			respuesta = authenticatingSMTPClient.getReplyCode();
			if (!SMTPReply.isPositiveCompletion(respuesta)) {
				authenticatingSMTPClient.disconnect();
				System.err.println("SMTP server refused connection.");
				System.exit(1);
			}

			authenticatingSMTPClient.ehlo(server);// necesario

			System.out.println("2 - " + authenticatingSMTPClient.getReplyString());

			// NO NECESITA NEGOCIACIÓN TLS
		

			if (authenticatingSMTPClient.auth(AuthenticatingSMTPClient.AUTH_METHOD.LOGIN, username, password)) {
				System.out.println("4 - " + authenticatingSMTPClient.getReplyString());

				String destino1 = "destino1@servidor1.com";
				String asunto = "Prueba de SMTPClient NO TLS";
				String mensaje = "Hola. \nEnviando saludos.\nSin negociacion TLS.\nChao.";

				// se crea la cabecera
				SimpleSMTPHeader simpleSMTPHeader = new SimpleSMTPHeader(remitente, destino1, asunto);
				authenticatingSMTPClient.setSender(remitente);
				authenticatingSMTPClient.addRecipient(destino1);
				System.out.println("5 - " + authenticatingSMTPClient.getReplyString());

				// se envia DATA
				Writer writer = authenticatingSMTPClient.sendMessageData();

				if (writer == null) { // fallo
					System.out.println("FALLO AL ENVIAR DATA.");
					System.exit(1);
				}
				writer.write(simpleSMTPHeader.toString()); // primero escribo
													// cabecera
				writer.write(mensaje);// luego mensaje
				writer.close();

				System.out.println("6 - " + authenticatingSMTPClient.getReplyString());

				boolean exito = authenticatingSMTPClient.completePendingCommand();

				System.out.println("7 - " + authenticatingSMTPClient.getReplyString());

				if (!exito) { // fallo
					System.out.println("FALLO AL FINALIZAR LA TRANSACCI�N.");
					System.exit(1);
				} else
					System.out.println("MENSAJE ENVIADO CON EXITO......");
			} else {
				System.out.println("USUARIO NO AUTENTICADO: ");
				System.out.println(authenticatingSMTPClient.getReplyString());
			}
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
		System.out.println("Fin de envío.");
		System.exit(0);
	}// main

}// ..ClienteSMTP3