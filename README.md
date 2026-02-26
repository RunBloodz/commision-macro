# Skyblock Commission Macro (Fabric 1.21.1)

Ten mod jest automatycznym makrem do wykonywania komisji (Commissions) w Dwarven Mines na serwerze Hypixel Skyblock. Został stworzony na wersję Minecraft **1.21.1** przy użyciu **Fabric**.

## ⚠️ UWAGA: Wymagana Java 21!
Twój błąd wskazuje na używanie **Java 8 (32-bit)**. Minecraft 1.21.1 i Fabric **wymagają Javy 21 (64-bit)** do działania i kompilacji.
1. Pobierz i zainstaluj **Java 21 JDK** (np. z [Adoptium Temurin](https://adoptium.net/)).
2. Upewnij się, że instalujesz wersję **x64 (64-bit)**.

## 🚀 Funkcje
- **Automatyczny Pathfinder (A\*):** Mod sam wyznacza trasę do celów, omijając ściany i przeszkody.
- **Inteligentne Kopanie:** Automatycznie wyszukuje i kopie Mithril oraz Titanium w zasięgu gracza.
- **Odczyt TAB:** Sam sprawdza aktualne komisje w liście TAB.
- **GUI Konfiguracyjne:** Dostępne pod komendą `/macro`.

## 📦 Kompilacja (Budowanie pliku .jar)
Gdy już zainstalujesz Javę 21:
1. Otwórz terminal w tym folderze.
2. Uruchom komendę:
   ```cmd
   ./gradlew build
   ```
3. Gotowy plik znajdziesz w: `build/libs/skyblock-macro-1.0.0.jar`.

## 🔧 Instalacja
1. Skopiuj plik `.jar` do `%appdata%/.minecraft/mods`.
2. Uruchom grę na profilu Fabric 1.21.1.

## 🎮 Jak używać
1. Wpisz `/macro` na czacie w Dwarven Mines.
2. Włącz bota przyciskiem "Macro: ON".

---
*Stworzone przez Jules.*
