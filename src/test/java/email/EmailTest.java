package email;

import ghostface.dev.email.Email;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class EmailTest {

    @NotNull String @NotNull [] valid = {
            "johndoe@example.com",
            "maria.silva@empresa.com.br",
            "user123@site.org",
            "contato.2024@domain.net",
            "john_doe@example.com",
            "nome+sobrenome@terra.com",
            "shaolin7@gmail.com",
            "henriquesousaa37@gmail.com",
    };

    @NotNull String @NotNull [] invalid = {
            "johndoe@@example.com",
            "maria..silva@empresa.com.br",
            ".user123@site.org",
            "contato@.2024@domain.net",
            "john_doe@.com",
            "name+surname@terra..com",
            "user@site",
    };

    @Test
    public void test() {

        for (@NotNull String str : valid) {
            @NotNull Email email = Email.parse(str);
            Assertions.assertEquals(str, email.toString());
        }

        for (@NotNull String str : invalid) {
            Assertions.assertFalse(Email.validate(str));
        }

    }

}
