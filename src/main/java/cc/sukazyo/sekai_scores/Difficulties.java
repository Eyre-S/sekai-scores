package cc.sukazyo.sekai_scores;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public interface Difficulties {
	
	Difficulty[] getAll ();
	
	void forEach(@Nonnull Consumer<Difficulty> action);
	
}
