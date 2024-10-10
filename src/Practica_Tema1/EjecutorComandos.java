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
    }

    // Método de prueba para ejecutar comandos
    public static void main(String[] args) {
        EjecutorComandos ejecutor = new EjecutorComandos();

        // Ejemplo de uso del método ejecutarComando
        int salida1 = ejecutor.ejecutarComando("echo Hola Mundo");
        System.out.println("Código de salida1: " + salida1);

        // Ejemplo de uso del método ejecutarComandoConEntrada (cambiamos el comando)
        int salida2 = ejecutor.ejecutarComandoConEntrada("echo", "Hola desde el input");
        System.out.println("Código de salida2: " + salida2);

        // Ejemplo de uso del método ejecutarComandoConRedireccion
        File archivoSalida = new File("src\\Practica_Tema1\\salida.txt");
        int salida3 = ejecutor.ejecutarComandoConRedireccion("echo Hola Mundo", archivoSalida);
        System.out.println("Código de salida3: " + salida3);
    }
}
