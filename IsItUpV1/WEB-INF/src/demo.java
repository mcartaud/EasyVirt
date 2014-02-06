import java.sql.Timestamp;
import java.util.Date;

import dao.WsRecordingDAO;


public class demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		long rangebegin = Timestamp.valueOf("2013-06-12 00:00:00").getTime();
		long rangeend = Timestamp.valueOf("2013-06-14 00:00:00").getTime();
		long diff = rangeend - rangebegin + 1;
		Timestamp rand = new Timestamp(rangebegin + (long)(Math.random() * diff));
			WsRecordingDAO.insertRecording(451, new Date(rand.getTime()), true, 1);

	}

}
