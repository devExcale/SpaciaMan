package ovh.excale.discord.spaciaman.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import ovh.excale.discord.spaciaman.SpaciaMan;

import java.util.Optional;
import java.util.regex.Pattern;

public class KickCommand extends Command {

	private static final Pattern MENTION_PATTERN = Pattern.compile("^<@!\\d+>$");

	public KickCommand() {
		this.name = "kick";
		this.help = "Kick someone for fun!";
		this.arguments = "<@user>";
		// Set command cooldown to 30m
		this.cooldown = SpaciaMan.debug() ? 0 : 1800;
		this.guildOnly = true;
	}

	@Override
	protected void execute(CommandEvent event) {

		Member selfMember = event.getSelfMember();
		User author = event.getAuthor();

		String args = event.getArgs();

		if(!MENTION_PATTERN.matcher(args)
				.matches()) {
			event.reply("Arguments error");
			return;
		}

		event.getGuild()
				.retrieveInvites()
				.queue(invites -> {

					Invite invite = null;
					for(Invite inv : invites)
						if(inv.isExpanded() && !inv.isTemporary() && inv.getMaxUses() == 0 && inv.getMaxAge() == 0) {
							invite = inv;
							break;
						}

					if(invite == null) {
						event.reply(
								"You have to create an invite first with no max uses, no expire date and not temporary.");
						return;
					}




				});

	}

}
