package Tema5_ProgramacionSegura.Criptografia;

import java.io.*;
import java.security.*;
import java.security.spec.*;

public class Ejemplo8 {
   public static void main(String[] args) {
     try {
	// LECTURA DEL FICHERO DE CLAVE PRIVADA
	FileInputStream fileInputStreamClave = new FileInputStream("Clave.privada");
	byte[] bufferPriv = new byte[fileInputStreamClave.available()];
	fileInputStreamClave.read(bufferPriv);// lectura de bytes
	fileInputStreamClave.close();

	//RECUPERA CLAVE PRIVADA DESDE DATOS CODIFICADOS EN FORMATO PKCS8
	PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(bufferPriv);
	KeyFactory keyFactoryDSA = KeyFactory.getInstance("DSA");
	PrivateKey privateKey = keyFactoryDSA.generatePrivate(pKCS8EncodedKeySpec);			

	//INICIALIZA FIRMA CON CLAVE PRIVADA 
	Signature signatureDSA = Signature.getInstance("SHA256withDSA");
	signatureDSA.initSign (privateKey);
			
    //LECTURA DEL FICHERO A FIRMAR
    //Se suministra al objeto Signature los datos a firmar
	FileInputStream fileInputStreamFichero = new FileInputStream("FICHERO.DAT");
	BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStreamFichero);
	byte[] buffer = new byte[bufferedInputStream.available()];
	int len;

	while ((len = bufferedInputStream.read(buffer)) >= 0) 			
		signatureDSA.update(buffer, 0, len);
	
    bufferedInputStream.close();
			
	//GENERA LA FIRMA DE LOS DATOS DEL FICHERO
	byte[] firma = signatureDSA.sign();
			
	// GUARDA LA FIRMA EN OTRO FICHERO
	FileOutputStream fos = new FileOutputStream("FICHERO.FIRMA");
	fos.write(firma);
	fos.close();				
     } catch (Exception  e1) {
	e1.printStackTrace();
     } 
   }// main
}//..Ejemplo8

