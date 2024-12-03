package codes.shawlas.email.domain;

import codes.shawlas.exception.DomainFormatException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

import static codes.shawlas.Main.*;

public final class Domain implements CharSequence {

    public static boolean validate(@NotNull String domain) {
        @NotNull String str = domain.replace("(dot)", ".");

        if (str.startsWith("-") || str.startsWith(".") || str.endsWith("-") || str.endsWith(".")) {
            return false;
        } else if (hasSequence(domain, dot)) {
            return false;
        } else if (hasSpace(domain)) {
            return false;
        } else if (!domain.matches("^[A-Za-z0-9-.]{3,253}$")) {
            return false;
        } else {
            @NotNull String @NotNull [] parts = domain.split("\\.");
            if (parts.length == 1) return false;

            if ((parts.length - 2) > 0) {
                for (int i = 0; i < parts.length - 2; i++) {
                    if (!SubDomain.validate(parts[i])) return false;
                }
            }

            return SLD.validate(parts[parts.length - 2]) && TLD.validate(parts[parts.length - 1]);
        }
    }

    public static @NotNull Domain parse(@NotNull String string) {
        @NotNull String domain = string.replace("(dot)", ".");
        @NotNull String @NotNull [] parts = domain.split("\\.");

        if (parts.length == 1) throw new DomainFormatException("Domain must have an TLD");

        try {
            @NotNull SubDomain @NotNull [] subDomain = new SubDomain[parts.length - 2];

            if (subDomain.length > 0) {
                for (int i = 0; i < parts.length - 2; i++) {
                    subDomain[i] = new SubDomain(parts[i]);
                }
            }

            return new Domain(subDomain, new SLD(parts[parts.length - 2]), new TLD(parts[parts.length - 1]));

        } catch (@NotNull Throwable e) {
            throw new DomainFormatException("Cannot parse '" + string  + "' as a valid domain");
        }
    }

    // Objects

    private final @NotNull SubDomain @NotNull [] subdomains;
    private final @NotNull SLD sld;
    private final @NotNull TLD tld;

    public Domain(@NotNull SubDomain @NotNull [] subdomains, @NotNull SLD sld, @NotNull TLD tld) {
        this.subdomains = subdomains;
        this.sld = sld;
        this.tld = tld;
    }

    public Domain(@NotNull SLD sld, @NotNull TLD tld) {
        this.subdomains = new SubDomain[0];
        this.sld = sld;
        this.tld = tld;
    }

    public @NotNull SubDomain @NotNull [] getSubdomains() {
        return subdomains;
    }

    public @NotNull SLD getSld() {
        return sld;
    }

    public @NotNull TLD getTld() {
        return tld;
    }

    public @NotNull Optional<SubDomain> getSubdomain(@NotNull String subDomain) {
        return Arrays.stream(subdomains).filter(sb -> sb.toString().equalsIgnoreCase(subDomain)).findFirst();
    }

    // CharSequence Implementations

    @Override
    @Contract(pure = true)
    public @NotNull String toString() {
        @NotNull StringJoiner joiner = new StringJoiner(".");

        for (@NotNull SubDomain subDomain : subdomains) {
            joiner.add(subDomain);
        }

        joiner.add(sld).add(tld);

        return joiner.toString();
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
        @NotNull Domain domain = (Domain) object;
        return Objects.deepEquals(subdomains, domain.subdomains) && Objects.equals(sld, domain.sld) && Objects.equals(tld, domain.tld);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(subdomains), sld, tld);
    }
}
