package CPF;

import ghostface.dev.person.CPF;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class CPFTest {

    @NotNull String @NotNull [] valid = {
            "09357215093",
            "05526291030",
            "21035582007",
            "12345678909",
            "98765432100",
            "42364577098",
            "94490621027",
            "53909762077",
            "09958132052"
    };

    @NotNull String @NotNull [] validFormated = {
            "093.572.150-93",
            "055.262.910-30",
            "210.355.820-07",
            "123.456.789-09",
            "987.654.321-00",
            "423.645.770-98",
            "944.906.210-27",
            "539.097.620-77",
            "099.581.320-52"
    };

    @Test
    public void test() {

        for (@NotNull String str : valid) {
            Assertions.assertEquals(str, new CPF(str).toString());
            Assertions.assertTrue(CPF.validate(str));
        }

        for (@NotNull String str : validFormated) {
            Assertions.assertEquals(str, new CPF(str).getFormated());
            Assertions.assertTrue(CPF.validate(str));
        }

    }

}
