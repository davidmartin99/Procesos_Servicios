package Tema1;

import java.io.IOException;
import java.io.InputStream;

public class Ejemplo2 {
    public static void main(String[] args) throws IOException {

        // Creamos un proceso para ejecutar el comando CMD
        Process p = new ProcessBuilder("CMD", "/C", "DIR").start();

        try {
            // Obtenemos el flujo de entrada del proceso
            InputStream is = p.getInputStream();
            
            // Mostramos en pantalla el resultado del proceso, caracter por caracter
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c);
            }
            
            // Cerramos el flujo de entrada
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }//Fin try-catch


        // COMPROBACION DE ERROR - 0   BIEN - 1 MAL  usamos waitFor()
        int exitVal;
        try{
            //Se recoge la salida de Sysyem.exit()
            exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }//Fin try-catch
        
    }//Fin main
    
}//Fin class

