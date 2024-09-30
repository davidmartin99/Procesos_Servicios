package Tema1;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Scanner;

public class LanzaVisualizarCadena {
    public static void main(String[] args) {
        // Leer la cadena desde el teclado
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce una cadena: ");
        String cadena = scanner.nextLine();

        // Ruta del directorio donde se encuentran los archivos .class compilados
        String classpath = "C:\\Users\\aludam2\\IdeaProjects\\Procesos_Servicios\\out\\production\\Procesos_Servicios";

        // Crear el proceso para ejecutar el primer programa con el argumento ingresado
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", classpath, "Tema1.VisualizarCadena", cadena);

        try {
            Process p = pb.start();

            // Capturar y mostrar la salida estándar del proceso
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            // Capturar el valor de salida del proceso
            int exitVal = p.waitFor();
            System.out.println("Valor de salida: " + exitVal);

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
