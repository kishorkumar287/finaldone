package com.project;

import java.io.FileNotFoundException;
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

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com")
public class PharmapaymentApplication {

	public static void main(String[] args) {
		
		 Document document = new Document();
	      try
	      {
	         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
	         document.open();
	         document.add(new Paragraph("A Hello World PDF document."));
	         document.close();
	         writer.close();
	      } catch (DocumentException e)
	      {
	         e.printStackTrace();
	      } catch (FileNotFoundException e)
	      {
	         e.printStackTrace();
	      }
		
		
		
		//SpringApplication.run(PharmapaymentApplication.class, args);
		
		

	}

	@Bean
	public LocalValidatorFactoryBean validate(MessageSource messageSource) {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();

		localValidatorFactoryBean.setValidationMessageSource(messageSource);
		return localValidatorFactoryBean;
	}

}
