//Programma permettant d'automatiser le SOL via console (net use + shutdown)

public class SOLconsole {
	public static void main (String [] args){
		ArrayListOrdis array = new ArrayListOrdis(); 
		array.ajout("192.168.1.24","Damien","damien");

		for(int i=0;i<array.size();i++){
			try{
				//Process p = Runtime.getRuntime().exec("cmd.exe /k copy C:\\Test\\Test1\\Bla.txt C:\\Test\\Test2");
				//java.io.BufferedReader out = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
				
				String commandes[] = {"cmd.exe /c net use \\\\"+array.get(i).getIP()+" /user:"+array.get(i).getUser()+" "+array.get(i).getPwd(),
						"cmd.exe /k shutdown -s -h -t 1 -m \\\\"+array.get(i).getIP()};
				System.out.println(commandes[0]);
				System.out.println(commandes[1]);
				Runtime.getRuntime().exec(commandes[0]);
				Runtime.getRuntime().exec(commandes[1]);
			}catch(Exception ex){
				System.out.println("Fail to ShutDown"+ex);
			}
			
		}
	}
}
