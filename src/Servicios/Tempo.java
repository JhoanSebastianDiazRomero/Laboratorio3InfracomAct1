package Servicios;

/**
 * Clase para medir el tiempo total de transmisión de una transferencia
 * @author hecto
 *
 */
public class Tempo {
	
	private long time;
	
	public Tempo() {
		time=0;
	}
	public void start() {
		this.time=System.currentTimeMillis();
	}
	public long stop() {
		long fin = System.currentTimeMillis();
		return (fin-time);
	}
}
