package cc.sukazyo.sekai_db;

public class Util {
	
	public static int[] arraysShortToInt (final Short[] source) {
		if (source == null) return null;
		final int[] result = new int[source.length];
		for (int i = 0; i < source.length; i++) {
			result[i] = source[i];
		}
		return result;
	}
	
}
