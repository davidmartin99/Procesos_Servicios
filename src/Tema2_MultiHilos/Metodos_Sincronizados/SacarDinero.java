package Tema2_MultiHilos.Metodos_Sincronizados;

// Clase que representa un hilo para retirar dinero de una cuenta
class SacarDinero extends Thread {
    private Cuenta c; // Atributo que almacena una referencia a la cuenta

    // Constructor que inicializa el hilo con un nombre y una cuenta
    public SacarDinero(String n, Cuenta c) {
        super(n); // Llama al constructor de la clase Thread con el nombre del hilo
        this.c = c; // Asigna la cuenta proporcionada al atributo c
    }

    // Método que se ejecuta al iniciar el hilo
    public void run() {
        // Bucle que se ejecuta 4 veces para intentar retirar dinero
        for (int x = 1; x <=4; x++) {
            // Intenta retirar 10 unidades de dinero de la cuenta y muestra el nombre del hilo
            c.retirarDinero(10, getName());
        }
    }
}
