import os
import sys
import json
from datetime import datetime
import shutil

class Colors:
    RED = '\033[0;31m'
    GREEN = '\033[0;32m'
    YELLOW = '\033[1;33m'
    BLUE = '\033[0;34m'
    NC = '\033[0m'


def print_header():
    print(f"{Colors.BLUE}╔════════════════════════════════════════╗{Colors.NC}")
    print(f"{Colors.BLUE}║   HtmlForge Preview Generator Script   ║{Colors.NC}")
    print(f"{Colors.BLUE}╚════════════════════════════════════════╝{Colors.NC}")
    print()


def print_error(message):
    print(f"{Colors.RED}{message}{Colors.NC}")


def print_success(message):
    print(f"{Colors.GREEN}{message}{Colors.NC}")


def print_warning(message):
    print(f"{Colors.YELLOW}{message}{Colors.NC}")


def print_info(message):
    print(f"{Colors.BLUE}{message}{Colors.NC}")


def ensure_files_exist():
    if not os.path.isfile('themes.json'):
        print_warning("Warning: themes.json not found in current directory")
        print("Attempting to navigate to themes directory...")

        if os.path.isdir('themes'):
            os.chdir('themes')
            print_success("Navigated to themes directory")

        if not os.path.isfile('themes.json'):
            print_error("Error: themes.json not found!")
            print("Please run this script from the themes directory.")
            sys.exit(1)

    if not os.path.isfile('index.html'):
        print_error("Error: index.html not found!")
        print("Please ensure index.html exists in the themes directory.")
        sys.exit(1)


def load_themes():
    print_info("Loading themes from themes.json...")
    try:
        with open('themes.json', 'r') as f:
            data = json.load(f)

        if 'themes' not in data or not data['themes']:
            print_error("Error: No themes found in themes.json")
            sys.exit(1)

        print_success(f"Found {len(data['themes'])} theme(s)")
        return data['themes']

    except Exception as e:
        print_error(f"Error reading themes.json: {e}")
        sys.exit(1)


def create_backup(filename):
    timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
    backup_file = f"{filename}.backup.{timestamp}"

    try:
        shutil.copy2(filename, backup_file)
        print_success(f"Created backup: {backup_file}")
        return backup_file
    except Exception as e:
        print_error(f"Error creating backup: {e}")
        sys.exit(1)


def generate_preview_iframe_content(theme_name, theme_css):
    html_content = f"""<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
{theme_css}
    </style>
</head>
<body>
    <h1>{theme_name} Theme</h1>
    <p>Experience the beautiful styling of this theme.</p>
    <button>Click Me</button>
    <div class="highlighted">
        <p>This is a highlighted section with custom styling.</p>
    </div>
</body>
</html>"""
    return html_content


def generate_theme_card_html(theme_key, theme_data):
    theme_name = theme_data.get('name', theme_key)
    theme_description = theme_data.get('description', 'No description available')
    theme_tags = theme_data.get('tags', [])
    theme_css = theme_data.get('css', '')

    iframe_content = generate_preview_iframe_content(theme_name, theme_css)
    import base64
    iframe_data = base64.b64encode(iframe_content.encode('utf-8')).decode('utf-8')

    tags_html = '\n                        '.join(
        [f'<span class="theme-tag">{tag}</span>' for tag in theme_tags]
    )

    card_html = f"""
            <!-- {theme_name} Theme -->
            <div class="theme-card">
                <div class="theme-preview">
                    <iframe 
                        class="theme-preview-iframe" 
                        src="data:text/html;base64,{iframe_data}"
                        sandbox="allow-same-origin"
                        scrolling="no"
                    ></iframe>
                </div>
                <div class="theme-info">
                    <h2>{theme_name}</h2>
                    <p>{theme_description}</p>
                    <div class="theme-tags">
                        {tags_html}
                    </div>
                    <div class="code-block">
<span class="keyword">builder</span>.<span class="method">useTheme</span>(<span class="enum">Theme.{theme_key}</span>);
                    </div>
                </div>
            </div>"""

    return card_html


