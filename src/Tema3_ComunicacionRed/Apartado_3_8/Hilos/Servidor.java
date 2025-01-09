package Tema3_ComunicacionRed.Apartado_3_8.Hilos;

import java.io.*;
import java.net.*;

public class Servidor {
	public static void main(String args[]) throws IOException {
		ServerSocket servidor;
		servidor = new ServerSocket(6000);
		System.out.println("Servidor iniciado...");

		while (true) {
			Socket cliente = new Socket();
			cliente = servidor.accept();// esperando cliente
			HiloServidor hilo = new HiloServidor(cliente);
			hilo.start();
		}
	}
}// ..




