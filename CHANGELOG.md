# Changelog

All notable changes to HtmlForge will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.1.0] - 2025-12-14

### Added
- **Core HTML Builder API** - Fluent API for building HTML programmatically
- **Complete HTML Element Support**:
  - Headings (h1-h6)
  - Paragraphs
  - Divs with class support
  - Links
  - Images (with local file copying)
  - Buttons (with onclick handlers)
  - Form inputs (text, email, password, etc.)
  - Textareas
  - Lists (ordered and unordered)
  - Text formatting (strong, emphasis, code)
  - Block quotes
  - Horizontal rules

- **Theme System**:
  - Built-in theme support with dual-source loading (remote + local fallback)
  - Two initial themes: CODIX (dark developer theme) and MINIMAL (clean light theme)
  - Theme JSON API for easy theme distribution
  - Automatic CSS generation and asset management

- **Fluent Div API** - Nested element building with method chaining
- **Asset Management** - Automatic copying of images and CSS files to output directory
- **Type-safe Theme Selection** - Enum-based theme management

### Features
- Java 21 support
- Maven-based project structure
- Comprehensive JUnit test coverage
- Javadoc documentation
- GitHub Pages deployment
- MIT License

### Links
- [GitHub Repository](https://github.com/htmlforge-team/HtmlForge)
- [Theme Gallery](https://htmlforge-team.github.io/HtmlForge/themes)
- [Example Site](https://htmlforge-team.github.io/HtmlForge)

---

[0.1.0]: https://github.com/htmlforge-team/HtmlForge/releases/tag/v0.1.0

