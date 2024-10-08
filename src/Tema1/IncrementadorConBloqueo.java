package Tema1;

import java.io.*;
import java.nio.channels.*;

public class IncrementadorConBloqueo {
    public static void main(String[] args) {
        File file = new File("contador.txt");
        File lockFile = new File("contador.lock");

        try (RandomAccessFile raf = new RandomAccessFile(file, "rw");
             FileChannel channel = raf.getChannel();
             FileLock lock = channel.lock()) {

            // Leer el valor actual del contador
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
