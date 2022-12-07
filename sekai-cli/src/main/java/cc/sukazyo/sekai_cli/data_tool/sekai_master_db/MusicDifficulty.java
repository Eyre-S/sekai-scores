package cc.sukazyo.sekai_cli.data_tool.sekai_master_db;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

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
	
}
