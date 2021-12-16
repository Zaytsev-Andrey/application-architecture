package ru.geekbrains.loader.config;

public class ConfigFactory {

    public static Config create(String[] args) {
        if (args.length == 4) {
            return new CliConfig(args);
        } else {
            return new PropertyFileConfig("application.properties");
        }
    }
}
