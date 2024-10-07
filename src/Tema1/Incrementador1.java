package Tema1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Incrementador1 {
    public static void main(String[] args) {
        try {
            File file = new File("src\\Tema1\\contador.txt");

            // Si el archivo no existe, crear y escribir 0
            if (!file.exists()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("0");
                writer.close();
            }

            // Leer el valor del contador
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int contador = Integer.parseInt(reader.readLine());
            reader.close();

            // Incrementar el contador
            contador++;

            // Escribir el nuevo valor en el archivo
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(Integer.toString(contador));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
