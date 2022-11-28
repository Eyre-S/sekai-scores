package cc.sukazyo.sekai_scores.meta.project_sekai_colorful_stage;

import cc.sukazyo.sekai_scores.Difficulty;
import cc.sukazyo.sekai_scores.DifficultiesSekai;
import cc.sukazyo.sekai_scores.Song;
import cc.sukazyo.sekai_scores.SongUnit;

import static cc.sukazyo.sekai_scores.DifficultiesSekai.*;

public class SekaiSpecial extends SongDefinition {
	
	/** <a href="https://www.sekaipedia.org/wiki/Yoru_ni_Kakeru">...</a> */
	public static final Song Yoru_ni_Kakeru = new Song(
			139, "夜に駆ける", SongUnit.SP,
			new DifficultiesSekai(
					new Difficulty(  EASY_NAME, 6,  182),
					new Difficulty(NORMAL_NAME,11,  357),
					new Difficulty(  HARD_NAME,18,  594),
					new Difficulty(EXPERT_NAME,25,  932),
					new Difficulty(MASTER_NAME,29, 1140)
			)
	);
	/** <a href="https://www.sekaipedia.org/wiki/Gunjou_Sanka">...</a> */
	public static final Song Gunjou_Sanka = new Song(
			141, "群青讃歌", SongUnit.SP,
			new DifficultiesSekai(
					new Difficulty(  EASY_NAME, 7,  199),
					new Difficulty(NORMAL_NAME,13,  344),
					new Difficulty(  HARD_NAME,17,  475),
					new Difficulty(EXPERT_NAME,24,  711),
					new Difficulty(MASTER_NAME,27,  833)
			)
	);
	/** <a href="https://www.sekaipedia.org/wiki/Journey">...</a> */
	public static final Song Journey = new Song(
			235, "Journey", SongUnit.SP,
			new DifficultiesSekai(
					new Difficulty(  EASY_NAME, 6,  199),
					new Difficulty(NORMAL_NAME,12,  512),
					new Difficulty(  HARD_NAME,18,  922),
					new Difficulty(EXPERT_NAME,25, 1285),
					new Difficulty(MASTER_NAME,28, 1322)
			)
	);
	
}
