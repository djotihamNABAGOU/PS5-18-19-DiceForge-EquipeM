package diceforge;

import Faces.SanctuarysFaces;
import Faces.SimpleFace;
import org.junit.Test;

import static org.junit.Assert.*;

public class TempleTest {

    @Test
    public void faceAvailable() {
        Temple temple = new Temple();
        SanctuarysFaces sanctuarysFaces = new SanctuarysFaces(2,"GoldenFace","Add",new SimpleFace(3,"G","GoldenFace"));
        assertTrue(temple.faceAvailable(sanctuarysFaces)!=-1);//Vrai car cette face est présente 4 fois en stock
        for (int i=0; i<4;i++){
            temple.buyFace(sanctuarysFaces);
        }
        assertFalse(temple.faceAvailable(sanctuarysFaces)!=-1);//En effet, après 4 achats, le stock de cette face est nul
    }

    @Test
    public void buyFace() {
        Temple temple = new Temple();
        SanctuarysFaces sanctuarysFaces = new SanctuarysFaces(2,"GoldenFace","Add",new SimpleFace(3,"G","GoldenFace"));
        for (int i=0; i<4;i++){
            temple.buyFace(sanctuarysFaces);
        }
        assertFalse(temple.buyFace(sanctuarysFaces));//On ne peut plus payer car il y en avait que 4 en stock de cette face
    }
}