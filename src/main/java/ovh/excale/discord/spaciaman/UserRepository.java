package ovh.excale.discord.spaciaman;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ovh.excale.HibernateUtil;
import ovh.excale.discord.spaciaman.models.KickMessage;
import ovh.excale.discord.spaciaman.models.UserModel;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

public class UserRepository implements AutoCloseable {

	private final Session session;
	private final Transaction tx;

	public UserRepository() throws IllegalStateException {

		SessionFactory factory = HibernateUtil.getSessionFactory();

		if(factory == null)
			throw new IllegalStateException("Cannot connect to datasource");

		session = factory.openSession();
		tx = session.getTransaction();
		tx.begin();
	}

	public Optional<UserModel> getUser(long id) {
		return Optional.ofNullable(session.find(UserModel.class, id));
	}

	public List<UserModel> getAllUsers() {

		CriteriaQuery<UserModel> criteria = session.getCriteriaBuilder()
				.createQuery(UserModel.class);
		criteria.from(UserModel.class);

		return session.createQuery(criteria)
				.getResultList();
	}

	public void saveUser(UserModel user) {
		session.saveOrUpdate(user);
	}

	public void deleteUser(UserModel user) {
		session.delete(user);
	}

	public Optional<KickMessage> addMessage(long userSnowflake, String message) {

//		UserModel user = session.find(UserModel.class, userSnowflake);

		return Optional.empty();
	}

	@Override
	public void close() throws HibernateException {
		tx.commit();
		session.close();
	}

	public void commit() {
		tx.commit();
	}

	public void rollback() {
		tx.rollback();
	}

	public Transaction getTransaction() {
		return tx;
	}

	public boolean isOpen() {
		return session.isOpen();
	}

}
