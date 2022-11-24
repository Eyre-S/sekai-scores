package cc.sukazyo.sekai_cli;

public class Log {
	
	private static final boolean IS_DEBUG = true;
	
	public static void _debug (String m) {
		if (!isDebug()) return;
		m = "[DEBUG]" + m;
		m = m.replaceAll("\\n", "'''''''");
		System.out.println(m);
	}
	
	private static boolean isDebug () { return IS_DEBUG; }
	
}
