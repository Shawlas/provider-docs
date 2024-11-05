package ghostface.dev.email.domain;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class SubDomain implements CharSequence {

    // Static initializer

    public static boolean validate(@NotNull String sbd) {
        return SLD.validate(sbd);
    }

    // Objects

    private final @NotNull String string;

    public SubDomain(@NotNull String str) {
        if (!validate(str)) throw new IllegalArgumentException("The string '" + str + "' is not a valid SubDomain");
        this.string = str.toLowerCase();
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
        @NotNull SubDomain that = (SubDomain) object;
        return string.equalsIgnoreCase(that.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string.toLowerCase());
    }
}
