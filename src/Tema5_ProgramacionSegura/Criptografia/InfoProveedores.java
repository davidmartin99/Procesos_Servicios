package Tema5_ProgramacionSegura.Criptografia;

import java.security.*;

public class InfoProveedores {
	public static void main(String[] args) {
		System.out.println("------------------------------------");
		System.out.println("Proveedores instalados en el sistema");
		System.out.println("------------------------------------");
		
		Provider[] listaProv = Security.getProviders();
		
		for (int i = 0; i < listaProv.length; i++) {
			System.out.println("N�m. proveedor : " + (i + 1));
			System.out.println("Nombre         : " + listaProv[i].getName());
			System.out.println("Versi�n        : " + listaProv[i].getVersion());
			System.out.println("Informaci�n    :\n  " + listaProv[i].getInfo());
			System.out.println("------------------------------------");
		}
	}
}
