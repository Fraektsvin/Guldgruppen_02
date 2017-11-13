
import java.io.Serializable;

public class Swag implements Serializable {

    String swagDescription;
    private final int VALUE = 100;

   
    //Der oprettes en streng som kan indeholde navnet til items(swag) i spillet.
    public Swag(String newSwagDescription) {

        swagDescription = newSwagDescription;
    }

    public String getSwagDescription() {
        return swagDescription;
    }
     public int getVALUE() {
        return VALUE;
    }
}
