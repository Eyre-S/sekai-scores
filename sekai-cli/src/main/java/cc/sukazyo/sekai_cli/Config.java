package cc.sukazyo.sekai_cli;

import cc.sukazyo.sekai_cli.db.PostgresConfig;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static cc.sukazyo.sekai_cli.Log._debug;
import static cc.sukazyo.sekai_cli.Log._user;

public class Config {
	
	private static final String CONFIG_ROOT_NAME = "sekai-scores";
	private static final String CONFIG_FILE = CONFIG_ROOT_NAME + ".properties";
	
	public final long sekai_id;
	@Nonnull public final String db_host;
	@Nonnull public final String db_name;
	@Nonnull public final String db_auth_user;
	@Nonnull public final String db_auth_pwd;
	@Nullable public final String db_schema;
	
	private PostgresConfig db = null;
	public PostgresConfig db () {
		if (db == null) db = new PostgresConfig(this);
		return db;
	}
	
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
		this.db_schema = props.getProperty("db.schema");
		_debug(this.db_schema == null ? "config field db.schema unset." : "config field db.schema set: " + this.db_schema);
	}
	
	@Nonnull
	private static String getNonnull (Properties props, String key) {
		final String v = props.getProperty(key);
		if (v == null) throw new NullPointerException("null at key " + key);
		return v;
	}
	
	public void echo() {
		echo("user.sekai-id", String.valueOf(sekai_id));
		echo("db.server", db_host);
		echo("db.database", db_name);
		echo("db.auth.user", db_auth_user);
		echo("db.auth.password", db_auth_pwd);
		echo("db.schema", db_schema);
	}
	
	private void echo (String k, String v) {
		if (v == null) System.out.println(k + " [unset]");
		System.out.println(k + " = " + v);
	}
	
	static Config load() {
		
		final Properties props = new Properties();
		final File propFileSys = getAppConfigFile(getAppConfigPath(getSysGlobalConfigRoot()));
		final File propFileUser = getAppConfigFile(getAppConfigPath(getSysUserConfigRoot()));
		
		final boolean propSysLoaded = load(props, propFileSys);
		final boolean propUserLoaded = load(props, propFileUser);
		if (!propUserLoaded && !propSysLoaded) {
			_user("no config available.\n  use `$ sekai-cli config create | edit` to create a config file and edit it.");
			return null;
		}
		
		return new Config(props);
		
	}
	
	private static boolean load (Properties target, File file) {
		try {
			target.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			_debug("config not created yet: " + file.getAbsolutePath());
			_debug(e);
			return false;
		} catch (SecurityException e) {
			_debug("reading config: " + file.getAbsolutePath() + ": permission denied");
			_debug(e);
			return false;
		} catch (IOException e) {
			_debug("failed read config: " + file.getAbsolutePath());
			_debug(e);
			return false;
		}
		_debug("loaded config file: " + file.getAbsolutePath());
		return true;
	}
	
	public static File getAppConfigFile (Path appConfigPath) {
		return Paths.get(appConfigPath.toString(), CONFIG_FILE).toFile();
	}
	
	public static Path getAppConfigPath(Path configRoot) {
		return Paths.get(configRoot.toString(), CONFIG_ROOT_NAME);
	}
	
	public static Path getSysUserConfigRoot () {
		final Path userhome = Paths.get(System.getProperty("user.home"), SysType.get().userConfigPath);
		_debug("read config from user home config : " + userhome.toAbsolutePath());
		return userhome;
	}
	
	public static Path getSysGlobalConfigRoot () {
		final Path syshome = Paths.get(File.listRoots()[0].getPath(), SysType.get().sysConfigPath);
		_debug("read config from system global config : " + syshome.toAbsolutePath());
		return syshome;
	}
	
	public enum SysType {
		
		WINDOWS(new String[]{"AppData", "Roaming"}, new String[]{"ProgramData"}),
//		LINUX(userConfigPath, sysConfigPath),
//		MACOS(userConfigPath, sysConfigPath),
		UNKNOWN(new String[]{".config"}, new String[]{"etc"});
		
		public final String[] userConfigPath;
		public final String[] sysConfigPath;
		
		SysType (String[] userConfigPath, String[] sysConfigPath) {
			this.userConfigPath = userConfigPath;
			this.sysConfigPath = sysConfigPath;
		}
		
		public static SysType get () {
			final String sysName = System.getProperty("os.name");
			if (sysName.contains("Windows")) return SysType.WINDOWS;
			return UNKNOWN;
		}
		
	}
	
}
