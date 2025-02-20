package Tema5_ProgramacionSegura.Criptografia;

import java.io.*;
import java.security.*;
import java.security.spec.*;

public class Ejemplo9 {
   public static void main(String[] args) {
     try {
	//LECTURA DE LA CLAVE PÚBLICA DEL FICHERO
	FileInputStream fileInputStreamKey = new FileInputStream("Clave.publica");
	byte[] bufferPub = new byte[fileInputStreamKey.available()];
	fileInputStreamKey.read(bufferPub);// lectura de bytes
	fileInputStreamKey.close();

	//RECUPERA CLAVE PUBLICA DESDE DATOS CODIFICADOS EN FORMATO X509
	KeyFactory keyFactoryDSA = KeyFactory.getInstance("DSA"); 
	X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(bufferPub);
	PublicKey publicKey = keyFactoryDSA.generatePublic(x509EncodedKeySpec);
			
    //LECTURA DEL FICHERO QUE CONTIENE LA FIRMA 	
	FileInputStream fileInputStreamFicheroFirma = new FileInputStream("FICHERO.FIRMA");
	byte[] firma = new byte[fileInputStreamFicheroFirma.available()]; 
	fileInputStreamFicheroFirma.read(firma);
	fileInputStreamFicheroFirma.close();			
	
	//INICIALIZA EL OBJETO Signature CON CLAVE PÚBLICA PARA VERIFICAR
	Signature signatureDSA = Signature.getInstance("SHA256withDSA");
	signatureDSA.initVerify(publicKey);
	
    //LECTURA DEL FICHERO QUE CONTIENE LOS DATOS A VERIFICAR
    //Se suministra al objeto Signature los datos a verificar 	  
	FileInputStream fileInputStreamFichero = new FileInputStream("FICHERO.DAT");
	BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStreamFichero);
	byte[] buffer = new byte[bufferedInputStream.available()];
	int len;
	while ((len = bufferedInputStream.read(buffer)) >= 0) 				
		signatureDSA.update(buffer, 0, len);		
	bufferedInputStream.close();
			
      //VERIFICAR LA FIRMA DE LOS DATOS LEIDOS			
	boolean verifica = signatureDSA.verify(firma);
     
      //COMPROBAR LA VERIFICACIÓN
	if (verifica) 
       System.out.println("LOS DATOS SE CORRESPONDEN CON SU FIRMA.");
	else 
	 System.out.println("LOS DATOS NO SE CORRESPONDEN CON SU FIRMA");
     } catch (Exception  e1) {
	e1.printStackTrace();
     } 
   }// main
}//..Ejemplo9

