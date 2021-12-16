package ru.geekbrains.loader.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
class PropertyFileConfig implements Config {

    private int serverPort;

    private String templatePrefix;

    private String templateSuffix;

    private String httpVersion;

    public PropertyFileConfig(String propertyFile) {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertyFile);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        serverPort = Integer.parseInt(properties.getProperty("server.port", "8080"));
        templatePrefix = properties.getProperty("template.prefix",
                "web-server-app/src/main/resources/templates");
        templateSuffix = properties.getProperty("template.suffix", ".html");
        httpVersion = properties.getProperty("http.version", "HTTP/1.1");
    }

    @Override
    public int getServerPort() {
        return serverPort;
    }

    @Override
    public String getTemplatePrefix() {
        return templatePrefix;
    }

    @Override
    public String getTemplateSuffix() {
        return templateSuffix;
    }

    @Override
    public String getHttpVersion() {
        return httpVersion;
    }

}
