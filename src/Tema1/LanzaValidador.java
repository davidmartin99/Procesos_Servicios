package Tema1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class LanzaValidador {
    public static void main(String[] args) throws IOException {
        // Ruta del directorio donde se encuentra el archivo .class compilado
        File directorio = new File("C:\\Users\\aludam2\\IdeaProjects\\Procesos_Servicios\\out\\production\\Procesos_Servicios");

        // Creamos el proceso para ejecutar el primer programa con un argumento
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", directorio.getAbsolutePath(), "Tema1.ArgumentosValidador", "10"); // Cambia el argumento según lo que quieras probar
        Process p = pb.start();

        // Capturamos y mostramos el flujo de salida del proceso
        InputStream is = p.getInputStream();
        int c;
        while ((c = is.read()) != -1) {
            System.out.print((char) c);
        }
        is.close();

        // Capturamos y mostramos el flujo de error si hay
        InputStream es = p.getErrorStream();
        int e;
        while ((e = es.read()) != -1) {
            System.out.print((char) e);
        }
        es.close();

        // Esperamos a que el proceso termine y capturamos su valor de salida
        int exitVal;
        try {
            exitVal = p.waitFor();
            System.out.println("\nValor de salida: " + exitVal);

            // Dependiendo del valor de salida, mostramos el mensaje correspondiente
            switch (exitVal) {
                case 1:
                    System.out.println("Error: No se recibió ningún argumento.");
                    break;
                case 2:
                    System.out.println("Error: El argumento es una cadena.");
                    break;
                case 3:
                    System.out.println("Error: El argumento es un número menor que 0.");
                    break;
                case 0:
                    System.out.println("Éxito: El argumento es un número válido mayor o igual que 0.");
                    break;
                default:
                    System.out.println("Error desconocido.");
            }

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
