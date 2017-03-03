package org.wargamesproject.weightplanner.model;

/**
 * Created by Matt on 2/25/2017.
 */

public enum WeightType
{
    KILOGRAMS("Kilograms",1.0,0.1574,2.2046),POUNDS("Pounds",0.4535,0.0714,1.0),STONE("Stone",6.3502,1.0,14.0);

    private String description;
    private double kg,stone,pounds;

    WeightType(String desc,double inKG,double inStone, double inPounds)
    {
        this.description = desc;
        this.kg = inKG;
        this.stone = inStone;
        this.pounds = inPounds;
    }

    public String getDescription()
    {
        return this.description;
    }

    public double convertTo(WeightType convertTo)
    {
        double conversionFactor = 0.0;

        if (convertTo == KILOGRAMS)
        {
            conversionFactor = this.getKg();
        }
        else if (convertTo == POUNDS)
        {
            conversionFactor = this.getPounds();
        }
        else if (convertTo == STONE)
        {
            conversionFactor = this.getStone();
        }

        return conversionFactor;
    }

    public double getKg() { return this.kg; }
    public double getStone() { return this.stone; }
    public double getPounds() { return this.pounds; }

    public String toString()
    {
        return this.description;
    }
}
