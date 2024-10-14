package Practica_Tema1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * La clase SincronizacionSubprocesos permite ejecutar comandos de PowerShell para contar
 * líneas y palabras en archivos de texto, gestionando la ejecución de subprocesos y la
 * sincronización de resultados.
 * @author david
 * @version 1.0
 * @date 12/10/2024
 */
public class SincronizacionSubprocesos {

    /**
     * Método privado que ejecuta un comando de PowerShell y
     * devuelve el conteo de líneas o palabras del archivo especificado.
     *
     * @param comando El comando de PowerShell a ejecutar, que debe devolver un número.
     * @return El conteo como un entero. Devuelve 0 si no se puede obtener el conteo.
     * @throws IOException Si ocurre un error al ejecutar el comando.
     * @throws InterruptedException Si el proceso es interrumpido.
     */
    private static int ejecutarComando(String comando) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("powershell.exe", "-Command", comando);
        Process proceso = builder.start();

        // Leer la salida del proceso
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
            String resultado;
            while ((resultado = reader.readLine()) != null) {
                // Si resultado.trim().matches("^\\d+$") devuelve true,
                // significa que la salida del comando es un valor numérico, que representa el conteo de líneas o palabras.
                if (resultado.trim().matches("^\\d+$")) {
                    return Integer.parseInt(resultado.trim()); // Si es un número, lo retorna
                }
            }
        }

        proceso.waitFor();
        System.err.println("No se pudo obtener el conteo.");
        return 0;
    } //Fin ejecutarComando

    /**
     * Método principal que se ejecuta al iniciar la aplicación. Se encarga de definir
     * los archivos a analizar y gestionar la ejecución de comandos para contar líneas y palabras.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        String archivo1 = "src\\Practica_Tema1\\archivo1.txt";
        String archivo2 = "src\\Practica_Tema1\\archivo2.txt";

        try {
            // Ejecutar comandos para contar líneas y palabras
            int totalLineas = ejecutarComando("Get-Content " + archivo1 + " | Measure-Object -Line");
            int totalPalabras = ejecutarComando("Get-Content " + archivo2 + " | Measure-Object -Word");

            // Mostrar los resultados
            System.out.println("Total líneas en archivo1: " + totalLineas);
            System.out.println("Total palabras en archivo2: " + totalPalabras);
            System.out.println("Total combinado de líneas y palabras: " + (totalLineas + totalPalabras));

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    } // Fin main

} // Fin class SincronizacionSubprocesos
