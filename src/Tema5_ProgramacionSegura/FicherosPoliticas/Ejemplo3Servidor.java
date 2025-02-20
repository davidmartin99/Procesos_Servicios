package Tema5_ProgramacionSegura.FicherosPoliticas;

import java.net.*;
public class Ejemplo3Servidor {
	public static void main(String[] arg) {
		int numeroPuerto = 6000;// Puerto
		ServerSocket servidor = null;
		
		//System.setProperty ("java.security.policy", 	"D:\\CAPIT5\\Politica4.policy");
        //System.setSecurityManager(new SecurityManager());
		
		try {
		  servidor = new ServerSocket(numeroPuerto);		
		  System.out.println("Esperando al cliente.....");
		  Socket clienteConectado = servidor.accept();	    
	      System.out.println("Cliente conectado.");		
		  clienteConectado.close();
		  System.out.println("Cliente desconectado.");
		  servidor.close();
		} catch (Exception e) {
				System.err.println("ERROR=> " + e.toString());
		}
	}// main
}//..Ejemplo3Servidor
