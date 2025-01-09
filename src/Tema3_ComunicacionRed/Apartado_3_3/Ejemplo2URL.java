package Tema3_ComunicacionRed.Apartado_3_3;

import java.net.*;
import java.io.*;

public class Ejemplo2URL {
  public static void main(String[] args) {
	URL url=null;
	try {
		url = new URL("https://www.geo-fs.com/");
	} catch (MalformedURLException e) {	e.printStackTrace();}
		
	BufferedReader in;
	try {
		InputStream inputStream = url.openStream();
		in = new BufferedReader(new InputStreamReader(inputStream));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			System.out.println(inputLine);
		in.close();

	} catch (IOException e) {e.printStackTrace();}
  }//
}//Ejemplo2URL


