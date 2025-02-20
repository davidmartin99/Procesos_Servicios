package Tema5_ProgramacionSegura.FicherosPoliticas;

import java.io.*;
public class Ejemplo2 {
   public static void main(String[] args) {

	try {
		//escritura en fichero		
		BufferedWriter fichero = new BufferedWriter
		   (new FileWriter("D://CLASE//Fichero.txt"));
		fichero.write("Escritura de una linea en fichero.");
		fichero.newLine(); // escribe un salto de l�nea
		fichero.close();
		System.out.println("Final proceso de escritura...");

		//lectura en fichero
		BufferedReader fichero2 = new BufferedReader
              (new FileReader("D://CLASE//Fichero.txt"));
		String linea = fichero2.readLine();
		System.out.println("Contenido del fichero: ");
		System.out.println("\t" + linea);
		fichero2.close();
		System.out.println("Final proceso de lectura...");
			
	} catch (FileNotFoundException fn) {
		System.out.println("No se encuentra el fichero");
	} catch (IOException io) {
		System.out.println("Error de E/S ");
	} catch ( Exception e) {		
	    System.out.println("ERROR => " + e.toString());			
	}

   }// main
}//..Ejemplo2
