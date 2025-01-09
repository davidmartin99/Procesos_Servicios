package Tema3_ComunicacionRed.Apartado_3_8.AdivinaNumero;

import java.io.*;
import java.net.*;

public class HiloServidorAdivina extends Thread {
	ObjectInputStream fentrada;
	ObjectOutputStream fsalida;

	Socket socket = null;

	ObjetoCompartido objeto;
	int identificador;
	int intentos = 0;

	public HiloServidorAdivina(Socket s, ObjetoCompartido objeto, int id) {
		this.socket = s;
		this.objeto = objeto;
		this.identificador = id;
		try {
			 fsalida = new ObjectOutputStream(socket.getOutputStream());
			 fentrada = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.out.println("ERROR DE E/S en HiloServidor");
			e.printStackTrace();
		}
	}// ..

	// ----------------------------------------------------------------
	public void run() {
				
		System.out.println("=>Cliente conectado: " + identificador);

		//PREPARAR PRIMER ENVIO AL CLIENTE	
		Datos datos = new Datos("Adivina un N�MERO ENTRE 1 Y 25", intentos, identificador);
		
		if (objeto.seAcabo()) {
			datos.setCadena("LO SENTIMOS, EL JUEGO HA TERMINADO, HAN ADIVINADO EL N�");
			datos.setJuega(false); // ya no TIENE QUE JUGAR
		}

		try {
			fsalida.reset();
			fsalida.writeObject(datos);
		} catch (IOException e1) {
			System.out.println("Error en el Hilo al realizar " + 
		                  "el primer envio al jugador: " + identificador);
			return;
		}

		while (!objeto.seAcabo() || !(datos.getIntentos() == 5)) { // mientras no se haya acabado el juego
			int numecli = 0; 
			try {
				Datos d = (Datos) fentrada.readObject(); // leer numero del cliente
				numecli = Integer.parseInt(d.getCadena());
			} catch (IOException e) {
				System.out.println("Error en el Hilo al leer del jugador: " + identificador);
				break;
			} catch (NumberFormatException n) {
				System.out.println("El jugador:" + identificador + " se ha desconectado");
				break;
			} catch (ClassNotFoundException e) {				
				e.printStackTrace();
				break;
			}
			// jugar el numero
			String cad = objeto.nuevaJugada(identificador, numecli);
			intentos++;
			datos = new Datos(cad, intentos, identificador);

			if (objeto.seAcabo()) {
				datos.setJuega(false); // ya no tiene que seguir jugando
				if (identificador == objeto.getGanador())
					datos.setGana(true);				
			}

			try {
				fsalida.reset();
				fsalida.writeObject(datos);				
			} catch (IOException n1) {
				System.out.println(
						"Error escribiendo en flujo de salida del jugador: " + identificador + " * " + n1.getMessage());
				break;
			} catch (NullPointerException n) {// se produce cuando el cliente				
				System.out.println("El jugador  " + identificador + " ha desconectado ");
				break;
			}

		} // fin while

		if (objeto.seAcabo()) {
			System.out.println("EL JUEGO SE HA ACABADO.....");
			System.out.println("\t==>Desconecta: " + identificador);
		}
		try {
			fsalida.close();
			fentrada.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("Error en Hilo al cerrar cliente: " + identificador);
			e.printStackTrace();
		}

	}// run

}// ..