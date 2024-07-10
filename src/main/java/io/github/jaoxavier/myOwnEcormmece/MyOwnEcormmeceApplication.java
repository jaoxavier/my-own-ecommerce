package io.github.jaoxavier.myOwnEcormmece;

import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.enums.Gender;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.enums.Situation;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.client.info.Client;
import io.github.jaoxavier.myOwnEcormmece.domain.entity.product.info.Product;
import io.github.jaoxavier.myOwnEcormmece.service.client.ClientService;
import io.github.jaoxavier.myOwnEcormmece.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class MyOwnEcormmeceApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired ClientService cs,
			@Autowired ProductService ps
	)
	{
		return args -> {
			Client client_1 = cs.saveClient(Client.builder().situation(Situation.ACTIVE).isCompany(false).number_ssn_ein("080-70-4255").name("João Xavier").gender(Gender.M).email("contact.joaoxavier@gmail.com").cell_number("(071) 992-2890").birthdate(LocalDate.of(2002, 5, 20)).build());
			Client client_2 = cs.saveClient(Client.builder().situation(Situation.ACTIVE).isCompany(true).number_ssn_ein("08-0704255").name("João Xavier").email("company.joaoxavier@gmail.com").cell_number("(071) 992-2890").birthdate(LocalDate.of(2002, 5, 20)).build());
			ps.saveProduct(Product.builder().client(client_2).brand("Sony").name("Camera").price(49.99).stock(10).build());
			ps.saveProduct(Product.builder().client(client_2).brand("DJI").name("Microphone").price(109.99).stock(10).build());
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(MyOwnEcormmeceApplication.class, args);
	}

}
