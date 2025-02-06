package Tema4_ServiciosEnRed.FTP;

import java.io.*;
import org.apache.commons.net.ftp.*;


public class ClienteFTP2 {
  public static void main(String[] args) {
	FTPClient clienteFTP = new FTPClient();
	String servidorFTP = "ftp.rediris.es";
	System.out.println("Nos conectamos a: " + servidorFTP);
	String usuario = "anonymous";
	String clave = "anonymous";
	try {
		clienteFTP.connect(servidorFTP);
        clienteFTP.enterLocalPassiveMode(); //modo pasivo

		boolean login = clienteFTP.login(usuario, clave);
		if (login)
			System.out.println("Login correcto...");
		else {
			System.out.println("Login Incorrecto...");
			clienteFTP.disconnect();
			System.exit(1);
		}
		System.out.println("Directorio actual: "
				         + clienteFTP.printWorkingDirectory());

		FTPFile[] files = clienteFTP.listFiles();
		System.out.println("Ficheros en el directorio actual:"
					+ files.length);
            //array para visualizar el tipo de fichero
		String tipos[] = {"Fichero", "Directorio","Enlace simb."};
		
		for (int i = 0; i < files.length; i++) {
			System.out.println("\t" + files[i].getName() + " => "
					+ tipos[files[i].getType()]);
		}
		boolean logout = clienteFTP.logout();
		if (logout) 
			System.out.println("Logout del servidor FTP...");
                else 
		        System.out.println("Error al hacer Logout...");
		//
		clienteFTP.disconnect();
		System.out.println("Desconectado...");
	} catch (IOException ioe) {
		ioe.printStackTrace();
	}		
  }
}// ..

