package name;

import codes.shawlas.person.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class NameTest {

    @Test
    public void test() {
        Assertions.assertFalse(Name.validate("Tony Stark", 12, 28));
        Assertions.assertFalse(Name.validate("Tony Stark", 16, 11));
        Assertions.assertFalse(Name.validate("Tony  Stark", 10, 28));
        Assertions.assertFalse(Name.validate("Tony Sta  rk", 10, 28));

        Assertions.assertTrue(Name.validate("Leandro Vasconcelos Ferreira da Silva Fonseca", 3, 50));
    }

}
