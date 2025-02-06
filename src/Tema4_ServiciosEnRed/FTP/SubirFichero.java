package Tema4_ServiciosEnRed.FTP;

import java.io.*;
import org.apache.commons.net.ftp.*;

public class SubirFichero {
	  public static void main(String[] args) {
		FTPClient clienteFTP = new FTPClient();

		String servidor = "localhost";
		String user = "usuario1";
		String pasw = "usuario1";

		try {
		   System.out.println("Conectandose a " + servidor);
		   clienteFTP.connect(servidor);
		   boolean login = clienteFTP.login(user, pasw);
			
		   clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);
		   String direc = "/NUEVODIR";
		   clienteFTP.enterLocalPassiveMode();

		   if (login) {				
			 System.out.println("Login correcto");
	                 
			 if (!clienteFTP.changeWorkingDirectory(direc)) {
			    String directorio = "NUEVODIR";
					
			    if (clienteFTP.makeDirectory(directorio)) {
				System.out.println("Directorio :  " + 
	                                  directorio + " creado ...");
	                  clienteFTP.changeWorkingDirectory(directorio);
			    } else {
				System.out.println("No se ha podido crear el Directorio");
				System.exit(0);
			    }
					
			  }
				
			  System.out.println("Directorio actual: " + clienteFTP.printWorkingDirectory());
					
			  String archivo ="D:\\Fernando\\Lorem_ipsum.pdf";				
			  BufferedInputStream in = new BufferedInputStream(new FileInputStream(archivo));
				
			  if (clienteFTP.storeFile("Lorem_ipsum.pdf", in))
				   System.out.println("Subido correctamente... ");
				else
				   System.out.println("No se ha podido subir el fichero... ");

			  in.close(); // Cerrar flujo
			  clienteFTP.logout();
			  clienteFTP.disconnect();
		   }

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	  }// main
	}// ..

