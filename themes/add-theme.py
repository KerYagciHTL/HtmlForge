import os
import sys
import json
import shutil
from datetime import datetime

class Colors:
    RED = '\033[0;31m'
    GREEN = '\033[0;32m'
    YELLOW = '\033[1;33m'
    BLUE = '\033[0;34m'
    NC = '\033[0m'


def print_header():
    print(f"{Colors.BLUE}╔════════════════════════════════════════╗{Colors.NC}")
    print(f"{Colors.BLUE}║   HtmlForge Theme Generator Script     ║{Colors.NC}")
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


def get_theme_name(theme_dir):
    return theme_dir.upper().replace('-', '_')


def ensure_themes_json():
    if not os.path.isfile('themes.json'):
        print_warning("Warning: themes.json not found in current directory")
        print("Attempting to navigate to themes directory...")

        if os.path.isdir('themes'):
            os.chdir('themes')
            print_success("Navigated to themes directory")

        if not os.path.isfile('themes.json'):
            print_warning("Creating new themes.json file...")
            with open('themes.json', 'w') as f:
                json.dump({"themes": {}}, f, indent=2)
            print_success("Created themes.json")


def validate_theme_directory(theme_path):
    if not os.path.isdir(theme_path):
        print_error(f"Error: Theme directory '{theme_path}' does not exist")
        print()
        print("Available theme directories:")

        try:
            dirs = [d for d in os.listdir('.') if os.path.isdir(d) and d != 'node_modules']
            if dirs:
                for d in dirs:
                    print(f"  {d}/")
            else:
                print("  (none found)")
        except Exception:
            print("  (none found)")

        sys.exit(1)


def validate_css_file(css_file):
    if not os.path.isfile(css_file):
        print_error(f"Error: CSS file not found at '{css_file}'")
        print()
        print(f"Please create a style.css file in the {os.path.dirname(css_file)} directory first.")
        sys.exit(1)


def read_css_content(css_file):
    print_info("Processing CSS file...")

    try:
        with open(css_file, 'r', encoding='utf-8') as f:
            css_content = f.read()

        if not css_content.strip():
            print_error("Error: CSS file is empty")
            sys.exit(1)

        print_success(f"CSS processed successfully ({len(css_content)} characters)")
        print()

        return css_content

    except Exception as e:
        print_error(f"Error reading CSS file: {e}")
        sys.exit(1)


def create_backup():
    timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
    backup_file = f"themes.json.backup.{timestamp}"

    try:
        shutil.copy2('themes.json', backup_file)
        print_success(f"Created backup: {backup_file}")
        return backup_file
    except Exception as e:
        print_error(f"Error creating backup: {e}")
        sys.exit(1)


def check_theme_exists(theme_name):
    try:
        with open('themes.json', 'r') as f:
            data = json.load(f)

        if 'themes' in data and theme_name in data['themes']:
            print_warning(f"Warning: Theme '{theme_name}' already exists in themes.json")
            response = input("Do you want to overwrite it? (y/N): ").strip().lower()

            if response not in ['y', 'yes']:
                return False

        return True

    except Exception as e:
        print_error(f"Error reading themes.json: {e}")
        sys.exit(1)


def update_themes_json(theme_name, theme_dir, css_content):
    print_info("Adding theme to themes.json...")

    try:
        with open('themes.json', 'r') as f:
            data = json.load(f)

        if 'themes' not in data:
            data['themes'] = {}

        data['themes'][theme_name] = {
            'name': theme_dir,
            'description': 'TODO: Add a description for your theme',
            'tags': ['TODO', 'Add', 'Tags'],
            'css': css_content
        }

        with open('themes.json', 'w') as f:
            json.dump(data, f, indent=2)

        return True

    except Exception as e:
        print_error(f"Error updating themes.json: {e}")
        return False


def print_success_message(theme_name, theme_dir, backup_file):
    print()
    print(f"{Colors.GREEN}╔════════════════════════════════════════╗{Colors.NC}")
    print(f"{Colors.GREEN}║         Theme Added Successfully!      ║{Colors.NC}")
    print(f"{Colors.GREEN}╚════════════════════════════════════════╝{Colors.NC}")
    print()
    print_warning("⚠  IMPORTANT: Next Steps")
    print()
    print("1. Edit themes.json and update your theme's metadata:")
    print("   - description: Add a meaningful description")
    print("   - tags: Add relevant tags (e.g., 'Dark', 'Light', 'Minimal')")
    print()
    print("2. Add your theme to the Java enum in:")
    print("   src/main/java/dev/kxrim/Theme.java")
    print()
    print("   Add this line:")
    print(f"   {Colors.BLUE}{theme_name}(\"{theme_dir}\"){Colors.NC}")
    print()
    print("3. Update themes/index.html with a preview card")
    print()
    print("4. Test your theme locally")
    print()
    print("5. Create a pull request!")
    print()
    print(f"Backup saved at: {Colors.GREEN}{backup_file}{Colors.NC}")
    print()


def main():
    print_header()

    theme_dir = input("Directory name: ")
    theme_name = get_theme_name(theme_dir)
    theme_path = theme_dir

    ensure_themes_json()

    validate_theme_directory(theme_path)

    css_file = os.path.join(theme_path, 'style.css')
    validate_css_file(css_file)

    print_success(f"Found theme directory: {theme_path}")
    print_success(f"Found CSS file: {css_file}")
    print()

    css_content = read_css_content(css_file)

    backup_file = create_backup()

    if not check_theme_exists(theme_name):
        print_warning(f"Aborted. Backup preserved at: {backup_file}")
        sys.exit(1)

    if update_themes_json(theme_name, theme_dir, css_content):
        print_success_message(theme_name, theme_dir, backup_file)
    else:
        print_error("Failed to update themes.json")
        print("Restoring from backup...")
        try:
            shutil.copy2(backup_file, 'themes.json')
            print_success("Restored from backup")
        except Exception as e:
            print_error(f"Error restoring backup: {e}")
        sys.exit(1)


if __name__ == "__main__":
    main()
