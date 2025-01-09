package Tema3_ComunicacionRed.Apartado_3_5;

import java.io.IOException;
import java.net.*;
public class TCPClienteBasico {

	public static void main(String[] args) throws UnknownHostException, IOException {
		String host = "localhost";
		int puerto = 6000;//puerto remoto
			
		// ABRIR SOCKET 
		Socket socket = new Socket(host, puerto);//conecta

		InetAddress inetAddress= socket.getInetAddress ();
		System.out.println ("Puerto local: "+ socket.getLocalPort());
		System.out.println ("Puerto Remoto: "+ socket.getPort());
		System.out.println ("Nombre Host/IP: "+ socket.getInetAddress());
		System.out.println ("Host Remoto: "+ inetAddress.getHostName().toString());
		System.out.println ("IP Host Remoto: "+ inetAddress.getHostAddress().toString());
		
		socket.close();// Cierra el socket
	}
}



