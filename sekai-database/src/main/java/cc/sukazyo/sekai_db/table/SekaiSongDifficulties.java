package cc.sukazyo.sekai_db.table;

import cc.sukazyo.sekai_db.PostgresSession;
import cc.sukazyo.sekai_db.type.SekaiDifficulties;
import cc.sukazyo.sekai_scores.Difficulty;
import cc.sukazyo.sekai_scores.Song;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class SekaiSongDifficulties {
	
	public static final String TABLE_DDL = """
			create table sekai_song_difficulties
			(
			    song_id    integer                  not null,
			    difficulty sekai.sekai_difficulties not null,
			    level      smallint                 not null,
			    notes      smallint                 not null,
			    "lvl+"     smallint,
			    "flvl+"    smallint,
			    "plvl+"    smallint
			);
			
			create index sekai_songs_difficulties_song_id_difficulty_name_index
			    on sekai_song_difficulties (song_id, difficulty);
			
			create index sekai_songs_difficulties_notes_index
			    on sekai_song_difficulties (notes);
			""";
	
	public record DatabaseStruct (
			int songId,
			@Nonnull SekaiDifficulties difficulty,
			int level,
			int notes,
			@Nullable Integer lvlp,
			@Nullable Integer flvlp,
			@Nullable Integer plvlp
	) {}
	
	private final PostgresSession session;
	
	private SekaiSongDifficulties (PostgresSession session) { this.session = session; }
	public static SekaiSongDifficulties as (PostgresSession session) { return new SekaiSongDifficulties(session); }
	
	public int create () throws SQLException {
		return session.session.prepareStatement(TABLE_DDL).executeUpdate();
	}
	
	public boolean contains (int songId) throws SQLException {
		final PreparedStatement check = session.session.prepareStatement("""
				select song_id, difficulty from sekai_song_difficulties
				where song_id = ?
				""");
		check.setInt(1, songId);
		return check.executeQuery().next();
	}
	
	public boolean contains (int songId, SekaiDifficulties difficulty) throws SQLException {
		final PreparedStatement check = session.session.prepareStatement("""
				select song_id, difficulty from sekai_song_difficulties
				where song_id = ? and difficulty = cast(? as sekai_difficulties)
				""");
		check.setInt(1, songId);
		check.setString(2, difficulty.name());
		return check.executeQuery().next();
	}
	
	public int insertDatabaseStructData (DatabaseStruct data) throws SQLException {
		final PreparedStatement insert = session.session.prepareStatement("""
				insert into sekai_song_difficulties
				(song_id, difficulty, level, notes, "lvl+", "flvl+", "plvl+")
				values (?, cast(? as sekai_difficulties), ?, ?, ?, ?, ?)
				""");
		insert.setInt(1, data.songId);
		insert.setString(2, data.difficulty.name());
		insert.setInt(3, data.level);
		insert.setInt(4, data.notes);
		if (data.lvlp == null) insert.setNull(5, Types.SMALLINT);
		else insert.setInt(5, data.lvlp);
		if (data.flvlp == null) insert.setNull(6, Types.SMALLINT);
		else insert.setInt(6, data.flvlp);
		if (data.plvlp == null) insert.setNull(7, Types.SMALLINT);
		else insert.setInt(7, data.plvlp);
		return insert.executeUpdate();
	}
	
	public int insertFromSong (Difficulty difficulty, Song parent) throws SQLException {
		final PreparedStatement insert = session.session.prepareStatement("""
				insert into sekai_song_difficulties
				(song_id, difficulty, level, notes, "lvl+", "flvl+", "plvl+")
				values (?, cast(? as sekai_difficulties), ?, ?, ?, ?, ?)
				""");
		insert.setInt(1, parent.id());
		insert.setString(2, difficulty.id());
		insert.setInt(3, difficulty.level());
		insert.setInt(4, difficulty.noteCount());
		if (difficulty.levelPlus() == Difficulty.NULL) insert.setNull(5, Types.SMALLINT);
		insert.setInt(5, difficulty.levelPlus());
		if (difficulty.levelPlus_f() == Difficulty.NULL) insert.setNull(6, Types.SMALLINT);
		insert.setInt(6, difficulty.levelPlus_f());
		if (difficulty.levelPlus_p() == Difficulty.NULL) insert.setNull(7, Types.SMALLINT);
		insert.setInt(7, difficulty.levelPlus_p());
		return insert.executeUpdate();
	}

}
