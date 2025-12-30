package team.htmlforge

import team.htmlforge.elements.*
import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.stream.Stream

class HtmlBuilder(
    private val lang: String = "en",
    private val charset: String = "UTF-8",
    private val viewport: String = "width=device-width, initial-scale=1.0",
    private val title: String = "Document"
) {
    private val html: StringBuilder = StringBuilder()
    private val head: StringBuilder = StringBuilder()
    private var currentTheme: Theme? = null

    var outputName: String = "index.html"

    companion object {
        val OUTPUT_DIR: Path = Paths.get("generated")
        val ASSETS_DIR: Path = OUTPUT_DIR.resolve("assets")
    }

    init {
        buildHead()
    }

    private fun buildHead() {
        head.setLength(0)
        head.append("<!DOCTYPE html>\n")
        head.append("<html lang=\"").append(lang).append("\">\n")
        head.append("<head>\n")
        head.append("<meta charset=\"").append(charset).append("\">\n")
        head.append("<meta name=\"viewport\" content=\"").append(viewport).append("\">\n")
        head.append("<title>").append(title).append("</title>\n")

        if (currentTheme != null) {
            head.append("<link rel=\"stylesheet\" href=\"assets/style.css\">\n")
        }

        head.append("</head>\n")
        head.append("<body>\n")
    }

    fun addElement(element: Element): HtmlBuilder {
        html.append(element.toHtml())
        return this
    }

    fun addText(text: String): HtmlBuilder {
        html.append(text)
        return this
    }

    // Fluent API methods
    fun heading(level: Int, text: String): HtmlBuilder = addElement(Heading(level, text))

    fun paragraph(content: String): HtmlBuilder = addElement(Paragraph(content))

    fun div(content: String): HtmlBuilder = addElement(Div(content))

    fun div(content: String, className: String): HtmlBuilder = addElement(Div(content, className))

    fun div(div: Div): HtmlBuilder = addElement(div)

    fun createDiv(): Div = Div()

    fun createDiv(className: String): Div = Div(className, true)

    fun button(text: String): HtmlBuilder = addElement(Button(text))

    fun button(text: String, onClick: String): HtmlBuilder = addElement(Button(text, onClick))

    fun link(url: String, text: String): HtmlBuilder = addElement(Link(url, text))

    fun image(src: String, alt: String): HtmlBuilder = addElement(Image(src, alt))

    fun input(type: String, name: String, placeholder: String): HtmlBuilder = addElement(Input(type, name, placeholder))

    fun textarea(name: String, placeholder: String, rows: Int, cols: Int): HtmlBuilder = addElement(Textarea(name, placeholder, rows, cols))

    fun listUnordered(vararg items: String): HtmlBuilder = addElement(ListElement(false, *items))

    fun listOrdered(vararg items: String): HtmlBuilder = addElement(ListElement(true, *items))

    fun blockQuote(content: String): HtmlBuilder = addElement(BlockQuote(content))

    fun code(content: String): HtmlBuilder = addElement(Code(content))

    fun strong(content: String): HtmlBuilder = addElement(Strong(content))

    fun emphasis(content: String): HtmlBuilder = addElement(Emphasis(content))

    fun horizontalRule(): HtmlBuilder = addElement(HorizontalRule())

    fun useTheme(theme: Theme): HtmlBuilder {
        this.currentTheme = theme
        println("Theme set to: ${theme.getThemeName()}")
        return this
    }

    private fun applyTheme() {
        val theme = currentTheme ?: return

        try {
            Files.createDirectories(ASSETS_DIR)

            val css = fetchThemeCssFromJson(theme)

            if (css != null) {
                val cssDestination = ASSETS_DIR.resolve("style.css")
                Files.writeString(cssDestination, css)
                println("Applied theme from remote: ${theme.getThemeName()}")
            } else {
                val themeCssSource = Paths.get(theme.getStylePath())
                if (Files.exists(themeCssSource)) {
                    val cssDestination = ASSETS_DIR.resolve("style.css")
                    Files.copy(themeCssSource, cssDestination, StandardCopyOption.REPLACE_EXISTING)
                    println("Applied theme from local files: ${theme.getThemeName()}")
                } else {
                    System.err.println("Theme CSS not found locally or remotely")
                }
            }
        } catch (e: IOException) {
            System.err.println("Error applying theme: ${e.message}")
        }
    }

    private fun fetchThemeCssFromJson(theme: Theme): String? {
        return try {
            val client = HttpClient.newHttpClient()
            val request = HttpRequest.newBuilder()
                .uri(URI.create(Theme.getThemeJsonUrl()))
                .GET()
                .build()

            val response = client.send(request, HttpResponse.BodyHandlers.ofString())

            if (response.statusCode() == 200) {
                val jsonResponse = response.body()
                extractCssFromJson(jsonResponse, theme.name)
            } else null
        } catch (e: Exception) {
            println("Could not fetch theme from remote, trying local files...")
            null
        }
    }

    private fun extractCssFromJson(json: String, themeName: String): String? {
        return try {
            val themeKey = "\"$themeName\""
            val themeStart = json.indexOf(themeKey)
            if (themeStart == -1) return null

            val cssStart = json.indexOf("\"css\":", themeStart)
            if (cssStart == -1) return null

            val cssValueStart = json.indexOf('"', cssStart + 6) + 1

            var cssValueEnd = cssValueStart
            while (cssValueEnd < json.length) {
                if (json[cssValueEnd] == '"' && json[cssValueEnd - 1] != '\\') {
                    break
                }
                cssValueEnd++
            }

            val cssContent = json.substring(cssValueStart, cssValueEnd)
            cssContent.replace("\\n", "\n")
                .replace("\\\"", "\"")
                .replace("\\\\", "\\")
        } catch (e: Exception) {
            System.err.println("Error parsing JSON: ${e.message}")
            null
        }
    }

    fun copyAssets(sourceDir: String): HtmlBuilder {
        try {
            val sourcePath = Paths.get(sourceDir)
            if (!Files.exists(sourcePath) || !Files.isDirectory(sourcePath)) {
                println("Source directory does not exist: $sourceDir")
                return this
            }

            Files.createDirectories(ASSETS_DIR)

            Files.walk(sourcePath).use { files ->
                files.filter { Files.isRegularFile(it) }
                    .filter { p ->
                        val name = p.fileName.toString().lowercase()
                        name.endsWith(".png") || name.endsWith(".jpg") ||
                                name.endsWith(".jpeg") || name.endsWith(".gif") ||
                                name.endsWith(".svg") || name.endsWith(".webp")
                    }
                    .forEach { source ->
                        try {
                            val destination = ASSETS_DIR.resolve(source.fileName)
                            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING)
                            println("Copied asset: ${source.fileName} -> assets/")
                        } catch (e: IOException) {
                            System.err.println("Failed to copy: $source")
                        }
                    }
            }
        } catch (e: IOException) {
            System.err.println("Error copying assets: ${e.message}")
        }
        return this
    }

    fun addLocalImage(localPath: String, alt: String): HtmlBuilder {
        try {
            val source = Paths.get(localPath)
            if (!Files.exists(source)) {
                System.err.println("Image file not found: $localPath")
                addElement(Image(localPath, alt)) // Add anyway with original path
                return this
            }

            Files.createDirectories(ASSETS_DIR)
            val fileName = source.fileName.toString()
            val destination = ASSETS_DIR.resolve(fileName)
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING)

            addElement(Image("assets/$fileName", alt))
            println("Added local image: $fileName")
        } catch (e: IOException) {
            System.err.println("Error adding local image: ${e.message}")
            addElement(Image(localPath, alt)) // Fallback to original path
        }
        return this
    }

    fun build() {
        applyTheme()

        buildHead()

        head.append(html)
        head.append("</body>\n")
        head.append("</html>")

        try {
            Files.createDirectories(Paths.get("generated"))
            val filePath = Paths.get("generated/$outputName")
            Files.writeString(filePath, head.toString())
            println("HTML file created at: ${filePath.toAbsolutePath()}")
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun getLang() = lang
    fun getCharset() = charset
    fun getViewport() = viewport
    fun getTitle() = title
}
