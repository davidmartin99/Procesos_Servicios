package Tema1;

public class VisualizarCadena {
    public static void main(String[] args) {
        // Comprobar si se recibió una cadena como argumento
        if (args.length < 1) {
            System.out.println("No se recibió ninguna cadena.");
            System.exit(1);
        }

        // Mostrar la cadena 5 veces
        for (int i = 0; i < 5; i++) {
            System.out.println(args[0]);
        }

        // Finalizar el programa con éxito
        System.exit(0);
    }
}
