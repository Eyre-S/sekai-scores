package cc.sukazyo.sekai_scores.util;

import javax.annotation.Nonnull;

public class Converter {
	
	@Nonnull
	public static String parseJSONString (@Nonnull String input) {
		return input.replaceAll("'", "\\'").replaceAll("\"", "\\\"");
	}
	
}
