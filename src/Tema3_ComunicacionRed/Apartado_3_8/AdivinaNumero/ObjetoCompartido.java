package Tema3_ComunicacionRed.Apartado_3_8.AdivinaNumero;

public class ObjetoCompartido {
	private int numero; // n�mero a adivinar
	private boolean acabo; // true cuando se haya terminado el juego
	private int ganador; // jugador ganador

	public ObjetoCompartido(int numero) {
		this.numero = numero;// numero A ADIVINAR
		this.acabo = false;
	}
	public boolean seAcabo() {
		return acabo;
	}
	public int getGanador() {
		return ganador;
	}
	public synchronized String nuevaJugada(int jugador, int suNumero) {
		String cad = "";
		if (!seAcabo()) { // si el juego ha terminado no se comprueba nada
			if (suNumero > numero) { // demasiado grande
				cad = "Numero demasiado grande";
			}
			if (suNumero < numero) { // demasiado peque
				cad = "Numero demasiado bajo";
			}
			if (suNumero == numero) { // ha acertado
				cad = "Jugador " + jugador + " gana, adivin� el n�mero: " + numero;
				acabo = true;
				ganador = jugador;
			}
		} else
			cad = "Jugador " + ganador + " adivin� el n�mero: " + numero;
		return cad;
	}// nuevaJugada
}// ObjetoCompartido..
