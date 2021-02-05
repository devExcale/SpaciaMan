package ovh.excale.discord.spaciaman.models;

import net.dv8tion.jda.api.entities.User;
import ovh.excale.discord.spaciaman.SpaciaMan;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "kicks")
public class KickAction implements Serializable {

	@Id
	@Column(name = "id_user", updatable = false)
	private Long kickedId;

	@Id
	@Column(name = "id_kicker", updatable = false)
	private Long kickerId;

	@Id
	private Timestamp timestamp;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user", nullable = false)
	private UserModel kicked;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_kicker", nullable = false)
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
