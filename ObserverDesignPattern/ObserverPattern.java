package ObserverDesignPattern;

import java.util.ArrayList;
import java.util.List;

interface StockObservable {
    public void add(NotificationAlertObserver obj);
    public void remove(NotificationAlertObserver obj);
    public void notifyObserver();
    public void setStock(int count);
    public int getStockCount();
}

class IphoneObservable implements StockObservable{
    int count=0;
    List<NotificationAlertObserver> objList = new ArrayList<>();

    @Override
    public void add(NotificationAlertObserver obj) {
        objList.add(obj);
    }

    @Override
    public void remove(NotificationAlertObserver obj) {
        objList.remove(obj);
    }

    @Override
    public void notifyObserver() {
        for(NotificationAlertObserver obj : objList){
            obj.update();
        }
    }

    @Override
    public void setStock(int newCount) {
        if(count == 0){
            count+=newCount;
            notifyObserver();
        }
    }

    @Override
    public int getStockCount() {
        return this.count;
    }
    
}


interface NotificationAlertObserver {
    public void update();
}

class EmailAlertObserver implements NotificationAlertObserver {
    StockObservable observable;
    String email;
    EmailAlertObserver(StockObservable observable, String email){
        this.observable = observable;
        this.email = email;
    }
    @Override
    public void update() {
        // here we can write business logic like send email
        sendMail(email,"message send on mail");
        System.out.println("Total available stock count are "+observable.getStockCount());
    }
    
    private void sendMail(String email, String msg){
        System.out.println(msg+" - "+email);
    }
}


class MobileAlertObserver implements NotificationAlertObserver {
    StockObservable observable;
    int mobile;

    MobileAlertObserver(StockObservable observable, int mobile){
        this.observable = observable;
        this.mobile = mobile;
    }

    
    @Override
    public void update() {
        System.out.println("Alert send on mobile - "+mobile);
    }
    
}
public class ObserverPattern {
    public static void main(String[] args) {
        StockObservable iphoneObservable = new IphoneObservable();

        NotificationAlertObserver observer1 = new EmailAlertObserver(iphoneObservable,"alp@gmail.com");
        NotificationAlertObserver observer2 = new EmailAlertObserver(iphoneObservable, "bkl@gmail.com");
        NotificationAlertObserver observer3 = new MobileAlertObserver(iphoneObservable, 99999);

        iphoneObservable.add(observer1);
        iphoneObservable.add(observer2);
        iphoneObservable.remove(observer2);
        iphoneObservable.add(observer3);
        iphoneObservable.setStock(10);
    }
}
