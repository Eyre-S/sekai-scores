package cc.sukazyo.sekai_cli.data_tool.sekai_master_db;

import cc.sukazyo.sekai_scores.DifficultiesEmpty;
import cc.sukazyo.sekai_scores.Song;
import cc.sukazyo.sekai_scores.SongUnit;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class Music {
	
	public int id;
	public int seq;
	public int releaseConditionId;
	public String[] categories;
	public String title;
	public String pronunciation;
	public String lyricist;
	public String composer;
	public String arranger;
	public int dancerCount;
	public int selfDancerPosition;
	public String assetbundleName;
	public String liveTalkBackgroundAssetbundleName;
	public long publishedAt;
	public int liveStageId;
	public int filterSec;
	
	public static Music[] readFrom (File sekaiMusicsFile, Charset charset) throws IOException, JsonIOException, JsonSyntaxException {
		return new Gson().fromJson(new FileReader(sekaiMusicsFile, charset), Music[].class);
	}
	
	public SongUnit getUnit () {
		return SongUnit.getBySeq((seq/100000) % 10);
	}
	
	public Song toSong () {
		return new Song(
				id, title, getUnit(), new DifficultiesEmpty(),
				null, getOrNull(arranger), getOrNull(composer), getOrNull(lyricist),
				null, null,
				ZonedDateTime.ofInstant(Instant.ofEpochMilli(publishedAt), ZoneOffset.UTC),
				new String[]{}
		);
	}
	
	public static Song[] toSong (Music... musics) {
		final List<Song> songs = new ArrayList<>();
		for (Music i : musics) songs.add(i.toSong());
		return songs.toArray(new Song[0]);
	}
	
	private String getOrNull (String name) {
		return name.equals("-") ? null : name;
	}
	
}
