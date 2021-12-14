package ru.geekbrains.loader.config;

public class CliConfig implements Config {

    private int serverPort;

    private String templatePrefix;

    private String templateSuffix;

    private String httpVersion;

    public CliConfig(String[] args) {
        serverPort = Integer.parseInt(args[0]);
        templatePrefix = args[1];
        templateSuffix = args[2];
        httpVersion = args[3];
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
