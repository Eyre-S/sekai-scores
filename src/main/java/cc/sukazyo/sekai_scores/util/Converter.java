package cc.sukazyo.sekai_scores.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Converter {
	
	@Nonnull
	public static String parseJSONString (@Nullable String input) {
		if (input == null) return "null";
		return "\"" + input.replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\\\"") + "\"";
	}
	
}
