package cc.sukazyo.sekai_scores;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class DifficultiesEmpty implements Difficulties{
	
	@Override
	public Difficulty[] getAll () {
		return new Difficulty[0];
	}
	
	@Override
	public void forEach (@Nonnull Consumer<Difficulty> action) {}
	
}
