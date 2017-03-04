package org.wargamesproject.weightplanner.model;

/**
 * Created by Matt on 2/25/2017.
 */

public enum WeightType
{
    KILOGRAMS("Kilograms",1.0f,0.1574f,2.2046f),POUNDS("Pounds",0.4535f,0.0714f,1.0f),STONE("Stone",6.3502f,1.0f,14.0f);

    private String description;
    private float kg,stone,pounds;

    WeightType(String desc,float inKG,float inStone,float inPounds)
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

    public float convertTo(WeightType convertTo)
    {
        float conversionFactor = 0.0f;

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

    public float getKg() { return this.kg; }
    public float getStone() { return this.stone; }
    public float getPounds() { return this.pounds; }

    public String toString()
    {
        return this.description;
    }
}
