package Tema5_ProgramacionSegura.Criptografia;

import java.io.*;
import java.security.*;

public class Ejemplo5 {
	public static void main(String args[]) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("DATOS.DAT");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			String datos = "En un lugar de la Mancha, "
					+ "de cuyo nombre no quiero acordarme, no ha mucho tiempo "
					+ "que vivía un hidalgo de los de lanza en astillero, "
					+ "adarga antigua, rocín flaco y galgo corredor.";

			byte dataBytes[] = datos.getBytes();
			messageDigest.update(dataBytes);// TEXTO A RESUMIR
			byte resumen[] = messageDigest.digest(); // SE CALCULA EL RESUMEN

			objectOutputStream.writeObject(datos); //se escriben los datos
			objectOutputStream.writeObject(resumen);//se escribe el resumen

			objectOutputStream.close();
			fileOutputStream.close();

		} catch (IOException e) {
			e.printStackTrace();

		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		}
	}
}//..Ejemplo5