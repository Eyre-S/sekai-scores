package cc.sukazyo.sekai_scores;

/**
 * 由于数据定义了好长所以数据定义被独立出来了，看下面的引用.
 * @see ScoreBaseData
 */
public class ScoreBase extends ScoreBaseData {
	
	public ScoreBase (int perfect, int great, int good, int bad, int miss, int fast, int slow, int flick, int combo) { super(perfect, great, good, bad, miss, fast, slow, flick, combo); }
	public ScoreBase (int perfect, int great, int good, int bad, int miss) { super(perfect, great, good, bad, miss); }
	public ScoreBase (int perfect, int great, int good, int bad, int miss, int fast, int slow, int flick) { super(perfect, great, good, bad, miss, fast, slow, flick); }
	public ScoreBase (int perfect, int great, int good, int bad, int miss, int combo) { super(perfect, great, good, bad, miss, combo); }
	
	/**
	 * calculate the skill score of this score.
	 * <p>
	 * the skill score means the scoring system used in the Project Sekai Championship (プロジェクトセカイ Championship)
	 * and the in-game RANK-MATCH mod.
	 * <p>
	 * the score calculated by the following simple rule:<br/>
	 * PERFECT: +3<br/>
	 * GREAT: +2<br/>
	 * GOOD: +1<br/>
	 * BAD, MISS: +0
	 * @return the skill score of this gameplay.
	 */
	public int calculateSkillScore () {
		return perfect()*3 + great()*2 + good();
	}
	
	/**
	 * calculate the skill score of the gameplay map.
	 * <p>
	 * the skill score means the scoring system used in the Project Sekai Championship (プロジェクトセカイ Championship)
	 * and the in-game RANK-MATCH mod.
	 * <p>
	 * the score calculated by the following simple rule:<br/>
	 * PERFECT: +3<br/>
	 * GREAT: +2<br/>
	 * GOOD: +1<br/>
	 * BAD, MISS: +0
	 * <p>
	 * so, this return value means just the note count * 3.
	 * @return the skill score of the gameplay map.
	 */
	public int calculateTheoreticalSkillScore () {
		return noteCount() * 3;
	}
	
	/**
	 * @return the note count of the gameplay map.
	 */
	public int noteCount () {
		return perfect() + great() + good() + bad() + miss();
	}
	
}
