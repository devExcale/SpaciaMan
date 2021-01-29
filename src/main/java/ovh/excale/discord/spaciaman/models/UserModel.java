package ovh.excale.discord.spaciaman.models;

import javax.persistence.*;
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
	private Set<KickActionModel> kicks;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id_user")
	private Set<RoleSnapshotModel> roles;


}
