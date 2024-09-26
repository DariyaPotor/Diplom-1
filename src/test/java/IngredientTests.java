import static constants.IngredientConstants.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import praktikum.Ingredient;

public class IngredientTests {

    @Test
    public void getPriceTest() {
        Ingredient ingredient = new Ingredient(INGREDIENT_TYPE, INGREDIENT_NAME, INGREDIENT_PRICE);
        assertEquals("Не удалось посчитать цену ингредиента :(", INGREDIENT_TYPE, ingredient.getType());
    }
    @Test
    public void getNameTest(){
        Ingredient ingredient = new Ingredient(INGREDIENT_TYPE, INGREDIENT_NAME, INGREDIENT_PRICE);
        assertEquals("Не удалось узнать имя ингредиента :(", INGREDIENT_NAME, ingredient.getName());
    }
    @Test
    public void getTypeTest(){
        Ingredient ingredient = new Ingredient(INGREDIENT_TYPE, INGREDIENT_NAME, INGREDIENT_PRICE);
        assertEquals("Не удалось узнать тип ингредиента :(", INGREDIENT_TYPE, ingredient.getType());
    }
}
