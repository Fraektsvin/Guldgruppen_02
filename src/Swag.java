
import java.util.Arrays;


class Swag {

   
    @Override
    public String toString() {
        return "Swag{" + "swagDescription=" + swagDescription + '}';
    }

    String swagDescription;
    
    //Der oprettes en streng som kan indeholde navnet til items(swag) i spillet.
    public Swag(String newSwagDescription) {
        swagDescription = newSwagDescription;
    }
    
    public String getSwagDescription() {
        return swagDescription;
    } 
}
