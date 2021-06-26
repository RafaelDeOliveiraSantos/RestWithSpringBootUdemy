package br.com.erudio.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.erudio.adapter.YamlJackson2HttpMessageAdapter;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	private static final MediaType MEDIA_TYPE_YAML = MediaType.valueOf("application/x-yaml");
		
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> adapters) {
		adapters.add(new YamlJackson2HttpMessageAdapter());
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
		//possibilita informar o formato via extesion (extensão)
		//exemplos:
		//http://localhost:8080/api/person/v1/6.xml
		//http://localhost:8080/api/person/v1/6.json
		//http://localhost:8080/api/person/v1.json
		//http://localhost:8080/api/person/v1.xml		
		/*
		 * configurer.favorParameter(false) .ignoreAcceptHeader(false)
		 * .defaultContentType(MediaType.APPLICATION_JSON) .mediaType("json",
		 * MediaType.APPLICATION_JSON) .mediaType("xml", MediaType.APPLICATION_XML);
		 */
		
		//possibilita informar o formato via query param (parâmetro)
		//exemplos:
		//http://localhost:8080/api/person/v1/6?mediaType=xml
		//http://localhost:8080/api/person/v1/6?mediaType=json
		//http://localhost:8080/api/person/v1?mediaType=xml
		//http://localhost:8080/api/person/v1?mediaType=json		
		/*
		 * configurer.favorPathExtension(false) .favorParameter(true)
		 * .parameterName("mediaType") .ignoreAcceptHeader(true)
		 * .useRegisteredExtensionsOnly(false)
		 * .defaultContentType(MediaType.APPLICATION_JSON) .mediaType("json",
		 * MediaType.APPLICATION_JSON) .mediaType("xml", MediaType.APPLICATION_XML);
		 */
		
		//possibilita informar o formato via header
		configurer.favorPathExtension(false)
		.favorParameter(false)
		.ignoreAcceptHeader(false)
		.useRegisteredExtensionsOnly(false)
		.defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json", MediaType.APPLICATION_JSON)
		.mediaType("xml", MediaType.APPLICATION_XML)
		.mediaType("x-yaml", MEDIA_TYPE_YAML);
	}
}
