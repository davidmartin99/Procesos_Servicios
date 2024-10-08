package Tema1;

import java.util.ArrayList;
import java.util.List;

public class ProcesosCooperativos {
	private static boolean recursoDisponible = true;

    private static class Proceso {
        private int id;

        public Proceso(int id) {
            this.id = id;
        }

        public void ejecutar() {
            while (true) {
                // Simular trabajo
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Intentar adquirir el recurso
                if (recursoDisponible) {
                    synchronized (ProcesosCooperativos.class) {
                        recursoDisponible = false;
                        System.out.println("Proceso " + id + " adquirió el recurso");
                        // Usar el recurso
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Proceso " + id + " liberó el recurso");
                        recursoDisponible = true;
                    }
                } else {
                    System.out.println("Proceso " + id + " esperando recurso");
                }
            }
        }
    }

    public static void main(String[] args) {
        Proceso proceso1 = new Proceso(1);
        Proceso proceso2 = new Proceso(2);

        // Simular la ejecución de los procesos en un solo hilo
        while (true) {
            proceso1.ejecutar();
            proceso2.ejecutar();
        }
    }
}
