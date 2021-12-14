package ru.geekbrains.loader.config;


public interface Config {

    int getServerPort();

    String getTemplatePrefix();

    String getTemplateSuffix();

    String getHttpVersion();

}
