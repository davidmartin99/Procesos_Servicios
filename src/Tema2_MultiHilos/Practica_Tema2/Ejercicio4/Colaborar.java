package Tema2_MultiHilos.Practica_Tema2.Ejercicio4;

/**
 * Creamos una clase que lance 10 hilos o más de la clase Lenguaje,
 * para crear un archivo con palabras.
 * Cada hilo generará un número creciente de palabras: 10, 20, 30...
 * Cada hilo seguirá escribiendo su palabra en una sola línea independiente de las otras
 */
public class Colaborar {

    /**
     * Método principal que lanza 10 hilos de la clase Lenguaje.
     * Cada hilo genera un número mayor de palabras y lo guarda en el mismo archivo.
     *
     * @param args
     */
    public static void main(String[] args) {
        String nombreFichero = "colaborarFichero.txt"; // Nombre del archivo de salida
        int numHilos = 10; // Número de hilos a lanzar

        // Crear y lanzar los hilos de Lenguaje
        for (int i = 1; i <= numHilos; i++) {
            int numPalabras = i * 10; // Número de palabras generado por el hilo actual

            // Instancia de Lenguaje con el número de palabras y el archivo donde lo guardaremos
            Thread hilo = new Thread(new Lenguaje(numPalabras, nombreFichero));
            hilo.start();

            // Espera a que el hilo termine antes de lanzar el siguiente
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }//Fin try-catch
        }//Fin for

        // Mostramos que se ha generado el fichero y
        System.out.println("Fichero generado con éxito: " + nombreFichero);
    }//Fin main
}//Fin Colaborar

