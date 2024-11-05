package ghostface.dev.email.localpart;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static ghostface.dev.main.Main.*;

public final class LocalPart implements CharSequence {

    public static boolean validate(@NotNull String localPart) {
        if (localPart.startsWith(".") || localPart.endsWith(".")) {
            return false;
        } else if (hasSpace(localPart)) {
            return false;
        } else if (hasSequence(localPart, dot)) {
            return false;
        } else

        return localPart.matches("^[A-Za-z0-9.!#$%&'*+-/=?^_`{|}~]{1,64}$");
    }

    // Objects

    private final @NotNull String string;

    public LocalPart(@NotNull String string) {
        if (!validate(string)) throw new IllegalArgumentException("The string '" + string + "' is not a valid email local part" );
        this.string = string;
    }

    @Override
    @Contract(pure = true)
    public @NotNull String toString() {
        return string;
    }

    @Override
    @Contract(pure = true)
    public int length() {
        return string.length();
    }

    @Override
    @Contract(pure = true)
    public char charAt(int index) {
        return toString().charAt(index);
    }

    @Override
    @Contract(pure = true)
    public @NotNull CharSequence subSequence(int start, int end) {
        return toString().subSequence(start, end);
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull LocalPart localPart = (LocalPart) object;
        return string.equalsIgnoreCase(localPart.string);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(string.toLowerCase());
    }
}
