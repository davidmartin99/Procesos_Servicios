package Practica_Tema1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class EjecutorComandos {
    // Método para ejecutar un comando y devolver el código de salida
    public int ejecutarComando(String comando) {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", comando);
            Process proceso = builder.start();
            proceso.waitFor();
            return proceso.exitValue();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Método para ejecutar un comando con entrada estándar proporcionada
    public int ejecutarComandoConEntrada(String comando, String entrada) {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", comando);
            Process proceso = builder.start();

            // Escribimos la entrada estándar
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()));
            writer.write(entrada);
            writer.flush();
            writer.close();

            proceso.waitFor();
            return proceso.exitValue();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Método para ejecutar un comando y redirigir la salida a un archivo
    public int ejecutarComandoConRedireccion(String comando, File archivoSalida) {
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd", "/c", comando);
            builder.redirectOutput(archivoSalida);
            Process proceso = builder.start();
            proceso.waitFor();
            return proceso.exitValue();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    // Método de prueba para ejecutar comandos
    public static void main(String[] args) {
        EjecutorComandos ejecutor = new EjecutorComandos();

        // Ejemplo de uso del método ejecutarComando
        System.out.println("Código de salida: " + ejecutor.ejecutarComando("echo Hola Mundo"));

        // Ejemplo de uso del método ejecutarComandoConEntrada
        System.out.println("Código de salida: " + ejecutor.ejecutarComandoConEntrada("type CON", "Hola desde el input"));

        // Ejemplo de uso del método ejecutarComandoConRedireccion
        File archivoSalida = new File("src\\Practica_Tema1\\salida.txt");
        System.out.println("Código de salida: " + ejecutor.ejecutarComandoConRedireccion("echo Hola Mundo", archivoSalida));
    }
}
