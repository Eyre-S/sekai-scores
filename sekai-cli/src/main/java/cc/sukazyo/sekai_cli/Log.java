package cc.sukazyo.sekai_cli;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Log {
	
	private static boolean IS_DEBUG = false;
	
	public static void enableDebugging () {
		if (IS_DEBUG) _debug("trying to enable debug: already in debug mode.");
		else {
			IS_DEBUG = true;
			_debug("enabled debug\n  cli will output the debug log now.");
		}
	}
	
	public static void _debug (String m) {
		if (!isDebug()) return;
		m = "[DEBUG]" + m;
		m = m.replaceAll("\\n", "\n'''''''");
		System.out.println(m);
	}
	
	public static void _debug (Exception e) {
		final StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		_debug(sw.toString());
	}
	
	public static void _user (String m) {
		m = "sekai-cli: " + m;
		m = m.replaceAll("\\n", "\nsekai-cli: ");
		System.out.println(m);
	}
	
	private static boolean isDebug () { return IS_DEBUG; }
	
}
