package dev.kxrim;

public class HtmlBuilder {
    private final StringBuilder html;
    private final StringBuilder head;
    private String title = "Document";

    public HtmlBuilder() {
        html = new StringBuilder();
        head = new StringBuilder();

        head.append("<!DOCTYPE html>\n");
        head.append("<html>\n");
        head.append("<head>\n");
        head.append("<meta charset=\"UTF-8\">\n");
        head.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        head.append("<title>").append(title).append("</title>\n");
        head.append("</head>\n");
        head.append("<body>\n");

    }

    public HtmlBuilder addElement(String tag, String content) {
        html.append("<").append(tag).append(">")
            .append(content)
            .append("</").append(tag).append(">\n");
        return this;
    }

    public String build() {
        return html.toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        head.insert(head.indexOf("<title>") + 7, title);
    }
}
