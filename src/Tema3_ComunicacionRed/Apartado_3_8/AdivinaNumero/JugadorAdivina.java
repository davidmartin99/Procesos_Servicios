package Tema3_ComunicacionRed.Apartado_3_8.AdivinaNumero;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class JugadorAdivina {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		String Host = "localhost";
		int Puerto = 6001;// puerto remoto
		Socket Cliente = new Socket(Host, Puerto);

		ObjectOutputStream fsalida = new ObjectOutputStream(Cliente.getOutputStream());
		ObjectInputStream fentrada = new ObjectInputStream(Cliente.getInputStream());

		// FLUJO PARA ENTRADA ESTANDAR
		Scanner sc = new Scanner(System.in);
		String cadena= "";
		
		//OBTENER PRIMER OBJETO ENVIADO POR EL SERVIDOR
		Datos datos = (Datos) fentrada.readObject();	
		
		int identificador = datos.getIdentificador();
		System.out.println("Id jugador: " + identificador);		
		System.out.println(datos.getCadena());
		
		cadena = "";
		
		if (!datos.isJuega()) 		
			cadena="*";
						
		while(datos.isJuega() && !cadena.trim().equals("*")) {						
				System.out.print("Intento: "+(datos.getIntentos() +1)+" => Introduce n�mero: ");
				cadena = sc.nextLine();
				Datos d = new Datos();
				if(!validarCadena(cadena))
					continue;
				
				d.setCadena(cadena);
				
				fsalida.reset();
				fsalida.writeObject(d);	
				
				datos = (Datos) fentrada.readObject();
				System.out.println("\t"+datos.getCadena());
			
				if (datos.getIntentos()>=5){
					System.out.println("\t<<JUEGO FINALIZADO, NO HAY M�S INTENTOS>>");
					cadena="*";					
				}
				if (datos.isGana()) {
					System.out.println("<<HAS GANADO!! EL JUEGO HA TERMINADO>>");	
					cadena="*";						
	             } else 
				if (!(datos.isJuega()) ){
					System.out.println("<<EL JUEGO HA TERMINADO, HAN ADIVINADO EL NUMERO>>");
					cadena="*";							
				}			

		}//fin while

		fsalida.close();
		fentrada.close();
		System.out.println("Fin de proceso... ");
		sc.close();
		Cliente.close();
	}//main

	private static boolean validarCadena(String cadena) {
		//comprueba si la cadena es numerica
		boolean valor = false;
		try{
			Integer.parseInt(cadena);
			valor=true;
		} catch (NumberFormatException e){
		
		}
		
		return valor;
	}//validarCadena
	
}//JugadorAdivina
