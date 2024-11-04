package Tema2_MultiHilos.Practica_Tema2;

public class TareaCalculo_b implements Runnable{
    public int suma = 0;
    public int n;

    @Override
    public void run() {
        while(true){
            try{
                //Creamos un aleatorio
                n = (int) (Math.random() * 901) + 100;

                suma += n; //Sumamos numeros

                //Mostramos en pantalla
                System.out.println("(" + Thread.currentThread().getName() + ") Valor acumulado: " + suma);

                //Esperamos 10s para el siguiente
                Thread.sleep(1000);

            }catch (InterruptedException e){ //Lanzamos un mensaje de error
                System.err.println("Hilo "+  Thread.currentThread().getName() +" interrumpido.");
                break; //Detenemos el hilo
            }//Fin try-catch
        }//Fin while

    }//Fin run

}//Fin class
