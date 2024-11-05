package Tema2_MultiHilos.Practica_Tema2.Ejercicio2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Clase GeneradorIdentAlfa que se encarga de generar 200 identificadores alfanuméricos únicos.
 * Cada identificador tiene una longitud fija de 6 caracteres.
 * @author David
 * @date 04/11/2024
 */

public class GeneradorIdentAlfa {
    //Constante de la longitud de cada identificador
    private static final int LONGITUD_ID = 6;
    //Constante del total de identificadores a generar
    private static final int CANTIDAD_IDS = 200;
    //Constante de los caracteres
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * Método que genera un conjunto de identificadores únicos.
     *
     * @return Set<String> que contiene los identificadores generados.
     */
    public Set<String> generarIdentificadores() {
        // Inicializa un conjunto Set para almacenar identificadores únicos
        Set<String> identificadores = new HashSet<>();
        // Crea un objeto Random para generar números aleatorios
        Random random = new Random();

        // Genera identificadores hasta que se alcance la cantidad deseada
        while (identificadores.size() < CANTIDAD_IDS) {
            StringBuilder id = new StringBuilder(LONGITUD_ID); // StringBuilder que crea el identificador
            // Crea un identificador aleatorio
            for (int i = 0; i < LONGITUD_ID; i++) {
                id.append(CARACTERES.charAt(random.nextInt(CARACTERES.length()))); // Agrega un carácter aleatorio
            }
            identificadores.add(id.toString()); // Añade el identificador al conjunto
        }//Fin while

        return identificadores; // Retorna el conjunto de identificadores generados
    }//Fin generarIdentificadores


    /**
     * Método main que sirve como punto de entrada de la aplicación.
     * Genera los identificadores y los imprime en la consola.
     *
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        GeneradorIdentAlfa generador = new GeneradorIdentAlfa(); // Crea una instancia del generador
        Set<String> ids = generador.generarIdentificadores(); // Genera los identificadores
        System.out.println("Identificadores generados: " + ids); // Imprime los identificadores generados
    }//Fin main

}//Fin class
