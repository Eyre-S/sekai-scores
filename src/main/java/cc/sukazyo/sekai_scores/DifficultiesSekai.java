package cc.sukazyo.sekai_scores;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

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
	public void forEach (@Nonnull Consumer<Difficulty> action) {
		action.accept(easy);
		action.accept(normal);
		action.accept(hard);
		action.accept(expert);
		action.accept(master);
	}
	
	@Override
	@Nonnull
	public String toString() {
		return String.format(
				"[%s,%s,%s,%s,%s]",
				easy,normal,hard,expert,master
		);
	}
	
}
