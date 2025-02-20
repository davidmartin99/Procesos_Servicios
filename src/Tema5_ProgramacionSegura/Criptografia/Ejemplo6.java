package Tema5_ProgramacionSegura.Criptografia;

import java.io.*;
import java.security.MessageDigest;

public class Ejemplo6 {
	public static void main(String args[]) {
		try {
			FileInputStream fileInputStream = new FileInputStream("DATOS.DAT");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			Object object = objectInputStream.readObject();

			// Primera lectura, se obtiene el String
			String datos = (String) object;
			System.out.println("Datos: " + datos);

			// Segunda lectura, se obtiene el resumen
			object = objectInputStream.readObject();
			byte resumenOriginal[] = (byte[]) object;

			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(datos.getBytes());// TEXTO A RESUMIR
			byte resumenActual[] = messageDigest.digest(); // SE CALCULA EL RESUMEN

			if (MessageDigest.isEqual(resumenActual, resumenOriginal))
				System.out.println("DATOS VÁLIDOS");
			else
				System.out.println("DATOS NO VÁLIDOS");
			objectInputStream.close();
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}//..Ejemplo6

