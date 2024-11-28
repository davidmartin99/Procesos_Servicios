package Tema2_MultiHilos.Practica_Tema2.Ejercicio1;
/**
 * Clase que representa una tarea de cálculo que implementa la interfaz Runnable.
 * Cada hilo de esta clase genera números aleatorios, los suma, y se detiene
 * cuando la suma alcanza un millón.
 *
 * @author David
 * @date 04/11/2024
 */

public class TareaCalculo_c implements Runnable{
    //Variable de la suma total
    public int suma = 0;
    //Variable del numero aleatorio
    public int n;
    //Variable de control para detener el hilo
    private boolean detenido = false;

    /**
     * Método que se ejecuta cuando se inicia el hilo.
     * En un bucle, crea un número aleatorio, lo va sumando al anterior,
     * y muestra el resultado en la consola.
     * El hilo se detiene cuando la suma alcanza o supera 1.000.000 .
     */
    @Override
    public void run() {
        while(!detenido){
            try{
                //Creamos un aleatorio entre 100 y 1000
                n = (int) (Math.random() * 901) + 100;

                suma += n; //Sumamos numeros

                //Mostramos en pantalla
                System.out.println("(" + Thread.currentThread().getName() + ") Valor acumulado: " + suma);

                //Detener el hilo si la suma llega a 1.000.000
                if (suma >= 1000) {
                    detenido = true;
                }

                //Esperamos 10s para el siguiente
                Thread.sleep(1000);

            }catch (InterruptedException e){ //Lanzamos un mensaje de error
                System.err.println("Hilo "+  Thread.currentThread().getName() +" interrumpido.");
                break; //Detenemos el hilo
            }//Fin try-catch
        }//Fin while

    }//Fin run

}//Fin class
