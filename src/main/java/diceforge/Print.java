/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diceforge;

/**
 *
 * @author The Beginners
 */
public class Print {
    
    int param;
    
    public Print(int a){
      param = a;   
    }
    
    public void PrintMessage(String a){
        if(param==0){
            System.out.println(a);
        }
    }
    
    public void PrintMessage(int a){
        if(param==0){
            System.out.println(a);
        }
    }
    
    public void PrintMessage(){

    }
}
