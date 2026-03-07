# Skyblock Commission Macro (Fabric 1.21.1)

Mod do automatyzacji komisji na Hypixel Skyblock.

## 🚀 Jak zbudować (Poprawiona metoda)
Twój komputer ma problem ze znalezieniem Javy 21. Zrobiłem nowy skrypt, który sam jej poszuka:

1.  Upewnij się, że masz zainstalowaną **Jave 21 JDK (x64)** z [adoptium.net](https://adoptium.net/temurin/releases/?version=21).
2.  Kliknij prawym przyciskiem na folder i wybierz "Otwórz w terminalu" (lub po prostu otwórz folder).
3.  Uruchom plik **`kompiluj.bat`**.

### Jeśli nadal masz błąd "javaHome seems to be invalid":
To oznacza, że Gradle zapamiętał starą, błędną ścieżkę do Javy. Mój nowy skrypt `kompiluj.bat` spróbuje to naprawić, usuwając folder `.gradle` przed budowaniem.

## 🌟 Funkcje
- Skanowanie świata w poszukiwaniu Mithrilu.
- Pathfinder A* do omijania ścian.
- Automatyczne oddawanie komisji u Króla.
- Menu pod komendą `/macro`.

---
*Używaj z głową.*
