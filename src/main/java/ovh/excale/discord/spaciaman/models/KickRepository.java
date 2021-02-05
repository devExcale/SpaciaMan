package ovh.excale.discord.spaciaman.models;

import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class KickRepository {

	public @Nullable UserModel getUser(@NotNull User user) {
		return getUser(user.getIdLong());
	}

	public @Nullable UserModel getUser(long snowflake) {

		return null;

	}

}
