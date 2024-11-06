package Tema2_MultiHilos.Practica_Tema2.Ejercicio4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase que crea conjuntos de letras de forma aleatoria y los guarda en un archivo.
 * Cada conjunto se escribe en una sola línea.
 * El usuario introduce el nombre del fichero a crear y el número de palabras que quiera.
 */
public class Lenguaje implements Runnable {
    private final int numPalabras;
    private final String nombreFichero;

    // Ruta del archivo a guardar
    private static final String ruta = "src\\Tema2_MultiHilos\\Practica_Tema2\\Ejercicio4";

    /**
     * Constructor de la clase Lenguaje.
     *
     * @param numPalabras Número de palabras a generar.
     * @param nombreFichero Nombre del archivo donde se escribirán las palabras.
     */
    public Lenguaje(int numPalabras, String nombreFichero) {
        this.numPalabras = numPalabras;
        this.nombreFichero = ruta + nombreFichero; // Ruta más el fichero
    }

    /**
     * Método que se ejecuta al iniciar el hilo.
     * Genera palabras aleatorias y las escribe en el archivo especificado.
     * Cada palabra se escribe en una línea independiente.
     */
    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreFichero, true))) {
            for (int i = 0; i < numPalabras; i++) {
                StringBuilder palabra = new StringBuilder();

                // Genera una longitud aleatoria entre 3 y 10 para cada palabra
                int longitudPalabra = 3 + (int) (Math.random() * 8);

                // Genera una palabra con la longitud aleatoria
                for (int j = 0; j < longitudPalabra; j++) {
                    char letra = (char) ('a' + (int) (Math.random() * 26));
                    palabra.append(letra);
                }

                writer.write(palabra.toString());
                writer.newLine();
            } // Fin for
        } catch (IOException e) {
            e.printStackTrace();
        } // Fin try-catch
    } // Fin run

    /**
     * Método main para ejecutar la clase Lenguaje.
     * Pide al usuario que ingrese el nombre del archivo y el número de palabras a generar.
     *
     * @param args no se utiliza.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("*** Bienvenido al generador aleatorio de palabras *** ");
        System.out.print("      Introduce el número de palabras a generar: ");
        int numPalabras = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea pendiente

        System.out.print("      Nombre del archivo donde se guardarán: ");
        String nombreFichero = scanner.nextLine();

        // Crear instancia de Lenguaje y ejecutar
        Lenguaje lenguaje = new Lenguaje(numPalabras, nombreFichero);
        lenguaje.run();

        System.out.println("Archivo generado con éxito: " + ruta + nombreFichero);

        scanner.close();
    } // Fin main
} // Fin class