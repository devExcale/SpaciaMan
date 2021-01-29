package ovh.excale.discord.spaciaman.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "roles")
public class RoleSnapshotModel {

	@Id
	@Column(name = "id_user")
	private Long userId;

	@Id
	@Column(name = "id_role")
	private Long roleId;

	@Column
	private Timestamp timestamp;

	@ManyToOne(fetch = FetchType.EAGER)
	@Column(name = "id_user")
	private UserModel user;

}
