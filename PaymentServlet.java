import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String cardNumber = request.getParameter("cardno");
        String cardName = request.getParameter("name");
        String expirationDate = request.getParameter("expDate");
        String cvv = request.getParameter("cvv");
        String totalAmount = request.getParameter("total");

        // Insert payment data into database
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database (replace with your DB URL, username, password)
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payment_db", "root", "your_password");

            // Create the SQL query
            String sql = "INSERT INTO payments (card_number, card_name, expiration_date, cvv, total_amount) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            // Set parameters for the query
            statement.setString(1, cardNumber);
            statement.setString(2, cardName);
            statement.setString(3, expirationDate);
            statement.setString(4, cvv);
            statement.setString(5, totalAmount);

            // Execute the query
            statement.executeUpdate();

            // Response on success
            response.getWriter().write("Payment processed successfully!");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().write("Failed to process the payment.");
        } finally {
            // Close the resources
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
