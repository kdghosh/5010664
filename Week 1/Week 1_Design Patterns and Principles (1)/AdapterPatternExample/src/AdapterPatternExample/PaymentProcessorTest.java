package AdapterPatternExample;

 interface PaymentProcessor 
{
    void processPayment(double amount);
}

//PayPal payment gateway class
 class PayPal 
{
 public void sendPayment(double amount) 
 {
     System.out.println("Processing payment of $" + amount + " through PayPal.");
 }
}

//Stripe payment gateway class
 class Stripe 
{
 public void makePayment(double amount) 
 {
     System.out.println("Processing payment of $" + amount + " through Stripe.");
 }
}

//Square payment gateway class
 class Square 
{
 public void doPayment(double amount) 
 {
     System.out.println("Processing payment of $" + amount + " through Square.");
 }
}


//PayPal adapter class
 class PayPalAdapter implements PaymentProcessor {
 private PayPal payPal;

 public PayPalAdapter(PayPal payPal) {
     this.payPal = payPal;
 }

 @Override
 public void processPayment(double amount) {
     payPal.sendPayment(amount);
 }
}

//Stripe adapter class
 class StripeAdapter implements PaymentProcessor {
 private Stripe stripe;

 public StripeAdapter(Stripe stripe) {
     this.stripe = stripe;
 }

 @Override
 public void processPayment(double amount) {
     stripe.makePayment(amount);
 }
}

//Square adapter class
 class SquareAdapter implements PaymentProcessor {
 private Square square;

 public SquareAdapter(Square square) {
     this.square = square;
 }

 @Override
 public void processPayment(double amount) {
     square.doPayment(amount);
 }
}

public class PaymentProcessorTest {
    public static void main(String[] args) {
        // Using PayPal through the adapter
        PaymentProcessor payPalProcessor = new PayPalAdapter(new PayPal());
        payPalProcessor.processPayment(100.0);

        // Using Stripe through the adapter
        PaymentProcessor stripeProcessor = new StripeAdapter(new Stripe());
        stripeProcessor.processPayment(200.0);

        // Using Square through the adapter
        PaymentProcessor squareProcessor = new SquareAdapter(new Square());
        squareProcessor.processPayment(300.0);
    }
 
}