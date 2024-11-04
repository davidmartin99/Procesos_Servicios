package Tema2_MultiHilos.Practica_Tema2.Ejercicio2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Clase GeneradorCSV que se encarga de generar archivos CSV con pares de identificadores y números enteros.
 * @author David
 * @date 04/11/2024
 */

public class GeneradorCSV {

    private static final int CANTIDAD_LINEAS = 50000; // Cantidad de líneas a generar en los archivos CSV
    private static final int RANGO_NUMEROS = 20000; // Rango máximo para los números aleatorios generados

    /**
     * Método que genera dos archivos CSV con pares de identificadores y números aleatorios.
     *
     * @param identificadores Conjunto de identificadores alfanuméricos.
     * @param nombreArchivo1 Nombre del primer archivo CSV a generar.
     * @param nombreArchivo2 Nombre del segundo archivo CSV a generar.
     */
    public void generarCSV(Set<String> identificadores, String nombreArchivo1, String nombreArchivo2) {
        Random random = new Random(); // Crea un objeto Random para generar números aleatorios

        try (BufferedWriter writer1 = new BufferedWriter(new FileWriter(nombreArchivo1)); // Inicializa el BufferedWriter para el primer archivo
             BufferedWriter writer2 = new BufferedWriter(new FileWriter(nombreArchivo2))) { // Inicializa el BufferedWriter para el segundo archivo

            // Genera las líneas para los archivos CSV
            for (int i = 0; i < CANTIDAD_LINEAS; i++) {
                String id = obtenerElementoAleatorio(identificadores); // Obtiene un identificador aleatorio del conjunto
                int numero = random.nextInt(RANGO_NUMEROS + 1); // Genera un número aleatorio entre 0 y RANGO_NUMEROS

                // Escribir en el primer archivo
                writer1.write(id + "," + numero); // Escribe el identificador y el número en el primer archivo
                writer1.newLine(); // Añade una nueva línea

                // Escribir en el segundo archivo
                writer2.write(id + "," + numero); // Escribe el identificador y el número en el segundo archivo
                writer2.newLine(); // Añade una nueva línea
            }
        } catch (IOException e) {
            e.printStackTrace(); // Imprime el stack trace en caso de una excepción de entrada/salida
        }
    }//Fin generarCSV

    /**
     * Método que obtiene un elemento aleatorio de un conjunto.
     *
     * @param conjunto Conjunto del cual se obtendrá un elemento aleatorio.
     * @return Elemento aleatorio del conjunto.
     */
    private String obtenerElementoAleatorio(Set<String> conjunto) {
        int index = new Random().nextInt(conjunto.size()); // Genera un índice aleatorio dentro del rango del conjunto
        return (String) conjunto.toArray()[index]; // Devuelve el elemento en el índice aleatorio
    }

    /**
     * Método main que sirve como punto de entrada de la aplicación.
     * Genera identificadores y luego crea archivos CSV con los pares generados.
     *
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        GeneradorIdentAlfa generadorIds = new GeneradorIdentAlfa(); // Crea una instancia del generador de identificadores
        Set<String> ids = generadorIds.generarIdentificadores(); // Genera los identificadores

        GeneradorCSV generadorCSV = new GeneradorCSV(); // Crea una instancia del generador de CSV
        generadorCSV.generarCSV(ids, "datos1.csv", "datos2.csv"); // Genera los archivos CSV con los identificadores
    }//Fin main

}//Fin GeneradorCSV