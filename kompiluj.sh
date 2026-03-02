#!/bin/bash
echo "========================================"
echo "  Skyblock Macro - Szybka Kompilacja"
echo "========================================"
echo
echo "Budowanie moda..."
chmod +x gradlew
./gradlew build
if [ $? -eq 0 ]; then
    echo
    echo "========================================"
    echo "  SUKCES! Gotowy mod znajdziesz w:"
    echo "  build/libs/SkyblockMacro.jar"
    echo "========================================"
else
    echo
    echo "BLAD: Kompilacja nie powiodla sie!"
fi
