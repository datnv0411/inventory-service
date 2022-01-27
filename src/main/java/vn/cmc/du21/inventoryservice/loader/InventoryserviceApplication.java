package vn.cmc.du21.inventoryservice.loader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"vn.cmc.du21.inventoryservice.presentation.external.controller",
		"vn.cmc.du21.inventoryservice.presentation.internal.controller",
		"vn.cmc.du21.inventoryservice.service"})
@EntityScan(basePackages = "vn.cmc.du21.inventoryservice.persistence.internal.entity")
@EnableJpaRepositories(basePackages = "vn.cmc.du21.inventoryservice.persistence.internal.repository")
public class InventoryserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryserviceApplication.class, args);
	}

}
