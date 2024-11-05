package Tema2_MultiHilos.Practica_Tema2.Ejercicio1;

/**
 * Clase que representa una tarea de cálculo que extiende la clase Thread.
 * Cada hilo de esta clase genera números aleatorios y los suma.
 * @author David
 * @date 04/11/2024
 */

public class TareaCalculo_a extends Thread{
    //Variable de la suma total
    public int suma = 0;
    //Variable del numero aleatorio
    public int n;

    /**
     * Método que se ejecuta cuando se inicia el hilo.
     * En un bucle infinito, crea un número aleatorio, lo va sumando al anterior,
     * y muestra el resultado en la consola.
     */
@Override
    public void run() {
        while(true){
            try{
                //Creamos un aleatorio entre 100 y 1000
                n = (int) (Math.random() * 901) + 100;

                suma += n; //Sumamos numeros

                //Mostramos en pantalla
                System.out.println("("+getName()+") Resultado:" + suma);

                //Esperamos 10s para el siguiente
                Thread.sleep(1000);

            }catch (InterruptedException e){ //Lanzamos un mensaje de error
                System.err.println("Hilo "+ getName() +" interrumpido.");
                break; //Detenemos el hilo
            }//Fin try-catch
        }//Fin while

    }//Fin run

}//Fin class
