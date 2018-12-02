package Faces;
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
    public void makeEffect(int action,Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>... data){
        
       /* Si le joueur possède une face multiplier : Ne rien faire car c'est la face
           Multiplier qui s'activera et fera effet
        */
        int a = 0; // Pas de face Multiplier obtenue, passe à 1 sinon
        if(data.length!=0){ // si == 0, faveur mineure
           for(GeneralFace face : data[numBot]){
                if(face instanceof MultiplierFace){
                    a = 1;
                }
            } 
        }
        
        if(a==0){
            makeEffectFaceMultiplier(action,temple,numBot,bot,1);
        }
    }
    
    @Override
    public void makeEffectFaceMultiplier(int action,Temple temple,int numBot,Bot bot,int a,ArrayList<GeneralFace>... data)
    {
       if(this.mode.equals("Add")){
          this.Offered.forEach(item->{
               item.makeEffectFaceMultiplier(action, temple, numBot, bot, a, data);
          });
       }else{           
                int choice = bot.getStrategy().giveMeYourChoice(this.Offered);
                this.Offered.get(choice).makeEffect(action, temple, numBot, bot, data);
                
       } 
    }
    
    
    
    
    // Effet Cyclope
    @Override
    public void makeCardCyclopEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>... data){
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
    public void makeCardSentinelEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>... data){
        
        /* Si le joueur possède une face multiplier : Ne rien faire car c'est la face
           Multiplier qui s'activera et fera effet
        */
        int a = 0; // Pas de face Multiplier obtenue, passe à 1 sinon
        if(data.length!=0){ // si == 0, faveur mineure
           for(GeneralFace face : data[numBot]){
                if(face instanceof MultiplierFace){
                    a = 1;
                }
            } 
        }
            
        if(a==0){
            makeEffectFaceMultiplierCardSentinelEffect(temple, numBot, 1, bot, data);
        }
    }
    
    
    
    @Override
    public void makeEffectFaceMultiplierCardSentinelEffect(Temple temple,int numBot,int d,Bot bot,ArrayList<GeneralFace>... data){
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
    

}
