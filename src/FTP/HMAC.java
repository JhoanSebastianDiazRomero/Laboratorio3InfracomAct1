package FTP;
import java.security.MessageDigest;

//SHA256
public class HMAC {
	private static final String ALGORITMO = "SHA-256";
	
	public static byte[] getDigest(byte[] buffer) {
		try {
			MessageDigest digest = MessageDigest.getInstance(ALGORITMO);
			digest.update(buffer);
			return digest.digest();
		} catch(Exception e){
			return null;
		}
	}
	
	public static void imprimirHexa(byte[] byteArray){
		String out = "";
		for(int i = 0; i< byteArray.length; i++){
			if((byteArray[i] & 0xff) <= 0xf){
				out += "0";
			}
			out += Integer.toHexString(byteArray[i] & 0xff).toLowerCase();
		}
		System.out.println(out);
	}
	
	/** Ejemplo
	public static void main(String[] args) {
		byte[] mensaje = "Hola1234".getBytes();
		byte[] digest = getDigest(mensaje);
		imprimirHexa(digest);
	}
	*/
}
