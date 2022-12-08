package cc.sukazyo.sekai_db.table;

import cc.sukazyo.sekai_db.PostgresSession;
import cc.sukazyo.sekai_db.type.SekaiDifficulties;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class SekaiSongDifficulties {
	
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

}
