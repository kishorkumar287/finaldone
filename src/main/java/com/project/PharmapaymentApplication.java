package com.project;

import java.io.FileOutputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.project.model.PasswordRecoveryFunction;
import com.project.model.pdf_function;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com")
public class PharmapaymentApplication {

	public static void main(String[] args) {
		
		
		
		SpringApplication.run(PharmapaymentApplication.class, args);
		System.out.println("pdfwriter");
		//pdf_function pdf=new pdf_function();
		
		//pdf.pdf();
		
		
		

	}

	@Bean
	public LocalValidatorFactoryBean validate(MessageSource messageSource) {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();

		localValidatorFactoryBean.setValidationMessageSource(messageSource);
		return localValidatorFactoryBean;
	}

}
