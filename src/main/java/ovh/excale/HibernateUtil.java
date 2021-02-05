package ovh.excale;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {

	private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
	private static SessionFactory sessionFactory = buildSessionFactory();

	private static @Nullable SessionFactory buildSessionFactory() {
		try {

			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure()
					.buildSessionFactory();

		} catch(Exception e) {
			logger.error("Initial SessionFactory creation failed", e);
		}

		return null;
	}

	public static @Nullable SessionFactory getSessionFactory() {
		if(sessionFactory == null)
			sessionFactory = buildSessionFactory();
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