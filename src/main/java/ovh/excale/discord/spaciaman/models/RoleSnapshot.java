package ovh.excale.discord.spaciaman.models;

import net.dv8tion.jda.api.entities.Role;
import org.hibernate.annotations.Where;
import org.jetbrains.annotations.Nullable;
import ovh.excale.discord.spaciaman.SpaciaMan;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "roles")
@Where(clause = "id_role IS NOT NULL")
public class RoleSnapshot implements Serializable {

	@Id
	@Column(name = "id_user")
	private Long userId;

	@Id
	private Timestamp timestamp;

	@Column(name = "id_role")
	private @Nullable Long roleId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user", nullable = false)
	private UserModel user;

	public @Nullable Role getRole() {
		Role role = null;

		if(roleId != null)
			role = SpaciaMan.jda()
					.getRoleById(roleId);

		return role;
	}

	public Long getUserId() {
		return userId;
	}

	public RoleSnapshot setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public @Nullable Long getRoleId() {
		return roleId;
	}

	public RoleSnapshot setRoleId(Long roleId) {
		this.roleId = roleId;
		return this;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public RoleSnapshot setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public UserModel getUser() {
		return user;
	}

	public RoleSnapshot setUser(UserModel user) {
		this.user = user;
		return this;
	}

}
