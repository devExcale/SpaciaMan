package ovh.excale.discord.spaciaman.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.util.regex.Pattern;

public class KickCommand extends Command {

	private static final Pattern MENTION_PATTERN = Pattern.compile("^<@!\\d+>$");

	public KickCommand() {
		this.name = "kick";
		this.help = "Kick someone for fun!";
		this.arguments = "<@user>";
		// Set command cooldown to 30m
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

//					invites.stream().filter(Invite::isExpanded).filter(invite -> invite);

				});

	}

}
