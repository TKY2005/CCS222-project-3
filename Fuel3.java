/*
 * Name: Youssef Mohamed Torki Ahmed
 * ID: 445820246
 * Project3- Fuel app
 */

import java.io.IOException;
import java.util.logging.*;

public class Fuel3{
    public String carType;
    float startDistance;
    float endDistance;
    float fuelUsed;
    float pricePerUnit;

    private String[] info = {
    // 1, 2, 4, 5, 7, 8 left to be determined by the user in runtime
    // 1, 2 for distance
    // 4, 5 for fuel measurment unit
    // 7, 8 for fuel consumption rate measurement unit
    "Car type", // 0 
    "start distance", // 1
    "end distance", // 2
    "total distance", // 3
    "fuel used", // 4
    "price per", // 5
    "total cost",// 6
    "", // 7
    ""}; // 8
    private String distanceUnit = "KM";
    private String fuelUnit = "L";
    private String consumptionUnit1 = "KM/L", consumptionUnit2 = "LP100KM";

    private FuelConsumptionStarategy readingMode;

    private Logger logger;
    private FileHandler file;
    private SimpleFormatter format;


    public Fuel3(String carType, float startDistance, float endDistance, float fuelUsed, float pricePerUnit){
        this.carType = carType;
        this.startDistance = startDistance;
        this.endDistance = endDistance;
        this.fuelUsed = fuelUsed;
        this.pricePerUnit = pricePerUnit;
        setUpLogger();
    }

    private void setUpLogger(){
        try {
            logger = Logger.getLogger(Fuel3.class.getName());
            logger.setUseParentHandlers(false);
            
            file = new FileHandler("222Proj.log");
            format = new SimpleFormatter();

            file.setFormatter(format);
            logger.addHandler(file);

        } catch (IOException e) {
            System.out.println("Error trying to create or write to the file.");
        }
    }

    public float calcDistance(float startDistance, float endDistance){
        return endDistance - startDistance;
    }
    
    public void setReadingMethod(FuelConsumptionStarategy starategy){
        readingMode = starategy;
    }
    public double[] calculate(double distance, double fuelUsed){
        if (readingMode instanceof MPGStartegy){
            distanceUnit = "Mi";
            fuelUnit = "Gal";
            consumptionUnit1 = "MPG"; consumptionUnit2 = "GPM";
        }else{
            distanceUnit = "KM";
            fuelUnit = "L";
            consumptionUnit1 = "KM/L"; consumptionUnit2 = "LP100KM";
        }

        return readingMode.calculate(distance, fuelUsed);
    }

    public float totalCost(){
        return pricePerUnit * fuelUsed;
    }

    public void displayAllInfo(){
        float distance = calcDistance(startDistance, endDistance);
        double[] fuelConsumption = calculate(distance, fuelUsed);

        info[4] += String.format(" (%s)", fuelUnit);
        info[5] += fuelUnit;
        info[7] += consumptionUnit1;
        info[8] += consumptionUnit2;
        
        String infoDisplay = String.format("""
            =============================================
                %s: %s
                %s: %s
                %s: %s
                %s: %s
                %s: %s
                %s: %s
                %s: %s
                %s: %s
                %s: %s
            ==============================================
                """, 
                info[0], carType,
                info[1], startDistance + " " + distanceUnit,
                info[2], endDistance + " " + distanceUnit,
                info[3], distance + " " + distanceUnit,
                info[4], fuelUsed + fuelUnit,
                info[5], pricePerUnit + " S.R",
                info[6], String.format("%.1f",totalCost()) + " S.R",
                info[7], String.format("%.1f", fuelConsumption[0]) + " " + distanceUnit,
                info[8], String.format("%.5f",fuelConsumption[1]) + " " + fuelUnit);
        System.out.println(infoDisplay);


        logger.info(infoDisplay);
    }
}
