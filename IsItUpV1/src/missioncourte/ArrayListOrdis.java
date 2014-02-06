package missioncourte;
import java.util.ArrayList;

public class ArrayListOrdis{
	ArrayList<Ordinateur> ordis;

	public ArrayListOrdis(){
		ordis = new ArrayList<Ordinateur>();
	}

	public void ajout(String IP, String user, String pwd){
		ordis.add(new Ordinateur(IP, user, pwd));
	}
	
		public Ordinateur get(int index){
		return ordis.get(index);
	}
	
	public int size(){
		return ordis.size();
	}
}