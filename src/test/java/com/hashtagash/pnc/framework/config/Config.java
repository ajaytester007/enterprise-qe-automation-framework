package com.hashtagash.pnc.framework.config;

import java.io.InputStream;
import java.util.Properties;

public final class Config {
    private static final Properties props = new Properties();
    static {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            props.load(in);
        } catch (Exception e) {
            throw new IllegalStateException("Unable to load config.properties", e);
        }
    }
    private Config() {}
    public static String get(String key) { return System.getProperty(key, props.getProperty(key)); }
    public static int getInt(String key) { return Integer.parseInt(get(key)); }
    public static boolean getBool(String key) { return Boolean.parseBoolean(get(key)); }
}
