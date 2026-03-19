package chess;

public class TerminalUtils {

    private static boolean unicodeSupported = checkUnicodeSupport();

    private static boolean checkUnicodeSupport() {
        String encoding = System.getProperty("file.encoding", "").toLowerCase();
        String osName = System.getProperty("os.name", "").toLowerCase();

        if (encoding.contains("utf") || encoding.contains("unicode")) {
            return true;
        }

        if (osName.contains("windows")) {
            String cp = System.getProperty("sun.stdout.encoding", "").toLowerCase();
            return cp.contains("utf") || cp.equals("65001");
        }

        return true;
    }

    public static boolean isUnicodeSupported() {
        return unicodeSupported;
    }
}