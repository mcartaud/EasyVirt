
public class Ordinateur {
	private String IP="";
	private String user="";
	private String pwd="";
	
	public Ordinateur(String IP, String user, String pwd){
		this.IP = IP;
		this.user=user;
		this.pwd=pwd;
	}

	public String getIP() {
		return IP;
	}

	public String getUser() {
		return user;
	}

	public String getPwd() {
		return pwd;
	}
}
