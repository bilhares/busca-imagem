package br.com.psg.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.Persistence;

public class CriaTabelas {
	public static void main(String[] args) {
		Map<String, Object> configOverrides = JpaUtil.getConfigJpa();
		Persistence.createEntityManagerFactory("psgPU", configOverrides);
	}
}
