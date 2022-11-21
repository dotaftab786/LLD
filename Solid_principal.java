// S = SINGLE RESPONSIBILITY PRINCIPLE
// O = OPEN/CLOSE PRINCIPLE
// L = LISKOV SUBSTITTION PRINCIPLE
// I = INTERFACE SEGMENTED PRINCIPLE
// D = DEPENDENCY INVERSION PRINCIPLE

// single responsibilty principle says that class should have only one reason to change

class Marker{
     String name;
     String color;
     int price;
    Marker(String name, String color, int price){
        this.name = name;
        this.color = color;
        this.price = price;
    }
}


class Invoice{
    private Marker marker;
    private int quantity;
    Invoice(Marker marker, int quantity){
        this.marker = marker;
        this.quantity = quantity;
    }

    public int calculatePrice(){
        return this.marker.price * this.quantity;
    }

    public void saveInvoiceInDb(){
        System.out.println("Invoices save in db");
    }

    public void printInvoices(){
        System.out.println("Invoices printed");
    }
}

 // so if we check above code then we find that this doesnot follow single responsibility principle
 // there are three reason in which classes changes calPrice,savedb,print so if we change one by one then classees changes

 // now check how can acheive single responsibility principle we separate each method into separate classes
 // so we will put only calculate method in invoice class and so one

 // this class is only used for invoice calculation
class InvoiceCal{
    private Marker marker;
    private int quantity;
    InvoiceCal(Marker marker, int quantity){
        this.marker = marker;
        this.quantity = quantity;
    }

    public int total(){
        return this.marker.price * this.quantity;
    }
}

 // this class is only used for invoice save to db

 class InvoiceDao{
    private InvoiceCal invoiceCal;
    InvoiceDao(InvoiceCal invoiceCal){
        this.invoiceCal = invoiceCal;
    }

    public void save(){
        System.out.println("Saved in db");
    }
 }

  // this class is only used for invoice printing

 class InvoicePrint{
    private InvoiceCal invoiceCal;
     InvoicePrint(InvoiceCal invoiceCal){
        this.invoiceCal = invoiceCal;
    }

    public void print(){
        System.out.println("Invoices printed");
    }
 }

 // Open closed principle states that class should be open for extension but closed for modification,it says this 
 // because if class is already tested and handle traffic live then why should modify that class.
 // lets suppose we have class invoiceDaosave which only used to save invoices into db and another requirement arises that 
 // we have to save invoices in files then we will add another method in invoiceDaosave class so we modify the tested code  
 // so we violet the open/closed principle see below code.
 class InvoiceDaoSave{
    private InvoiceCal invoiceCal;
    InvoiceDaoSave(InvoiceCal invoiceCal){
        this.invoiceCal = invoiceCal;
    }

    public void saveInDb(){
        System.out.println("Saved in db");
    }

    public void saveInFile(){
        System.out.println("Saved in file");
    }
 }

 // lets check how can acheive open/closed principle 
 // see below code we created interface and define a method now create seperate class for save in db and save in file now on the basis
 // of condition separate class will be called now suppose if any other features come in future to save in nosql db then we create 
 // another class and implements invoiceSaveDao in that class.
  interface invoiceSaveDao{
     void save(InvoiceCal invoiceCal);
 }

 class InvoiceSaveDb implements invoiceSaveDao{

    @Override
    public void save(InvoiceCal invoiceCal){
        System.out.println("Invoices Save in sql db");
    }
 }

 class InvoiceSaveFile implements invoiceSaveDao{

        @Override
        public void save(InvoiceCal invoiceCal){
            System.out.println("Invoices Save in file");
        }
 }

 // Liskov substitution principle states that if class B is subtype of class A, then we should be able to replace object of A with B
 //  without breaking the behaviour of the program

 // subclass should extend the capability of parent class not narrow it down that means suppose class A have some feature then 
 // class B should have feature equal or more than parent not less than parent