def update_index_html(themes):
    print_info("Updating index.html with theme previews...")

    try:
        with open('index.html', 'r') as f:
            html_content = f.read()

        preview_style_pattern = '.theme-preview {\n            height: 320px;'
        preview_style_replacement = '.theme-preview {\n            height: 500px;'

        if preview_style_pattern in html_content:
            html_content = html_content.replace(preview_style_pattern, preview_style_replacement)

        iframe_styles = """
        /* Theme Preview Iframe Styles - Auto-generated */
        .theme-preview-iframe {
            width: 100%;
            height: 100%;
            border: none;
            display: block;
        }
        /* End Theme Preview Iframe Styles */"""

        style_end_pos = html_content.rfind('</style>')
        if style_end_pos == -1:
            print_error("Error: Could not find </style> tag in index.html")
            return False

        iframe_start_marker = "/* Theme Preview Iframe Styles - Auto-generated */"
        iframe_end_marker = "/* End Theme Preview Iframe Styles */"

        iframe_start = html_content.find(iframe_start_marker)
        if iframe_start != -1:
            iframe_end = html_content.find(iframe_end_marker)
            if iframe_end != -1:
                html_content = html_content[:iframe_start] + html_content[iframe_end + len(iframe_end_marker):]
                style_end_pos = html_content.rfind('</style>')

        old_preview_start = html_content.find("/* Theme Preview Styles - Auto-generated */")
        if old_preview_start != -1:
            old_preview_end = html_content.find("/* End Theme Preview Styles */")
            if old_preview_end != -1:
                html_content = html_content[:old_preview_start] + html_content[old_preview_end + len("/* End Theme Preview Styles */"):]
                style_end_pos = html_content.rfind('</style>')

        html_content = html_content[:style_end_pos] + "\n" + iframe_styles + "\n    " + html_content[style_end_pos:]

        all_cards_html = ""
        for theme_key, theme_data in themes.items():
            card_html = generate_theme_card_html(theme_key, theme_data)
            all_cards_html += card_html + "\n"

        grid_start = html_content.find('<div class="theme-grid">')
        if grid_start == -1:
            print_error("Error: Could not find theme-grid div in index.html")
            return False

        depth = 1
        pos = grid_start + len('<div class="theme-grid">')
        while depth > 0 and pos < len(html_content):
            if html_content[pos:pos+4] == '<div':
                next_char = html_content[pos+4:pos+5]
                if next_char in [' ', '>']:
                    depth += 1
            elif html_content[pos:pos+6] == '</div>':
                depth -= 1
                if depth == 0:
                    grid_end = pos
                    break
            pos += 1

        if depth != 0:
            print_error("Error: Could not find end of theme-grid div")
            return False

        new_grid_content = f'<div class="theme-grid">{all_cards_html}\n        </div>'
        html_content = html_content[:grid_start] + new_grid_content + html_content[grid_end + 6:]

        with open('index.html', 'w') as f:
            f.write(html_content)

        return True

    except Exception as e:
        print_error(f"Error updating index.html: {e}")
        import traceback
        traceback.print_exc()
        return False


def print_success_message():
    print()
    print(f"{Colors.GREEN}╔════════════════════════════════════════╗{Colors.NC}")
    print(f"{Colors.GREEN}║      Preview Updated Successfully!     ║{Colors.NC}")
    print(f"{Colors.GREEN}╚════════════════════════════════════════╝{Colors.NC}")
    print()
    print_success("✓ index.html has been updated with all theme previews")
    print()
    print_info("Next Steps:")
    print("1. Open index.html in your browser to view the previews")
    print("2. Verify that all themes display correctly")
    print("3. If everything looks good, commit your changes")
    print("4. Create a pull request!")
    print()


def main():
    print_header()

    ensure_files_exist()

    themes = load_themes()

    backup_file = create_backup('index.html')

    if update_index_html(themes):
        print_success_message()
        print_success(f"Backup saved at: {backup_file}")
    else:
        print_error("Failed to update index.html")
        print_warning("Restoring from backup...")
        try:
            shutil.copy2(backup_file, 'index.html')
            print_success("Restored from backup")
        except Exception as e:
            print_error(f"Error restoring backup: {e}")
        sys.exit(1)


if __name__ == "__main__":
    main()

