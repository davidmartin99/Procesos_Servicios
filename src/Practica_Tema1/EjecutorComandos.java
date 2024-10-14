package Practica_Tema1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * La clase EjecutorComandos encapsula la funcionalidad para ejecutar comandos del sistema operativo
 * utilizando ProcessBuilder.
 *
 * Proporciona métodos para ejecutar comandos simples, ejecutar comandos
 * con entrada estándar y redirigir la salida a archivos.
 * @author david
 * @version 1.0
 * @date 12/10/2024
 */
public class EjecutorComandos {
    /**
     * Método main de prueba para ejecutar comandos y mostrar el código de salida en la consola.
     */
    public static void main(String[] args) {
        // Creamos el ejecutor
        EjecutorComandos ejecutor = new EjecutorComandos();

        // Ejemplo de uso del método ejecutarComando
        int salida1 = ejecutor.ejecutarComando("echo Hola Mundo");
        System.out.println("Código de salida1: " + salida1);

        // Ejemplo de uso del método ejecutarComandoConEntrada
        int salida2 = ejecutor.ejecutarComandoConEntrada("echo", "Hola desde el input");
        System.out.println("Código de salida2: " + salida2);

        // Ejemplo de uso del método ejecutarComandoConRedireccion
        File archivoSalida = new File("src\\Practica_Tema1\\salida.txt");
        int salida3 = ejecutor.ejecutarComandoConRedireccion("echo Hola Mundo", archivoSalida);
        System.out.println("Código de salida3: " + salida3);

    }//Fin main

    /**
     * Ejecuta un comando en el sistema operativo y devuelve el código de salida del proceso.
     *
     * @param comando El comando a ejecutar.
     * @return El código de salida del proceso. 0 indica éxito, mientras que -1 indica un error.
     */
    public int ejecutarComando(String comando) {
        try {
            // Creamos el proceso
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", comando);
            Process proceso = builder.start(); // Se inicia
            proceso.waitFor();
            return proceso.exitValue(); // Devuelve una salida
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }//Fin ejecutarComando

    /**
     * Ejecuta un comando en el sistema operativo y proporciona entrada estándar al proceso.
     *
     * @param comando El comando a ejecutar.
     * @param entrada La entrada que se pasará al comando.
     * @return El código de salida del proceso. 0 indica éxito, mientras que -1 indica un error.
     */
    public int ejecutarComandoConEntrada(String comando, String entrada) {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", comando);
            Process proceso = builder.start();

            // Escribimos la entrada estándar
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()));
            writer.write(entrada);
            writer.flush(); // Vacíamos el buffered de salida
            writer.close(); // Ceramos

            proceso.waitFor();
            return proceso.exitValue();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }//Fin ejecutarComandoConEntrada

    /**
     * Ejecuta un comando en el sistema operativo y redirige la salida a un archivo especificado.
     *
     * @param comando El comando a ejecutar.
     * @param archivoSalida El archivo donde se redirigirá la salida del comando.
     * @return El código de salida del proceso. 0 indica éxito, mientras que -1 indica un error.
     */
    public int ejecutarComandoConRedireccion(String comando, File archivoSalida) {
        try {
            // Creamos el archivo de salida si no existe
            if (!archivoSalida.exists()) {
                archivoSalida.createNewFile();
            }

            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", comando);
            builder.redirectOutput(archivoSalida);
            Process proceso = builder.start();
            proceso.waitFor();
            return proceso.exitValue();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }//Fin ejecutarComandoConRedireccion

}//Fin class
