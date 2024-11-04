package Tema2_MultiHilos.Practica_Tema2.Ejercicio2;

import java.util.Set;

/**
 * Clase GeneradorArchivos que se encarga de generar múltiples archivos CSV utilizando hilos.
 * @author David
 * @date 04/11/2024
 */
public class GeneradorArchivos {
    private static final int CANTIDAD_ARCHIVOS = 100; // Cambiado a 50 en el apartado d)
    private static final int CANTIDAD_LINEAS = 100000; // Cantidad de líneas por archivo (no se usa en esta clase)

    /**
     * Método que genera múltiples archivos CSV en hilos separados.
     *
     * @param identificadores Conjunto de identificadores alfanuméricos a utilizar en los archivos.
     */
    public void generarArchivos(Set<String> identificadores) {
        // Itera para crear la cantidad especificada de archivos
        for (int i = 0; i < CANTIDAD_ARCHIVOS; i++) {
            // Crea y empieza un hilo usando GeneradorCSVRunnable
            new Thread(new GeneradorCSVRunnable(identificadores, "archivo_" + (i + 1) + ".csv")).start();
        }
    }//Fin generarArchivos

    /**
     * Método main que sirve como punto de entrada de la aplicación.
     * Genera identificadores y luego inicia la generación de archivos CSV.
     *
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        GeneradorIdentAlfa generadorIds = new GeneradorIdentAlfa(); // Crea una instancia del generador de identificadores
        Set<String> ids = generadorIds.generarIdentificadores(); // Genera los identificadores

        GeneradorArchivos generadorArchivos = new GeneradorArchivos(); // Crea una instancia del generador de archivos
        generadorArchivos.generarArchivos(ids); // Llama al método para generar los archivos CSV
    }//Fin main

}//Fin class GeneradorArchivos

/**
 * Clase GeneradorCSVRunnable que implementa la interfaz Runnable para crear un hilo
 * que genera un archivo CSV.
 */
class GeneradorCSVRunnable implements Runnable {
    private Set<String> identificadores; // Conjunto de identificadores a utilizar
    private String nombreArchivo; // Nombre del archivo CSV a generar

    /**
     * Constructor de GeneradorCSVRunnable.
     *
     * @param identificadores Conjunto de identificadores alfanuméricos.
     * @param nombreArchivo Nombre del archivo CSV a generar.
     */
    public GeneradorCSVRunnable(Set<String> identificadores, String nombreArchivo) {
        this.identificadores = identificadores; // Asigna el conjunto de identificadores
        this.nombreArchivo = nombreArchivo; // Asigna el nombre del archivo
    }

    @Override
    public void run() {
        // Crea una instancia de GeneradorCSV y llama al método para generar el archivo CSV
        GeneradorCSV generadorCSV = new GeneradorCSV();
        generadorCSV.generarCSV(identificadores, nombreArchivo, nombreArchivo); // Genera el archivo CSV usando el mismo nombre para ambos parámetros
    }//Fin run

}//Fin class GeneradorCSVRunnable