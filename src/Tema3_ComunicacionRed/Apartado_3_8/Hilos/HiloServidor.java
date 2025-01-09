package Tema3_ComunicacionRed.Apartado_3_8.Hilos;

import java.io.*;
import java.net.*;

public class HiloServidor extends Thread {
	BufferedReader fentrada;
	PrintWriter fsalida;
	Socket socket = null;

	public HiloServidor(Socket s) throws IOException {// CONSTRUCTOR
		socket = s;
		// se crean flujos de entrada y salida
		fsalida = new PrintWriter(socket.getOutputStream(), true);
		fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	public void run() {// tarea a realizar con el cliente
		String cadena = "";

		System.out.println("COMUNICO CON: " + socket.toString());

		while (!cadena.trim().equals("*")) {

			try {
				cadena = fentrada.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // obtener cadena
			fsalida.println(cadena.trim().toUpperCase());// enviar may�scula
		} // fin while

		System.out.println("FIN CON: " + socket.toString());

		fsalida.close();
		try {
			fentrada.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}// ..
