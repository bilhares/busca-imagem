package br.com.psg.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

	
	private static EntityManagerFactory factory;

    static {
        Map<String, Object> configOverrides = getConfigJpa();
        factory =  Persistence.createEntityManagerFactory("psgPU",configOverrides);
    }
	
	public static EntityManager getEntityManager() {

		//EntityManagerFactory factory = null;
		//Map<String, Object> configOverrides = getConfigJpa();
		//factory = Persistence.createEntityManagerFactory("psgPU", configOverrides);
		return factory.createEntityManager();
	}

	public static Map<String, Object> getConfigJpa() {

		Map<String, Object> configOverrides = new HashMap<String, Object>();
		try {
			Properties prop = new Properties();
			PropertiesConfig config = new PropertiesConfig();
			prop.load(config.getProperties());
			configOverrides.put("javax.persistence.jdbc.user", prop.getProperty("user"));
			configOverrides.put("javax.persistence.jdbc.password", prop.getProperty("password"));
			configOverrides.put("javax.persistence.jdbc.url", "jdbc:sqlserver://" + prop.getProperty("server")
					+ ";DatabaseName=" + prop.getProperty("dataBaseName"));
			configOverrides.put("javax.persistence.jdbc.driver", prop.getProperty("driver"));
			configOverrides.put("hibernate.dialect", prop.getProperty("dialect"));
			configOverrides.put("hibernate.show_sql", prop.getProperty("show.sql"));
			configOverrides.put("hibernate.format_sql", prop.getProperty("format.sql"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return configOverrides;
	}

}
