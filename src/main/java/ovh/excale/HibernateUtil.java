package ovh.excale;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.Nullable;
import ovh.excale.discord.spaciaman.SpaciaMan;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static @Nullable SessionFactory buildSessionFactory() {
		try {

			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure()
					.buildSessionFactory();

		} catch(Exception e) {
			SpaciaMan.logger.error("Initial SessionFactory creation failed", e);
		}

		return null;
	}

	public static @Nullable SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static boolean isConnected() {
		return sessionFactory != null && sessionFactory.isOpen();
	}

	public static void shutdown() {
		if(getSessionFactory() != null)
			getSessionFactory().close();
	}

}