package Tema5_ProgramacionSegura.Criptografia;

import java.io.*;
import java.security.*;
import javax.crypto.*;
public class Ejemplo13Descifra {
	public static void main(String[] args) {
		try {
			//RECUPERAMOS CLAVE SECRETA DEL FICHERO
			FileInputStream fileInputStreamKey = new FileInputStream("Clave.secreta");
			ObjectInputStream objectInputStreamKey = new ObjectInputStream(fileInputStreamKey);
	        Key key = (Key)objectInputStreamKey.readObject();
	        objectInputStreamKey.close();
	        
	        //SE DEFINE EL OBJETO Cipher			
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			
			//OBJETO CipherInputStream
			FileInputStream fileInputStreamFichCifrado = new FileInputStream("Lorem_ipsum.Cifrado");
			CipherInputStream cipherInputStream = new CipherInputStream(fileInputStreamFichCifrado, cipher);
			
			int blockSize = cipher.getBlockSize();
			byte[] bytes = new byte[blockSize];
			
			//LEEMOS BLOQUES DE BYTES DEL FICHERO PDF 
			//Y LO VAMOS ESCRIBIENDO AL CipherInputStream
			int i = cipherInputStream.read(bytes);		
			FileOutputStream fileOutputStreamFichDescifrado = new FileOutputStream("Lorem_ipsum_descifrado.pdf");
		
			while (i != -1)	{
			  fileOutputStreamFichDescifrado.write(bytes, 0, i);
			  i = cipherInputStream.read(bytes);	
			}		
			fileOutputStreamFichDescifrado.close();
			cipherInputStream.close(); 
			System.out.println("Fichero descifrado con clave secreta.");		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//main
}//.. Ejemplo13Descifra

