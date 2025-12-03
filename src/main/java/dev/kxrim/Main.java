package dev.kxrim;

public class Main {
    public static void main(String[] args) {
        // Create HtmlBuilder with custom title
        HtmlBuilder htmlBuilder = new HtmlBuilder("HtmlBuilder - Full Feature Demonstration");

        // ===== HEADING ELEMENTS =====
        htmlBuilder.addElement("h1", "HtmlBuilder Feature Showcase");
        htmlBuilder.addElement("h2", "All Available Methods Demonstrated");

        // ===== PARAGRAPH =====
        htmlBuilder.addElement("p", "This page demonstrates all the features of the HtmlBuilder class.");

        // ===== DIV ELEMENTS =====
        htmlBuilder.addDiv("This is a simple div");
        htmlBuilder.addDiv("This is a div with a CSS class", "highlighted");

        // ===== BUTTONS =====
        htmlBuilder.addElement("h3", "Buttons");
        htmlBuilder.addButton("Simple Button");
        htmlBuilder.addButton("Click Me", "alert('Hello from HtmlBuilder!')");
        htmlBuilder.addButton("Console Log", "console.log('Button clicked!')");

        // ===== LINKS =====
        htmlBuilder.addElement("h3", "Links");
        htmlBuilder.addLink("https://github.com", "Visit GitHub");
        htmlBuilder.addLink("https://www.java.com", "Learn Java");

        // ===== IMAGES =====
        htmlBuilder.addElement("h3", "Images");
        htmlBuilder.addImage("https://via.placeholder.com/150", "Placeholder Image");

        // If you have local images, uncomment these:
        // htmlBuilder.addLocalImage("images/logo.png", "Company Logo");
        // htmlBuilder.copyAssets("images"); // Copy all images from images/ to assets/

        // ===== FORM ELEMENTS =====
        htmlBuilder.addElement("h3", "Form Elements");
        htmlBuilder.addInput("text", "username", "Enter your username");
        htmlBuilder.addInput("email", "email", "Enter your email");
        htmlBuilder.addInput("password", "password", "Enter your password");
        htmlBuilder.addTextarea("message", "Enter your message", 5, 40);

        // ===== LISTS =====
        htmlBuilder.addElement("h3", "Unordered List");
        htmlBuilder.addList(false, "First item", "Second item", "Third item");

        htmlBuilder.addElement("h3", "Ordered List");
        htmlBuilder.addList(true, "Step one", "Step two", "Step three");

        // ===== CUSTOM ELEMENTS =====
        htmlBuilder.addElement("h3", "Custom Elements");
        htmlBuilder.addElement("h4", "Subheading");
        htmlBuilder.addElement("h5", "Smaller subheading");
        htmlBuilder.addElement("h6", "Smallest heading");
        htmlBuilder.addElement("blockquote", "This is a quote using addElement");
        htmlBuilder.addElement("code", "const x = 42;");
        htmlBuilder.addElement("strong", "Bold text");
        htmlBuilder.addElement("em", "Italic text");

        // ===== FINAL SECTION =====
        htmlBuilder.addElement("hr", "");
        htmlBuilder.addElement("p", "Built with ❤️ using HtmlBuilder");

        // Build and save the HTML file
        htmlBuilder.build();
    }
}
