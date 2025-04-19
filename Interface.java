/*
 * Name: Youssef Mohamed Torki Ahmed
 * ID: 445820246
 * Project3- Fuel app
 */


import java.util.*;

public class Interface {

    // TODO: Use java logging class to log this info to a file.
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String carType1;
        float startKms1;
        float endKMS1;
        float litersUsed1;
        float pricePerLiter1;
        List<Fuel3> allCars = new ArrayList<Fuel3>();

        while (true){
        int choice;
        System.out.println("""
                1- Enter new car Data.
                2- Display all cars.
                press anything else to exit
                """);
        System.out.print("Enter your choice : "); choice = in.nextInt();

        in = new Scanner(System.in);
        switch (choice){
            case 1 -> {
                System.out.print("Enter car type and model : "); carType1 = in.nextLine();
                System.out.println("1- Mile, Gallon\n2- Kilometer, Liter");
                System.out.print("Enter reading mode : "); int readingMode = in.nextInt();

                if (readingMode != 1 && readingMode != 2){
                    System.out.println("Invalid reading mode. setting it to default value of 1");
                    readingMode = 1;
                }
                System.out.print("Enter start distance : "); startKms1 = in.nextFloat();
                System.out.print("Enter end distance : "); endKMS1 = in.nextFloat();
                System.out.print("Enter the amount of fuel used : "); litersUsed1 = in.nextFloat();

                if (readingMode == 1) System.out.print("Enter price per gallon : ");
                else if (readingMode == 2) System.out.print("Enter price per liter : ");
                pricePerLiter1 = in.nextFloat();

                Fuel3 car1 = new Fuel3(carType1, startKms1, endKMS1, litersUsed1, pricePerLiter1);
                if (readingMode == 1) car1.setReadingMethod(new MPGStartegy());
                else if (readingMode == 2) car1.setReadingMethod(new LP100KMStartegy());
                car1.displayAllInfo();
                allCars.add(car1);
            }

            case 2 -> {
                    for(int i = 0; i < allCars.size(); i++){
                        System.out.printf("|||| Entry for car # %d ||||\n", i);
                        allCars.get(i).displayAllInfo();
                        System.out.println("||||||||||||||||||||||||||||");
                    }
                }

                default -> System.exit(0);
            }
        }
    }
    
}
