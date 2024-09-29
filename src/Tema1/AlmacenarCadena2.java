package Tema1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AlmacenarCadena2 {
    public static void main(String[] args) {
        // Leer la cadena desde el teclado
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce una cadena: ");
        String cadena = scanner.nextLine();

        // Ruta del archivo donde se almacenará la cadena
        String rutaArchivo = "C:\\Users\\david\\IdeaProjects\\Procesos_Servicios\\src\\Tema1\\cadena.txt"; // Cambia esta ruta a la deseada

        // Almacenar la cadena en el fichero
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            writer.write(cadena);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }

        // Finaliza el programa
        System.exit(0);
    }
}
