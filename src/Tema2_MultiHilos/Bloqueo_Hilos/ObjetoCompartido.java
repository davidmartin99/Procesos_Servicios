package Tema2_MultiHilos.Bloqueo_Hilos;

class ObjetoCompartido {
    public void pintaCadena(String s) {
        System.out.print(s);
    }
} // ObjetoCompartido

class HiloCadena extends Thread {
    private ObjetoCompartido objeto; // Objeto compartido
    String cad; // Cadena a imprimir

    public HiloCadena(ObjetoCompartido c, String s) {
        this.objeto = c; // Asignar el objeto compartido
        this.cad = s; // Asignar la cadena
    }

    public void run() {
        synchronized (objeto) { // Bloquear el objeto compartido
            for (int j = 0; j < 10; j++) {
                objeto.pintaCadena(cad); // Imprimir la cadena
                objeto.notify(); // Avisar que se ha usado el objeto

                try {
                    objeto.wait(); // Esperar a que llegue un notify
                } catch (InterruptedException e) {
                    e.printStackTrace(); // Manejar la excepción si ocurre
                }
            } // for
            objeto.notify(); // Despertar a todos los wait sobre el objeto
        } // fin bloque synchronized

        System.out.print("\n" + cad + " finalizado"); // Indicar que el hilo ha terminado
    } // run

} // HiloCadena

