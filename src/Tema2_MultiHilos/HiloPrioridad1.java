package Tema2_MultiHilos;

class HiloPrioridad1 extends Thread {
    private int c = 0; // Contador que se incrementará en el hilo
    private boolean stopHilo = false; // Variable para detener el hilo

    // Constructor que recibe el nombre del hilo y lo pasa a la clase Thread
    public HiloPrioridad1(String nombre) {
        super(nombre);
    }

    // Método para obtener el valor del contador
    public int getContador() {
        return c;
    }

    // Método para detener el hilo, cambia la variable stopHilo a true
    public void pararHilo() {
        stopHilo = true;
    }

    // Método que se ejecuta cuando se inicia el hilo
    public void run() {
        // Mientras el hilo no se haya detenido
        while (!stopHilo) {
            try {
                Thread.sleep(100); // Pausa el hilo por 2 milisegundos
                c++; // Incrementa el contador
            } catch (Exception e) {
                // Captura cualquier excepción, aunque en este caso no se maneja
            }
        }
        // Al finalizar el bucle, imprime que el hilo ha terminado
        System.out.println("Fin hilo " + this.getName());
    }//Fin run

    // Método principal que crea e inicia los hilos
    public static void main(String args[]) {
        // Crea tres hilos con diferentes nombres
        HiloPrioridad1 h1 = new HiloPrioridad1("Hilo1");
        HiloPrioridad1 h2 = new HiloPrioridad1("Hilo2");
        HiloPrioridad1 h3 = new HiloPrioridad1("Hilo3");

        // Asigna prioridades a cada hilo
        h1.setPriority(Thread.NORM_PRIORITY);  // Prioridad normal (5)
        h2.setPriority(Thread.MAX_PRIORITY);   // Prioridad máxima (10)
        h3.setPriority(Thread.MIN_PRIORITY);   // Prioridad mínima (1)

        // Inicia los tres hilos
        h1.start();
        h2.start();
        h3.start();

        try {
            Thread.sleep(10000); // El hilo principal duerme durante 10 segundos
        } catch (Exception e) {
            // Captura cualquier excepción, aunque no se maneja
        }//Fin try-catch

        // Detiene los tres hilos
        h1.pararHilo();
        h2.pararHilo();
        h3.pararHilo();

        // Imprime los contadores de cada hilo después de detenerlos
        System.out.println("h2 (Prioridad Maxima): " + h2.getContador());
        System.out.println("h1 (Prioridad Normal): " + h1.getContador());
        System.out.println("h3 (Prioridad Minima): " + h3.getContador());
    }//Fin main

}//Fin class
