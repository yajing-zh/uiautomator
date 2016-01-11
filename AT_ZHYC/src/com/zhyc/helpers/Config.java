package com.zhyc.helpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {

	private String configPath = "/data/local/tmp/config.properties";

	// config keys constants
	public static class Key {
		public static final String ANDROID_VERSION = "androidVersion";
		public static final String PHONE_TYPE = "phoneType";
		public static final String SIM1_NUMBER = "sim1Number";
		public static final String SIM2_NUMBER = "sim2Number";
		public static final String REFER_NUMER = "referNumber";
		public static final String REFER_BT_NAME = "referBTName";
		public static final String OUTGOING_NUMBER = "outGoingNumber";
		public static final String WLAN_NAME = "wlanName";
		public static final String WLAN_PASSWORD = "wlanPassword";
	}

	private static Config instance;

	private Properties prop = null;
	private static final String DEFAULT_VALUE = "";

	private Config() {
		init();
	}

	private Config(String configPath) {
		this.configPath = configPath;
		init();
	}

	private void init() {
		BufferedReader br = null;
		try {
			prop = null;
			br = new BufferedReader(new FileReader(configPath));
			prop = new Properties();
			prop.load(br);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public static Config getInstance() {
		if (instance == null)
			instance = new Config();
		return instance;
	}

	public static Config getInstance(String configPath) {
		if (instance == null)
			instance = new Config(configPath);
		return instance;
	}

	public void reload() {
		init();
	}

	public void reload(String configFilePath) {
		this.configPath = configFilePath;
		init();
	}

	public String getValue(String key) {
		return this.prop.getProperty(key, DEFAULT_VALUE);
	}

	public static void main(String[] args) {
		final String configPath = "C:\\Java\\workspace\\AT_Dialer\\config\\config.properties";
		System.out.println(Config.getInstance(configPath).getValue(
				Config.Key.OUTGOING_NUMBER));
	}

}
