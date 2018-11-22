package Faces;
import Player.Bot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SanctuarysFaces extends GeneralFace {

    private final int price;
    private boolean selected;
    private final ArrayList<SimpleFace> Offered;
    private final String mode ; //Add,Choice or None

    public SanctuarysFaces() {
        super("null");
        this.price = 0;
        this.selected = false;
        this.mode= "None";
        this.Offered = new ArrayList<>();
        
    }
    
    public SanctuarysFaces(int price, String name, String mode,SimpleFace... data) {
        super(name);
        this.price = price;
        this.selected = false;
        this.mode= mode;
        this.Offered = new ArrayList<>();
        this.Offered.addAll(Arrays.asList(data));
        
    }

    @Override
    public String getName() {
        return name;
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
    
    @Override
    public void makeEffect(Bot bot){
       if(this.mode.equals("Add")){
          this.Offered.forEach(item->{
              bot.getHerosInventory().increaseInventoryWithDiceFace(item);
          });
       }else{
                Random randomInt = new Random();
                int choice = randomInt.nextInt(this.Offered.size());
                bot.getHerosInventory().increaseInventoryWithDiceFace(this.Offered.get(choice));
                
       }
    }
    

}
