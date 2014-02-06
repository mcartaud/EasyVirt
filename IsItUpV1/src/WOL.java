//Code d'envoi du magic packet en connaissant l'adresse IP et l'adresse MAC de la cible.

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class WOL {
	public static final int PORT = 9;    

	public static void main(String[] args) {

	    String macStr = "00:1A:A0:0E:0D:20"; // XP
//		String macStr = "00:1A:A0:0E:OC:2C"; // 7
//		String macStr = "14:FE:B5:C6:0C:79"; // dam dam
//	    String ipStr = "192.168.1.255"; // Free
//	    String ipStr = "172.21.5.255"; // EasyVirt
	    String ipStr = "192.168.1.255"; //Local
	    try {
	    	
	        byte[] macBytes = getMacBytes(macStr);
	        byte[] bytes = new byte[6 + 16 * macBytes.length];
	        
	        //Fabrication du paquet
	        for (int i = 0; i < 6; i++) {
	            bytes[i] = (byte) 0xff;
	        }
	        for (int i = 6; i < bytes.length; i += macBytes.length) {
	            System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
	        }
	        //
	        
	        //Envoi du paquet
	        InetAddress address = InetAddress.getByName(ipStr);
	        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, PORT);
	        DatagramSocket socket = new DatagramSocket();
	        socket.send(packet);
	        socket.close();
	        //

	        System.out.println("Wake-on-LAN packet sent.");
	    }
	    catch (Exception e) {
	        System.out.println("Failed to send Wake-on-LAN packet: + e");
	        System.exit(1);
	    }

	}
	
	// Transforme l'adresse MAC en octets.
	private static byte[] getMacBytes(String macStr) throws IllegalArgumentException {
	    byte[] bytes = new byte[6];
	    String[] hex = macStr.split(":");
	    if (hex.length != 6) {
	        throw new IllegalArgumentException("Invalid MAC address.");
	    }
	    try {
	        for (int i = 0; i < 6; i++) {
	            bytes[i] = (byte) Integer.parseInt(hex[i], 16);
	        }
	    }
	    catch (NumberFormatException e) {
	        throw new IllegalArgumentException("Invalid hex digit in MAC address.");
	    }
	    return bytes;
	}
}
