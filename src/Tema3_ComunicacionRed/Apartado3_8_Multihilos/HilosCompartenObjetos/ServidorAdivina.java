package Tema3_ComunicacionRed.Apartado3_8_Multihilos.HilosCompartenObjetos;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorAdivina {
    private static final int PUERTO = 12345;
    private static ObjetoCompartido objeto = new ObjetoCompartido();

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor en espera de conexiones...");

            while (true) {
                Socket socket = serverSocket.accept();  // Aceptar conexión de un cliente
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                // Crear un nuevo hilo para manejar la conexión de un jugador
                HiloServidorAdivina hilo = new HiloServidorAdivina(socket, objeto);
                hilo.start();
            }

        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                System.out.println("Error cerrando el servidor: " + e.getMessage());
            }
        }
    }
}

class ObjetoCompartido {
    private int numeroAdivinar;
    private boolean seAcabo = false;
    private int ganador = -1;
    private HashMap<Integer, Integer> jugadores = new HashMap<>();

    public ObjetoCompartido() {
        Random rand = new Random();
        this.numeroAdivinar = rand.nextInt(25) + 1;
    }

    public synchronized int nuevoJugador() {
        return jugadores.size() + 1;  // Asignar un nuevo ID al jugador
    }

    public synchronized void nuevaJugada(int id, int numero) {
        jugadores.put(id, numero);  // Registrar el número jugado por el jugador
        if (numero == numeroAdivinar && !seAcabo) {
            seAcabo = true;
            ganador = id;
        }
    }

    public synchronized boolean seAcabo() {
        return seAcabo;
    }

    public synchronized int getGanador() {
        return ganador;
    }
}

class HiloServidorAdivina extends Thread {
    private Socket socket;
    private ObjectInputStream fentrada;
    private ObjectOutputStream fsalida;
    private ObjetoCompartido objeto;
    private int identificador;
    private int intentos = 0;

    public HiloServidorAdivina(Socket socket, ObjetoCompartido objeto) {
        this.socket = socket;
        this.objeto = objeto;
        this.identificador = objeto.nuevoJugador();  // Obtener el identificador del nuevo jugador
        try {
            fsalida = new ObjectOutputStream(socket.getOutputStream());
            fentrada = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("ERROR DE E/S en HiloServidor: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("=>Cliente conectado: " + identificador);
            Datos datos = new Datos("Adivina un número entre 1 y 25", intentos, identificador);

            // Enviar el mensaje inicial al jugador
            fsalida.writeObject(datos);

            while (!objeto.seAcabo() && intentos < 5) {
                // Leer el número enviado por el cliente
                Datos d = (Datos) fentrada.readObject();
                int numecli = Integer.parseInt(d.getCadena());
                intentos++;
                datos.setIntentos(intentos);

                // Comprobar el número y actualizar el estado del juego
                objeto.nuevaJugada(identificador, numecli);
                datos.setCadena("Intento " + intentos + ": " + numecli);

                // Si el juego ha terminado, notificar al cliente
                if (objeto.seAcabo()) {
                    datos.setJuega(false);
                    if (identificador == objeto.getGanador()) {
                        datos.setGana(true);
                    }
                }

                // Enviar el resultado al jugador
                fsalida.reset();
                fsalida.writeObject(datos);

                if (objeto.seAcabo()) {
                    break;
                }
            }

            // Finalizar el juego
            System.out.println("El juego ha terminado para el jugador: " + identificador);
            fsalida.close();
            fentrada.close();
            socket.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en el Hilo del servidor: " + e.getMessage());
        }
    }
}

class Datos implements Serializable {
    private String cadena;
    private int intentos;
    private int identificador;
    private boolean juega;
    private boolean gana;

    public Datos(String cadena, int intentos, int identificador) {
        this.cadena = cadena;
        this.intentos = intentos;
        this.identificador = identificador;
        this.juega = true;
        this.gana = false;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setJuega(boolean juega) {
        this.juega = juega;
    }

    public boolean isJuega() {
        return juega;
    }

    public void setGana(boolean gana) {
        this.gana = gana;
    }

    public boolean isGana() {
        return gana;
    }
}
