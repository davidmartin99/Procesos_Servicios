package Tema4_ServiciosEnRed.FTP;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class ClienteFTP1 {
	public static void main(String[] args) throws SocketException, IOException{
		FTPClient clienteFTP = new FTPClient();
		String servidorFTP = "ftp.rediris.es"; // servidor FTP
		System.out.println("Nos conectamos a: " + servidorFTP);
		
		clienteFTP.connect(servidorFTP);
		clienteFTP.enterLocalPassiveMode();
		// respuesta del servidor FTP
		System.out.print(clienteFTP.getReplyString());
		// codigo de respuesta
		int respuesta = clienteFTP.getReplyCode();

		System.out.println("Respuesta: " + respuesta);

		// comprobacion del codigo de respuesta
		if (!FTPReply.isPositiveCompletion(respuesta)) {
			clienteFTP.disconnect();
			System.out.println("Conexion rechazada: " + respuesta);
			System.exit(0);
		}
		// desconexion del servidor FTP
		clienteFTP.disconnect();
		System.out.println("Conexion finalizada.");
	}
}// ..


