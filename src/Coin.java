/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sigur
 */
public class Coin extends Swag{
    private int value;

    public Coin(int value, String newSwagDescription) {
        super(newSwagDescription);
        this.value = value;
    }

    @Override
    public String toString() {
        return "Coin{" + "value=" + value + " desc: " + getSwagDescription();
    }
    
    public void doIt(){
        System.out.println("doIt called");
    }
    
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
}