package Tema1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EjemploLectura {
    public static void main(String[] args) {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        String texto;

        try {
            System.out.println("Introduce una cadena....");
            texto = br.readLine(); // Read user input
            System.out.println("Cadena escrita: " + texto); // Print the input
            in.close(); // Close the input stream
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace in case of an exception
        }
    }
}
