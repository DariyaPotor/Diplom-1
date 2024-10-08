import org.junit.Test;
import praktikum.IngredientType;

import static org.junit.Assert.assertNotNull;

public class IngredientTypeTests {
    @Test
    public void ingredientSauceTest() {
        assertNotNull("Тип соус не найден :(", IngredientType.SAUCE);
    }

    @Test
    public void ingredientFillingTest() {
        assertNotNull("Тип начинка не найден :(", IngredientType.FILLING);
    }
}
