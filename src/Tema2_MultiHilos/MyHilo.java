package Tema2_MultiHilos;

class MyHilo extends Thread {
    private SolicitaSuspender suspender = new SolicitaSuspender();
    private boolean hayTrabajoPorHacer = true; // Variable para controlar el trabajo

    // Método que suspende el hilo
    public void suspende() {
        suspender.set(true);
    }

    // Método que reanuda el hilo
    public void reanuda() {
        suspender.set(false);
    }

    // Método para detener el trabajo
    public void detenerTrabajo() {
        hayTrabajoPorHacer = false; // Cambia el estado para salir del bucle
        reanuda(); // Asegúrate de reanudar en caso de que esté suspendido
    }

    public void run() {
        try {
            while (hayTrabajoPorHacer) {
                suspender.esperandoParaReanudar(); // Comprobación
                // Aquí puedes agregar el trabajo que debe hacer el hilo
                System.out.println("El hilo está trabajando."); // Ejemplo de trabajo
            } // while
        } catch (InterruptedException exception) {
            exception.printStackTrace(); // Manejo de excepciones
        }
    } // run
} // MyHilo
