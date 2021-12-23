package ru.geekbrains.template_method;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TemplateMethodTest {

    private ByteArrayOutputStream out;

    @BeforeEach
    public void initMethod() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void androidBuilderTest() {
        Builder androidBuilder = new AndroidBuilder();
        androidBuilder.build();

        StringBuilder buildLog = new StringBuilder();
        buildLog.append("Running android tests").append(System.lineSeparator());
        buildLog.append("Linting the android code").append(System.lineSeparator());
        buildLog.append("Assembling the android build").append(System.lineSeparator());
        buildLog.append("Deploying android build to server").append(System.lineSeparator());

        Assertions.assertEquals(buildLog.toString(), out.toString());
    }

    @Test
    public void iosBuilderTest() {
        Builder iosBuilder = new IosBuilder();
        iosBuilder.build();

        StringBuilder buildLog = new StringBuilder();
        buildLog.append("Running ios tests").append(System.lineSeparator());
        buildLog.append("Linting the ios code").append(System.lineSeparator());
        buildLog.append("Assembling the ios build").append(System.lineSeparator());
        buildLog.append("Deploying ios build to server").append(System.lineSeparator());

        Assertions.assertEquals(buildLog.toString(), out.toString());
    }

    @AfterEach
    public void completeMethod() {
        System.setOut(System.out);
    }
}
