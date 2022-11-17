package cc.sukazyo.sekai_scores.db.project_sekai_colorful_stage;

import cc.sukazyo.sekai_scores.Difficulty;
import cc.sukazyo.sekai_scores.DifficultiesSekai;
import cc.sukazyo.sekai_scores.Song;
import cc.sukazyo.sekai_scores.SongUnit;

public class Other {
	
	public static final Song Dont_Fight_the_Music = new Song(
			"Don't Fight The Music", SongUnit.OTHER,
			new DifficultiesSekai(
					new Difficulty( 9,  520),
					new Difficulty(15,  701),
					new Difficulty(23, 1175),
					new Difficulty(30, 1545),
					new Difficulty(36, 1888)
			)
	);
	public static final Song Tokugawa_Kappu_Nuudoru_Kinshirei = new Song(
			"徳川カップヌードル禁止令", SongUnit.OTHER,
			new DifficultiesSekai(
					new Difficulty( 8,  264),
					new Difficulty(13,  498),
					new Difficulty(19,  758),
					new Difficulty(27, 1065),
					new Difficulty(31, 1241)
			)
	);
	
}
