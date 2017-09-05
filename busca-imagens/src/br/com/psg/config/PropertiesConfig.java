package br.com.psg.config;

import java.io.InputStream;

public class PropertiesConfig {
	
	public InputStream getProperties() {
		InputStream input;
		input = getClass().getResourceAsStream("/br/com/psg/properties/config.properties");
		return input;
	}
}
