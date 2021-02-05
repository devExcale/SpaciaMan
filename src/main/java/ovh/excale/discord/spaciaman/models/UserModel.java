package ovh.excale.discord.spaciaman.models;

import javax.persistence.*;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserModel {

	@Id
	@Column(name = "id_user")
	private Long snowflake;

	@Column(name = "nickname")
	private String nickname;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id_user")
	private Set<KickAction> kicks;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id_user")
	private Set<RoleSnapshot> roles;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id_user")
	private List<KickMessage> kickMessages;

	public UserModel setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public UserModel setKicks(Set<KickAction> kicks) {
		this.kicks = kicks;
		return this;
	}

	public UserModel setRoleSnapshots(Set<RoleSnapshot> roles) {
		this.roles = roles;
		return this;
	}

	public UserModel setKickMessages(List<KickMessage> kickMessages) {
		this.kickMessages = kickMessages;
		return this;
	}

	public Long getSnowflake() {
		return snowflake;
	}

	public String getNickname() {
		return nickname;
	}

	public Set<KickAction> getKicks() {
		return kicks;
	}

	public Set<RoleSnapshot> getRoleSnapshots() {
		return roles;
	}

	public List<KickMessage> getKickMessages() {
		return kickMessages;
	}

	public KickMessage getRandomKickMessage() {
		return kickMessages.get(new Random().nextInt() % kickMessages.size());
	}

}
