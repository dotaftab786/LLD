package StrategyPattern;

//First try without strategy pattern then check what problem arises and how can be solved using strategy pattern

class Vehicles1 {
    public void drive(){
        System.out.println("Normal Drive used");
    }
}

class OffRoadVehicles1 extends Vehicles1 {

}

class SportyVehicles1 extends Vehicles1 {

    @Override
    public void drive(){
        System.out.println("Special drive used");
    }
}

class PassengerVehicles1 extends Vehicles1 {

    @Override
    public void drive(){
        System.out.println("special drive used");
    }
}

// so in above code we experience that in parent class we have one method drive and there behaviour is defined but suppose classes
// which inherite them and they used the same behaviour of parent class then there is no problem,but problem arises if child overide their
// own drive method then also not much problem but suppose if another child also overide the parent method and the behaviour of child class 
// is same as another child so duplication of code arises.but this cases arises if one method but suppose more methods in parent class 
// and child inherit parent then lots of duplication arises on child level.

// so check how can use strategy pattern to solve this problem

interface DesignStrategy {
    public void drive();
}

class NormalStrategy implements DesignStrategy{

    @Override
    public void drive() {
        System.out.println("Normal drive");        
    }
    
}

class SpecialStrategy implements DesignStrategy{

    @Override
    public void drive() {
        System.out.println("Special drive");        
    }
    
}
 class Vehicles{
    DesignStrategy designStrategy;
    // HERE if we dont assigned dynamically object to constructor then we have to create lots of object with new key word like
    // designStrategy = new NormalStrategy();
    // designStrategy  = new SpecialStrategy()
    Vehicles(DesignStrategy designStrategy){
        this.designStrategy = designStrategy;
    }
    public void drive(){
        designStrategy.drive();
    }
 }

 class OffRoadVehicles extends Vehicles {
    OffRoadVehicles(){
        super(new NormalStrategy());
    }
 }


class PassengerVehicles extends Vehicles {
    PassengerVehicles(){
        super(new SpecialStrategy());
    }
 }

 // so we understand that to implement design strategy we will create an inteface and the classes which have strategy will implement 
 // their method now the parent class has driveStrategy now if any child class of vehicle will have to simply pass the drive strategy they
 // want to implement then that strategy drive will be called; 

public class StrategyDesignPattern {
    public static void main(String[] args) {
        System.out.println("WITHOUT strategy");
        Vehicles1 vehicles1 = new PassengerVehicles1();
        vehicles1.drive();
        OffRoadVehicles1 offRoadVehicles1 = new OffRoadVehicles1();
        offRoadVehicles1.drive();
        SportyVehicles1 sportyVehicles1 = new SportyVehicles1();
        sportyVehicles1.drive();

        System.out.println("WITH strategy");
        Vehicles ofVehicles = new OffRoadVehicles();
        ofVehicles.drive();
        Vehicles pVehicles = new PassengerVehicles();
        pVehicles.drive();
        
    }
}