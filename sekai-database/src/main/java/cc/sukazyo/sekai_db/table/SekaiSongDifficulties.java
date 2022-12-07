package cc.sukazyo.sekai_db.table;

import cc.sukazyo.sekai_db.PostgresSession;
import cc.sukazyo.sekai_db.type.SekaiDifficulties;

import javax.annotation.Nonnull;

public class SekaiSongDifficulties {
	
	public record DatabaseStruct (
			int songId,
			@Nonnull SekaiDifficulties difficulty,
			int level,
			int notes,
			int lvlp,
			int flvlp,
			int plvlp
	) {}
	
	private final PostgresSession session;
	
	private SekaiSongDifficulties (PostgresSession session) { this.session = session; }
	static SekaiSongDifficulties as (PostgresSession session) { return new SekaiSongDifficulties(session); }
	
//	public boolean contains (int songId, SekaiDifficulties difficulty) {
//
//	}
//
//	public int insertDatabaseStructData (DatabaseStruct data) {
//	}
//
}
