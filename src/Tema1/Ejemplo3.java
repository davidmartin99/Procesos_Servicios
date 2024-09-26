package Tema1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Ejemplo3 {
    public static void main(String[] args) throws IOException {
        //Creamos objeto File al directorio donde esta Ejemplo2
        File directorio = new File("C:\\Users\\aludam2\\IdeaProjects\\Procesos_Servicios\\src\\Tema1");

        //El proceso a ejecutar es Ejemplo2
        ProcessBuilder pb = new ProcessBuilder("java", "Ejemplo2");

        //Se establece el directorio donde se encuentra ejecutable
        pb.directory(directorio);

        System.out.printf("Directorio de trabajo: %s%n", pb.directory());

        //Se ejecuta el proceso
        Process p = pb.start();

        //Obtener la salida de vuelta por el proceso
        try{
            InputStream is = p.getInputStream();
            int c;
            while( (c = is.read()) != -1)
                System.out.print((char)c);
            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }//Fin try-catch


    }//Fin main
}//Fin class
