import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredientMock;

    @Test
    public void setBunsTest() {
        Bun bunStub = new Bun("stubBun", 100);
        Burger burger = new Burger();
        burger.setBuns(bunStub);
        assertEquals(bunStub, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        Burger burger = new Burger();
        Ingredient ingredientStub = new Ingredient(IngredientType.SAUCE, "stubSauce", 50);
        burger.addIngredient(ingredientStub);
        assertFalse("Ингредиенты пусты :(", burger.ingredients.isEmpty());
    }

    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();
        Ingredient ingredientStub = new Ingredient(IngredientType.FILLING, "stubFilling", 75);
        burger.addIngredient(ingredientStub);
        burger.removeIngredient(0);
        assertTrue("Ингредиенты не удалились :(", burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();
        Ingredient sauceStub = new Ingredient(IngredientType.SAUCE, "stubSauce", 40);
        Ingredient fillingStub = new Ingredient(IngredientType.FILLING, "stubFilling", 80);
        burger.addIngredient(sauceStub);
        burger.addIngredient(fillingStub);
        burger.moveIngredient(0, 1);
        String expectedResult = "stubFilling";
        String actualResult = burger.ingredients.get(0).name;
        assertEquals("Не удалось поменять ингредиенты местами :(", expectedResult, actualResult);
    }

    @Test
    public void getPriceTest() {
        Bun bunStub = new Bun("white bun", 200);
        Burger burger = new Burger();
        burger.setBuns(bunStub);
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "hot sauce", 100));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "dinosaur", 200));
        float expectedPrice = 700;
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void getReceiptTest() {
        Burger burger = new Burger();
        burger.setBuns(bun);

        burger.addIngredient(ingredientMock);

        Mockito.when(bun.getPrice()).thenReturn(200F);
        Mockito.when(ingredientMock.getPrice()).thenReturn(200F);
        Mockito.when(bun.getName()).thenReturn("Флюоресцентная булка R2-D3");
        Mockito.when(ingredientMock.getName()).thenReturn("Говяжий метеорит(отбивная)");
        Mockito.when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);

        String result = String.format("(==== Флюоресцентная булка R2-D3 ====)%n" +
                "= filling Говяжий метеорит(отбивная) =%n" +
                "(==== Флюоресцентная булка R2-D3 ====)%n" +
                "%n" +
                "Price: 600,000000%n");

        Assert.assertEquals(result, burger.getReceipt());
    }
}