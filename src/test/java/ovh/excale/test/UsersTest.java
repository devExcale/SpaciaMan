package ovh.excale.test;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import ovh.excale.discord.spaciaman.UserRepository;
import ovh.excale.discord.spaciaman.models.UserModel;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@TestMethodOrder(OrderAnnotation.class)
@DisplayNameGeneration(ReplaceUnderscores.class)
public class UsersTest {

	private static final Logger logger = LoggerFactory.getLogger(UsersTest.class);

	@Test
	@Order(1)
	void get_all_users() {

		UserRepository repo = new UserRepository();
		List<UserModel> users = repo.getAllUsers();

		assumingThat(users.size() == 0, () -> assertTrue(repo.isOpen()));
		logger.info(() -> "Size: " + users.size());

		repo.close();

	}

	@Test
	@Order(2)
	void insert_and_retrieve() {

		UserModel user = new UserModel().setSnowflake(123L)
				.setNickname("testestest");

		UserRepository repo = new UserRepository();
		repo.saveUser(user);

		Optional<UserModel> retrived = repo.getUser(123L);

		assertTrue(retrived.isPresent(), "UserModel not found when supposed to");
		assertEquals(user.getSnowflake(),
				retrived.get()
						.getSnowflake(),
				"UserModel snowflake mismatch");

		logger.info(() -> "Size: " + repo.getAllUsers()
				.size());

		repo.close();

	}

	@Test
	@Order(3)
	void and_delete() {

		UserRepository repo = new UserRepository();
		Optional<UserModel> userOptional = repo.getUser(123L);

		assertTrue(userOptional.isPresent(), "UserModel not found when supposed to");

		repo.deleteUser(userOptional.get());
		userOptional = repo.getUser(123L);

		assertFalse(userOptional.isPresent(), "UserModel found when not supposed to");

		logger.info(() -> "Size: " + repo.getAllUsers()
				.size());

		repo.close();

	}

}
