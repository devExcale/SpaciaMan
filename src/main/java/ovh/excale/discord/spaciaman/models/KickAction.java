package ovh.excale.discord.spaciaman.models;

import net.dv8tion.jda.api.entities.User;
import ovh.excale.discord.spaciaman.SpaciaMan;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "kicks")
public class KickAction {

	@Id
	@Column(name = "id_user")
	private Long kickedId;

	@Id
	@Column(name = "id_kicker")
	private Long kickerId;

	@Id
	private Timestamp timestamp;

	@ManyToOne(fetch = FetchType.EAGER)
	@Column(name = "id_user")
	private UserModel kicked;

	@ManyToOne(fetch = FetchType.LAZY)
	@Column(name = "id_kicker")
	private UserModel kicker;

	public User getKickedUser() {
		return SpaciaMan.jda()
				.getUserById(kickedId);
	}

	public User getKickerUser() {
		return SpaciaMan.jda()
				.getUserById(kickerId);
	}

	public Long getKickedId() {
		return kickedId;
	}

	public KickAction setKickedId(Long kickedId) {
		this.kickedId = kickedId;
		return this;
	}

	public Long getKickerId() {
		return kickerId;
	}

	public KickAction setKickerId(Long kickerId) {
		this.kickerId = kickerId;
		return this;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public KickAction setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public UserModel getKickedModel() {
		return kicked;
	}

	public KickAction setKickedModel(UserModel kicked) {
		this.kicked = kicked;
		return this;
	}

	public UserModel getKickerModel() {
		return kicker;
	}

	public KickAction setKickerModel(UserModel kicker) {
		this.kicker = kicker;
		return this;
	}

}
