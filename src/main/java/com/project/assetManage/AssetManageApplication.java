package com.project.assetManage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ComponentScan(basePackages = "com.project.assetManage")
@EnableJpaAuditing
@SpringBootApplication
public class AssetManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetManageApplication.class, args);
	}

}
