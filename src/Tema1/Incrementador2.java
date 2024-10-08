package Tema1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Incrementador2 {
    public static void main(String[] args) {
        try {
            File file = new File("src\\Tema1\\contador.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int contador = Integer.parseInt(reader.readLine());
            reader.close();

            contador++;

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(Integer.toString(contador));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
