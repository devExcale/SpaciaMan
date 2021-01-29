package ovh.excale.discord.spaciaman.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class KickMessageModel {

	@Id
	@Column(name = "id_msg")
	private String id;



}
