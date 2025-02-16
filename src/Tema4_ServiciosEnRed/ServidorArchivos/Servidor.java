package Tema4_ServiciosEnRed.ServidorArchivos;

import java.io.*;
import java.net.*;
import javax.swing.*;

//SERVIDOR
public class Servidor {
	static Integer PUERTO = 44441;
	static public EstructuraFicheros ESTRUCTURAFICHEROS;
	static ServerSocket SERVERSOCKET;

	// MAIN
	public static void main(String[] args) throws IOException {
		String directorio = "";
		JFileChooser jFileChooser = new JFileChooser();
		jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jFileChooser.setDialogTitle("SELECCIONA EL DIRECTORIO DONDE ESTÁN LOS FICHEROS");
		int returnVal = jFileChooser.showDialog(jFileChooser, "Seleccionar");

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = jFileChooser.getSelectedFile();
			directorio = file.getAbsolutePath();
			System.out.println(directorio);
		}

		if (directorio.equals("")) {
			System.out.println("Debe seleccionar un directorio .....");
			System.exit(1);
		}
		SERVERSOCKET = new ServerSocket(PUERTO);
		System.out.println("Servidor Iniciado en Puerto: " + PUERTO);

		while (true) {
			try {
				Socket socket = SERVERSOCKET.accept();
				System.out.println("Bienvenido al cliente");
				ESTRUCTURAFICHEROS = new EstructuraFicheros(directorio);
				HiloServidor hiloServidor = new HiloServidor(socket, ESTRUCTURAFICHEROS);
				hiloServidor.start(); // Ejecutamos el hilo
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}
		} // while
	} // main

} // ..fin SERVIDOR
