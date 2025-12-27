package team.htmlforge;

public enum Theme {
    CODIX("Codix"),
    MINIMAL("Minimal"),
    MINIMAL_DARK("MinimalDark"),
    LUXURY("Luxury"),
    ;

    private final String themeName;
    private static final String THEME_JSON_URL = "https://htmlforge-team.github.io/HtmlForge/themes/themes.json";

    Theme(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeName() {
        return themeName;
    }

    public String getStylePath() {
        return "themes/" + themeName + "/style.css";
    }

    public String getThemeDirectory() {
        return "themes/" + themeName;
    }

    public static String getThemeJsonUrl() {
        return THEME_JSON_URL;
    }
}

