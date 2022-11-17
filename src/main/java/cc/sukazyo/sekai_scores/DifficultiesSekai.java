package cc.sukazyo.sekai_scores;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;

public record DifficultiesSekai(
		@Nonnull Difficulty easy,
		@Nonnull Difficulty normal,
		@Nonnull Difficulty hard,
		@Nonnull Difficulty expert,
		@Nonnull Difficulty master
) implements Difficulties {
	
	public static final String EASY_NAME = "EASY";
	public static final String NORMAL_NAME = "NORMAL";
	public static final String HARD_NAME = "HARD";
	public static final String EXPERT_NAME = "EXPERT";
	public static final String MASTER_NAME = "MASTER";
	
	@Override
	public void forEach (@Nonnull BiConsumer<String, Difficulty> action) {
		action.accept(EASY_NAME, easy);
		action.accept(NORMAL_NAME, normal);
		action.accept(HARD_NAME, hard);
		action.accept(EXPERT_NAME, expert);
		action.accept(MASTER_NAME, master);
	}
	
}
