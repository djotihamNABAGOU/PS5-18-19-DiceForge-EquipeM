package Faces.Sanctuary;
import Faces.Garden.ShieldOfTheGuardianFace;
import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;
import java.util.Arrays;

public class SanctuarysFaces extends GeneralFace {

    private final int price;
    private boolean selected;
    private final ArrayList<SimpleFace> Offered;
    private final String mode ; //Add,Choice 

    public ArrayList<SimpleFace> getOffered() {
        return Offered;
    }
    

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

    public String getMode(){
        return mode;
    }
    
    public boolean isSelected() {
        return selected;
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    @Override
    public void makeEffect(int action,int favMin,Temple temple,int numBot,
                               Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        
              makeEffectFaceMultiplier(action,favMin,temple,numBot,bot,1,data,listBot);
     
    }
    
    @Override
    public void makeEffectFaceMultiplier(int action,int favMin,Temple temple,int numBot,
                                      Bot bot,int a,ArrayList<GeneralFace>[] data,Bot... listBot){
        
       if(this.mode.equals("Add")){
          this.Offered.forEach(item->{
               item.makeEffectFaceMultiplier(action,favMin,temple, numBot, bot, a, data,listBot);
          });
       }else{           
                int choice = bot.getStrategy().giveMeYourChoice(this.Offered);
                this.Offered.get(choice).makeEffectFaceMultiplier(action,favMin,temple, numBot, bot, a, data,listBot);
                
       } 
    }
    
    
    
    
    // Effet Cyclope
    @Override
    public void makeCardCyclopEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,
                                                                                        Bot... listBot){
        if(this.mode.equals("Add")){
          this.Offered.forEach(item->{
              item.makeCardCyclopEffect(temple, numBot, bot, data);
          });
       }else{           
                int choice = bot.getStrategy().giveMeYourChoice(this.Offered);
                this.Offered.get(choice).makeCardCyclopEffect(temple, numBot, bot, data);
                
       } 
        
    }
    
    
    
    // Effet Sentinelle
    
    @Override
    public void makeCardSentinelEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,
                                                Bot... listBot){
        
            makeEffectFaceMultiplierCardSentinelEffect(temple, numBot, 1, bot, data);
        
    }
    
    
    
    @Override
    public void makeEffectFaceMultiplierCardSentinelEffect(Temple temple,int numBot,
                                          int d,Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        for(int b=0;b<d;b++){
             if(this.mode.equals("Add")){
          this.Offered.forEach(item->{
              item.makeCardSentinelEffect(temple, numBot, bot, data);
          });
       }else{           
                int choice = bot.getStrategy().giveMeYourChoice(this.Offered);
                this.Offered.get(choice).makeCardSentinelEffect(temple, numBot, bot, data);
                
       } 
        }
    }
    
    @Override
    public String toString() {
        if (this.Offered.size() == 1)
            return this.Offered.get(0).toString();
        else return ""
                    +this.getName()+"->"
                    +this.Offered.stream()
                                .map(simpleFace -> simpleFace.toString())
                                .reduce("",(total, count) -> total + count + " | ");
    }
    
    @Override
    public int giveMeShieldGain(int action,Bot bot,int numBot,ShieldOfTheGuardianFace face,ArrayList<GeneralFace>[] data,Bot... listBot){
        int a = 1;
        if(this.mode.equals("Choice")){
              int number = bot.getStrategy().giveMeYourChoice(this.Offered);
              if(this.Offered.get(number).getType().equals(face.getType2().getType())){
                  a = 0;
              }
        }else{
            for(int c=0;c<this.Offered.size();c++){
               if(this.Offered.get(c).getType().equals(face.getType2().getType())){
                   a = 0;
               } 
            }
        }
        return a;
    }
    
    @Override
    public void initialize() {}
    
}
