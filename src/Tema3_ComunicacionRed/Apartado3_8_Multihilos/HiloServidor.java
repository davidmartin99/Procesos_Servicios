package Tema3_ComunicacionRed.Apartado3_8_Multihilos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloServidor extends Thread {
    private BufferedReader fentrada;
    private PrintWriter fsalida;
    private Socket socket;

    // Constructor
    public HiloServidor(Socket s) throws IOException {
        this.socket = s;

        // Create input and output streams
        fsalida = new PrintWriter(socket.getOutputStream(), true);
        fentrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        String cadena = "";

        System.out.println("COMUNICANDO CON: " + socket.toString());

        // Task to perform with the client
        while (!"=".equals(cadena.trim())) {
            try {
                cadena = fentrada.readLine(); // Read input from client
                if (cadena != null) {
                    fsalida.println(cadena.trim().toUpperCase()); // Send uppercase response
                }
            } catch (IOException e) {
                e.printStackTrace();
                break; // Exit loop if an exception occurs
            }
        }

        System.out.println("FIN DE COMUNICACIÓN CON: " + socket.toString());

        // Close resources
        try {
            fsalida.close();
            fentrada.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

