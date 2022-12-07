package cc.sukazyo.sekai_db;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConfig {
	
	@Nonnull public final String host;
	@Nonnull public final String database;
	@Nullable public final String schema;
	@Nonnull public final String user;
	@Nonnull public final String token;
	
	public PostgresConfig (
			@Nonnull String host, @Nonnull String database, @Nullable String schema,
			@Nonnull String user, @Nonnull String token
	) {
		this.host = host;
		this.database = database;
		this.schema = schema;
		this.user = user;
		this.token = token;
	}
	
	@Override
	public String toString () {
		return String.format("jdbc:postgresql://%s/%s", host, database);
	}
	
	public String schema () {
		return schema;
	}
	
	public String table (String table) {
		return (schema == null ? "" : '"' + schema() + '"' + ".") + '"' + table + '"';
	}
	
	public PostgresSession connect () throws SQLException {
		return PostgresSession.as(this);
	}
	
	Connection getConnection () throws SQLException {
		return DriverManager.getConnection(this.toString(), user, token);
	}
	
}
