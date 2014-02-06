package intermediateJsonObjects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dao.WorkStationDAO;

public class WorkstationToJSON {

	private String OS;
	private int number;

	public WorkstationToJSON() {
		super();
	}

	/**
	 * Retuns analysis of OS in JSON
	 * 
	 * @return
	 */
	public static List<WorkstationToJSON> retrieveNumberWorkstationByOSinJSON() {
		Map<String, Integer> map = WorkStationDAO
				.retrieveNumberWorkstationByOS();
		Set<String> cles = map.keySet();
		Iterator<String> it = cles.iterator();
		List<WorkstationToJSON> out = new ArrayList<WorkstationToJSON>();

		while (it.hasNext()) {
			WorkstationToJSON workstationToJSON = new WorkstationToJSON();
			String temp = it.next();
			workstationToJSON.setOS(temp);
			workstationToJSON.setNumber(map.get(temp));
			out.add(workstationToJSON);
		}
		return out;
	}

	/**
	 * Setter pour OS.
	 * 
	 * @return Le oS.
	 */
	public String getOS() {
		return OS;
	}

	/**
	 * Setter pour OS.
	 * 
	 * @param oS
	 *            Le oS à setter.
	 */
	public void setOS(String oS) {
		OS = oS;
	}

	/**
	 * Setter pour number.
	 * 
	 * @return Le number.
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Setter pour number.
	 * 
	 * @param number
	 *            Le number à setter.
	 */
	public void setNumber(int number) {
		this.number = number;
	}

}
