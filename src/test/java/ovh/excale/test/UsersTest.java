package ovh.excale.test;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import ovh.excale.HibernateUtil;
import ovh.excale.discord.spaciaman.models.UserModel;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsersTest {

	@Test
	void insert_user_test() {

		SessionFactory factory = HibernateUtil.getSessionFactory();
		assertNotNull(factory, "SessionFactory null");

		EntityManager em = factory.createEntityManager();

		CriteriaQuery<UserModel> criteria = em.getCriteriaBuilder()
				.createQuery(UserModel.class);
		criteria.from(UserModel.class);

		List<UserModel> users = em.createQuery(criteria)
				.getResultList();

		assertNotEquals(users.size(), 0);
		System.out.println("Size: " + users.size());

	}

}
