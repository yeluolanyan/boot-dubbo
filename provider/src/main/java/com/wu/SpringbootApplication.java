package com.wu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
@EnableAsync
@EnableTransactionManagement //如果mybatis中service实现类中加入事务注解，需要此处添加该注解
@EnableConfigurationProperties({})
@PropertySource(value = {"classpath:application-prod.properties"})
@ServletComponentScan
@EnableScheduling
@MapperScan("com.wu.dao.mapper") //扫描的是mapper.xml中namespace指向值的包位置
public class SpringbootApplication {
	@Value("#{new Boolean('${swagger.show}')}")
	private boolean swaggerShow;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.enable(this.swaggerShow)
				.groupName("*")
				.apiInfo(apiInfo())
				.select()
				.paths(regex("/*.*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("API for 项目测试")
				.description("后端接口")
				.termsOfServiceUrl("未特别说明，都返回Json。")
				.license("")
				.licenseUrl("")
				.version("1.0")
				.build();
	}
}
