package cc.sukazyo.sekai_cli.db;

import cc.sukazyo.sekai_cli.Config;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConfig {
	
	@Nonnull public final String host;
	@Nonnull public final String database;
	@Nullable public final String struct;
	@Nonnull public final String user;
	@Nonnull public final String token;
	
	public PostgresConfig (
			@Nonnull String host, @Nonnull String database, @Nullable String struct,
			@Nonnull String user, @Nonnull String token
	) {
		this.host = host;
		this.database = database;
		this.struct = struct;
		this.user = user;
		this.token = token;
	}
	
	public PostgresConfig (Config global) {
		this(global.db_host, global.db_name, global.db_schema, global.db_auth_user, global.db_auth_pwd);
	}
	
	@Override
	public String toString () {
		return String.format("jdbc:postgresql://%s/%s", host, database);
	}
	
	public String struct() {
		return struct;
	}
	
	public String table (String table) {
		return (struct == null ? "" : '"' + struct() + '"' + ".") + '"' + table + '"';
	}
	
	public Connection connect () throws SQLException {
		return DriverManager.getConnection(this.toString(), user, token);
	}
	
}
