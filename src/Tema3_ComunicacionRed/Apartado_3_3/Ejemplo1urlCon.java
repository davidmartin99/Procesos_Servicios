package Tema3_ComunicacionRed.Apartado_3_3;

import java.net.*;
import java.io.*;

public class Ejemplo1urlCon {
  public static void main(String[] args) {
	URL url=null;
	URLConnection urlCon=null;
	try {
		url = new URL("https://www.discoduroderoer.es/");		
		urlCon= url.openConnection();
		
		BufferedReader in;
		InputStream inputStream = urlCon.getInputStream();
		in = new BufferedReader(new InputStreamReader(inputStream));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		
		in.close();
	}
	catch (MalformedURLException e) {e.printStackTrace();} 
	catch (IOException e) {e.printStackTrace();}
  }//
}//Ejemplo1urlCon

