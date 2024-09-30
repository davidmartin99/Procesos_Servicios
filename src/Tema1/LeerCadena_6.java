package Tema1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LeerCadena_6 {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            System.out.println("Introduce cadenas de texto (escribe '*' para salir):");
            while (!(input = reader.readLine()).equals("*")) {
                System.out.println("Has introducido: " + input);
            }
            System.out.println("Programa finalizado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
