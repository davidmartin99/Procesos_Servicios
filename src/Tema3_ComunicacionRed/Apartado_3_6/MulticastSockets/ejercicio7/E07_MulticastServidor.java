package Tema3_ComunicacionRed.Apartado_3_6.MulticastSockets.ejercicio7;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class E07_MulticastServidor {
    private static final String MULTICAST_IP = "225.0.0.1";
    private static final int PUERTO = 6000;
    private static MulticastSocket socket;
    private static InetAddress grupo;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Servidor Multicast");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JTextField textField = new JTextField();
        JLabel label = new JLabel("Texto a enviar");
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(label, BorderLayout.NORTH);
        inputPanel.add(textField, BorderLayout.CENTER);

        JButton enviarButton = new JButton("Enviar");
        JButton salirButton = new JButton("Salir");

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createTitledBorder("Visualización de mensajes enviados"));
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        buttonPanel.add(enviarButton);
        buttonPanel.add(salirButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.EAST);

        frame.setVisible(true);

        try {
            socket = new MulticastSocket();
            grupo = InetAddress.getByName(MULTICAST_IP);

            enviarButton.addActionListener(e -> {
                String mensaje = textField.getText();
                if (!mensaje.isEmpty()) {
                    try {
                        byte[] buffer = mensaje.getBytes();
                        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, grupo, PUERTO);
                        socket.send(paquete);

                        textArea.append("Mensaje enviado: " + mensaje + "\n");
                        textField.setText("");
                    } catch (IOException ex) {
                        textArea.append("Error al enviar mensaje: " + ex.getMessage() + "\n");
                    }
                }
            });

            salirButton.addActionListener(e -> {
                try {
                    if (socket != null && !socket.isClosed()) {
                        socket.close();
                    }
                } catch (Exception ex) {
                    textArea.append("Error al cerrar el socket: " + ex.getMessage() + "\n");
                }
                frame.dispose();
                System.exit(0);
            });

        } catch (IOException ex) {
            textArea.append("Error al iniciar el servidor: " + ex.getMessage() + "\n");
        }
    }
}
