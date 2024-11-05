package ghostface.dev.main;

import org.jetbrains.annotations.NotNull;


public final class Main {

    public static @NotNull String hyphen = "-";
    public static @NotNull String dot = "\\.";

    public static boolean hasSequence(@NotNull String str, @NotNull String chr) {
        for (@NotNull String string : str.split(chr)) {
            if (string.isEmpty()) return true;
        }

        return false;
    }

    public static boolean hasSpace(@NotNull String str) {
        return str.split("\\s").length != 1;
    }

    public static void main(@NotNull String @NotNull [] args) {
    }
}
