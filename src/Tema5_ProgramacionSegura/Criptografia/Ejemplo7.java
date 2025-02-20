package Tema5_ProgramacionSegura.Criptografia;

import java.security.*;

public class Ejemplo7 {
    public static void main(String[] args) {
    	try {
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
		//SE INICIALIZA EL GENERADOR DE CLAVES
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		keyPairGenerator.initialize(2048, secureRandom);
			
		//SE CREA EL PAR DE CLAVES PRIVADA Y P�BLICA
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
			
		//FIRMA CON CLAVE PRIVADA EL MENSAJE
        //AL OBJETO Signature SE LE SUMINISTRAN LOS DATOS A FIRMAR
		Signature signatureDSA = Signature.getInstance("SHA256withDSA");
		signatureDSA.initSign(privateKey);
		String mensaje = "Este mensaje va a ser firmado";
		signatureDSA.update(mensaje.getBytes());
			
		byte[] mensajeFirmado = signatureDSA.sign(); //MENSAJE FIRMADO 
		
		//EL RECEPTOR DEL MENSAJE
		//VERIFICA CON LA CLAVE PÚBLICA EL MENSAJE FIRMADO
        //AL OBJETO Signature SE LE SUMINISTRAN LOS DATOS A VERIFICAR
		Signature signatureDsaVerificada = Signature.getInstance("SHA256withDSA");
		signatureDsaVerificada.initVerify(publicKey);			
		signatureDsaVerificada.update(mensaje.getBytes());
		boolean check = signatureDsaVerificada.verify(mensajeFirmado);
		
		if(check)
			System.out.println("FIRMA VERIFICADA CON CLAVE PÚBLICA");
		else 
			System.out.println("FIRMA NO VERIFICADA");
		
    	} catch (NoSuchAlgorithmException e1) {			
		e1.printStackTrace();
	} catch (InvalidKeyException e) {			
		e.printStackTrace();
	} catch (SignatureException e) {			
		e.printStackTrace();
	} 
    }//main

}//..Ejemplo7
