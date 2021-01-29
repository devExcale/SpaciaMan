package ovh.excale.discord.spaciaman.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.util.regex.Pattern;

public class BaseCommand extends Command {

	private static final Pattern MENTION_PATTERN = Pattern.compile("^<@\\d+>$");

	public BaseCommand() {
		this.name = "kick";
		this.help = "Kick someone for fun!";
		this.arguments = "<@User>";
		// Set command cooldown to 30m
		this.cooldown = 1800;
		this.guildOnly = true;
	}

	@Override
	protected void execute(CommandEvent commandEvent) {

		Member selfMember = commandEvent.getSelfMember();
		User author = commandEvent.getAuthor();

		commandEvent.reply(commandEvent.getArgs()
				.replaceAll("@", "@\u200B"));

	}

}
