package Tema3_ComunicacionRed.Apartado_3_5;

import java.io.*;
import java.net.*;

public class TCPejemplo1Cliente  {
  public static void main(String[] args) throws Exception {
	String host = "localhost";
	int puerto = 6000;//puerto remoto	
	
	System.out.println("PROGRAMA CLIENTE INICIADO....");
	Socket socketCliente = new Socket(host, puerto);

	// CREO FLUJO DE SALIDA AL SERVIDOR	
	DataOutputStream dataOutputStream = new
            DataOutputStream(socketCliente.getOutputStream());

	// ENVIO UN SALUDO AL SERVIDOR
	dataOutputStream.writeUTF("Saludos al SERVIDOR DESDE EL CLIENTE");

	// CREO FLUJO DE ENTRADA AL SERVIDOR	
	DataInputStream dataInputStream = new 
            DataInputStream(socketCliente.getInputStream());

	// EL SERVIDOR ME ENVIA UN MENSAJE
	System.out.println("Recibiendo del SERVIDOR: \n\t" + 
               dataInputStream.readUTF());

	// CERRAR STREAMS Y SOCKETS	
	dataInputStream.close();	
	dataOutputStream.close();	
	socketCliente.close();	
  }// main
}// 


