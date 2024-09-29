package Tema1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class LanzaValidador {
    public static void main(String[] args) throws IOException {
        // Ruta del directorio donde se encuentran los archivos .class compilados
        File directorio = new File("C:\\Users\\david\\IdeaProjects\\Procesos_Servicios\\out\\production\\Procesos_Servicios");

        // Cambia el argumento a "Hola" para probar el caso de cadena
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", directorio.getAbsolutePath(), "Tema1.ArgumentosValidador", "-3");
        Process p = pb.start();

        // Capturamos y mostramos el flujo de salida estándar y error estándar
        capturaSalida(p.getInputStream(), "Salida");
        capturaSalida(p.getErrorStream(), "Error");

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

    // Metodo para capturar la salida (tanto estándar como de error) en un hilo separado
    private static void capturaSalida(InputStream inputStream, String tipo) {
        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(tipo + ": " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
