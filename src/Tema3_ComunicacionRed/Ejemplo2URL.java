package Tema3_ComunicacionRed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Ejemplo2URL {
    public static void main(String[] args) {
        try {
            URL url = null;
            url = new URL("https://www.google.com/");

            BufferedReader in;
            try {
                InputStream inputStream = url.openStream();
                in = new BufferedReader(new InputStreamReader(inputStream));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine);
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
