package Tema2_MultiHilos.Metodos_Sincronizados;

/**
 * Clase principal que simula la retirada de dinero de una cuenta
 * usando múltiples hilos.
 * pag.82
 */
public class CompartirInf3 {
    public static void main(String[] args) {
        // Se inicializa una cuenta con un saldo de 40
        Cuenta c = new Cuenta(40);

        // Se crean dos hilos para realizar retiros de dinero
        SacarDinero h1 = new SacarDinero("Ana", c); // Hilo para Ana
        SacarDinero h2 = new SacarDinero("Juan", c); // Hilo para Juan

        // Se inician los hilos
        h1.start(); // Inicia el hilo de Ana
        h2.start(); // Inicia el hilo de Juan
    }

}
