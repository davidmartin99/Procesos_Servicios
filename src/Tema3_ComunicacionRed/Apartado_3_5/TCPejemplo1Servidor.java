package Tema3_ComunicacionRed.Apartado_3_5;

import java.io.*;
import java.net.*;

public class TCPejemplo1Servidor {
  public static void main(String[] arg) throws IOException {
	int numeroPuerto = 6000;// Puerto
	ServerSocket serverSocket = new ServerSocket(numeroPuerto);
	Socket socket = null;
	System.out.println("Esperando al cliente.....");
	socket = serverSocket.accept();

	// CREO FLUJO DE ENTRADA DEL CLIENTE
	InputStream inputStream = null;
	inputStream = socket.getInputStream();
	DataInputStream dataInputStream = new DataInputStream(inputStream);

	// EL CLIENTE ME ENVIA UN MENSAJE
	System.out.println("Recibiendo del CLIENTE: \n\t" + 
                      dataInputStream.readUTF());

	// CREO FLUJO DE SALIDA AL CLIENTE
	OutputStream outputStream = null;
	outputStream = socket.getOutputStream();
	DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

	// ENVIO UN SALUDO AL CLIENTE
	dataOutputStream.writeUTF("Saludos al cliente del servidor");

	// CERRAR STREAMS Y SOCKETS
	inputStream.close();
	dataInputStream.close();
	outputStream.close();
	dataOutputStream.close();
	socket.close();
	serverSocket.close();
  }// main
}// fin



