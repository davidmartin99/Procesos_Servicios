package Tema4_ServiciosEnRed.Examen.ExamenR4;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Scanner;

public class ClienteFTP_SMTP {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException, InvalidKeyException, InvalidKeySpecException, IOException {
        Scanner scanner = new Scanner(System.in);

        Date[] fecha;
        fecha = new Date[100];



        String servidorFTP = "localhost";
        int puertoFTP = 21;
        String usuarioFTP = "usuario1";
        String contrasenaFTP = "usuario1";

        FTPClient clienteFTP = new FTPClient();

        System.out.println("Iniciando aplicación...");
        String archivo = "LOG.txt";
        try {
            // Se intenta conectar con el servidor FTP
            System.out.println("Conectándose a " + servidorFTP);
            clienteFTP.connect(servidorFTP);

            System.out.println("Guardando fecha... ");
            for (int i = 0; i < 1; i++) {
                fecha[i] = new Date( System.currentTimeMillis());
            }

            // Se realiza el inicio de sesión con usuario y contraseña
            boolean login = clienteFTP.login(usuarioFTP, contrasenaFTP);

            // Se configura el modo de transferencia de archivos en binario (para evitar corrupción de archivos)
            clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);

            // Directorio donde se subirá el archivo en el servidor FTP
            String direc = "/usuario1";

            // Se activa el modo pasivo para la conexión (necesario en muchos servidores)
            clienteFTP.enterLocalPassiveMode();

            if (login) { // Si el login es exitoso
                System.out.println("Login correcto");

                // Se intenta cambiar al directorio de trabajo en el servidor FTP
                if (!clienteFTP.changeWorkingDirectory(direc)) {
                    // Si el directorio no existe, se intenta crearlo
                    String directorio = "usuario1";

                    if (clienteFTP.makeDirectory(directorio)) {
                        System.out.println("Directorio : " + directorio + " creado ...");
                        // Se cambia al nuevo directorio creado
                        clienteFTP.changeWorkingDirectory(directorio);
                    } else {
                        System.out.println("No se ha podido crear el Directorio");
                        System.exit(0); // Sale del programa si falla la creación del directorio
                    }
                }

                // Muestra el directorio actual en el servidor FTP
                System.out.println("Directorio actual: " + clienteFTP.printWorkingDirectory());

                // Ruta del archivo que se desea subir desde el sistema local
                archivo = "C:\\Users\\aludam2\\IdeaProjects\\Procesos_Servicios\\src\\Tema4_ServiciosEnRed\\Examen\\ExamenR4\\LOG.txt";

                // Se abre el archivo para leerlo en un flujo de entrada
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(archivo));

                // Se intenta subir el archivo al servidor FTP con el mismo nombre
                if (clienteFTP.storeFile("LOG.txt", in))
                    System.out.println("Subido correctamente... ");
                else
                    System.out.println("No se ha podido subir el fichero... ");

                // Se cierra el flujo de entrada
                in.close();

                // Se cierra sesión en el servidor FTP
                clienteFTP.logout();

                // Se desconecta del servidor FTP
                clienteFTP.disconnect();
            }

        } catch (IOException ioe) {
            // Manejo de excepciones en caso de error
            ioe.printStackTrace();
        }



        /**
         * Enviamos el correo
         */
        //Enviar SMTP
        AuthenticatingSMTPClient clienteSMTP = new AuthenticatingSMTPClient();

        String servidorSMTP = "localhost";
        int puertoSMTP = 25;
        String remitente = "postmaster@localhost";


        System.out.print("Enviando mail... ");
        String destinatario = "examen2dam@gmail.com";
        String asunto = "Examen RA4";
        String mensajeTexto = "Enviando documentos... "+fecha;

        try {
            clienteSMTP.connect(servidorSMTP, puertoSMTP);
            System.out.println("Conectado al servidor SMTP: " + clienteSMTP.getReplyString());

            clienteSMTP.ehlo("localhost");
            System.out.println("Respuesta EHLO: " + clienteSMTP.getReplyString());

            SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destinatario, asunto);
            clienteSMTP.setSender(remitente);
            clienteSMTP.addRecipient(destinatario);

            Writer writer = clienteSMTP.sendMessageData();
            if (writer != null) {
                writer.write(cabecera.toString());
                writer.write("\n" + mensajeTexto);
                if (archivo != null) {
                    writer.write("\n\nArchivo adjunto: " + archivo);
                }
                writer.close();
                if (clienteSMTP.completePendingCommand()) {
                    System.out.println("Correo enviado con éxito.");
                } else {
                    System.err.println("Error al enviar el correo.");
                }
            }

            clienteSMTP.logout();
            clienteSMTP.disconnect();
        } catch (IOException e) {
            System.err.println("Error al conectar al servidor SMTP.");
            e.printStackTrace();
        }

        System.out.println("fechas: "+fecha.length + fecha);

    }


}

       
