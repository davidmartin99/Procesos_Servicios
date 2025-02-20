package Tema5_ProgramacionSegura.Criptografia;

import java.io.*;
import java.security.*;

public class RecuperaClaveSecreta {
	public static void main(String[] args) {
		try {
			FileInputStream fileInputStream = new FileInputStream("Clave.secreta");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			Key key = (Key) objectInputStream.readObject();
			objectInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// main
}// ..RecuperaClaveSecreta 


