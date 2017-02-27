package org.wargamesproject.weightplanner.model;

/**
 * Created by Matt on 2/26/2017.
 */

public class WeightConverter
{
    // Member variables
    private double weight;  // All weights are in kg
    private double weightInPounds,weightInStone;
    // Constants
    private static final double STONE_PER_KG = 0.157473d;
    private static final double POUNDS_PER_KG = 2.20462d;

    public WeightConverter()
    {
        this.weight = 0.0d;
    }

    public void setWeight(double value)
    {
        this.weight = value;
        this.calculateWeights();
    }

    public double getWeight()
    {
        return this.weight;
    }

    public double getWeightInPounds()
    {
        return this.weightInPounds;
    }

    public double getWeightInStone()
    {
        return this.weightInStone;
    }

    //
    // Private methods
    //

    private void calculateWeights()
    {
        this.weightInStone = this.weight * STONE_PER_KG;
        this.weightInPounds = this.weight * POUNDS_PER_KG;
    }
}
