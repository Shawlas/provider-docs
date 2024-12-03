package codes.shawlas;

import codes.shawlas.email.Email;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
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

    public static void main(@NotNull String @NotNull [] args) {
        @NotNull String msg = "para mais contato acesse contato@gmail.com ou contatodois@gmail.com";

        System.out.println(findEmails(msg));
    }

    private static @NotNull List<String>  findEmails(@NotNull String string) {
        @NotNull String @NotNull [] str = string.split("\\s");
        @NotNull List<String> list = new ArrayList<>();

        for (@NotNull String s : str) {
            if (Email.validate(s)) list.add(s);
        }

        return list;
    }

    private Main() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}
