package Tema5_ProgramacionSegura.Criptografia;

import java.security.*;
public class Ejemplo4 {
	public static void main(String[] args) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-512");
			String texto = "Esto.";
			byte dataBytes[] = texto.getBytes();
			messageDigest.update(dataBytes);// SE INTRODUCE TEXTO A RESUMIR
			byte resumen[] = messageDigest.digest(); // SE CALCULA EL RESUMEN
			System.out.println("Mensaje original: " + texto);
			System.out.println("Número de bytes: " + messageDigest.getDigestLength());
			System.out.println("Algoritmo: " + messageDigest.getAlgorithm());
			
			System.out.println("Mensaje resumen: " + new String(resumen));
			System.out.println("Mensaje en Hexadecimal: "
					+ hexadecimal(resumen));
			Provider proveedor = messageDigest.getProvider();
			System.out.println("Proveedor: " + proveedor.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}// main
	// CONVIERTE UN ARRAY DE BYTES A HEXADECIMAL
	static String hexadecimal(byte[] resumen) {
		String hex = "";
		for (int i = 0; i < resumen.length; i++) {
			String h = Integer.toHexString(resumen[i] & 0xFF);
			if (h.length() == 1)
				hex += "0";
			hex += h;
		}
		return hex.toUpperCase();
	}// hexadecimal
}// ..


