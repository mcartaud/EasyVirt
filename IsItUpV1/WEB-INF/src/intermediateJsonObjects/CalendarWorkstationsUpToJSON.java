package intermediateJsonObjects;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import dao.WorkStationDAO;
import dao.WsRecordingDAO;

public class CalendarWorkstationsUpToJSON {

	private String date;
	private int TotalComputers;
	private int NbComputerOn;
	private int totalComputerPreviousWeek;
	private int totalComputerOfTheWeek;
	private double hoursOutOfIntervalPreviousWeek;
	private double hoursOutOfIntervalofWeek;

	public CalendarWorkstationsUpToJSON() {
		super();
	}

	public CalendarWorkstationsUpToJSON getAnalyticsOfADay(Date day) {
		CalendarWorkstationsUpToJSON calendarWorkstationsUp = new CalendarWorkstationsUpToJSON();
		// 604800000 = une semaine en ms
		Date dateLastWeek = new Date(day.getTime() - 604800000);
		int numberWorkstationsUp = WsRecordingDAO.retrieveNumberWorkstationsUpPerDay(day);
		int numberWorkstationsUpLastWeek = WsRecordingDAO.retrieveNumberWorkstationsUpPerDay(dateLastWeek);
		double hoursOutWeek = WsRecordingDAO.retrieveHoursOutOfNormal(day);
		
		calendarWorkstationsUp.setDate(day.toString());
		calendarWorkstationsUp.setNbComputerOn(numberWorkstationsUp);
		calendarWorkstationsUp.setTotalComputerOfTheWeek(numberWorkstationsUp);
		calendarWorkstationsUp.setTotalComputerPreviousWeek(numberWorkstationsUpLastWeek);
		
		calendarWorkstationsUp.setHoursOutOfIntervalofWeek(hoursOutWeek);
		
		return calendarWorkstationsUp;
	}
	
	
	public CalendarWorkstationsUpToJSON getAnalyticsOfADayCalendar(Date day) {
		CalendarWorkstationsUpToJSON calendarWorkstationsUp = new CalendarWorkstationsUpToJSON();
		int numberWorkstationsUp = WsRecordingDAO.retrieveNumberWorkstationsUpPerDay(day);
		int totalNumberWorkstations = WorkStationDAO .retrieveNumberWorkstations();

		calendarWorkstationsUp.setNbComputerOn(numberWorkstationsUp);
		calendarWorkstationsUp.setTotalComputers(totalNumberWorkstations);
		calendarWorkstationsUp.setDate(day.toString());
		return calendarWorkstationsUp;
	}

