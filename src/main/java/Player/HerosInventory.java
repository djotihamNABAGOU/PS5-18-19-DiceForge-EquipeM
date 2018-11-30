package Player;
import Faces.SimpleFace;
import java.util.Random;


public class HerosInventory {
    private int gloryPoints;
    private int sunPoints;
    private int moonPoints;
    private int goldPoints;
    private static int gloryPointsLimit = 100;
    public int moonPointsLimit = 6;
    public int goldPointsLimit = 12;
    public int sunPointsLimit = 6;
    
    public int tokenHammer = 0;   /* jeton marteau */
    public int tokenNewt = 0;   /* jeton Triton */
    public int tokenCerberus = 0; /* jeton Cerbere */
      

    public int getGloryPoints() {
        return gloryPoints;
    }

    public int getSunPoints() {
        return sunPoints;
    }

    public int getMoonPoints() {
        return moonPoints;
    }

    public int getGoldPoints() {
        return goldPoints;
    }
    
    

    public HerosInventory() {
        this.gloryPoints = 0;
        this.sunPoints = 0;
        this.moonPoints = 0;
        this.goldPoints = 0;
    }

    public HerosInventory(int gloryPoints, int sunPoints, int moonPoints, int goldPoints) {
        this.gloryPoints = gloryPoints;
        this.sunPoints = sunPoints;
        this.moonPoints = moonPoints;
        this.goldPoints = goldPoints;
    }
    
    public void IncreaseGloryPoints(int points){
        int maxToAdd = gloryPointsLimit - this.gloryPoints;
        if(maxToAdd>points){
            this.gloryPoints+=points;
        }else{
            this.gloryPoints=points-maxToAdd;
            //On doit lui donner un jeton de point de gloire 100
        }
    }
    public void IncreaseMoonPoints(int points){
        int maxToAdd = moonPointsLimit - this.moonPoints;
        if(maxToAdd>points){
            this.moonPoints+=points;
        }else{
            this.moonPoints=moonPointsLimit;
        }
        
    }
    public void IncreaseGoldPoints(int points){
        int maxToAdd = goldPointsLimit - this.goldPoints;
        if(maxToAdd>points){
            this.goldPoints+=points;
        }else{
            this.goldPoints=goldPointsLimit;
        }
    }
    public void DecreaseGoldPoints(int points){
       this.goldPoints-=points;
    }
    public void IncreaseSunPoints(int points){
        int maxToAdd = sunPointsLimit - this.sunPoints;
        if(maxToAdd>points){
            this.sunPoints+=points;
        }else{
            this.sunPoints=sunPointsLimit;
        }
    }
    
    public void makeDefaultHerosInventory(int a){   //Hero's inventory initializing
        this.gloryPoints=0;
        this.goldPoints=0;
        this.moonPoints=0;
        this.goldPoints=a;
    }
  
    
    public void increaseInventoryWithDiceFace(SimpleFace faceObtained,int multiplier){
        if(faceObtained.getType().equals("S")){
            this.IncreaseSunPoints(faceObtained.getValue()*multiplier);
        }
        if(faceObtained.getType().equals("M")){
            this.IncreaseMoonPoints(faceObtained.getValue()*multiplier);
        }
        if(faceObtained.getType().equals("G")){
            this.IncreaseGoldPoints(faceObtained.getValue()*multiplier);
        }
        if(faceObtained.getType().equals("Gl")){
            this.IncreaseGloryPoints(faceObtained.getValue()*multiplier);
        }
       
    }
    
    /*On doit avoir des fonctions decrease pour les achats/depenses des points*/  
}
