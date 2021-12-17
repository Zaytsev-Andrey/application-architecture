package ru.geekbrains.bridge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BridgeTest {

    @Test
    public void AboutPageWithDarkThemeTest() {
        Theme darkTheme = new DarkTheme();
        WebPage about = new About(darkTheme);
        Assertions.assertEquals("About page in Dark Black", about.getContent());
    }

    @Test
    public void AboutPageWithLightThemeTest() {
        Theme darkTheme = new LightTheme();
        WebPage about = new About(darkTheme);
        Assertions.assertEquals("About page in Off white", about.getContent());
    }

    @Test
    public void AboutPageWithAquaThemeTest() {
        Theme darkTheme = new AquaTheme();
        WebPage about = new About(darkTheme);
        Assertions.assertEquals("About page in Light blue", about.getContent());
    }

    @Test
    public void CareersPageWithDarkThemeTest() {
        Theme darkTheme = new DarkTheme();
        WebPage about = new Careers(darkTheme);
        Assertions.assertEquals("Careers page in Dark Black", about.getContent());
    }

    @Test
    public void CareersPageWithLightThemeTest() {
        Theme darkTheme = new LightTheme();
        WebPage about = new Careers(darkTheme);
        Assertions.assertEquals("Careers page in Off white", about.getContent());
    }

    @Test
    public void CareersPageWithAquaThemeTest() {
        Theme darkTheme = new AquaTheme();
        WebPage about = new Careers(darkTheme);
        Assertions.assertEquals("Careers page in Light blue", about.getContent());
    }
}
