package codes.shawlas.person;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.util.Objects;

// Todo #getLocal()
public final class CPF implements CharSequence {

    public static boolean validate(@NotNull String string) {
        @NotNull String cpf = string.replace(".", "").replace("-", "");

        if (!cpf.matches("^[0-9]{11}$")) {
            return false;
        }

        return computeIndex(cpf, 9) && computeIndex(cpf, 10);
    }

    private static boolean computeIndex(final @NotNull String cpf, @Range(from = 9, to = 10) int index) {
        if (cpf.length() != 11) return false;

        @Range(from = 0, to = 9)
        int numericValue = Character.getNumericValue(cpf.charAt(index));

        @Range(from = 10, to = 11)
        int multiplier = index + 1;

        @Range(from = 1, to = Integer.MAX_VALUE)
        int sum = 0;

        for (int i = 0; i < index; i++) {
            int digit = Character.getNumericValue(cpf.charAt(i)) * multiplier;
            sum += digit;
            multiplier--;
        }

        @Range(from = 1, to = Integer.MAX_VALUE)
        int result = (sum * 10) % 11;

        return result == 10 ? 0 == numericValue : result == numericValue;
    }

    private static @NotNull String format(@NotNull String s) {
        @NotNull String cpf = s.replace(".", "").replace("-", "");

        if (!cpf.matches("^[0-9]{11}$")) {
            throw new IllegalArgumentException("invalid CPF String");
        }

        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
    }

    // Objects

    private final @NotNull String string;

    public CPF(@NotNull String string) {
        if (!validate(string)) throw new IllegalArgumentException("The string '" + string + "' is not valid CPF");
        this.string = string.replace(".","").replace("-", "");
    }

    public @NotNull String getFormated() {
        return format(string);
    }

    @Override
    public @NotNull String toString() {
        return string;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull CPF cpf = (CPF) object;
        return Objects.equals(string, cpf.string);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(string);
    }

    // CharSequence Implementations

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
}
