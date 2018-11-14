package Faces;

import Faces.DiceFaces;

public class SanctuarysFaces extends DiceFaces {

    private int price;
    private boolean selected;

    public SanctuarysFaces(int value, String type,int price) {
        super(value,type);
        this.price=price;
        this.selected=false;
    }

    public int getPrice() {
        return price;
    }

    public boolean isSelected() {
        return selected;
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
