package Practica_Tema1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class GestorTareas {

    public static void main(String[] args) {
        try {
            // Archivos a procesar (simulación de selección de archivos por el usuario)
            String[] archivosParaComprimir = {
                    "src\\Practica_Tema1\\archivo11.txt",
                    "src\\Practica_Tema1\\archivo22.txt",
                    "src\\Practica_Tema1\\archivo33.txt"
            };

            // Crear un pool de hilos para procesar múltiples tareas
            ExecutorService executor = Executors.newFixedThreadPool(archivosParaComprimir.length);

            // Lista para almacenar los resultados de las tareas
            List<Future<Boolean>> resultados = new ArrayList<>();

            // Iniciar la compresión de cada archivo en un proceso paralelo
            for (String archivo : archivosParaComprimir) {
                Future<Boolean> resultado = executor.submit(() -> comprimirArchivo(archivo));
                resultados.add(resultado);
            }

            // Esperar a que todas las tareas terminen y obtener sus resultados
            for (int i = 0; i < archivosParaComprimir.length; i++) {
                try {
                    boolean exito = resultados.get(i).get();
                    String estado = exito ? "Éxito" : "Error";
                    System.out.println("Compresión de " + archivosParaComprimir[i] + ": " + estado);
                } catch (Exception e) {
                    System.out.println("Error al procesar " + archivosParaComprimir[i]);
                    e.printStackTrace();
                }
            }

            // Apagar el pool de subprocesos
            executor.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para comprimir un archivo
    public static boolean comprimirArchivo(String archivo) {
        try {
            File file = new File(archivo);
            if (!file.exists()) {
                System.out.println("Archivo no encontrado: " + archivo);
                return false;
            }

            String zipFile = archivo + ".zip";
            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));
                 FileInputStream fis = new FileInputStream(file)) {

                ZipEntry zipEntry = new ZipEntry(file.getName());
                zos.putNextEntry(zipEntry);

                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }

                zos.closeEntry();
            }
            System.out.println("Archivo comprimido exitosamente: " + archivo);
            return true;
        } catch (IOException e) {
            System.out.println("Error al comprimir el archivo: " + archivo);
            e.printStackTrace();
            return false;
        }
    }
}//Fin class
