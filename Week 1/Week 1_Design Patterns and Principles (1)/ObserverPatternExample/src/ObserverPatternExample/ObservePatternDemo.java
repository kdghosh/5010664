package ObserverPatternExample;

import java.util.List;
import java.util.ArrayList;


 interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers();
}

 class StockMarket implements Stock {
     private List<Observer> observers;
     private double stockPrice;

     public StockMarket() {
         observers = new ArrayList<>();
     }

     @Override
     public void registerObserver(Observer observer) {
         observers.add(observer);
     }

     @Override
     public void deregisterObserver(Observer observer) {
         observers.remove(observer);
     }

     @Override
     public void notifyObservers() {
         for (Observer observer : observers) {
             observer.update(stockPrice);
         }
     }

     public void setStockPrice(double stockPrice) {
         this.stockPrice = stockPrice;
         notifyObservers();
     }
 }

 
 interface Observer {
	    void update(double stockPrice);
	}

  class MobileApp implements Observer {
	    private String appName;

	    public MobileApp(String appName) {
	        this.appName = appName;
	    }

	    @Override
	    public void update(double stockPrice) {
	        System.out.println(appName + " received stock price update: " + stockPrice);
	    }
	}

  class WebApp implements Observer {
	    private String appName;

	    public WebApp(String appName) {
	        this.appName = appName;
	    }

	    @Override
	    public void update(double stockPrice) {
	        System.out.println(appName + " received stock price update: " + stockPrice);
	    }
	}

  public class ObservePatternDemo {
	    public static void main(String[] args) {
	        StockMarket stockMarket = new StockMarket();

	        Observer mobileApp = new MobileApp("MobileApp1");
	        Observer webApp = new WebApp("WebApp1");

	        stockMarket.registerObserver(mobileApp);
	        stockMarket.registerObserver(webApp);

	        stockMarket.setStockPrice(100.00);
	        System.out.println("");

	        stockMarket.setStockPrice(150.00);
	        System.out.println("");

	        stockMarket.deregisterObserver(webApp);
	        stockMarket.setStockPrice(200.00);
	    }
	}
