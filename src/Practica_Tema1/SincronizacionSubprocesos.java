package Practica_Tema1;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class SincronizacionSubprocesos {

    private static int totalLineas = 0;
    private static int totalPalabras = 0;

    public static void main(String[] args) {
        try {
            // Archivos a procesar
            String archivoLineas = "src\\Practica_Tema1\\archivo1.txt";  // Cambia por la ruta de tu archivo de texto
            String archivoPalabras = "src\\Practica_Tema1\\archivo2.txt";  // Cambia por la ruta de tu archivo de texto

            // Comprobar si los archivos existen
            if (!new File(archivoLineas).exists() || !new File(archivoPalabras).exists()) {
                System.out.println("Uno o ambos archivos no existen.");
                return;
            }

            // Crear los hilos para contar líneas y palabras
            Thread hiloContarLineas = new Thread(() -> {
                totalLineas = contarLineas(archivoLineas);
            });

            Thread hiloContarPalabras = new Thread(() -> {
                totalPalabras = contarPalabras(archivoPalabras);
            });

            // Iniciar los hilos
            hiloContarLineas.start();
            hiloContarPalabras.start();

            // Esperar a que ambos hilos terminen
            hiloContarLineas.join();
            hiloContarPalabras.join();

            // Mostrar el resultado total combinado
            System.out.println("Total de líneas: " + totalLineas);
            System.out.println("Total de palabras: " + totalPalabras);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }// Fin main

    private static int contarLineas(String archivo) {
        int lineas = 0;
        try {
            String comando = "powershell.exe Get-Content " + archivo + " | Measure-Object -Line";
            ProcessBuilder builder = new ProcessBuilder(comando.split(" "));
            builder.redirectErrorStream(true);
            Process proceso = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println("Salida PowerShell contarLineas: " + linea);  // Depuración
                if (linea.trim().matches("\\d+")) {  // Si es una línea que contiene solo números
                    lineas = Integer.parseInt(linea.trim());
                    break;  // Salir después de encontrar el número
                }
            }
            proceso.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineas;
    }

    private static int contarPalabras(String archivo) {
        int palabras = 0;
        try {
            String comando = "powershell.exe Get-Content " + archivo + " | Measure-Object -Word";
            ProcessBuilder builder = new ProcessBuilder(comando.split(" "));
            builder.redirectErrorStream(true);
            Process proceso = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println("Salida PowerShell contarPalabras: " + linea);  // Depuración
                if (linea.trim().matches("\\d+")) {  // Si es una línea que contiene solo números
                    palabras = Integer.parseInt(linea.trim());
                    break;  // Salir después de encontrar el número
                }
            }
            proceso.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return palabras;
    }

}// Fin class