// so in below code motorcycle and bicyle class implements parent class but in motorcycle everything is fine all feature of the 
// parent class is right but in bicycle we narrow down the parent so it violets liskov substitution principle

 interface Bike{
    public void turnOnEngine();
    public void accelerate(int speed);
 }

 class MotorCycle implements Bike{
    private boolean isEngineOn;
    @Override
    public void turnOnEngine() {
        isEngineOn = true;
        System.out.println("Engine turn on - "+isEngineOn);
    }

    @Override
    public void accelerate(int speed) {
        System.out.println("Accelerate by - "+speed);
    }
    
 }

 class BiCycle implements Bike{
    private boolean isEngineOn;
    @Override
    public void turnOnEngine() {
       throw new AssertionError("there is no engine");
    }

    @Override
    public void accelerate(int speed) {
        System.out.println("Accelerate by - "+speed);
    }
    
 }

 // Interface segmented principle states that interface should be such, that client should not implement unnecessary
 // function they donot need
// so in below code we saw that waiter has to do unnecessary function waiter only work is to serve customer nothing else
// but then implements unnecesary function
 interface RestaurantEmployee {
    public void washDishes();
    public void serveCustomer();
    public void cookFood();
 }

 class Waiter implements RestaurantEmployee {

    @Override
    public void washDishes() {
        System.out.println("This is not my task");
    }

    @Override
    public void serveCustomer() {
        System.out.println("This is my task");
    }

    @Override
    public void cookFood() {
        System.out.println("This is not my task");
    }

 }
// see below code how can acheive interface segmented principle we will segregate interface in small small part and
// classes on the basis of requirement implement respective interface

interface WaiterInterface{
    public void serveCustomer();
    public void takeOrder();
}

interface ChefInterface{
    public void cookFood();
    public void decideMenu();
}

interface WashHelper{
    public void washDishes();
}


class WaiterEmployee implements WaiterInterface{

    @Override
    public void serveCustomer() {
        System.out.println("waiter will serve customer");
    }

    @Override
    public void takeOrder() {
        System.out.println("Waiter will take order");
    }

}

class washDishesEmployee implements WashHelper {

    @Override
    public void washDishes() {
        System.out.println("Wash dishes");
    }
    
}

// Dependency inversion principe states that class should depends on interface rather than concrete class
interface Keyboard{
    public void wiredKeyboard();
    public void bluetoothKeyboard();
}

interface Mouse {
    public void wiredMouse();
    public void bluetoothMouse();
}

class WiredKeyboard {

}
class WiredMouse {

}
class MackBook1{
    private final WiredKeyboard keyboard;
    private final WiredMouse mouse;

    MackBook1(){
        keyboard = new WiredKeyboard();
        mouse = new WiredMouse();
    }
}

// so in above code we saw that we depends on class rather than interface so this violets dependency inversion principles.
// lets see how can acheive dependency inversion principle
// 
class MackBook2{
    private final Keyboard keyboard;
    private final Mouse mouse;
    
    MackBook2(Keyboard keyboard, Mouse mouse){
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    

}
public class Solid_principal{
    public static void main(String[] args) {
        Marker marker = new Marker("PERMA", "RED", 10);
        // Invoice invoice = new Invoice(marker, 2);
        // System.out.println(invoice.calculatePrice());
        // invoice.saveInvoiceInDb();
        // invoice.printInvoices();

        InvoiceCal invoiceCal = new InvoiceCal(marker, 8);
    //     System.out.println(invoiceCal.total());

    //     InvoiceDao invoiceDao = new InvoiceDao(invoiceCal);
    //     invoiceDao.save();

        // InvoicePrint invoicePrint = new InvoicePrint(invoiceCal);
        // invoicePrint.print();

        // InvoiceDaoSave invoiceDaoSave = new InvoiceDaoSave(invoiceCal);
        // invoiceDaoSave.saveInDb();
        // invoiceDaoSave.saveInFile();

        // InvoiceSaveDb invoiceSaveDb = new InvoiceSaveDb();
        // invoiceSaveDb.save(invoiceCal);

        // InvoiceSaveFile invoiceSaveFile = new InvoiceSaveFile();
        // invoiceSaveFile.save(invoiceCal);

        // MotorCycle motorCycle = new MotorCycle();
        // motorCycle.turnOnEngine();
        // motorCycle.accelerate(20);

        // BiCycle biCycle = new BiCycle();
        // biCycle.turnOnEngine();

        // Waiter waiter = new Waiter();
        // waiter.cookFood();
        // waiter.serveCustomer();
        // waiter.washDishes();

        // WaiterEmployee waiterEmployee = new WaiterEmployee();
        // waiterEmployee.takeOrder();
        // waiterEmployee.serveCustomer();

        washDishesEmployee washDishesEmployee = new washDishesEmployee();
        washDishesEmployee.washDishes();
    }
}  