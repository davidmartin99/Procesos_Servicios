package Tema2_MultiHilos.Practica_Tema2;

public class CuentaVocales {
    private static final String TEXTO = "Holaa que tal estas? yo muy bien y tu? genial!";
    private static int contadorTotal = 0; // Variable compartida
    public static final Object lock = new Object(); // Objeto de bloqueo para sincronización

    public static void main(String[] args) {
        // Cada hilo creado contará una vocal
        Thread hiloA = new Thread(new ContadorVocal('a'));
        Thread hiloE = new Thread(new ContadorVocal('e'));
        Thread hiloI = new Thread(new ContadorVocal('i'));
        Thread hiloO = new Thread(new ContadorVocal('o'));
        Thread hiloU = new Thread(new ContadorVocal('u'));

        // Iniciamos los hilos
        hiloA.start();
        hiloE.start();
        hiloI.start();
        hiloO.start();
        hiloU.start();

        // Mostramos el texto en pantalla
        System.out.println(TEXTO);

        // Esperamos a que todos los hilos cuenten cada uno su vocal. Para ello usaremos join()
        try {
            hiloA.join();
            hiloE.join();
            hiloI.join();
            hiloO.join();
            hiloU.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }//Fin try-catch

        // Visualizamos en pantalla el resultado
        System.out.println("Hay un total de " + contadorTotal+ " vocales en el texto");
    }//Fin main

    // Clase interna para contar una vocal específica
    static class ContadorVocal implements Runnable {
        private char vocal;

        public ContadorVocal(char vocal) {
            this.vocal = vocal;
        }

        // El metodo run() que almacena el numero de cada vocal
        @Override
        public void run() {
            int cuentaVocal = 0;

            // Contar las ocurrencias de la vocal en el texto
            for (char c : TEXTO.toLowerCase().toCharArray()) {
                if (c == vocal) {
                    cuentaVocal++;
                }
            }

            // Utilizamos un bloque synchronized para actualizar la variable compartida contadorTotal sin riesgo de condiciones de carrera.
            synchronized (lock) {
                contadorTotal += cuentaVocal;
            }

            // Visualizamos tambien cuantas veces aparece cada vocal
            System.out.println("Vocal '" + vocal + "' contada: " + cuentaVocal);
        }//Fin run
    }//Fin ContadorVocal
}//Fin class

