# Contributing to HtmlForge
Hey there! Thanks for your interest in contributing to HtmlForge! We're excited to have you here.
This is a friendly, open project and we welcome contributions of all kinds - whether you're fixing a typo, adding a feature, or just sharing ideas. Don't worry about making mistakes; we're all learning together!
## Table of Contents
- [Quick Start](#quick-start)
- [Ways to Contribute](#ways-to-contribute)
- [Development Setup](#development-setup)
- [Code Guidelines (Optional)](#code-guidelines-optional)
- [Creating Custom Themes](#creating-custom-themes)
- [Getting Help](#getting-help)
---
## Quick Start
**New to open source?** No problem! Here's the simplest way to contribute:
1. **Found a typo?** Just open an issue or submit a PR with the fix
2. **Have an idea?** Share it in the issues - we'd love to hear it!
3. **Want to code?** Look for `good first issue` labels
4. **Not sure?** Just ask! There are no silly questions
---
## Ways to Contribute
### Reporting Bugs
Found something broken? Let us know! 
Just open an [issue](https://github.com/KerYagciHTL/HtmlForge/issues) and tell us:
- What you were trying to do
- What happened instead
- Any error messages you saw
**Example:**
> I tried to add an image but got a NullPointerException when the alt text was null.
That's it! Feel free to add more details if you want (Java version, code sample, etc.), but a simple description is totally fine.
### Suggesting Ideas
Have an idea for a new feature or improvement? We'd love to hear it!
Open an issue and describe your idea. It doesn't need to be formal - just explain what you're thinking and why it would be helpful.
**Example:**
> It would be cool if we could add tables. I'm building a dashboard and need to display data in a grid format.
### Improving Documentation
Spot a typo? Think something could be explained better? Documentation improvements are always welcome!
You can edit files directly on GitHub by clicking the pencil icon, or clone the repo and submit a PR.
### Contributing Code
Want to add a feature or fix a bug? Awesome! Here's the general flow:
1. **Fork the repo** and create a branch
   ```bash
   git checkout -b fix-something-cool
   ```
2. **Make your changes** - write your code!
3. **Test it** - make sure it works
   ```bash
   mvn test
   ```
4. **Submit a PR** - we'll review it and work with you to get it merged
Don't stress about getting everything perfect. We're happy to help you refine your contribution!
### Adding a Theme
Love design? Create a custom theme! Check out the [themes directory](themes/) for examples.
1. Create a new folder in `themes/YourThemeName/`
2. Add a `style.css` file
3. Test it out
4. Submit a PR with some screenshots
See [Creating Custom Themes](#creating-custom-themes) below for more details.
---
## Development Setup
### What You Need
- **Java 21+** - [Download](https://www.oracle.com/java/technologies/downloads/)
- **Maven** - [Download](https://maven.apache.org/download.cgi)
- **Git** - [Download](https://git-scm.com/downloads)
### Getting Started
```bash
# Clone your fork
git clone https://github.com/YOUR-USERNAME/HtmlForge.git
cd HtmlForge
# Build it
mvn clean install
# Run the example
mvn exec:java -Dexec.mainClass="team.htmlforge.Main"
# Check the generated HTML
open generated/index.html
```
That's it! You're ready to code.
---
## Code Guidelines (Optional)
These are guidelines, not rules! We won't reject your PR if you don't follow these perfectly. We can always refine things together.
### General Java Style
- Use 4 spaces for indentation (most IDEs do this by default)
- Try to keep lines under 120 characters
- Use meaningful variable names
**Example:**
```java
// Good ✓
public class Paragraph implements Element {
    private final String content;
    public Paragraph(String content) {
        this.content = content;
    }
}
// Also fine - we can help you refactor if needed
public class Paragraph implements Element {
    private String text;
    public Paragraph(String t) { text = t; }
}
```
### Writing Tests
If you're adding new functionality, tests are helpful but not required for your first PR. We can work on them together!
Here's a simple example if you want to try:
```java
@Test
void toHtml_SimpleText_Works() {
    Paragraph p = new Paragraph("Hello");
    assertEquals("<p>Hello</p>", p.toHtml());
}
```
### Commit Messages
Write commit messages however feels natural to you. Here are some examples:
```bash
git commit -m "Fix image bug"
git commit -m "Add table element"
git commit -m "Update README typo"
```
That's perfectly fine! If you want to be more descriptive, that's cool too:
```bash
git commit -m "Fix NullPointerException in Image element
The alt text wasn't being validated, causing crashes
when null values were passed. Added a check and test."
```
---
## Creating Custom Themes
Want to contribute a theme? Awesome! Themes are just CSS files, so it's super easy.
### Quick Theme Setup
1. **Create a folder**: `themes/YourThemeName/`
2. **Add CSS**: Create `style.css` with your styles
3. **Test it**: Apply it to a test HTML file
4. **Submit**: Open a PR with some screenshots
### Theme Tips
- Make it mobile-friendly (use media queries)
- Ensure good contrast for readability
- Style all the common elements (h1-h6, p, a, etc.)
- Have fun with it!
**Example Theme:**
```css
/* themes/Ocean/style.css */
body {
    font-family: 'Georgia', serif;
    background: linear-gradient(to bottom, #e0f7fa, #ffffff);
    color: #006064;
    padding: 2rem;
}
h1 { 
    color: #00838f;
    border-bottom: 3px solid #00acc1;
}
a {
    color: #0097a7;
    text-decoration: none;
}
a:hover {
    color: #00838f;
}
```
Check out [themes/README.md](themes/README.md) for more examples.
---
## Getting Help
### Questions?
- **Open an issue** with the `question` label
- **Start a discussion** on [GitHub Discussions](https://github.com/KerYagciHTL/HtmlForge/discussions)
- **Check the docs** in [README.md](README.md)
### Stuck on Something?
That's totally normal! Don't hesitate to:
- Ask in your PR - we'll help you figure it out
- Open an issue asking for help
- Tag a maintainer for guidance
### Resources
- [Java Documentation](https://docs.oracle.com/en/java/)
- [Maven Guide](https://maven.apache.org/guides/)
- [GitHub Flow](https://docs.github.com/en/get-started/quickstart/github-flow)
---
## Recognition
Everyone who contributes gets:
- Listed in our [README](README.md) contributors section
- Mentioned in release notes
- Our eternal gratitude! 🙏
---
## Be Cool
Just one rule: **Be kind and respectful.** That's it!
We're all here to learn and build cool stuff together. Different skill levels, different perspectives - all welcome! 
---
<div align="center">
<b>Thank you for contributing to HtmlForge!</b>
<br><br>
Every contribution, big or small, makes this project better.
</div>
