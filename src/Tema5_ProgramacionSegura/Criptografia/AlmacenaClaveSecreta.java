package Tema5_ProgramacionSegura.Criptografia;

import java.io.*;
import java.security.*;
import javax.crypto.*;

public class AlmacenaClaveSecreta {
   public static void main(String[] args) {
	try {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);

        //genera clave secreta
		SecretKey secretKey = keyGenerator.generateKey();
		FileOutputStream fileOutputStream = new FileOutputStream("Clave.secreta");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		
		objectOutputStream.writeObject(secretKey);
		objectOutputStream.close();
	} catch (NoSuchAlgorithmException e) {e.printStackTrace();} 
	  catch (FileNotFoundException e) {e.printStackTrace();} 
	  catch (IOException e) {e.printStackTrace();}
   }//main
}//..AlmacenaClaveSecreta

