package diceforge;

public class SanctuarysFaces extends BasinFaces {

    private int price;
    boolean selected;

    public SanctuarysFaces(DiceFaces basinfaces,int price,boolean selected) {
        super(basinfaces);
        this.price=price;
        this.selected=false;
    }

    public int getPrice() {
        return price;
    }

    public boolean isSelected() {
        return selected;
    }

}
