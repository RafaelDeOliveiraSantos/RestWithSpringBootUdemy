package br.com.erudio.adapter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public final class YamlJackson2HttpMessageAdapter extends AbstractJackson2HttpMessageConverter{
	
	public YamlJackson2HttpMessageAdapter() {
		super(new YAMLMapper(), MediaType.parseMediaType("application/x-yaml"));
	}

}
