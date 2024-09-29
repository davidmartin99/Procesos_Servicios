package Tema1;

public class ArgumentosValidador {

    public static void main(String[] args) {
        int numArgs;
        // Si no se recibe ningún argumento
        if (args.length < 1) {
            System.out.println(args[0]);
            System.exit(1);
        }

        // Si el argumento no es un número
        if (!esNumeroEntero(args[0])) {
            System.out.println("Error: El argumento no es un número, es una cadena.");
            System.exit(2);
        }

        // Convertimos el argumento a entero
        int numero = Integer.parseInt(args[0]);

        // Si el número es menor que 0
        if (numero < 0) {
            System.out.println("Error: El número es menor que 0.");
            System.exit(3);
        }

        // Si es un número válido y mayor o igual que 0
        System.out.println("Éxito: El número es válido y mayor o igual que 0.");
        System.exit(0);
    }

    // Función que comprueba si una cadena es un número entero
    public static boolean esNumeroEntero(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (i == 0 && str.charAt(i) == '-') {
                // Permite el signo negativo solo en la primera posición
                if (str.length() == 1) {
                    return false;  // Solo un signo no es válido
                } else {
                    continue;
                }
            }
            // Comprueba si cada carácter es un dígito
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }//Fin main

}//Fin class
