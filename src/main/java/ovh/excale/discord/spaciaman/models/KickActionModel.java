package ovh.excale.discord.spaciaman.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "kicks")
public class KickActionModel {

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



}
