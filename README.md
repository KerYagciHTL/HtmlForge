<div align="center">
  <img src="assets/htmlforge-icon.png" alt="HtmlForge Logo" width="200"/>
  
  **A fluent Java library for building HTML programmatically**
  
  [![Maven Central](https://img.shields.io/maven-central/v/io.github.htmlforge-team/htmlforge?style=for-the-badge&logo=apache-maven&logoColor=white)](https://central.sonatype.com/artifact/io.github.htmlforge-team/htmlforge)
  [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](https://opensource.org/licenses/MIT)
  [![Java Version](https://img.shields.io/badge/Java-21%2B-orange?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
  
  [![GitHub Stars](https://img.shields.io/github/stars/htmlforge-team/HtmlForge?style=for-the-badge&logo=github)](https://github.com/htmlforge-team/HtmlForge/stargazers)
  [![GitHub Forks](https://img.shields.io/github/forks/htmlforge-team/HtmlForge?style=for-the-badge&logo=github)](https://github.com/htmlforge-team/HtmlForge/network/members)
  [![GitHub Issues](https://img.shields.io/github/issues/htmlforge-team/HtmlForge?style=for-the-badge&logo=github)](https://github.com/htmlforge-team/HtmlForge/issues)
  [![Contributors](https://img.shields.io/github/contributors/htmlforge-team/HtmlForge?style=for-the-badge&logo=github)](https://github.com/htmlforge-team/HtmlForge/graphs/contributors)
  
  [Live Demo](https://htmlforge-team.github.io/HtmlForge/) • [Theme Gallery](https://htmlforge-team.github.io/HtmlForge/themes)
  
</div>

---

## Table of Contents

- [Features](#features)
- [Quick Start](#quick-start)
- [Installation](#installation)
- [Theme System](#theme-system)
- [Usage Examples](#usage-examples)
- [Available Elements](#available-elements)
- [Building & Running](#building--running)
- [Contributing](#contributing)
- [License](#license)

---

## Features

<div align="center">

| **Type-Safe** | **Themeable** | **Fluent API** | **Zero Dependencies** |
|:---:|:---:|:---:|:---:|
| Catch errors at compile time | Beautiful pre-built themes | Chain methods elegantly | Lightweight & fast |

</div>

- **No more string concatenation** - Build HTML with clean, readable Java code
- **IDE autocomplete support** - IntelliSense helps you write code faster
- **Type-safe** - Catch mistakes at compile time, not runtime
- **Fluent API** - Chain methods for clean, expressive code
- **Built-in theme system** - Professional styling with one line of code
- **Extensible** - Easy to add custom elements
- **Well-tested** - Comprehensive test coverage

---

## Quick Start

```java
HtmlBuilder builder = new HtmlBuilder("My First Page");

builder.heading(1, "Welcome to HtmlForge!")
       .paragraph("Building HTML has never been this easy.")
       .link("https://github.com/htmlforge-team/HtmlForge", "Star us on GitHub")
       .build();
```

**Output:** A complete, valid HTML5 document ready to use!

---

## Installation

<details open>
<summary><b>Maven</b></summary>

```xml
<dependency>
    <groupId>io.github.keryagcihtl</groupId>
    <artifactId>htmlforge</artifactId>
    <version>0.1.0</version>
</dependency>
```

</details>

<details>
<summary><b>Gradle (Groovy)</b></summary>

```groovy
dependencies {
    implementation 'io.github.keryagcihtl:htmlforge:0.1.0'
}
```

</details>

<details>
<summary><b>Gradle (Kotlin DSL)</b></summary>

```kotlin
dependencies {
    implementation("io.github.keryagcihtl:htmlforge:0.1.0")
}
```

</details>

> **Note:** Replace `0.1.0` with the latest version from [Maven Central](https://central.sonatype.com/artifact/io.github.htmlforge-team/htmlforge)
---
> **Note:** This will soon be changed to `io.github.htmlforge-team` once we set up the organization properly.
---

## Theme System

Apply professional, pre-built themes to your HTML with a single line of code!

```java
HtmlBuilder builder = new HtmlBuilder("Styled Page");
builder.useTheme(Theme.CODIX);  // Dark developer theme
// or
builder.useTheme(Theme.MINIMAL);  // Clean minimal theme

builder.heading(1, "Beautifully Styled!")
       .paragraph("Themes handle all the CSS for you.")
       .build();
```

### Available Themes

| Theme | Style | Best For |
|-------|-------|----------|
| **CODIX** | Dark, developer-focused | Documentation, code examples |
| **MINIMAL** | Clean, lightweight | Landing pages, simple sites |

**Resources:**
- [**Theme Gallery**](https://keryagcihtl.github.io/HtmlForge/themes) - Browse themes with live previews
- [**Create Custom Themes**](themes/README.md) - Full theme documentation
- [**JSON API**](https://keryagcihtl.github.io/HtmlForge/themes/themes.json) - Theme data endpoint

---

## Usage Examples

### Basic Document

```java
HtmlBuilder builder = new HtmlBuilder("My Page");

builder.addElement(new Heading(1, "Welcome"));
builder.addElement(new Paragraph("This is so much easier than strings."));
builder.addElement(new Link("https://github.com/htmlforge-team/HtmlForge", "Check it out"));

builder.build();
```

### Fluent API Style

```java
new HtmlBuilder("Blog Post")
    .heading(1, "My Amazing Blog Post")
    .paragraph("Here's some content...")
    .image("hero.jpg", "Hero image")
    .heading(2, "Section Title")
    .paragraph("More content here...")
    .link("https://example.com", "Read more")
    .build();
```

### Form Example

```java
new HtmlBuilder("Contact Form")
    .heading(1, "Contact Us")
    .addElement(new Input("text", "name", "Your Name"))
    .addElement(new Input("email", "email", "your@email.com"))
    .addElement(new Textarea("message", "Your message...", 5, 40))
    .addElement(new Button("Submit", "submitForm()"))
    .build();
```

### Lists

```java
// Unordered list
builder.addElement(new ListElement(false, 
    "First item", 
    "Second item", 
    "Third item"
));

// Ordered list
builder.addElement(new ListElement(true, 
    "Step 1: Install", 
    "Step 2: Configure", 
    "Step 3: Build"
));
```

---

## Available Elements

<table>
<tr>
<td valign="top" width="33%">

### Text Elements
- `Heading` - H1-H6 headings
- `Paragraph` - Text paragraphs
- `Strong` - Bold text
- `Emphasis` - Italic text
- `Code` - Inline code
- `BlockQuote` - Quotations

</td>
<td valign="top" width="33%">

### Interactive
- `Link` - Hyperlinks
- `Button` - Buttons with JS
- `Input` - Form inputs
- `Textarea` - Text areas

### Layout
- `Div` - Containers
- `ListElement` - Lists (ul/ol)
- `HorizontalRule` - Dividers

</td>
<td valign="top" width="33%">

### Media
- `Image` - Images (URL/local)

### Extensibility
Create custom elements by implementing the `Element` interface:

```java
public class MyElement 
    implements Element {
  @Override
  public String toHtml() {
    return "<tag>content</tag>";
  }
}
```

</td>
</tr>
</table>

---

## Building & Running

### Prerequisites
- Java 21 or higher
- Maven 3.6+

### Build the Project

```bash
mvn clean install
```

### Run the Example

```bash
mvn compile
mvn exec:java -Dexec.mainClass="team.htmlforge.Main"
```

Generated HTML will be in the `generated/` folder.

### Run Tests

```bash
mvn test
```

---

## Contributing

We love contributions! Whether it's bug fixes, new features, or documentation improvements.

**[Read our Contributing Guide](CONTRIBUTING.md)** for detailed information on how to get started.

### Contributors

<a href="https://github.com/htmlforge-team/HtmlForge/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=htmlforge-team/HtmlForge" />
</a>

**Core Team:**
- [Kerimcan Yagci](https://github.com/KerYagciHTL) - Creator & Maintainer
- [Nico Haider](https://github.com/nhaiderhtl) - Tests Contributor
- [Julian Mostbauer](https://github.com/Julian-Mostbauer) - Scripting Contributor

### How to Contribute

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Make your changes and test them
4. Commit your changes (`git commit -m 'Add amazing feature'`)
5. Push to the branch (`git push origin feature/amazing-feature`)
6. Open a Pull Request

---

## License

**MIT License with Attribution**

```
Copyright (c) 2025 htmlforge-team

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

**TL;DR:** Use it however you want, just keep the credits if you modify or redistribute it.

---

<div align="center">

### Star Us!

If you find HtmlForge helpful, please consider giving us a star on GitHub!

[![Star History Chart](https://api.star-history.com/svg?repos=htmlforge-team/HtmlForge&type=Date)](https://star-history.com/#htmlforge-team/HtmlForge&Date)

**Made with care by the HtmlForge Team**

[Report Bug](https://github.com/htmlforge-team/HtmlForge/issues) • [Request Feature](https://github.com/htmlforge-team/HtmlForge/issues)

</div>
