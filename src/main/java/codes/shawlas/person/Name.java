package codes.shawlas.person;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.Objects;
import java.util.regex.Pattern;

import static codes.shawlas.Main.hasSequence;

public final class Name implements CharSequence {

    public static boolean validate(
            @NotNull String name,
            @Range(from = 1, to = Integer.MAX_VALUE - 1) int min,
            @Range(from = 1, to = Integer.MAX_VALUE) int max
    ) {
        if (min > max) {
            return false;
        } else if (hasSequence(name, Pattern.compile("\\s"))) {
            return false;
        } else {
            return name.length() > min && name.length() <= max && name.matches("^[A-Za-z\\s]+$");
        }
    }

    // Objects

    private final @NotNull String string;
    @Range(from = 1, to = Integer.MAX_VALUE - 1)
    private final int min;
    @Range(from = 1, to = Integer.MAX_VALUE)
    private final int max;

    public Name(
            @NotNull String string,
            @Range(from = 1, to = Integer.MAX_VALUE - 1) int min,
            @Range(from = 1, to = Integer.MAX_VALUE) int max
    ) {
        if (!validate(string, min, max)) throw new IllegalArgumentException("Invalid name");
        this.string = string;
        this.min = min;
        this.max = max;
    }

    @Range(from = 1, to = Integer.MAX_VALUE - 1)
    public int getMin() {
        return min;
    }

    @Range(from = 1, to = Integer.MAX_VALUE)
    public int getMax() {
        return max;
    }

    @Override
    public @NotNull String toString() {
        return string;
    }

    @Override
    public int length() {
        return toString().length();
    }

    @Override
    public char charAt(int index) {
        return toString().charAt(index);
    }

    @Override
    public @NotNull CharSequence subSequence(int start, int end) {
        return toString().subSequence(start, end);
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull Name name = (Name) object;
        return string.equalsIgnoreCase(name.string);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(string.toLowerCase());
    }
}
