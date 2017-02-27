package org.wargamesproject.weightplanner.model;

/**
 * Created by Matt on 2/25/2017.
 */

public enum WeightType
{
    KILOGRAMS("Kilograms"),POUNDS("Pounds"),STONE("Stone");

    private String description;

    WeightType(String desc)
    {
        this.description = desc;
    }

    public String getDescription()
    {
        return this.description;
    }

    public String toString()
    {
        return this.description;
    }
}
