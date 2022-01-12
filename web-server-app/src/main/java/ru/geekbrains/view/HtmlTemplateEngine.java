package ru.geekbrains.view;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.geekbrains.model.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class HtmlTemplateEngine implements TemplateEngine {

    private Model model;

    public HtmlTemplateEngine(Model model) {
        this.model = model;
    }

    public String getView(Path templatePath) {
        Document document;
        try {
            String html = Files.readAllLines(templatePath).stream()
                    .reduce("", (partialString, element) -> partialString + element);

            document = Jsoup.parse(html);
            Elements elements = document.select("[model-attr]");

            elements.forEach(element -> {
                element.text(model.getAttribute(element.attr("model-attr")));
                element.removeAttr("model-attr");
            });
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return document.toString();
    }
}
