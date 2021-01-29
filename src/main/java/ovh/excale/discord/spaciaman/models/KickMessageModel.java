package ovh.excale.discord.spaciaman.models;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public class KickMessageModel {

	@Id
	@Column(name = "id_msg")
	private String id;

	@Column
	private String message;

	@ManyToOne(fetch = FetchType.LAZY)
	@Column(name = "id_user")
	private UserModel user;

}
