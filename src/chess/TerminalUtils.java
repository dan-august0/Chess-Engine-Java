package chess;

import java.io.PrintStream;

public class TerminalUtils {

    private static boolean unicodeSupported = checkUnicodeSupport();

    private static boolean checkUnicodeSupport() {
        try {
            PrintStream out = new PrintStream(System.out, true, "UTF-8");
            out.print("");
            String osName = System.getProperty("os.name", "").toLowerCase();
            
            if (osName.contains("windows")) {
                // No Windows testa imprimindo um símbolo e verificando
                byte[] bytes = "♙".getBytes("UTF-8");
                return System.out.charset().name().toLowerCase().contains("utf");
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isUnicodeSupported() {
        return unicodeSupported;
    }
}