package Tema1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Ejemplo4 {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start");

        try {
            // Start the process
            Process p = pb.start();

            // Writing to the process (sending input)
            OutputStream os = p.getOutputStream();
            os.write("30-09-24".getBytes()); // Include a newline to simulate pressing Enter
            os.flush(); // Flush the output buffer

            // Reading from the process (getting output)
            InputStream is = p.getInputStream();
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c);
            }
            is.close();

            // Check for exit value (0 for success, 1 for error)
            int exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

