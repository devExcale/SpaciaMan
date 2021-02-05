package ovh.excale.test;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import ovh.excale.HibernateUtil;
import ovh.excale.discord.spaciaman.models.UserModel;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsersTest {

	private static final Logger logger = LoggerFactory.getLogger(UsersTest.class);

	@Test
	void get_users_test() {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		assertNotNull(factory, "SessionFactory null");

		EntityManager em = factory.createEntityManager();

		CriteriaQuery<UserModel> criteria = em.getCriteriaBuilder()
				.createQuery(UserModel.class);
		criteria.from(UserModel.class);

		List<UserModel> users = em.createQuery(criteria)
				.getResultList();

		assertNotEquals(users.size(), 0);
		logger.info(() -> "Size: " + users.size());

	}

}
