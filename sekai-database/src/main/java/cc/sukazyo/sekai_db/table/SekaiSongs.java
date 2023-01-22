package cc.sukazyo.sekai_db.table;

import cc.sukazyo.sekai_db.PostgresSession;
import cc.sukazyo.sekai_scores.DifficultiesEmpty;
import cc.sukazyo.sekai_scores.Difficulty;
import cc.sukazyo.sekai_scores.Song;
import cc.sukazyo.sekai_scores.SongUnit;

import java.sql.*;
import java.time.Duration;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static cc.sukazyo.sekai_db.Util.arraysShortToInt;

public class SekaiSongs {
	
	public static final String TABLE_DDL = """
			create table sekai_songs
			(
			    id           integer not null
			        constraint sekai_songs_pk
			            primary key,
			    name         text    not null
			        constraint sekai_songs_pk_name
			            unique,
			    producer     text,
			    arranger     text,
			    composer     text,
			    bpm          smallint[],
			    duration     interval(1),
			    release_date timestamp(3) with time zone,
			    name_alias   text[],
			    lyricist     text,
			    unit_seq     smallint
			);
			
			comment on column sekai_songs.name_alias is
			'the alias/tags for this song,
			it usually is a unofficial name of this song.
			
			used to search songs.';
			
			create index sekai_songs_name_alias_index
			    on sekai_songs (name_alias);
			
			create index sekai_songs_producer_index
			    on sekai_songs (producer);
			""";
	
	private final PostgresSession session;
	
	private SekaiSongs(PostgresSession session) {
		this.session = session;
	}
	public static SekaiSongs as (PostgresSession session) {
		return new SekaiSongs(session);
	}
	
	public int create () throws SQLException {
		return session.session.prepareStatement(TABLE_DDL).executeUpdate();
	}
	
	public boolean hasSong(int songId) throws SQLException {
		final PreparedStatement statement = session.session.prepareStatement("""
				select id from sekai_songs
				where id = ?
				""");
		statement.setInt(1, songId);
		return statement.executeQuery().next();
	}
	
	public Song getSong (int songId) throws SQLException {
		final PreparedStatement getById = session.session.prepareStatement("""
				select
					id, unit_seq, name, producer, arranger, composer, lyricist, bpm, duration, release_date, name_alias
				from sekai_songs
				where id = ?
				""");
		getById.setInt(1, songId);
		final ResultSet data = getById.executeQuery();
		if (!data.next()) return null;
		final Timestamp _release_date = data.getTimestamp("release_date");
		return new Song(
				data.getInt("id"),
				data.getString("name"),
				SongUnit.getBySeq(data.getInt("unit_seq")),
				new DifficultiesEmpty(),
				data.getString("producer"),
				data.getString("arranger"),
				data.getString("composer"),
				data.getString("lyricist"),
				arraysShortToInt((Short[])data.getArray("bpm").getArray()),
				Duration.parse("PT0s"), // todo
				_release_date == null ? null : ZonedDateTime.ofInstant(_release_date.toInstant(), ZoneOffset.UTC),
				(String[])data.getArray("name_alias").getArray()
		);
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
