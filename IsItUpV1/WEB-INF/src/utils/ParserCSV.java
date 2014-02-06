package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import dao.OperatingSystemDAO;
import dao.WorkStationDAO;
import dto.WorkStationDTO;

/**
 * Classe permettant de parser le fichier CSV contenant les machines d'un parc.
 */
public class ParserCSV {

	/**
	 * Parse un fichier CSV.
	 * 
	 * @param path
	 *            Le chemin vers le fichier CSV à parser.
	 */
	public static void parsingCSV(String path) {
		WorkStationDTO newWorksation = new WorkStationDTO();

		try {
			String csvFile = path;
			String delimiter = ";";
			String line = null;
			StringTokenizer strToken = null;
			BufferedReader bufferReader;
			int fieldID = 0;

			// Ouvrir le fichier CSV
			bufferReader = new BufferedReader(new FileReader(csvFile));

			// parcourir les lignes du fichier CSV
			while ((line = bufferReader.readLine()) != null) {

				newWorksation = new WorkStationDTO();

				// Parcourir les champs séparés par delimiter
				strToken = new StringTokenizer(line, delimiter);

				while (strToken.hasMoreTokens()) {
					if (fieldID == 0) {

						String ip = strToken.nextToken();

						checksIPInDB(ip);

						newWorksation.setIp(ip);
						System.out.println("***" + ip + "***");
						fieldID++;
					}
					if (fieldID == 1) {
						String nameSystem = strToken.nextToken();
						int osSystem = OperatingSystemDAO
								.retrieveOsIdByLabel(nameSystem);
						if (osSystem == 0) {
							OperatingSystemDAO
									.insertOperatingSystem(nameSystem);
							while (osSystem == 0) {
								osSystem = OperatingSystemDAO
										.retrieveOsIdByLabel(nameSystem);
							}

						}
						newWorksation.setId_os(osSystem);
						fieldID++;
					}
					if (fieldID == 2) {
						newWorksation.setLabel(strToken.nextToken());
						fieldID++;
					}
					if (fieldID == 3) {
						strToken.nextToken();
						Date date = new Date();
						newWorksation.setDate(date);
						fieldID = 0;
					}
				}
				WorkStationDAO.insert(newWorksation);
			}
			bufferReader.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Vérifie que l'adresse IP n'est pas déjà en base.
	 * 
	 * @param ip
	 *            L'adresse IP que l'on veut ajouter.
	 */
	private static void checksIPInDB(String ip) {
		List<WorkStationDTO> listWS = WorkStationDAO.retrieveAll();
		for (WorkStationDTO ws : listWS) {
			if (ws.getIp().equals(ip)) {
				WorkStationDAO.deleteWorkstationByIp(ws.getIp());
			}
		}
	}
}
