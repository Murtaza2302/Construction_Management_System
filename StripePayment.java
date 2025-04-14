import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

public class StripePayment {
    public static void main(String[] args) {
        // Set your secret API key (replace with your own key from the Stripe dashboard)
        Stripe.apiKey = "your_stripe_secret_key";

        // Create payment intent with amount in paise (1 INR = 100 paise)
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
            .setAmount(5000L)  // Amount in paise (5000 paise = 50 INR)
            .setCurrency("inr")  // Currency set to INR (Indian Rupees)
            .setPaymentMethod("your_payment_method_id")  // Payment method (replace with actual ID)
            .setConfirm(true)  // Automatically confirm the payment intent
            .build();

        try {
            // Create the payment intent
            PaymentIntent intent = PaymentIntent.create(params);
            System.out.println(intent);  // Log the payment intent details
        } catch (Exception e) {
            e.printStackTrace();  // Handle any errors that occur
        }
    }
}
