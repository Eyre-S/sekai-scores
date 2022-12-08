package cc.sukazyo.sekai_db.table;

import cc.sukazyo.sekai_db.PostgresSession;
import cc.sukazyo.sekai_scores.Difficulty;
import cc.sukazyo.sekai_scores.Song;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;

public class SekaiSongs {
	
	private final PostgresSession session;
	
	private SekaiSongs(PostgresSession session) {
		this.session = session;
	}
	public static SekaiSongs as (PostgresSession session) {
		return new SekaiSongs(session);
	}
	
	public boolean hasSong(int songId) throws SQLException {
		final PreparedStatement statement = session.session.prepareStatement("""
				select id from sekai_songs
				where id = ?
				""");
		statement.setInt(1, songId);
		return statement.executeQuery().next();
	}
	
	public int insert (Song song) throws SQLException {
		
		int updated = 0;
		
		final PreparedStatement statement = session.session.prepareStatement("""
				insert into sekai_songs
				(id, unit_seq, name, producer, arranger, composer, lyricist, bpm, duration, release_date, name_alias)
				values (?, ?, ?, ?, ?, ?, ?, ?, cast(? AS interval), ?, ?)
				""");
		statement.setInt(1, song.id());
		statement.setInt(2, song.unit().seq);
		statement.setString(3, song.name());
		statement.setString(4, song.producer());
		statement.setString(5, song.arranger());
		statement.setString(6, song.composer());
		statement.setString(7, song.lyricist());
		if (song.bpm() == null) statement.setNull(8, Types.ARRAY);
		else statement.setArray(8, session.session.createArrayOf("smallint", Arrays.stream(song.bpm()).boxed().toArray()));
		if (song.duration() == null) statement.setNull(9, Types.VARCHAR);
		else statement.setString(9, song.duration().toString());
		if (song.releaseDate() == null) statement.setNull(10, Types.TIMESTAMP_WITH_TIMEZONE);
		else statement.setTimestamp(10, Timestamp.from(song.releaseDate().toInstant()));
		statement.setArray(11, session.session.createArrayOf("text", song.nameAlias()));
		updated += statement.executeUpdate();
		
		for (Difficulty difficulty : song.difficulties().getAll())
			updated += SekaiSongDifficulties.as(session).insertFromSong(difficulty, song);
		
		return updated;
		
	}
	
}
