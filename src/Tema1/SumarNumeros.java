package Tema1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SumarNumeros {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // Solicitar los dos números al usuario
            System.out.println("Introduce el primer número:");
            String num1 = reader.readLine();  // Leer primer número

            System.out.println("Introduce el segundo número:");  // Mostrar mensaje para el segundo número
            String num2 = reader.readLine();  // Leer segundo número

            // Intentar convertir las entradas a números
            try {
                double number1 = Double.parseDouble(num1);
                double number2 = Double.parseDouble(num2);

                // Calcular la suma y mostrar el resultado
                double suma = number1 + number2;
                System.out.println("La suma de los dos números es: " + suma);

            } catch (NumberFormatException e) {
                System.out.println("Error: Uno o ambos valores no son números válidos.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
