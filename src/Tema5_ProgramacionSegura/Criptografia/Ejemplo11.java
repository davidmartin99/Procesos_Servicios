package Tema5_ProgramacionSegura.Criptografia;

import java.security.*;
import javax.crypto.*;

public class Ejemplo11 {
	public static void main(String args[]) {
		
		try {
			//SE CREA EL PAR DE CLAVES		
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(1024);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			PrivateKey privateKey = keyPair.getPrivate();
			PublicKey publicKey = keyPair.getPublic();
			
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);

			//CIFRAMOS TEXTO
			byte textoPlano[] = "Esto es un Texto Plano".getBytes();
			byte textoCifrado[] = cipher.doFinal(textoPlano);
			System.out.println("Encriptado: "+ new String(textoCifrado));
			
		    //DESCIFRAMOS TEXTO					
			cipher.init(Cipher.DECRYPT_MODE, privateKey);		
			byte textoDesencriptado[] = cipher.doFinal(textoCifrado);
			System.out.println("Desencriptado: "+ new String(textoDesencriptado));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


