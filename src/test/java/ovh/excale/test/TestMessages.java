package ovh.excale.test;

import org.junit.jupiter.api.*;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import ovh.excale.discord.spaciaman.UserRepository;
import ovh.excale.discord.spaciaman.models.UserModel;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMessages {

	private static final Logger logger = LoggerFactory.getLogger(TestMessages.class);

	@Test
	void try_insert() {

		UserModel user = new UserModel().setSnowflake(123L)
				.setNickname("testuser");

		UserRepository repo = new UserRepository();
		repo.saveUser(user);

		Optional<UserModel> optional = repo.getUser(123L);

		assertTrue(optional.isPresent());
		assertEquals("testuser",
				optional.get()
						.getNickname());

	}

}
