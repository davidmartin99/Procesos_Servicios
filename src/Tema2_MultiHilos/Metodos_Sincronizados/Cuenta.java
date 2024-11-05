package Tema2_MultiHilos.Metodos_Sincronizados;

class Cuenta {
    private int saldo; // Atributo que almacena el saldo de la cuenta

    // Constructor que inicializa el saldo de la cuenta
    Cuenta(int s) {
        saldo = s; // Se inicializa con el saldo pasado como argumento
    }

    // Método para obtener el saldo actual de la cuenta
    int getSaldo() {
        return saldo; // Devuelve el saldo actual
    }

    // Método para restar una cantidad del saldo
    synchronized void restar(int cantidad) {
        saldo = saldo - cantidad; // Resta la cantidad del saldo
    }

    // Método para retirar dinero de la cuenta
    synchronized void retirarDinero(int cant, String nom) {
        // Verifica si hay suficiente saldo para retirar
        if (getSaldo() >= cant) {
            // Muestra un mensaje indicando que se va a retirar dinero
            System.out.println(nom + ": SE VA A RETIRAR SALDO (ACTUAL ES: " + getSaldo() + ")");
            try {
                Thread.sleep(500); // Simula un retraso en la operación para imitar un tiempo de procesamiento
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); // Si ocurre una interrupción, se restablece el estado de interrupción
            }
            restar(cant); // Resta la cantidad solicitada del saldo
            // Muestra un mensaje con la cantidad retirada y el nuevo saldo
            System.out.println("\t" + nom + " retira => " + cant + " ACTUAL (" + getSaldo() + ")");
        } else {
            // Si no hay suficiente saldo, muestra un mensaje de error
            System.out.println(nom + " No puede retirar dinero, NO HAY SALDO (" + getSaldo() + ")");
        }
        // Verifica si el saldo es negativo y muestra un mensaje
        if (getSaldo() < 0) {
            System.out.println("SALDO NEGATIVO => " + getSaldo());
        }
    }//Fin retirarDinero

}//Fin class
