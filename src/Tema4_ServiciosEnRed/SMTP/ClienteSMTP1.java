package Tema4_ServiciosEnRed.SMTP;

import java.io.IOException;
import org.apache.commons.net.smtp.*;

public class ClienteSMTP1 {
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

			// NOS DESCONECTAMOS
			clienteSMTP.disconnect();

		} catch (IOException e) {
			System.err.println("NO SE PUEDE CONECTAR AL SERVIDOR.");
			e.printStackTrace();
			System.exit(2);
		}

	}// main
}// ..
