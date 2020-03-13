package Logger;

public class LoggerInfo {
	/**
	 * CLiente al que se le transifere el archivo
	 */
	private String cliente;
	
	/**
	 * Indica si el archivo fue entregado 
	 */
	private boolean entregado;
	
	/**
	 * Tiempo de transferencia total del archivo 
	 */
	private String tiempoTransf;
	
	/**
	 * Paquetes que se enviarón
	 */
	private int paquetesE;
	
	/**
	 * Paquetes que se recibieron
	 */
	private int paquetesR;
	
	/**
	 * Bytes que se transmitieron
	 */
	private int bytesT;
	
	/**
	 * Bytes que se recibieron
	 */
	private int bytesR;

	/**
	 * Constructor de un bloque de información de una transferencia para un cliente
	 * @param cliente Cliente que realiza la conexión
	 * @param ttransferencia Tiempo total de transferencia del archivo 
	 * @param paquetesEnviados Paquetes enviados por el servidor
	 * @param paquetesRecibidos Paquetes recibidos por el cliente
	 * @param bytesTransmitidos Bytes transmitidos por el servidor
	 * @param bytesRecibidos Bytes recibidos por el cliente
	 */
	public LoggerInfo(String cliente, String ttransferencia, int paquetesEnviados, int paquetesRecibidos,
			int bytesTransmitidos, int bytesRecibidos) {
		this.cliente = cliente;
		this.tiempoTransf = ttransferencia;
		this.paquetesE = paquetesEnviados;
		this.paquetesR = paquetesRecibidos;
		this.bytesT = bytesTransmitidos;
		this.bytesR = bytesRecibidos;
	}

	public String getCliente() {
		return cliente;
	}

	public boolean isEntrega() {
		return entregado;
	}

	public String getTransferencia() {
		return tiempoTransf;
	}

	public int getPaquetesEnviados() {
		return paquetesE;
	}

	public int getPaquetesRecibidos() {
		return paquetesR;
	}

	public int getBytesTransmitidos() {
		return bytesT;
	}

	public int getBytesRecibidos() {
		return bytesR;
	}
}
