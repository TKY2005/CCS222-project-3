public class MPGStartegy implements FuelConsumptionStarategy {

    public double[] calculate(double distance, double fuelUsed){
        double[] calculations = new double[2];
        calculations[0] = distance / fuelUsed; // miles per gallon
        calculations[1] = fuelUsed / distance; // gallons per mile
        return calculations;
    }
}
