package codes.shawlas.email.domain;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import static codes.shawlas.Main.*;

public final class SLD implements CharSequence {

    // Static initializer

    public static boolean validate(@NotNull String sld) {
        if (sld.startsWith("-") || sld.endsWith("-")) {
            return false;
        } else if (hasSpace(sld)) {
            return false;
        } else if (hasSequence(sld, hyphen)) {
            return false;
        } else

        return sld.matches("^[A-Za-z0-9-]{1,63}$");
    }

    // Objects

    private final @NotNull String string;

    public SLD(@NotNull String string) {
        if (!validate(string)) throw new IllegalArgumentException("The string '" + string + "' is not a valid SLD");
        this.string = string.toLowerCase();
    }

    // CharSequence Implementations

    @Override
    @Contract(pure = true)
    public @NotNull String toString() {
        return string;
    }

    @Override
    @Contract(pure = true)
    public int length() {
        return toString().length();
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
        @Nullable SLD sld = (SLD) object;
        return string.equalsIgnoreCase(sld.string);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(string);
    }
}
