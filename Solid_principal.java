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
 // see below code
  interface invoiceSaveDao{
     void save(InvoiceCal invoiceCal);
 }

 class InvoiceSaveDb implements invoiceSaveDao{

    public void save(InvoiceCal invoiceCal){
        System.out.println("Invoices Save in db");
    }
 }

 class InvoiceSaveFile implements invoiceSaveDao{
        public void save(InvoiceCal invoiceCal){
            System.out.println("Invoices Save in file");
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
    }
}  