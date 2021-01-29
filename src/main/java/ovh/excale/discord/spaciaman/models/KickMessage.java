package ovh.excale.discord.spaciaman.models;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class KickMessage {

	@Id
	@Column(name = "id_msg")
	private String id;

	@Column
	private @NotNull String message;

	@ManyToOne(fetch = FetchType.LAZY)
	@Column(name = "id_user")
	private UserModel user;

	public String getId() {
		return id;
	}

	public KickMessage setId(String id) {
		this.id = id;
		return this;
	}

	public @NotNull String getMessage() {
		return message;
	}

	public KickMessage setMessage(String message) {
		this.message = message;
		return this;
	}

	public UserModel getUser() {
		return user;
	}

	public KickMessage setUser(UserModel user) {
		this.user = user;
		return this;
	}

	public String apply(UserModel kicker) {
		return message.replaceAll("\\{\\{kicked}}", user.getNickname())
				.replaceAll("\\{\\{kicker}}", kicker.getNickname());
	}

}
