package Tema3_ComunicacionRed.Apartado_3_8.Hilos;

import java.io.*;
import java.net.*;

public class Cliente {
  public static void main(String[] args) throws IOException {
	String Host = "localhost";
	int Puerto = 6000;// puerto remoto
	Socket Cliente = new Socket(Host, Puerto);
		
	// CREO FLUJO DE SALIDA AL SERVIDOR	
	PrintWriter fsalida = new PrintWriter (Cliente.getOutputStream(), true);
	// CREO FLUJO DE ENTRADA AL SERVIDOR	
	BufferedReader fentrada =  new BufferedReader
	     (new InputStreamReader(Cliente.getInputStream()));
		 
	// FLUJO PARA ENTRADA ESTANDAR
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	String cadena, eco="";
		
	
	do{ 
		System.out.print("Introduce cadena: ");
		cadena = in.readLine();
		fsalida.println(cadena);
		eco=fentrada.readLine();			
		System.out.println("  =>ECO: "+eco);	
	} while(!cadena.trim().equals("*"));
		
	fsalida.close();
	fentrada.close();
	System.out.println("Fin del envío... ");
	in.close();
	Cliente.close();
	}//
}//



