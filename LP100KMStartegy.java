public class LP100KMStartegy implements FuelConsumptionStarategy {
    
    public double[] calculate(double distance, double fuelUsed){
        double[] calculations = new double[2];
        calculations[0] = distance / fuelUsed; // kilometer per liter
        calculations[1] = (fuelUsed / distance) * 100; // liter per 100 kilometers
        return calculations;
    }
}
