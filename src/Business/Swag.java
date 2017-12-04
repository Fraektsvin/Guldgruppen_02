package Business;

import java.io.Serializable;

public class Swag implements Serializable {

    String swagDescription;
    private final int VALUE = 100;

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
