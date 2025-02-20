package Tema5_ProgramacionSegura.Criptografia;

import java.io.*;
import java.security.*;
import javax.crypto.*;

public class Ejemplo13Cifra {
	public static void main(String[] args) {
		try {	
			//RECUPERAMOS CLAVE SECRETA DEL FICHERO
			FileInputStream fileInputStreamKey = new FileInputStream("Clave.secreta");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStreamKey);
	        Key key = (Key) objectInputStream.readObject();
	        objectInputStream.close();
	        
			//SE DEFINE EL OBJETO Cipher
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			
			//FICHERO A CIFRAR
			FileInputStream fileInputStreamFichero = new FileInputStream("Lorem_ipsum.pdf");
			
			//OBJETO CipherOutputStream
			FileOutputStream fileOutputStream = new FileOutputStream("Lorem_ipsum.Cifrado");
			CipherOutputStream cipherOutputStream = new CipherOutputStream(fileOutputStream, cipher);
			int blockSize = cipher.getBlockSize();
			byte[] bytes = new byte[blockSize];
			
			//LEEMOS BLOQUES DE BYTES DEL FICHERO PDF 
			//Y LO VAMOS ESCRIBIENDO AL CipherOutputStream
			int i = fileInputStreamFichero.read(bytes);		
			while (i != -1) {				
				cipherOutputStream.write(bytes, 0, i);
				i = fileInputStreamFichero.read(bytes);
			}
			cipherOutputStream.flush();
			cipherOutputStream.close();
			fileInputStreamFichero.close(); 
			System.out.println("Fichero cifrado con clave secreta.");					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//main
}//.. Ejemplo13Cifra

