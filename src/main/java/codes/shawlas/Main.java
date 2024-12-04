package codes.shawlas;

import org.jetbrains.annotations.NotNull;
import java.util.regex.Pattern;


public final class Main {

    public static @NotNull String hyphen = "-";
    public static @NotNull String dot = "\\.";

    public static boolean hasSequence(@NotNull String str, @NotNull Pattern regex) {
        for (@NotNull String string : str.split(regex.pattern())) {
            if (string.isEmpty()) return true;
        }
        return false;
    }

    public static boolean hasSequence(@NotNull String str, @NotNull String chr) {
        for (@NotNull String string : str.split(chr)) {
            if (string.isEmpty()) return true;
        }
        return false;
    }

    public static boolean hasSpace(@NotNull String str) {
        return str.split("\\s").length != 1;
    }

    private Main() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}
