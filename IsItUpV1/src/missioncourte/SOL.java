package missioncourte;
//CODE du programme tournant en permanence sur l'ordinateur cible permettant à un ordinateur de le réveiller avec un autre programme non fourni.


import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class SOL{
	public int State=0;
	private int Port = 9;
	public boolean ComboBoxIsChecked;
    String macStr = "00:1A:A0:0E:0D:20"; // XP
    String ipStr = "192.168.1.255"; // Free

    
    public static void main(String[]args){
    	Work();
    }
    public static void Work(){
    	while(true){
    		try{
    			DatagramPacket packet = new DatagramPacket(null, 102);
    			DatagramSocket socket = new DatagramSocket();
    			socket.receive(packet);
    			socket.close();
    			byte[] data = packet.getData();
    			String print = "";
    			for(int i=0;i<data.length;i++){
    				print+="-"+data[i];
    			}
    			System.out.println(print);
    		}
    		catch(Exception e){
    			System.out.println("Failed to send Wake-on-LAN packet: + e");
    		}
    	}
    }
	// Do some actions on the assumption of comboBox selected index.
	private void DoAction(byte action){
		if (ComboBoxIsChecked){
			State = action;
		}
		if (State == 0){
			try{
				Runtime.getRuntime().exec("shutdown -m \\toshiba_cwb -s -t99");
			}catch(Exception ex){
				System.out.println("Fail to ShutDown"+ex);
			}
		}
	}

	private static byte[] getAntiMagicPacket(String macStr) throws IllegalArgumentException{
		byte[] macBytes = getAntiMacBytes(macStr);
        byte[] bytes = new byte[6 + 16 * macBytes.length];
        for (int i = 0; i < 6; i++) {
            bytes[i] = (byte) 0xff;
        }
        for (int i = 6; i < bytes.length; i += macBytes.length) {
            System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
        }
        return bytes;
	}
	
	private static byte[] getAntiMacBytes(String macStr) throws IllegalArgumentException {
	    byte[] bytes = new byte[6];
	    String[] hex = macStr.split(":");
	    if (hex.length != 6) {
	        throw new IllegalArgumentException("Invalid MAC address.");
	    }
	    try {
	        for (int i = 0; i < 6; i++) {
	            bytes[5-i] = (byte) Integer.parseInt(hex[i], 16);
	        }
	    }
	    catch (NumberFormatException e) {
	        throw new IllegalArgumentException("Invalid hex digit in MAC address.");
	    }
	    return bytes;
	}
}