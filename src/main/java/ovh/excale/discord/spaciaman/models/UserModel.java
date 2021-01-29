package ovh.excale.discord.spaciaman.models;

import javax.persistence.*;
import java.util.List;
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

	public Long getSnowflake() {
		return snowflake;
	}

	public String getNickname() {
		return nickname;
	}

	public UserModel setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public Set<KickAction> getKicks() {
		return kicks;
	}

	public UserModel setKicks(Set<KickAction> kicks) {
		this.kicks = kicks;
		return this;
	}

	public Set<RoleSnapshot> getRoles() {
		return roles;
	}

	public UserModel setRoles(Set<RoleSnapshot> roles) {
		this.roles = roles;
		return this;
	}

	public List<KickMessage> getKickMessages() {
		return kickMessages;
	}

	public UserModel setKickMessages(List<KickMessage> kickMessages) {
		this.kickMessages = kickMessages;
		return this;
	}

}
