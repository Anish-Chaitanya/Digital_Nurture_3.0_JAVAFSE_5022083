import java.util.List;
import java.util.ArrayList;

// Subject Interface
public interface Stock {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Concrete Subject Class
public class StockMarket implements Stock {
    private List<Observer> observers;
    private double stockPrice;

    public StockMarket() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockPrice);
        }
    }

    public void setStockPrice(double price) {
        this.stockPrice = price;
        notifyObservers();
    }
}

// Observer Interface
public interface Observer {
    void update(double stockPrice);
}

// Concrete Observer for Mobile Apps
public class MobileApp implements Observer {
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(double stockPrice) {
        System.out.println(appName + " received stock price update: " + stockPrice);
    }
}

// Concrete Observer for Web Apps
public class WebApp implements Observer {
    private String appName;

    public WebApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(double stockPrice) {
        System.out.println(appName + " received stock price update: " + stockPrice);
    }
}

// Test Class
public class ObserverPatternExample {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobileApp1 = new MobileApp("MobileApp1");
        Observer webApp1 = new WebApp("WebApp1");

        stockMarket.registerObserver(mobileApp1);
        stockMarket.registerObserver(webApp1);

        // Set new stock price and notify all observers
        stockMarket.setStockPrice(100.0);

        // Remove one observer
        stockMarket.removeObserver(mobileApp1);

        // Set new stock price and notify remaining observers
        stockMarket.setStockPrice(105.5);
    }
}

