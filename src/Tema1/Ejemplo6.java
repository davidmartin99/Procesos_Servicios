package Tema1;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*
    2 métodos de la clase ProcessBuilder:
        - environment(): Devuelve las variables de entorno del proceso.
        - command(): Sin parámetros, devuelve los argumentos del proceso definido en el objeto ProcessBuilder.
        - command(): Con parámetros, define un nuevo proceso y sus argumentos.
 */

public class Ejemplo6 {
    public static void main(String args[]) {
        // Creamos un objeto ProcessBuilder sin argumentos iniciales
        ProcessBuilder test = new ProcessBuilder();

        // Obtenemos las variables de entorno del proceso y las almacenamos en un mapa
        Map<String, String> entorno = test.environment();
        System.out.println("Variables de entorno:");
        // Imprimimos las variables de entorno en la consola
        System.out.println(entorno);

        // Definimos un nuevo comando que ejecutará "java" con los argumentos "LeerNombre" y "Maria Jesus"
        test = new ProcessBuilder("java", "LeerNombre", "Maria Jesus");

        // Obtenemos la lista de argumentos del comando definido
        List<String> l = test.command();
        // Creamos un iterador para recorrer la lista de argumentos
        Iterator<String> iter = l.iterator();
        System.out.println("\nArgumentos del comando:");
        // Imprimimos cada argumento en la consola
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        // Modificamos el comando para ejecutar el comando del sistema "DIR" usando CMD
        test = test.command("CMD", "/C", "DIR");
        try {
            // Iniciamos el proceso definido por el ProcessBuilder
            Process p = test.start();

            // Obtenemos el flujo de entrada del proceso para leer su salida
            InputStream is = p.getInputStream();
            System.out.println();

            // Leemos la salida del proceso carácter a carácter y la imprimimos en la consola
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c);
            }
            // Cerramos el flujo de entrada
            is.close();
        } catch (Exception e) {
            // Imprimimos cualquier excepción que ocurra
            e.printStackTrace();
        }
    }
} // Ejemplo6
