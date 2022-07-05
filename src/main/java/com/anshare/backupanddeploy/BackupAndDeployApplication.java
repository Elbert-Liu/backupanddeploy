package com.anshare.backupanddeploy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.anshare.backupanddeploy.mapper"})
public class BackupAndDeployApplication {

	public static void main(String[] args) {

		System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","{}");
		SpringApplication.run(BackupAndDeployApplication.class, args);
	}

}
