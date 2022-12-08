package cc.sukazyo.sekai_cli.data_tool.sekai_master_db;

import cc.sukazyo.sekai_db.table.SekaiSongDifficulties.DatabaseStruct;
import cc.sukazyo.sekai_db.type.SekaiDifficulties;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MusicDifficulty {
	
	public int id;
	public int musicId;
	public String musicDifficulty;
	public int playLevel;
	public int releaseConditionId;
	public int noteCount;
	
	public static MusicDifficulty[] readFrom (File sekaiMusicDifficultiesFile, Charset charset)
	throws IOException, JsonIOException, JsonSyntaxException {
		return new Gson().fromJson(new FileReader(sekaiMusicDifficultiesFile, charset), MusicDifficulty[].class);
	}
	
	/**
	 * @throws IllegalArgumentException no such difficulty in {@link SekaiDifficulties}
	 */
	public DatabaseStruct toDatabaseStruct () throws IllegalArgumentException {
		return new DatabaseStruct(
				musicId, SekaiDifficulties.valueOf(musicDifficulty.toUpperCase()),
				playLevel, noteCount,
				null, null, null
		);
	}
	
	/**
	 * @throws IllegalArgumentException no such difficulty in {@link SekaiDifficulties}
	 * @see #toDatabaseStruct()
	 */
	public static DatabaseStruct[] toDatabaseStruct (MusicDifficulty... source) throws IllegalArgumentException {
		final List<DatabaseStruct> target = new ArrayList<>();
		for (MusicDifficulty i : source) target.add(i.toDatabaseStruct());
		return target.toArray(DatabaseStruct[]::new);
	}
	
}
