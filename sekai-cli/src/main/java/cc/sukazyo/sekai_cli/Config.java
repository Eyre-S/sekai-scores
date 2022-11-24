package cc.sukazyo.sekai_cli;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static cc.sukazyo.sekai_cli.Log._debug;

public class Config {
	
	private static final String CONFIG_ROOT_NAME = "sekai-scores";
	private static final String CONFIG_FILE = CONFIG_ROOT_NAME + ".properties";
	
	public final long sekai_id;
	@Nonnull public final String db_host;
	@Nonnull public final String db_name;
	@Nonnull public final String db_auth_user;
	@Nonnull public final String db_auth_pwd;
	@Nullable public final String db_prefix;
	
	private Config (Properties props) {
		// user config field
		this.sekai_id = Long.parseLong(props.getProperty("user.sekai-id"));
		_debug("config field user.sekai-id set: " + this.sekai_id);
		this.db_host = getNonnull(props, "db.server");
		_debug("config field db.server set: " + this.db_host);
		this.db_name = getNonnull(props, "db.database");
		_debug("config field db.database set: " + this.db_name);
		this.db_auth_user = getNonnull(props, "db.auth.user");
		_debug("config field db.auth.user set: " + this.db_auth_user);
		this.db_auth_pwd = getNonnull(props, "db.auth.password");
		_debug("config field db.auth.password set.");
		this.db_prefix = props.getProperty("db.table-prefix");
		_debug(this.db_prefix == null ? "config field db.prefix unset." : "config field db.table-prefix set: " + this.db_prefix);
	}
	
	@Nonnull
	private static String getNonnull (Properties props, String key) {
		final String v = props.getProperty(key);
		if (v == null) throw new NullPointerException("null at key " + key);
		return v;
	}
	
	static Config loadUserConfig() {
		try {
			
			final Properties props = new Properties();
			final File propFile = Paths.get(getAppConfigPath(getSysUserConfigRoot()).toString(), CONFIG_FILE).toFile();
			props.load(new FileInputStream(propFile));
			_debug("loaded config file: " + propFile.getAbsolutePath());
			
			return new Config(props);
			
		} catch (IOException e) {
			throw new RuntimeException("error while load user config", e);
		}
	}
	
	private static Path getAppConfigPath(Path configRoot) {
		return Paths.get(configRoot.toString(), CONFIG_ROOT_NAME);
	}
	
	private static Path getSysUserConfigRoot () {
		final Path userhome = Paths.get(System.getProperty("user.home"), ".config");
		_debug("read config from user home : " + userhome.toAbsolutePath());
		return userhome;
	}
	
}
