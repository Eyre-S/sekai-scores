package cc.sukazyo.sekai_scores.db.project_sekai_colorful_stage;

import cc.sukazyo.sekai_scores.Difficulty;
import cc.sukazyo.sekai_scores.DifficultiesSekai;
import cc.sukazyo.sekai_scores.Song;
import cc.sukazyo.sekai_scores.SongUnit;

public class SekaiSpecial {
	
	public static final Song Gunjou_Sanka = new Song(
			"群青讃歌", SongUnit.SP,
			new DifficultiesSekai(
					new Difficulty( 7,  199),
					new Difficulty(13,  344),
					new Difficulty(17,  475),
					new Difficulty(24,  711),
					new Difficulty(27,  833)
			)
	);
	public static final Song Journey = new Song(
			"Journey", SongUnit.SP,
			new DifficultiesSekai(
					new Difficulty( 6,  199),
					new Difficulty(12,  512),
					new Difficulty(18,  922),
					new Difficulty(25, 1285),
					new Difficulty(28, 1322)
			)
	);
	
}
