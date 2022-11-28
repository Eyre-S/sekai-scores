package cc.sukazyo.sekai_scores.meta.project_sekai_colorful_stage;

import cc.sukazyo.sekai_scores.Difficulty;
import cc.sukazyo.sekai_scores.DifficultiesSekai;
import cc.sukazyo.sekai_scores.Song;
import cc.sukazyo.sekai_scores.SongUnit;

import static cc.sukazyo.sekai_scores.DifficultiesSekai.*;

public class VSinger extends SongDefinition {
	
	/** <a href="https://www.sekaipedia.org/wiki/Donna_Ketsumatsu_ga_Onozomi_Dai%3F">...</a> */
	public static final Song Luka_Luka_Night_Fever = new Song(
			216, "ルカルカ★ナイトフィーバー", SongUnit.VSINGER,
			new DifficultiesSekai(
					new Difficulty(  EASY_NAME, 7,  218),
					new Difficulty(NORMAL_NAME,13,  457),
					new Difficulty(  HARD_NAME,18,  616),
					new Difficulty(EXPERT_NAME,25,  950),
					new Difficulty(MASTER_NAME,29, 1311)
			)
	);
	
}
