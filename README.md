# Skyblock Commission Macro (Fabric 1.21.1)

Ten mod jest automatycznym makrem do wykonywania komisji (Commissions) w Dwarven Mines na serwerze Hypixel Skyblock. Został stworzony na wersję Minecraft **1.21.1** przy użyciu **Fabric**.

## 🚀 Funkcje
- **Automatyczny Pathfinder (A\*):** Mod sam wyznacza trasę do celów, omijając ściany i przeszkody.
- **Inteligentne Kopanie:** Automatycznie wyszukuje i kopie Mithril oraz Titanium w zasięgu gracza.
- **Odczyt TAB:** Sam sprawdza aktualne komisje w liście TAB i decyduje, gdzie się udać.
- **System Stanów:** Obsługuje cykl: Skanowanie -> Podróż -> Kopanie -> Odbiór nagrody u Króla.
- **GUI Konfiguracyjne:** Dostępne pod komendą `/macro`.
- **Zwiększone Bezpieczeństwo:**
  - Płynne ruchy kamery (Smooth Rotation).
  - Losowe przerwy między akcjami (Human-like pauses).
  - Lekkie losowe odchylenia w celowaniu i chodzeniu.

## 🛠️ Wymagania
- **Java 21** (niezbędna do kompilacji i działania Minecrafta 1.21).
- **Fabric Loader** zainstalowany w Minecraft Launcherze.

## 📦 Kompilacja (Budowanie pliku .jar)
Jeśli pobrałeś kod źródłowy, musisz go skompilować:
1. Otwórz folder z modem w terminalu/konsoli.
2. Uruchom komendę:
   ```bash
   ./gradlew build
   ```
   (Na Windows: `gradlew.bat build`)
3. Po zakończeniu, gotowy plik `.jar` znajdziesz w folderze `build/libs/skyblock-macro-1.0.0.jar`.

## 🔧 Instalacja
1. Skopiuj wygenerowany plik `.jar` do folderu `%appdata%/.minecraft/mods`.
2. Upewnij się, że masz zainstalowany **Fabric API** dla wersji 1.21.1 (choć mod stara się być samowystarczalny, Fabric API jest zawsze zalecany).
3. Uruchom grę na profilu Fabric 1.21.1.

## 🎮 Jak używać
1. Wejdź na Hypixel Skyblock i udaj się do **Dwarven Mines**.
2. Wpisz komendę `/macro` na czacie, aby otworzyć menu.
3. W menu możesz:
   - Włączyć/Wyłączyć makro (**Macro: ON/OFF**).
   - Zmienić domyślny cel podróży.
4. Po włączeniu mod sam zacznie czytać Twoje komisje i wykonywać ruchy.

## ⚠️ Ważne informacje (Bezpieczeństwo)
- **Ryzyko Bana:** Każde makro na Hypixelu jest niezgodne z regulaminem. Używasz go na **własną odpowiedzialność**.
- **Koordynaty:** Koordynaty w kodzie są przybliżone. Jeśli bot utknie, możesz zmienić stałe w pliku `MacroConfig.java` i przebudować mod.
- **Anti-Cheat:** Mod posiada mechanizmy udające człowieka, ale długie sesje (wiele godzin bez przerwy) mogą zwrócić uwagę administracji.

## ⚙️ Zaawansowana konfiguracja
Możesz edytować plik `com.example.macro.MacroConfig.java`, aby zmienić:
- `kingPos` - Miejsce, gdzie stoi Król (do oddawania komisji).
- `upperMinesPos`, `royalMinesPos` - Lokacje stref kopania.
- `minPauseTicks`, `maxPauseTicks` - Czas trwania losowych przerw.

---
*Stworzone przez Jules.*
