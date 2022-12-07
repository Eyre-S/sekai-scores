package cc.sukazyo.sekai_db;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgresSession implements AutoCloseable {
	
	public final Connection session;
	private final PostgresConfig config;
	private PostgresSession (Connection session, PostgresConfig config) {
		this.config = config;
		this.session = session;
	}
	static PostgresSession as (PostgresConfig config) throws SQLException {
		final PostgresSession $this = new PostgresSession(config.getConnection(), config);
		$this.session.setAutoCommit(false);
		$this.session.beginRequest();
		$this.session.setSchema($this.config.schema);
		return $this;
	}
	
	@Override
	public void close () throws SQLException {
		session.commit();
		session.close();
	}
	
}
