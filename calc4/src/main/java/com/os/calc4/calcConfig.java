package com.os.calc4;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class calcConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(context);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet,"/soapWS/*");
	}
	
	@Bean
	public XsdSchema caclulatorDescription() {
		return new SimpleXsdSchema(new ClassPathResource("calculatorDescription.xsd"));
	}
	
	@Bean(name = "calc")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema calculatorDescription) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setSchema(calculatorDescription);
		definition.setLocationUri("/soapWS");
		definition.setPortTypeName("CalculatorServicePort");
		definition.setTargetNamespace("http://www.example.org/calculatorDescription");
		
		return definition;
	}
}