import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
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
    private String name;
    private IngredientType type;
    private float price;


    @Parameterized.Parameters
    public static Object[] data() {
        return new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100},
                {IngredientType.SAUCE, "sour cream", 200},
                {IngredientType.SAUCE, "chili sauce", 300},
                {IngredientType.FILLING, "cutlet", 100},
                {IngredientType.FILLING, "dinosaur", 200},
                {IngredientType.FILLING, "sausage", 300},
                {IngredientType.SAUCE, "", 0},
                {IngredientType.SAUCE, "", -100},
        };
    }

    @Test
    public void setBunsTest() {
        Bun bun = new Bun(name, price);
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(new Ingredient(type, name, price));
        assertFalse("Ингредиенты пусты :(", burger.ingredients.isEmpty());
    }

    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(new Ingredient(type, name, price));
        burger.removeIngredient(0);
        assertTrue("Ингредиенты не удалились :(", burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "hot sauce", 100));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "cutlet", 100));
        burger.moveIngredient(0, 1);
        String expectedResult = "cutlet";
        String actualResult = burger.ingredients.get(0).name;
        assertEquals("Не удалось поменять ингредиенты местами :(", expectedResult, actualResult);
    }

    @Test
    public void getPriceTest() {
        Bun bun = new Bun("white bun", 200);
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "hot sauce", 100));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "dinosaur", 200));
        float expectedPrice = 700;
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Mock
    Ingredient ingredientMock;

    @Test
    public void getReceiptTest() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        String result = String.format("(==== Флюоресцентная булка R2-D3 ====)%n" + "= filling Говяжий метеорит(отбивная) =%n" + "(==== Флюоресцентная булка R2-D3 ====)%n" + "%n" + "Price: 600,000000%n");

        burger.addIngredient(ingredientMock);

        Mockito.when(bun.getPrice()).thenReturn(200F);
        Mockito.when(ingredientMock.getPrice()).thenReturn(200F);
        Mockito.when(bun.getName()).thenReturn("Флюоресцентная булка R2-D3");
        Mockito.when(ingredientMock.getName()).thenReturn("Говяжий метеорит(отбивная)");
        Mockito.when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);

        Assert.assertEquals(result, burger.getReceipt());
    }
}

