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
    """Check if themes.json and index.html exist"""
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
    """Load themes from themes.json"""
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
    """Create a backup of the file"""
    timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
    backup_file = f"{filename}.backup.{timestamp}"

    try:
        shutil.copy2(filename, backup_file)
        print_success(f"Created backup: {backup_file}")
        return backup_file
    except Exception as e:
        print_error(f"Error creating backup: {e}")
        sys.exit(1)


def generate_preview_styles(theme_name_lower, theme_css):
    """Generate CSS styles for theme preview"""
    # Basic CSS parsing to extract colors and styles
    styles = f"""
        /* {theme_name_lower.title()} Preview Styles */
        .{theme_name_lower}-preview {{"""

    # Try to extract background color
    if 'background-color:' in theme_css or 'background:' in theme_css:
        # Simple extraction - look for body background
        lines = theme_css.split('\n')
        in_body = False
        for line in lines:
            if 'body' in line and '{' in line:
                in_body = True
            if in_body:
                if 'background-color:' in line:
                    bg_color = line.split('background-color:')[1].split(';')[0].strip()
                    styles += f"\n            background: {bg_color};"
                    in_body = False
                    break
                elif 'background:' in line and 'linear-gradient' not in line and 'radial-gradient' not in line:
                    bg_color = line.split('background:')[1].split(';')[0].strip()
                    styles += f"\n            background: {bg_color};"
                    in_body = False
                    break

    # Extract color for text
    if 'color:' in theme_css:
        lines = theme_css.split('\n')
        in_body = False
        for line in lines:
            if 'body' in line and '{' in line:
                in_body = True
            if in_body and 'color:' in line and 'background-color' not in line:
                text_color = line.split('color:')[1].split(';')[0].strip()
                styles += f"\n            color: {text_color};"
                break

    # Try to extract font-family
    if 'font-family:' in theme_css:
        lines = theme_css.split('\n')
        in_body = False
        for line in lines:
            if 'body' in line and '{' in line:
                in_body = True
            if in_body and 'font-family:' in line:
                font = line.split('font-family:')[1].split(';')[0].strip()
                styles += f"\n            font-family: {font};"
                break

    styles += "\n        }"

    # Add h1 styles
    styles += f"\n\n        .{theme_name_lower}-preview h1 {{"
    if 'h1' in theme_css:
        lines = theme_css.split('\n')
        in_h1 = False
        for line in lines:
            if 'h1' in line and '{' in line:
                in_h1 = True
            if in_h1:
                if 'color:' in line:
                    color = line.split('color:')[1].split(';')[0].strip()
                    styles += f"\n            color: {color};"
                if 'border-bottom:' in line:
                    border = line.split('border-bottom:')[1].split(';')[0].strip()
                    styles += f"\n            border-bottom: {border};"
                if 'padding-bottom:' in line:
                    padding = line.split('padding-bottom:')[1].split(';')[0].strip()
                    styles += f"\n            padding-bottom: {padding};"
                if 'font-size:' in line:
                    in_h1 = False

    styles += "\n            font-size: 1.8em;"
    styles += "\n            margin-bottom: 15px;"
    styles += "\n        }"

    # Add paragraph styles
    styles += f"\n\n        .{theme_name_lower}-preview p {{"
    if ' p {' in theme_css or ' p{' in theme_css:
        lines = theme_css.split('\n')
        in_p = False
        for line in lines:
            if ' p ' in line and '{' in line:
                in_p = True
            if in_p and 'color:' in line:
                color = line.split('color:')[1].split(';')[0].strip()
                styles += f"\n            color: {color};"
                break

    styles += "\n            margin: 12px 0;"
    styles += "\n        }"

    # Add button styles
    styles += f"\n\n        .{theme_name_lower}-preview button {{"
    if 'button' in theme_css:
        lines = theme_css.split('\n')
        in_button = False
        for line in lines:
            if 'button' in line and '{' in line:
                in_button = True
            if in_button:
                if 'background:' in line or 'background-color:' in line:
                    key = 'background:' if 'background:' in line else 'background-color:'
                    bg = line.split(key)[1].split(';')[0].strip()
                    styles += f"\n            background: {bg};"
                if 'color:' in line and 'background' not in line:
                    color = line.split('color:')[1].split(';')[0].strip()
                    styles += f"\n            color: {color};"
                if 'border:' in line:
                    border = line.split('border:')[1].split(';')[0].strip()
                    styles += f"\n            border: {border};"
                if 'padding:' in line:
                    padding = line.split('padding:')[1].split(';')[0].strip()
                    styles += f"\n            padding: {padding};"
                if 'border-radius:' in line:
                    radius = line.split('border-radius:')[1].split(';')[0].strip()
                    styles += f"\n            border-radius: {radius};"
                if 'font-family:' in line:
                    font = line.split('font-family:')[1].split(';')[0].strip()
                    styles += f"\n            font-family: {font};"
                    in_button = False

    styles += "\n            cursor: pointer;"
    styles += "\n            margin-top: 10px;"
    styles += "\n        }"

    # Add div styles
    styles += f"\n\n        .{theme_name_lower}-preview .demo-div {{"
    if 'div' in theme_css:
        lines = theme_css.split('\n')
        in_div = False
        for line in lines:
            if 'div' in line and '{' in line:
                in_div = True
            if in_div:
                if 'background:' in line or 'background-color:' in line:
                    key = 'background:' if 'background:' in line else 'background-color:'
                    bg = line.split(key)[1].split(';')[0].strip()
                    styles += f"\n            background: {bg};"
                if 'border-left:' in line:
                    border = line.split('border-left:')[1].split(';')[0].strip()
                    styles += f"\n            border-left: {border};"
                if 'padding:' in line:
                    padding = line.split('padding:')[1].split(';')[0].strip()
                    styles += f"\n            padding: {padding};"
                if 'border-radius:' in line:
                    radius = line.split('border-radius:')[1].split(';')[0].strip()
                    styles += f"\n            border-radius: {radius};"
                    in_div = False

    styles += "\n            margin-top: 15px;"
    styles += "\n        }"

    return styles


