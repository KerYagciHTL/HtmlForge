# HtmlForge Themes

**Beautiful, ready-to-use themes for your HtmlForge projects**

## Quick Start

### 1. Browse Available Themes

Open `themes/index.html` in your browser to see all available themes with live previews.

### 2. Apply a Theme

```java
HtmlBuilder builder = new HtmlBuilder("My Page");

// Choose your theme
builder.useTheme(Theme.CODIX);  // Dark developer theme
// or
builder.useTheme(Theme.MINIMAL);  // Clean minimal theme

// Build your HTML as usual
builder.heading(1, "Hello World")
       .paragraph("This page uses the Codix theme!")
       .button("Click Me")
       .build();
```

### 3. That's It!

Your generated HTML will automatically include the theme's CSS, and all assets will be copied to the `generated/assets/` folder.

---

## Available Themes

### Codix
**A dark developer-focused theme with green and blue accents**

- Perfect for: Technical documentation, developer portfolios, code-related projects
- Style: Dark background with terminal-like aesthetics
- Colors: Green (#00ff41) and blue (#1f6feb) on dark (#0d1117)

```java
builder.useTheme(Theme.CODIX);
```

### Minimal
**A clean and simple light theme**

- Perfect for: Blogs, documentation, content-focused websites
- Style: Minimal with excellent readability
- Colors: Black text on white background with subtle grays

```java
builder.useTheme(Theme.MINIMAL);
```

---

## Contributing Your Own Theme

We love community contributions! Here's how to add your own theme:

### Quick Method (Recommended)

We have an automated script that makes adding themes super easy!

**Step 1: Create Your Theme Folder and CSS**

```bash
cd themes
mkdir YourThemeName
```

Create `YourThemeName/style.css` with your styles. Make sure to style these elements:

- `body`, `h1`-`h6`, `p`, `a`
- `button`, `div`, `input`, `textarea`
- `ul`, `ol`, `li`, `blockquote`, `code`
- `strong`, `em`, `hr`, `img`
- Common classes: `.highlighted`, `.container`, `.wrapper`, `.inner-section`, `.highlight`

**Step 2: Run the Theme Generator Script**

```bash
./add-theme.sh YourThemeName
```

This script will automatically:
- Read your CSS file
- Convert it to a single-line JSON format
- Add it to `themes.json` with placeholder metadata
- Create a backup of `themes.json`
- Show you next steps

**Step 3: Update Theme Metadata**

Edit `themes.json` and update your theme's entry:

```json
"YOUR_THEME_NAME": {
  "name": "YourThemeName",
  "description": "Add a meaningful description here",  // Update this!
  "tags": ["Dark", "Modern", "Cool"],                 // Update these!
  "css": "..."
}
```

**Step 4: Add Theme to Java Enum**

Edit `src/main/java/dev/kxrim/Theme.java`:

```java
public enum Theme {
    CODIX("Codix"),
    MINIMAL("Minimal"),
    YOUR_THEME_NAME("YourThemeName");  // Add this line
    
    // ...existing code...
}
```

**Step 5: Update the Gallery**

Add a preview card for your theme in `themes/index.html` (follow the existing pattern).

**Step 6: Submit a Pull Request**

Create a PR with:
- Your theme folder (`themes/YourThemeName/`)
- Updated `themes.json`
- Updated `Theme.java`
- Updated `themes/index.html`
- Screenshots of your theme in action!

---

### Manual Method (Advanced)

If you prefer to do it manually or the script doesn't work:

1. Create your theme folder and CSS file
2. Manually escape your CSS for JSON (replace `\` with `\\`, `"` with `\"`, newlines with `\n`)
3. Add the escaped CSS to `themes.json`
4. Continue with steps 3-6 above

---

## Theme Structure

```
themes/
├── index.html              # Theme gallery (view in browser)
├── Codix/
│   └── style.css          # Codix theme styles
├── Minimal/
│   └── style.css          # Minimal theme styles
└── YourTheme/
    └── style.css          # Your custom theme
```

---

## How Themes Work

1. When you call `builder.useTheme(Theme.CODIX)`, HtmlForge remembers your choice
2. During `builder.build()`, the theme's CSS is fetched from `https://keryagcihtl.github.io/HtmlForge/themes/themes.json`
3. If the remote fetch fails, it falls back to local theme files in `themes/ThemeName/style.css`
4. The CSS is saved to `generated/assets/style.css`
5. A `<link>` tag is added to the HTML `<head>` to include the stylesheet
6. Your HTML is styled without any extra work!

**Theme Gallery**: Visit [https://keryagcihtl.github.io/HtmlForge/themes](https://keryagcihtl.github.io/HtmlForge/themes) to browse all available themes.

---

## Tips

- **Preview themes locally**: Open `themes/index.html` in your browser
- **Switch themes easily**: Just change the enum value - no other code changes needed
- **Customize**: Copy a theme folder, rename it, and modify the CSS to create your own variant
- **No theme**: Simply don't call `useTheme()` and your HTML will have no styling

---

## Troubleshooting

**Theme not applied?**
- Make sure you called `builder.useTheme(Theme.XXX)` before `builder.build()`
- Check that `themes/ThemeName/style.css` exists
- Verify the `generated/assets/style.css` file was created

**CSS not loading?**
- Open your browser's developer tools and check the console for errors
- Ensure you're opening `generated/index.html` (not a different copy)

---

## License

Themes are part of the HtmlForge project and follow the same license.

---
