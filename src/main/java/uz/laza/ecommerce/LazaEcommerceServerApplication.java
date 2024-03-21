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
		// Additionally controller methods, check all controller classes like ProductController generic methods
		// ExceptionHandler
		// Validation for requests
		// Log4j
		// Complete full Swagger doc
		// Unit Test
		// Add Stripe/MasterCard and upgrade Payment)
		// Extra product config (expiry date, VAT price, percentage
		// Add Tag and Category
	}

}
