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
	}

}
