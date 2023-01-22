package cc.sukazyo.sekai_cli.client;

import cc.sukazyo.sekai_cli.ClientMain;
import cc.sukazyo.sekai_cli.data_tool.sekai_master_db.Music;
import cc.sukazyo.sekai_cli.data_tool.sekai_master_db.MusicDifficulty;
import cc.sukazyo.sekai_db.PostgresSession;
import cc.sukazyo.sekai_db.table.SekaiSongDifficulties;
import cc.sukazyo.sekai_db.table.SekaiSongs;
import cc.sukazyo.sekai_scores.Song;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static cc.sukazyo.sekai_cli.Log._debug;
import static cc.sukazyo.sekai_cli.Log._user;

public class Database {
	
	public static void main (String[] args) {
		
		final List<String> $args = new ArrayList<>(List.of(args));
		
		if ($args.size() < 1) {
			System.out.println("""
					usage of database:
					  import - import song/difficulty data to database
					""");
		}
		final String subcommand = $args.remove(0);
		if (subcommand.equals("import")) {
			
			int ii;
			Charset charset = Charset.defaultCharset();
			if ((ii = $args.indexOf("--charset")) != -1) {
				$args.remove(ii);
				try { charset = Charset.forName($args.remove(ii)); }
				catch (IllegalCharsetNameException e) {
					_user("db_import: unknown/illegal charset " + e.getCharsetName());
					_debug(e);
					return;
				} catch (UnsupportedCharsetException e) {
					_user("db_import: unsupported charset by jvm: charset " + e.getCharsetName());
					_debug(e);
					return;
				}
			}
			_debug("db_import: will use charset " + charset.displayName());
			
			if ($args.size() != 2) {
				System.out.println("""
						database import: <type> <file-path>
						currently available types:
						  song
						  song-difficulty
						""");
			} else {
				final Path file;
				try { file = Paths.get($args.remove(1)); }
				catch (IndexOutOfBoundsException e) {
					_user("database import: need a file path.");
					_debug(e);
					return;
				}
				switch ($args.remove(0)) {
					case "song" -> {
						try (final PostgresSession session = ClientMain.config().db().connect()) {
							final Song[] songs = Music.toSong(Music.readFrom(file.toFile(), charset));
							for (Song i : songs) {
								_user(String.format("db_import: start insertion for song #%d (%s)", i.id(), i.name()));
								try {
									if (SekaiSongs.as(session).hasSong(i.id()))
										_user(String.format("db_import: song #%d already exists, skipped.", i.id()));
									else {
										final int changes = SekaiSongs.as(session).insert(i);
										_user(String.format("db_import: song #%d insert succeed: %d row updated.", i.id(), changes));
									}
								} catch (SQLException e) {
									_user("db_import: song #"+i.id()+": data insert failed: " + e.getMessage());
									_debug(e);
								}
							}
						} catch (IOException | JsonIOException | JsonSyntaxException e) {
							_user("db_import: error while parsing song file: " + e.getMessage());
							_debug(e);
						} catch (SQLException e) {
							_user("db_import: error while connecting to database: " + e.getMessage());
							_debug(e);
						}
					}
					case "song-difficulty" -> {
						try (final PostgresSession session = ClientMain.config().db().connect()) {
							final List<SekaiSongDifficulties.DatabaseStruct> difficultyList = new ArrayList<>();
							for (MusicDifficulty source : MusicDifficulty.readFrom(file.toFile(), charset)) {
								try { difficultyList.add(source.toDatabaseStruct()); }
								catch (final IllegalArgumentException e) {
									_user(String.format("""
											db_import: song-difficulty: failed converting source data to database struct
											  at source id #%d (song #%d, difficulty %s)
											%s
											""",
											source.id, source.musicId, source.musicDifficulty,
											e.getMessage().indent(2)
									));
									_debug(e);
								}
							}
							difficultyList.forEach( i -> {
								_user(String.format("db_import: start insertion for difficulty #%d.%s", i.songId(), i.difficulty()));
								try {
									if (SekaiSongDifficulties.as(session).contains(i.songId(), i.difficulty()))
										_user(String.format("db_import:  #%d.%s already exists, skipped.", i.songId(), i.difficulty()));
									else {
										final int changes = SekaiSongDifficulties.as(session).insertDatabaseStructData(i);
										_user(String.format("db_import: song #%d.%s insert succeed: %d row updated.", i.songId(), i.difficulty(), changes));
									}
								} catch (SQLException e) {
									_user(String.format("db_import: #%d.%s: data insert failed: %s", i.songId(), i.difficulty(), e.getMessage()));
									_debug(e);
								}
							});
						} catch (IOException | JsonIOException | JsonSyntaxException e) {
							_user("db_import: error while parsing difficulty list file: " + e.getMessage());
							_debug(e);
						} catch (SQLException e) {
							_user("db_import: error while connecting to database: " + e.getMessage());
							_debug(e);
						}
					}
					default ->
							_user("unavailable type.\ncurrently available types:\n  song\n  song-difficulty");
				}
			}
			
		}
		
	}
	
}
