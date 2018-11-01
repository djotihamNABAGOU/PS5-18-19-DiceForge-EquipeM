package diceforge;

import org.junit.Test;

import static org.junit.Assert.*;

public class TempleTest {

    @Test
    public void faceAvailable() {
        //test faux car de 1, le == au lieu de equals ne me rassure pas meme si ca marche
        Temple temple = new Temple();
        assertTrue(temple.faceAvailable(new SanctuarysFaces(1,"M",2))!=-1);//Ok
        temple.buyFace(new SanctuarysFaces(1,"M",2));
        temple.buyFace(new SanctuarysFaces(1,"M",2));
        temple.buyFace(new SanctuarysFaces(1,"M",2));
        temple.buyFace(new SanctuarysFaces(1,"M",2));
        temple.buyFace(new SanctuarysFaces(1,"M",2));
        temple.buyFace(new SanctuarysFaces(1,"M",2));
        temple.buyFace(new SanctuarysFaces(1,"M",2));
        //temple.buyFace(new SanctuarysFaces(1,"M",2));
        assertTrue(temple.faceAvailable(new SanctuarysFaces(1,"M",2))!=-1);//pas ok, il s'arrete apres 8 au lieu de s'arreter apres 4, lire le test suivant pour mieux comprendre
    }

    @Test
    public void buyFace() {
        //en gros ce test est faux parce que la seule condition est le type, il n'ya de condition ni sur la valeur ou le prix dans faceAvailable, or il peut avoir plusieus bassins de meme type mais de != valeurs, c'est dailleurs le cas de Moon ou Sun
        //Du coup on on par exemple 2 bassins de moon, ce qui fait 8 faces de type moon, il faut attendre la neuvieme ligne avant qu'il ne souleve le beug
        //decommentez la derniere ligne et testez pour voir
        //pourtant on veut acheter une face bien spécifique qui doit normelement etre dispo 4 fois et pas plus
        //le bon test devrait mettre tout en vert après un assertFalse à la 5e ligne.
        Temple temple = new Temple();
        assertTrue(temple.buyFace(new SanctuarysFaces(1,"M",2)));
        assertTrue(temple.buyFace(new SanctuarysFaces(1,"M",2)));
        assertTrue(temple.buyFace(new SanctuarysFaces(1,"M",2)));
        assertTrue(temple.buyFace(new SanctuarysFaces(1,"M",2)));
        assertTrue(temple.buyFace(new SanctuarysFaces(1,"M",2)));
        assertTrue(temple.buyFace(new SanctuarysFaces(1,"M",2)));
        assertTrue(temple.buyFace(new SanctuarysFaces(1,"M",2)));
        assertTrue(temple.buyFace(new SanctuarysFaces(1,"M",2)));
        //assertTrue(temple.buyFace(new SanctuarysFaces(1,"M",2)));
    }
}