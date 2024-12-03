package email;

import codes.shawlas.email.domain.Domain;
import codes.shawlas.email.domain.SLD;
import codes.shawlas.email.domain.SubDomain;
import codes.shawlas.email.domain.TLD;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class DomainsTest {

    @Test
    @DisplayName("tests the creation of a domain")
    public void test() {
        @NotNull String @NotNull [] valid = {
                "google.com",
                "example.com",
                "wikipedia.org",
                "github.com",
                "openai.com",
                "microsoft.com",
                "apple.com",
                "amazon.com",
                "netflix.com",
        };

        @NotNull String @NotNull [] validWithSubDomain = {
                "mail.google.com",
                "support.microsoft.com",
                "developer.apple.com",
                "blog.github.com",
                "portal.amazon.com",
                "en.wikipedia.org",
                "status.openai.com",
                "community.netflix.com",
                "news.yahoo.com",
        };

        @NotNull String @NotNull [] invalid = {
                "-exemplo.com",
                "exemplo-.net",
                "exemplo!@#.com",
                "site ex.com",
                "exemplo.abc",
                "meu.site.123",
                "exemplo .com",
                "domínio$.org",
                "a-very-very-very-very-very-very-very-long-domain-name-that-exceeds-the-allowed-character-limit.com",
        };

        for (@NotNull String str : valid) {
            @NotNull Domain domain = Domain.parse(str);
            Assertions.assertEquals(str, domain.toString());
            Assertions.assertEquals(str.split("\\.")[0], domain.getSld().toString());
            Assertions.assertEquals(str.split("\\.")[1], domain.getTld().toString());
        }

        for (@NotNull String str : validWithSubDomain) {
            @NotNull Domain domain = Domain.parse(str);
            Assertions.assertEquals(str, domain.toString());
            Assertions.assertEquals(str.split("\\.")[0], domain.getSubdomains()[0].toString());
            Assertions.assertEquals(str.split("\\.")[1], domain.getSld().toString());
            Assertions.assertEquals(str.split("\\.")[2], domain.getTld().toString());
        }

        @NotNull Domain domain = Domain.parse("shop.bbc.co.uk");
        Assertions.assertEquals("shop.bbc.co.uk", domain.toString());

        Assertions.assertTrue(domain.getSubdomain("shop").isPresent());
        Assertions.assertTrue(domain.getSubdomain("bbc").isPresent());
        Assertions.assertFalse(domain.getSubdomain("co").isPresent());

        Assertions.assertEquals("shop", domain.getSubdomain("shop").get().toString());
        Assertions.assertEquals("bbc", domain.getSubdomain("bbc").get().toString());

        for (@NotNull String str : invalid) {
            try {
                domain = Domain.parse(str);
                Assertions.fail("The domain '" + str + "' is not a valid domain");
            } catch (@NotNull Throwable t) {
                Assertions.assertTrue(true);
            }
        }
    }

    @Test
    public void domainTest() {
        Assertions.assertTrue(Domain.validate("gmail.com.br"));
        Assertions.assertTrue(Domain.validate("hotmail.com"));
        Assertions.assertTrue(Domain.validate("example.com.org"));
        Assertions.assertTrue(Domain.validate("gmail-com.org"));

        Assertions.assertFalse(Domain.validate("example..com.org"));
        Assertions.assertFalse(Domain.validate(".example.com.org."));
        Assertions.assertFalse(Domain.validate("-example.com.org-"));
    }

    @Test
    public void subdomainTest() {
        Assertions.assertTrue(SubDomain.validate("gmail"));
        Assertions.assertTrue(SubDomain.validate("hotmail"));
        Assertions.assertTrue(SubDomain.validate("outlook"));
        Assertions.assertTrue(SubDomain.validate("com"));

        Assertions.assertFalse(SubDomain.validate("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
        Assertions.assertFalse(SubDomain.validate("gmail.com"));
    }

    @Test
    public void sldTest() {
        Assertions.assertTrue(SLD.validate("gmail"));
        Assertions.assertTrue(SLD.validate("hotmail"));
        Assertions.assertTrue(SLD.validate("outlook"));
        Assertions.assertTrue(SLD.validate("com"));

        Assertions.assertFalse(SLD.validate("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"));
        Assertions.assertFalse(SLD.validate("gmail.com"));
    }

    @Test
    public void tldTest() {
        Assertions.assertTrue(TLD.validate("com"));
        Assertions.assertTrue(TLD.validate("org"));
        Assertions.assertTrue(TLD.validate("br"));
        Assertions.assertTrue(TLD.validate("xyz"));
        Assertions.assertTrue(TLD.validate("XYZ"));
        Assertions.assertTrue(TLD.validate("cOm"));
        Assertions.assertTrue(TLD.validate("java"));

        Assertions.assertFalse(TLD.validate("JavaIsBetterThanJS"));
        Assertions.assertFalse(TLD.validate("I am Batman"));
    }
}
