package ovh.excale.discord.spaciaman;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ovh.excale.HibernateUtil;
import ovh.excale.discord.spaciaman.commands.KickCommand;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class SpaciaMan {

	public static final Logger logger;
	public static final String VERSION;
	public static final String OWNER;
	public static final String[] CO_OWNERS;

	private static final Class<SpaciaMan> selfClass = SpaciaMan.class;
	private static final transient String TOKEN;
	private static boolean debug;
	private static JDA jda;

	public static JDA jda() {
		return jda;
	}

	public static boolean debug() {
		return debug;
	}

	public static void debug(boolean debug) {
		SpaciaMan.debug = debug;
		logger.info("Debug mode set to `{}`", debug);
	}

	static {

		// GET LOGGER
		logger = LoggerFactory.getLogger(selfClass);

		// VERSION META
		String version;
		InputStream in = selfClass.getClassLoader()
				.getResourceAsStream("VERSION");
		if(in != null)
			try(Scanner scanner = new Scanner(in)) {
				version = scanner.nextLine();
			} catch(Exception e) {
				logger.warn("Coudln't retrieve VERSION meta", e);
				version = "unknown";
			}
		else
			version = "unknown";

		VERSION = version;

		// MANDATORY ARGUMENTS
		for(String arg : new String[] { "DS_TOKEN", "DS_OWNER" })
			try {
				Objects.requireNonNull(System.getenv(arg));
			} catch(NullPointerException e) {
				logger.error("Missing argument {}, shutting down.", arg);
				System.exit(-1);
			}

		// GET ENVs
		TOKEN = System.getenv("DS_TOKEN");
		OWNER = System.getenv("DS_OWNER");
		CO_OWNERS = Arrays.stream(Optional.ofNullable(System.getenv("DS_COOWNERS"))
				.orElse("")
				.trim()
				.split(" *, *"))
				.filter(s -> s.length() != 0)
				.toArray(String[]::new);
	}

	public static void main(String[] args) {

		for(String s : args)
			if("debug".equals(s)) {
				debug = true;
				logger.info("Enabled debug mode");
			} else
				logger.warn("Unknown option \"{}\"", s);

		CommandClient client = new CommandClientBuilder().setOwnerId(OWNER)
				.addCommands(new KickCommand())
				.setCoOwnerIds(CO_OWNERS)
				.setActivity(Activity.watching(" for someone to kick | kc:help"))
				.setPrefix("kc:")
				.build();

		try {

			jda = JDABuilder.create(TOKEN,
					GatewayIntent.GUILD_MESSAGES,
					GatewayIntent.GUILD_MEMBERS)
					.disableCache(CacheFlag.ACTIVITY,
							CacheFlag.VOICE_STATE,
							CacheFlag.EMOTE,
							CacheFlag.CLIENT_STATUS)
					.addEventListeners(new EventWaiter(), client)
					.build()
					.awaitReady();

		} catch(Exception e) {
			logger.error(e.getMessage(), e);
			System.exit(-1);
		}

		logger.info("Bot running on version {}", VERSION);

		if(HibernateUtil.isConnected())
			logger.info("Connected to datasource");
		else
			logger.warn("Couldn't connect to datasource");

	}

}
