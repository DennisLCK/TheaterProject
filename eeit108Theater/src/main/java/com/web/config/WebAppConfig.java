package com.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.ResourceBundleViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan({ "com.web", "ecpay" })
public class WebAppConfig extends WebMvcConfigurerAdapter {

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("messages");
		return resource;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**")
				.addResourceLocations("/WEB-INF/resources/assets/");
		registry.addResourceHandler("/images/**")
				.addResourceLocations("/WEB-INF/resources/images/");
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/resources/js/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/resources/fonts/");
		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/css/");
		registry.addResourceHandler("/admin/vendor/**")
				.addResourceLocations("/WEB-INF/resources/vendor/");
		registry.addResourceHandler("/admin/images/**")
				.addResourceLocations("/WEB-INF/resources/images/");
		registry.addResourceHandler("/admin/js/**").addResourceLocations("/WEB-INF/resources/js/");
		registry.addResourceHandler("/admin/css/**")
				.addResourceLocations("/WEB-INF/resources/css/");
		registry.addResourceHandler("/bootstrap/**")
				.addResourceLocations("/WEB-INF/resources/bootstrap/");

	}

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry)
//	{
//		registry.addResourceHandler("/assets/css/**").addResourceLocations("/WEB-INF/resources/assets/css/");
//		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/resources/images/");
//		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/resources/js/");
//		registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/resources/fonts/");
//		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/css/");
//		registry.addResourceHandler("/admin/**").addResourceLocations("/WEB-INF/views/admin/");
//	
//	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/EmpLogin3").setViewName("EmpLogin3");

	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxUploadSize(81920000);
		return resolver;
	}

	@Bean
	public ViewResolver contextNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		resolvers.add(jspViewResolver());
		resolvers.add(pdfViewResolver());
//		resolvers.add(jasperReportResolver());
//		views.add((ViewResolver) jsonView());
		resolver.setViewResolvers(resolvers);
		return resolver;
	}

//org.springframework.web.servlet.view.json.MappingJackson2JsonView
//	@Bean
//	public MappingJackson2JsonView jsonView() {
//		MappingJackson2JsonView view = new MappingJackson2JsonView();
//		view.setPrettyPrint(true);
//		return view;
//	}

//	@Bean
//	public static ApplicationIReportView iReportView() {
//		ApplicationIReportView iReport = new ApplicationIReportView();
//		iReport.setReportDataKey("jrMainDataSource");
//		return iReport;
//	}
//
//	@Bean
//	public ViewResolver jasperReportResolver() {
//		XmlViewResolver view = new XmlViewResolver();
//		view.setOrder(0);
//		view.setLocation();
//		return view;
//
//	}

	@Bean
	public ViewResolver jspViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	public ViewResolver pdfViewResolver() {
		ResourceBundleViewResolver pdfView = new ResourceBundleViewResolver();
		pdfView.setBasename("views");
		pdfView.setOrder(1);
		return pdfView;
	}

}
