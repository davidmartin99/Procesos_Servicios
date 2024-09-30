package Tema1;

public class VisualizarCadena {
    public static void main(String[] args) {
        // Check if a string was received as an argument
        if (args.length < 1) {
            System.out.println("No se recibió ninguna cadena.");
            System.exit(1);
        }

        // Show the string 5 times
        for (int i = 0; i < 5; i++) {
            System.out.println(args[0]);
        }

        // Finish the program successfully
        System.exit(0);
    }
}
