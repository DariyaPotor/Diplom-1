import org.junit.Test;
import praktikum.Bun;
import static constants.BunConstants.*;

import static org.junit.Assert.assertEquals;

public class BunTests  {

    @Test
    public void getBunNameTest(){
        Bun bun = new Bun(FLORESCENT_BUN, FLORESCENT_PRICE);
        assertEquals(FLORESCENT_BUN, bun.getName());
    }
    @Test
    public void getBunPriceTest(){
        Bun bun = new Bun(CRATER_BUN, CRATER_PRICE);
        assertEquals(CRATER_PRICE, bun.getPrice(), DELTA);
    }
}
