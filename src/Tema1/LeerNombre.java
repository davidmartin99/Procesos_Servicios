package Tema1;

public class LeerNombre {

    public static void main(String[] args) {
        // Si hay argumentos, mostramos el nombre y terminamos con éxito (0)
        if(args.length > 0) {
            System.out.println(args[0]);
            System.exit(0); // El programa termina con éxito
        } else {
            System.out.println("No se ha introducido ningún nombre");
            System.exit(1); // Terminamos con error si no hay nombre
        }
    }

}//Fin class
