/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Arrays;
/**
 *
 * @author sigur
 */
public class Coin {

    
   private final int value = 10;
    private final String name = "penge";

    public Coin(int value, String name) {
    }
  
 @Override
    public String toString() {
        return "Coin{" + "coinDescription=" + coinDescription + '}';      
    }

    String coinDescription;
    
    //Der oprettes en streng som kan indeholde navnet til items(swag) i spillet.
    public Coin(String newCoinDescription) {
        
        coinDescription = newCoinDescription;
    }
    
    public String getCoinDescription() {
        return coinDescription;
    } 
    
}
/*

*/

    /*
        public static void main (String[] arg){
        Swag[] swags = new Swag[3];
        swags[0] = new Swag("dfgh");
        swags[1] = new Coin(27, "Falsk mænt");
        swags[2] = new Swag("fyugsuo");
        System.out.println(Arrays.toString(swags));
        ((Coin)swags[1]).doIt();
    }
    */
/*
  String description;
    public Coin(String newdescription){
        description = newdescription;
                
    }
    
    public Coin(int value, String newSwagDescription) {
        
        this.value = value;
        
    }

  
    public String toString() {
        return "Coin{" + "value=" + value + " desc: " + getSwagDescription();
    }
*/