def generate_theme_card_html(theme_key, theme_data):
    """Generate HTML for a theme preview card"""
    theme_name = theme_data.get('name', theme_key)
    theme_description = theme_data.get('description', 'No description available')
    theme_tags = theme_data.get('tags', [])
    theme_name_lower = theme_name.lower().replace(' ', '').replace('-', '')

    tags_html = '\n                        '.join(
        [f'<span class="theme-tag">{tag}</span>' for tag in theme_tags]
    )

    card_html = f"""
            <!-- {theme_name} Theme -->
            <div class="theme-card">
                <div class="theme-preview">
                    <div class="theme-preview-content {theme_name_lower}-preview">
                        <h1>{theme_name} Theme</h1>
                        <p>A {theme_description.split('.')[0].lower()}</p>
                        <button>Click Me</button>
                        <div class="demo-div">
                            <p>Styled div element</p>
                        </div>
                    </div>
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
    """Update index.html with all theme preview cards"""
    print_info("Updating index.html with theme previews...")

    try:
        with open('index.html', 'r') as f:
            html_content = f.read()

        # Generate preview styles for all themes
        all_preview_styles = ""
        for theme_key, theme_data in themes.items():
            theme_css = theme_data.get('css', '')
            theme_name_lower = theme_data.get('name', theme_key).lower().replace(' ', '').replace('-', '')
            preview_styles = generate_preview_styles(theme_name_lower, theme_css)
            all_preview_styles += preview_styles + "\n"

        # Find the position to insert preview styles (before closing </style>)
        style_end_pos = html_content.rfind('</style>')
        if style_end_pos == -1:
            print_error("Error: Could not find </style> tag in index.html")
            return False

        # Check if preview styles already exist and remove them
        # Look for comment markers
        preview_start_marker = "/* Theme Preview Styles - Auto-generated */"
        preview_end_marker = "/* End Theme Preview Styles */"

        preview_start = html_content.find(preview_start_marker)
        if preview_start != -1:
            preview_end = html_content.find(preview_end_marker)
            if preview_end != -1:
                # Remove old preview styles
                html_content = html_content[:preview_start] + html_content[preview_end + len(preview_end_marker):]
                style_end_pos = html_content.rfind('</style>')

        # Insert new preview styles
        new_preview_section = f"\n        {preview_start_marker}\n{all_preview_styles}\n        {preview_end_marker}\n    "
        html_content = html_content[:style_end_pos] + new_preview_section + html_content[style_end_pos:]

        # Generate theme cards HTML
        all_cards_html = ""
        for theme_key, theme_data in themes.items():
            card_html = generate_theme_card_html(theme_key, theme_data)
            all_cards_html += card_html + "\n"

        # Find the theme-grid section and replace its content
        grid_start = html_content.find('<div class="theme-grid">')
        if grid_start == -1:
            print_error("Error: Could not find theme-grid div in index.html")
            return False

        grid_end = html_content.find('</div>', grid_start)
        # Find the actual end of theme-grid (need to skip nested divs)
        depth = 1
        pos = grid_start + len('<div class="theme-grid">')
        while depth > 0 and pos < len(html_content):
            if html_content[pos:pos+5] == '<div ':
                depth += 1
            elif html_content[pos:pos+6] == '</div>':
                depth -= 1
                if depth == 0:
                    grid_end = pos
                    break
            pos += 1

        if grid_end == -1:
            print_error("Error: Could not find end of theme-grid div")
            return False

        # Replace the content of theme-grid
        new_grid_content = f'<div class="theme-grid">{all_cards_html}\n        </div>'
        html_content = html_content[:grid_start] + new_grid_content + html_content[grid_end + 6:]

        # Write updated HTML
        with open('index.html', 'w') as f:
            f.write(html_content)

        return True

    except Exception as e:
        print_error(f"Error updating index.html: {e}")
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

    # Ensure required files exist
    ensure_files_exist()

    # Load themes from themes.json
    themes = load_themes()

    # Create backup of index.html
    backup_file = create_backup('index.html')

    # Update index.html with theme previews
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

