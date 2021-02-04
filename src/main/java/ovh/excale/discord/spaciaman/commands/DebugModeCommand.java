package ovh.excale.discord.spaciaman.commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import ovh.excale.discord.spaciaman.SpaciaMan;

public class DebugModeCommand extends Command {

	public DebugModeCommand() {
		this.name = "debug";
		this.arguments = "<true|false|on|off>";
		this.help = "Enable/Disable debug mode";
		this.ownerCommand = true;
		this.guildOnly = false;
	}

	@Override
	protected void execute(CommandEvent event) {

		String[] split = event.getArgs()
				.split(" ");

		if(split.length > 1)
			event.reply("Too many arguments");
		else if(split.length == 0)
			event.reply("Not enough arguments");

		String arg = split[0];
		if(arg.equalsIgnoreCase("true") || arg.equalsIgnoreCase("on")) {
			SpaciaMan.debug(true);
		} else if(arg.equalsIgnoreCase("false") || arg.equalsIgnoreCase("off")) {
			SpaciaMan.debug(false);
		} else
			event.reply("Unknown option `" + arg + '`');

	}

}
