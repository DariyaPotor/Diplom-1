import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredientMock;

    @InjectMocks
    Burger burger;

    @Test
    public void setBunsTest() {
        when(bun.getName()).thenReturn("stubBun");
        when(bun.getPrice()).thenReturn(100F);
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        when(ingredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock.getName()).thenReturn("stubSauce");
        when(ingredientMock.getPrice()).thenReturn(50F);
        burger.addIngredient(ingredientMock);
        assertFalse("Ингредиенты пусты :(", burger.ingredients.isEmpty());
    }

    @Test
    public void removeIngredientTest() {
        when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock.getName()).thenReturn("stubFilling");
        when(ingredientMock.getPrice()).thenReturn(75F);
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);
        assertTrue("Ингредиенты не удалились :(", burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientTest() {
        Ingredient sauceStub = mock(Ingredient.class);
        Ingredient fillingStub = mock(Ingredient.class);
        when(sauceStub.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceStub.getName()).thenReturn("stubSauce");
        when(sauceStub.getPrice()).thenReturn(40F);
        when(fillingStub.getType()).thenReturn(IngredientType.FILLING);
        when(fillingStub.getName()).thenReturn("stubFilling");
        when(fillingStub.getPrice()).thenReturn(80F);
        burger.addIngredient(sauceStub);
        burger.addIngredient(fillingStub);
        burger.moveIngredient(0, 1);
        String expectedResult = "stubFilling";
        String actualResult = burger.ingredients.get(0).getName();
        assertEquals("Не удалось поменять ингредиенты местами :(", expectedResult, actualResult);
    }

    @Test
    public void getPriceTest() {
        when(bun.getPrice()).thenReturn(200F);
        Ingredient sauceStub = mock(Ingredient.class);
        Ingredient fillingStub = mock(Ingredient.class);
        when(sauceStub.getPrice()).thenReturn(100F);
        when(fillingStub.getPrice()).thenReturn(200F);
        burger.setBuns(bun);
        burger.addIngredient(sauceStub);
        burger.addIngredient(fillingStub);
        float expectedPrice = 700F;
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void getReceiptTest() {
        when(bun.getPrice()).thenReturn(200F);
        when(ingredientMock.getPrice()).thenReturn(200F);
        when(bun.getName()).thenReturn("Флюоресцентная булка R2-D3");
        when(ingredientMock.getName()).thenReturn("Говяжий метеорит(отбивная)");
        when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);
        burger.setBuns(bun);
        burger.addIngredient(ingredientMock);
        String result = String.format("(==== Флюоресцентная булка R2-D3 ====)%n" +
                "= filling Говяжий метеорит(отбивная) =%n" +
                "(==== Флюоресцентная булка R2-D3 ====)%n" +
                "%n" +
                "Price: 600,000000%n");
        Assert.assertEquals(result, burger.getReceipt());
    }
}