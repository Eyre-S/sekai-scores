package cc.sukazyo.sekai_scores;

import javax.annotation.Nonnull;
import java.util.function.BiConsumer;

public interface Difficulties {
	
	void forEach(@Nonnull BiConsumer<String, Difficulty> action);
	
}
