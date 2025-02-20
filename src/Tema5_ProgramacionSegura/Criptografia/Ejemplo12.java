package Tema5_ProgramacionSegura.Criptografia;

import java.security.*;
import javax.crypto.*;
public class Ejemplo12 {
	public static void main(String args[]) {		
		try {
			//SE CREA EL PAR DE CLAVES pública y privada	
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(1024);
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			PrivateKey privateKey = keyPair.getPrivate();
			PublicKey publicKey = keyPair.getPublic();			
		
			//SE CREA LA CLAVE SECRETA AES 
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			SecretKey secretKey = keyGenerator.generateKey();
			
			//SE ENVUELVE LA CLAVE SECRETA CON LA RSA PÚBLICA					
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.WRAP_MODE, publicKey);
			byte claveEnvuelta[] = cipher.wrap(secretKey);				
			
			//CIFRAMOS TEXTO CON LA CLAVE SECRETA
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);			
			byte textoPlano[] = "Esto es un Texto Plano".getBytes();
			byte textoCifrado[] = cipher.doFinal(textoPlano);
			System.out.println("Encriptado: "+ new String(textoCifrado));						
			
		    //SE DESENVUELVE LA CLAVE SECRETA CON LA CLAVE RSA PRIVADA	
			Cipher cipher2 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher2.init(Cipher.UNWRAP_MODE, privateKey);
			Key key = cipher2.unwrap(claveEnvuelta, "AES", Cipher.SECRET_KEY);
			
			//DESCIFRAMOS TEXTO CON LA CLAVE DESENVUELTA
			cipher2 = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher2.init(Cipher.DECRYPT_MODE, key);		
			byte textoDesencriptado[] = cipher2.doFinal(textoCifrado);
			System.out.println("Desencriptado: "+ new String(textoDesencriptado));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//main
}//..Ejemplo12