	/**
	 * Return analytics of Workastations up in a period of the year
	 * 
	 * @param begin  format : 26-06-2008
	 * @param end format : 26-06-2008
	 */
	public static List<CalendarWorkstationsUpToJSON> getAnalyticsPeriodJson(String begin, String end) {
		List<CalendarWorkstationsUpToJSON> listComputers = new ArrayList<CalendarWorkstationsUpToJSON>();
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			java.util.Date dateStart = dateFormat.parse(begin);
			java.util.Date dateEnd = dateFormat.parse(end);

			GregorianCalendar calendar = new java.util.GregorianCalendar();

			for (java.util.Date date = dateStart; !date.equals(dateEnd);) {
				calendar.setTime(date);
				
				java.sql.Date dateSQL = new Date(date.getTime());
				
				calendar.add(Calendar.DATE, 1);
				date = calendar.getTime();
				
				CalendarWorkstationsUpToJSON calendarWorkstationsUp = new CalendarWorkstationsUpToJSON();
				listComputers.add(calendarWorkstationsUp.getAnalyticsOfADay(dateSQL));
			}
		} catch (ParseException e) {
			System.out.println("Issue in the date ");
		}
		return listComputers;
	}

	
	/**
	 * Return analytics of Workastations up in a period of the year for the calendar view
	 * 
	 * @param begin  format : 26-06-2008
	 * @param end format : 26-06-2008
	 */
	public static List<CalendarWorkstationsUpToJSON> getAnalyticsPeriodJsonCalendar(String begin, String end) {
		List<CalendarWorkstationsUpToJSON> listComputers = new ArrayList<CalendarWorkstationsUpToJSON>();
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			java.util.Date dateStart = dateFormat.parse(begin);
			java.util.Date dateEnd = dateFormat.parse(end);

			GregorianCalendar calendar = new java.util.GregorianCalendar();

			for (java.util.Date date = dateStart; !date.equals(dateEnd);) {
				calendar.setTime(date);
				
				java.sql.Date dateSQL = new Date(date.getTime());
				
				calendar.add(Calendar.DATE, 1);
				date = calendar.getTime();
				
				CalendarWorkstationsUpToJSON calendarWorkstationsUp = new CalendarWorkstationsUpToJSON();
				listComputers.add(calendarWorkstationsUp.getAnalyticsOfADayCalendar(dateSQL));
			}
		} catch (ParseException e) {
			System.out.println("Issue in the date ");
		}
		return listComputers;
	}
	
	public static List<CalendarWorkstationsUpToJSON> getAnalyticsYearJson() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return getAnalyticsPeriodJsonCalendar("01-01-" + year, "31-12-" + year);
	}

	public static List<CalendarWorkstationsUpToJSON> getAnalyticsWeekN() {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTimeInMillis(System.currentTimeMillis());
		while (calendar.get(Calendar.DAY_OF_WEEK) > calendar.getFirstDayOfWeek()) {
			calendar.add(Calendar.DATE, -1); // Substract 1 day until first day
												// of week.
		}
		int firstDay = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);

		
		
		return getAnalyticsPeriodJson(firstDay + "-" + month + "-" + year,
				firstDay + 6 + "-" + month + "-" + year);
	}

	public static int retrieveComputerUpToday() {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Date utilDate = cal.getTime();
		java.sql.Date sqlDate = new Date(utilDate.getTime());

		CalendarWorkstationsUpToJSON workstationUp = new CalendarWorkstationsUpToJSON();
		return workstationUp.getAnalyticsOfADay(sqlDate).NbComputerOn;
	}

	public static int retrieveTotalComputers() {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		java.util.Date utilDate = cal.getTime();
		java.sql.Date sqlDate = new Date(utilDate.getTime());

		CalendarWorkstationsUpToJSON workstationUp = new CalendarWorkstationsUpToJSON();
		return workstationUp.getAnalyticsOfADay(sqlDate).TotalComputers;
	}

	/**
	 * Setter pour date.
	 * 
	 * @return Le date.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Setter pour date.
	 * 
	 * @param date
	 *            Le date à setter.
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Setter pour TotalComputers.
	 * 
	 * @return Le totalComputers.
	 */
	public int getTotalComputers() {
		return TotalComputers;
	}

	/**
	 * Setter pour TotalComputers.
	 * 
	 * @param totalComputers
	 *            Le totalComputers à setter.
	 */
	public void setTotalComputers(int totalComputers) {
		TotalComputers = totalComputers;
	}

	/**
	 * Setter pour NbComputerOn.
	 * 
	 * @return Le nbComputerOn.
	 */
	public int getNbComputerOn() {
		return NbComputerOn;
	}

	/**
	 * Setter pour NbComputerOn.
	 * 
	 * @param nbComputerOn
	 *            Le nbComputerOn à setter.
	 */
	public void setNbComputerOn(int nbComputerOn) {
		NbComputerOn = nbComputerOn;
	}

	/**
	 * Setter pour totalComputerPreviousWeek.
	 * 
	 * @return Le totalComputerPreviousWeek.
	 */
	public int getTotalComputerPreviousWeek() {
		return totalComputerPreviousWeek;
	}

	/**
	 * Setter pour totalComputerPreviousWeek.
	 * 
	 * @param totalComputerPreviousWeek
	 *            Le totalComputerPreviousWeek à setter.
	 */
	public void setTotalComputerPreviousWeek(int totalComputerPreviousWeek) {
		this.totalComputerPreviousWeek = totalComputerPreviousWeek;
	}

	/**
	 * Setter pour totalComputerOfTheWeek.
	 * 
	 * @return Le totalComputerOfTheWeek.
	 */
	public int getTotalComputerOfTheWeek() {
		return totalComputerOfTheWeek;
	}

	/**
	 * Setter pour totalComputerOfTheWeek.
	 * 
	 * @param totalComputerOfTheWeek
	 *            Le totalComputerOfTheWeek à setter.
	 */
	public void setTotalComputerOfTheWeek(int totalComputerOfTheWeek) {
		this.totalComputerOfTheWeek = totalComputerOfTheWeek;
	}

	public double getHoursOutOfIntervalofWeek() {
		return hoursOutOfIntervalofWeek;
	}

	public void setHoursOutOfIntervalofWeek(double hoursOutOfIntervalofWeek) {
		this.hoursOutOfIntervalofWeek = hoursOutOfIntervalofWeek;
	}

	public double getHoursOutOfIntervalPreviousWeek() {
		return hoursOutOfIntervalPreviousWeek;
	}

	public void setHoursOutOfIntervalPreviousWeek(double hoursOutOfIntervalPreviousWeek) {
		this.hoursOutOfIntervalPreviousWeek = hoursOutOfIntervalPreviousWeek;
	}

}
