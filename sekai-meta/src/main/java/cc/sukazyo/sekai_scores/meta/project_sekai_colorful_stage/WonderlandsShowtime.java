package cc.sukazyo.sekai_scores.meta.project_sekai_colorful_stage;

import cc.sukazyo.sekai_scores.Difficulty;
import cc.sukazyo.sekai_scores.DifficultiesSekai;
import cc.sukazyo.sekai_scores.Song;
import cc.sukazyo.sekai_scores.SongUnit;

import static cc.sukazyo.sekai_scores.DifficultiesSekai.*;

public class WonderlandsShowtime extends SongDefinition {
	
	/** <a href="https://www.sekaipedia.org/wiki/Donna_Ketsumatsu_ga_Onozomi_Dai%3F">...</a> */
	public static final Song Donna_Ketsumatsu_ga_Onozomi_Dai = new Song(
			290, "どんな結末がお望みだい？", SongUnit.WONDERLANDS_SHOWTIME,
			new DifficultiesSekai(
					new Difficulty(  EASY_NAME, 8,  215),
					new Difficulty(NORMAL_NAME,12,  325),
					new Difficulty(  HARD_NAME,17,  494),
					new Difficulty(EXPERT_NAME,24,  638),
					new Difficulty(MASTER_NAME,28,  835)
			)
	);
	
}
