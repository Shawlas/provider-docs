package codes.shawlas.email;

import codes.shawlas.email.domain.Domain;
import codes.shawlas.email.localpart.LocalPart;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class Email implements CharSequence {

    public static boolean validate(@NotNull String str) {
        @NotNull String email = str.replace("(at)", "@").replace("(dot)", ".");
        if (email.length() > 254) return false;

        @NotNull String @NotNull [] parts = email.split("@");

        if (parts.length != 2) {
            return false;
        } else {
            return LocalPart.validate(parts[0]) && Domain.validate(parts[1]);
        }
    }

    public static @NotNull Email parse(@NotNull String str) {
        @NotNull String email = str.replace("(at)", "@").replace("(dot)", ".");
        if (email.length() > 254) throw new IllegalArgumentException("the email '" + str + "' is not a valid email");

        @NotNull String @NotNull [] parts = email.split("@");
        if (parts.length != 2) throw new IllegalArgumentException("the email '" + str + "' is not a valid email");

        return new Email(new LocalPart(parts[0]), Domain.parse(parts[1]));
    }

    // Objects

    private final @NotNull LocalPart localPart;
    private final @NotNull Domain domain;

    public Email(@NotNull LocalPart localPart, @NotNull Domain domain) {
        this.localPart = localPart;
        this.domain = domain;
    }

    @Override
    @Contract(pure = true)
    public @NotNull String toString() {
        return localPart + "@" + domain;
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
        @NotNull Email email = (Email) object;
        return Objects.equals(localPart, email.localPart) && Objects.equals(domain, email.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localPart, domain);
    }
}
