package diceforge;

import org.junit.Test;

import static org.junit.Assert.*;

public class TempleTest {

    @Test
    public void faceAvailable() {
        Temple temple = new Temple();
        assertTrue(temple.faceAvailable(new SanctuarysFaces(1,"M",2))!=-1);
        for (int i=0; i<4;i++){
            temple.buyFace(new SanctuarysFaces(1,"M",2));
        }
        assertFalse(temple.faceAvailable(new SanctuarysFaces(1,"M",2))!=-1);
    }

    @Test
    public void buyFace() {
        Temple temple = new Temple();
        for (int i=0; i<4;i++){
            temple.buyFace(new SanctuarysFaces(1,"M",2));
        }
        assertFalse(temple.buyFace(new SanctuarysFaces(1,"M",2)));
    }

}