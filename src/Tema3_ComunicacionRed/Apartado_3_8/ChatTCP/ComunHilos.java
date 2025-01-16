package Tema3_ComunicacionRed.Apartado_3_8.ChatTCP;

import java.net.Socket;

public class ComunHilos {
	int CONEXIONES; // NUM. CONEXIONES TOTALES, OCUPADAS EN EL ARRAY
	int ACTUALES; // NUM. CONEXIONES ACTUALES
	int MAXIMO; // NUM. CONEXIONES PERMITIDAS
	Socket socketsClientes[] = new Socket[MAXIMO];// SOCKETS CONECTADOS
	String mensajes; // MENSAJES DEL CHAT

	public ComunHilos(int maximo, int actuales, int conexiones, Socket[] socketsClientes) {
		MAXIMO = maximo;
		ACTUALES = actuales;
		CONEXIONES = conexiones;
		this.socketsClientes = socketsClientes;
		mensajes = "";
	}

	public ComunHilos() {
		super();
	}

	public int getMAXIMO() {
		return MAXIMO;
	}

	public void setMAXIMO(int maximo) {
		MAXIMO = maximo;
	}

	public int getCONEXIONES() {
		return CONEXIONES;
	}

	public synchronized void setCONEXIONES(int conexiones) {
		CONEXIONES = conexiones;
	}

	public String getMensajes() {
		return mensajes;
	}

	public synchronized void setMensajes(String mensajes) {
		this.mensajes = mensajes;
	}

	public int getACTUALES() {
		return ACTUALES;
	}

	public synchronized void setACTUALES(int actuales) {
		ACTUALES = actuales;
	}

	public synchronized void addTabla(Socket socket, int i) {
		socketsClientes[i] = socket;
	}

	public Socket getElementoTabla(int i) {
		return socketsClientes[i];
	}

}// ComunHilos
