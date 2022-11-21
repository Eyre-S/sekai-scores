package cc.sukazyo.sekai_scores;

import javax.annotation.CheckForSigned;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * @see ScoreBase
 */
public class ScoreBaseData {
	
	public static final int NULL = -1;
	
	@Nonnegative private final int notePerfect;
	@Nonnegative private final int noteGreat;
	@Nonnegative private final int noteGood;
	@Nonnegative private final int noteBad;
	@Nonnegative private final int noteMiss;
	
	@CheckForSigned private final int judgeFast;
	@CheckForSigned private final int judgeSlow;
	@CheckForSigned private final int judgeFlick;
	
	@CheckForSigned private final int combo;
	
	public ScoreBaseData (
			int perfect, int great, int good, int bad, int miss,
			int fast, int slow, int flick, int combo
	) {
		this.notePerfect = perfect;
		this.noteGreat = great;
		this.noteGood = good;
		this.noteBad = bad;
		this.noteMiss = miss;
		this.judgeFast = fast;
		this.judgeSlow = slow;
		this.judgeFlick = flick;
		this.combo = combo;
	}
	
	public ScoreBaseData (int perfect, int great, int good, int bad, int miss) {
		this(perfect, great, good, bad, miss, NULL, NULL, NULL, NULL);
	}
	
	public ScoreBaseData (int perfect, int great, int good, int bad, int miss, int fast, int slow, int flick) {
		this(perfect, great, good, bad, miss, fast, slow, flick, NULL);
	}
	
	public ScoreBaseData (int perfect, int great, int good, int bad, int miss, int combo) {
		this(perfect, great, good, bad, miss, NULL, NULL, NULL, combo);
	}
	
	/**
	 * the count of PERFECT in the score.
	 * <p>
	 * it must exist and means the PERFECT displayed in the final score page.
	 * <p>
	 * dut to some card_skill, the number may be not exactly ture score.
	 * @return the exact PERFECT count.
	 */
	@Nonnegative
	public int perfect() {
		return notePerfect;
	}
	
	/**
	 * the count of GREAT in the score.
	 * <p>
	 * it must exist and means the GREAT displayed in the final score page.
	 * <p>
	 * dut to some card_skill, the number may be not exactly ture score.
	 * @return the exact GREAT count.
	 */
	@Nonnegative
	public int great() {
		return noteGreat;
	}
	
	/**
	 * the count of GOOD in the score.
	 * <p>
	 * it must exist and means the GOOD displayed in the final score page.
	 * <p>
	 * dut to some card_skill, the number may be not exactly ture score.
	 * @return the exact GOOD count.
	 */
	@Nonnegative
	public int good() {
		return noteGood;
	}
	
	/**
	 * the count of BAD in the score.
	 * <p>
	 * it must exist and means the BAD displayed in the final score page.
	 * <p>
	 * dut to some card_skill, the number may be not exactly ture score.
	 * @return the exact BAD count.
	 */
	@Nonnegative
	public int bad() {
		return noteBad;
	}
	
	/**
	 * the count of MISS in the score.
	 * <p>
	 * it must exist and means the MISS displayed in the final score page.
	 * <p>
	 * dut to some card_skill, the number may be not exactly ture score.
	 * @return the exact MISS count.
	 */
	@Nonnegative
	public int miss() {
		return noteMiss;
	}
	
	/**
	 * the count of FAST judgement during gameplay.
	 * <p>
	 * it may return {@code -1} that means this score missed the value.
	 * due to the pjsekai doesn't have fast/slow/flick counting while gameplay,
	 * or the data just missed recording.
	 * <p>
	 * and the number of it sums with {@link #slow()} & {@link #slow()} must be equals with the sum of
	 * {@link #great()} & {@link #good()} & {@link #miss()},
	 * <p>
	 * dut to some card_skill, the number may be not exactly ture score.
	 * @return the count of FAST judgement, or {@code -1} means missing data.
	 */
	@CheckForSigned
	public int fast() {
		return judgeFast;
	}
	
	/**
	 * the count of SLOW judgement during gameplay.
	 * <p>
	 * it may return {@code -1} that means this score missed the value.
	 * due to the pjsekai doesn't have fast/slow/flick counting while gameplay,
	 * or the data just missed recording.
	 * <p>
	 * and the number of it sums with {@link #fast()} & {@link #flick()} must be equals with the sum of
	 * {@link #great()} & {@link #good()} & {@link #miss()},
	 * <p>
	 * dut to some card_skill, the number may be not exactly ture score.
	 * @return the count of SLOW judgement, or {@code -1} means missing data.
	 */
	@CheckForSigned
	public int slow() {
		return judgeSlow;
	}
	
	/**
	 * the count of FLICK judgement during gameplay.
	 * <p>
	 * it may return {@code -1} that means this score missed the value.
	 * due to the pjsekai doesn't have fast/slow/flick counting while gameplay,
	 * or the data just missed recording.
	 * <p>
	 * and the number of it sums with {@link #fast()} & {@link #slow()} must be equals with the sum of
	 * {@link #great()} & {@link #good()} & {@link #miss()},
	 * <p>
	 * and it must be less than {@link #great()}
	 * <p>
	 * dut to some card_skill, the number may be not exactly ture score.
	 * @return the count of FLICK judgement, or {@code -1} means missing data.
	 */
	@CheckForSigned
	public int flick() {
		return judgeFlick;
	}
	
	/**
	 * the count of Max COMBO in final score.
	 * <p>
	 * it may return {@code -1} that means this score missed the value.
	 * maybe due to the data missed recording.
	 * @return the final Max COMBO, or {@code -1} means missing this data.
	 */
	@CheckForSigned
	public int combo() {
		return combo;
	}
	
	@Override
	@Nonnull
	public String toString() {
		return String.format(
				"{\"perfect\":%d,\"great\":%d,\"good\":%d,\"bad\":%d,\"miss\":%d,\"judge\":{\"fast\":%s,\"slow\":%s,\"flick\":%s},\"combo\":%s}",
				notePerfect, noteGreat, noteGood, noteBad, noteMiss,
				judgeFast==NULL ? "null" : judgeFast,
				judgeSlow==NULL ? "null" : judgeFast,
				judgeFlick==NULL ? "null" : judgeFast,
				combo==NULL ? "null" : combo
		);
	}
	
}
