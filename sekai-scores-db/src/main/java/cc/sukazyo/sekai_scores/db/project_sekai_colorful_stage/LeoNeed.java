package cc.sukazyo.sekai_scores.db.project_sekai_colorful_stage;

import cc.sukazyo.sekai_scores.Difficulty;
import cc.sukazyo.sekai_scores.DifficultiesSekai;
import cc.sukazyo.sekai_scores.Song;
import cc.sukazyo.sekai_scores.SongUnit;

import static cc.sukazyo.sekai_scores.DifficultiesSekai.*;

public class LeoNeed {
	
	/** <a href="https://www.sekaipedia.org/wiki/Calc.">...</a> */
	public static final Song Calc = new Song(
			243, "Calc.", SongUnit.LEO_NEED,
			new DifficultiesSekai(
					new Difficulty(  EASY_NAME, 7,  325),
					new Difficulty(NORMAL_NAME,13,  464),
					new Difficulty(  HARD_NAME,18,  844),
					new Difficulty(EXPERT_NAME,25, 1126),
					new Difficulty(MASTER_NAME,30, 1295)
			)
	);

}
