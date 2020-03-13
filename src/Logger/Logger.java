package Logger;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

public class Logger {
	@SuppressWarnings("deprecation")
	public static void loggear(LoggerInfo in, String nombre, int tamanio) {

		FileWriter fileW = null;
		PrintWriter printw = null;
		Timestamp time = new Timestamp(System.currentTimeMillis());
		try {
			fileW = new FileWriter("./dataCliente/logger.txt", true);
			printw = new PrintWriter(fileW, true);
			printw.println("Fecha: " + new Date(time.getTime()));
			printw.println("Hora: " + time.getHours() + ":" + time.getMinutes());
			printw.println("Archivo: " + nombre + " Tamaño: " + tamanio);
			String cadena = "";
			cadena += "Cliente: " + in.getCliente() + "\n";
			cadena += "Tiempo total de transferencia de archivo: " + in.getTransferencia() + " ms\n";
			cadena += "Entregado: " + true + "\n";
			cadena += "Paquetes enviados: " + in.getPaquetesEnviados() + "\n";
			cadena += "Paquetes recibidos: " + in.getPaquetesRecibidos() + "\n";
			cadena += "Bytes recibidos: " + in.getBytesRecibidos() + "\n";
			cadena += "Bytes transmitidos: " + in.getBytesTransmitidos() + "\n";
			printw.println(cadena);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fileW)
					fileW.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}
}