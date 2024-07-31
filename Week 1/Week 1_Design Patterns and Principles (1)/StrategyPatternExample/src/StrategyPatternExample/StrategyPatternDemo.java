package StrategyPatternExample;

interface PaymentStrategy {
    void pay(double amount);
}
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;
    private String cvv;
    private String expiryDate;

    public CreditCardPayment(String cardNumber, String cardHolderName, String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " paid using Credit Card.");
        // Add actual payment processing code here
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;
    private String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " paid using PayPal.");
        // Add actual payment processing code here
    }
}

class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(double amount) {
        paymentStrategy.pay(amount);
    }
}

public class StrategyPatternDemo {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // Pay using Credit Card
        PaymentStrategy creditCardPayment = new CreditCardPayment("1234-5678-9101-1121", "John Doe", "123", "12/24");
        context.setPaymentStrategy(creditCardPayment);
        context.executePayment(250.00);
        System.out.println();

        // Pay using PayPal
        PaymentStrategy payPalPayment = new PayPalPayment("johndoe@example.com", "password123");
        context.setPaymentStrategy(payPalPayment);
        context.executePayment(120.00);
    }
}
