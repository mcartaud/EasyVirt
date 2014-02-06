package utils;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import dao.PropertyDAO;
import dao.WorkStationDAO;
import dao.WsRecordingDAO;
import dto.WorkStationDTO;

/**
 * Classe permettant de pinger les machines.
 */
public class PingTask extends TimerTask {

	protected int pingFrequency = 900000;
	protected final String pingFrequencyProperty = "ping_frequency";

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.TimerTask#run()
	 */
	public void run() {
		try {
			InetAddress inet;
			List<WorkStationDTO> listWorkStation = WorkStationDAO.retrieveAll();
			pingFrequency = PropertyDAO.retrievePropertyByName(
					pingFrequencyProperty).getValue() * 60000; // en ms

			// Emission d'une salve de pings
			for (int i = 0; i < listWorkStation.size(); i++) {
				inet = InetAddress.getByName(listWorkStation.get(i).getIp());

				if (inet.isReachable(4000)) {
					WsRecordingDAO.insertRecording(listWorkStation.get(i)
							.getId(), new Date(), true, 1);
				} else {
					WsRecordingDAO.insertRecording(listWorkStation.get(i)
							.getId(), new Date(), false, 2);
				}
			}

			// Mise en attente du thread avant la prochaine salve de pings
			Thread.sleep(pingFrequency);
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
}
