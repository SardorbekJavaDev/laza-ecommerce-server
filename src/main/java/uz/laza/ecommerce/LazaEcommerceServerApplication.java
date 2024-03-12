package uz.laza.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
/**
 * @author Sardorbek Yorqulov
 */
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class LazaEcommerceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LazaEcommerceServerApplication.class, args);
		//TODO
		// Retrieve(get) UserID in JWT and verify every request belongs to true owner
		// ExceptionHandler
		// Validation for requests
		// Unit Test
		// Log4j
		// Complete full Swagger doc

		// TODO for Upgrade
		// Extra product config (expiry date, VAT price, percentage)
        // Add Tag and Category
		// Add Stripe/MasterCard and upgrade Payment

		// Additionally controller methods, check all controller classes
	}

}
