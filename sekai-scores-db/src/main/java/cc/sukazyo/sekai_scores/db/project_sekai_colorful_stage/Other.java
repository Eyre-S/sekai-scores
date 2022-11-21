package cc.sukazyo.sekai_scores.db.project_sekai_colorful_stage;

import cc.sukazyo.sekai_scores.Difficulty;
import cc.sukazyo.sekai_scores.DifficultiesSekai;
import cc.sukazyo.sekai_scores.Song;
import cc.sukazyo.sekai_scores.SongUnit;

import static cc.sukazyo.sekai_scores.DifficultiesSekai.*;

public class Other {
	
	
	/** <a href="https://www.sekaipedia.org/wiki/Don%27t_Fight_The_Music">...</a> */
	public static final Song Dont_Fight_the_Music = new Song(
			164, "Don't Fight The Music", SongUnit.OTHER,
			new DifficultiesSekai(
					new Difficulty(  EASY_NAME, 9,  520),
					new Difficulty(NORMAL_NAME,15,  701),
					new Difficulty(  HARD_NAME,23, 1175),
					new Difficulty(EXPERT_NAME,30, 1545),
					new Difficulty(MASTER_NAME,36, 1888)
			)
	);
	/** <a href="https://www.sekaipedia.org/wiki/Tokugawa_Cup_Noodle_Kinshirei">...</a> */
	public static final Song Tokugawa_Kappu_Nuudoru_Kinshirei = new Song(
			234, "徳川カップヌードル禁止令", SongUnit.OTHER,
			new DifficultiesSekai(
					new Difficulty(  EASY_NAME, 8,  264),
					new Difficulty(NORMAL_NAME,13,  498),
					new Difficulty(  HARD_NAME,19,  758),
					new Difficulty(EXPERT_NAME,27, 1065),
					new Difficulty(MASTER_NAME,31, 1241)
			)
	);
	
}
