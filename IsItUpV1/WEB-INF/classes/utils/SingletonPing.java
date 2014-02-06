package utils;

import java.util.Date;
import java.util.Timer;

public class SingletonPing {

	Timer timer;

	/** Constructeur privé */
	private SingletonPing() {
	}

	/** Instance unique non préinitialisée */
	private static SingletonPing INSTANCE = null;

	/** Point d'accès pour l'instance unique du singleton */
	public static synchronized SingletonPing getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SingletonPing();
		}
		return INSTANCE;
	}

	public static void start() {
		SingletonPing ping = getInstance();
		if (ping.timer == null) {
			ping.timer = new Timer();
			ping.timer.scheduleAtFixedRate(new utils.PingTask(), new Date(), 1);
		}
	}

	public static void stop() {
		SingletonPing ping = getInstance();
		if (ping.timer != null) {
			ping.timer.cancel();
			ping.timer = null;
		}
	}
}
