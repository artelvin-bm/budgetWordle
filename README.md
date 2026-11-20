# BudgetWordleGame

A Java Swing desktop game inspired by Wordle, built as a NetBeans project.

Players get **6 attempts** to guess a hidden word in one of four lengths:

- 5 letters
- 6 letters
- 7 letters
- 8 letters

The app uses color feedback per letter after each guess:

- **Green** = correct letter in the correct position
- **Yellow** = correct letter in the wrong position
- **No color** = letter not in the word (or extra duplicate beyond the hidden word count)

---

## Features

- Multiple difficulty levels based on word length (`5`, `6`, `7`, `8`)
- Random difficulty mode
- 6-row guess board per round
- Input validation with dedicated invalid-input dialog
- Victory and defeat dialogs with replay/return options
- Hidden cheat display (click the logo to toggle answer dialog)

---

## Codebase Analysis (Quick Technical Walkthrough)

### Core classes

- `src/my/game/BudgetWordleGameUI.java`
  - Main JFrame and game flow controller.
  - Handles panel/dialog switching, difficulty selection, validation, guess attempts, and end-state dialogs.
  - Uses `Jsoup.parse(...).text()` to safely detect if a guess label is empty even when labels contain HTML color markup.

- `src/my/game/RandomWordSelector.java`
  - Loads words from resource `.txt` files via classpath `InputStream`.
  - Selects random entry from loaded list.

- `src/my/game/guessChecker.java`
  - Returns an HTML-formatted string for each guess, coloring letters similarly to Wordle.
  - Includes repeat-letter handling logic so yellow counts do not exceed target-word occurrences.

### Word resources

Located in `src/my/resources/`:

- `five.txt` (999 words)
- `six.txt` (999 words)
- `seven.txt` (999 words)
- `eight.txt` (999 words)

These files are loaded at runtime depending on selected difficulty.

### Build/project metadata

- NetBeans Ant project files: `build.xml`, `nbproject/*`
- Main class: `my.game.BudgetWordleGameUI`
- Java source/target level in project metadata: **22**

---

## Project Structure

```text
BudgetWordleGame/
├─ src/
│  └─ my/
│     ├─ game/
│     │  ├─ BudgetWordleGameUI.java
│     │  ├─ guessChecker.java
│     │  └─ RandomWordSelector.java
│     └─ resources/
│        ├─ five.txt
│        ├─ six.txt
│        ├─ seven.txt
│        ├─ eight.txt
│        ├─ logo.png
│        └─ back-icon.png
├─ test/
├─ nbproject/
├─ build.xml
└─ manifest.mf
```

---

## Requirements

- Java JDK (project metadata targets Java 22)
- NetBeans (recommended) or Apache Ant + JDK for command-line builds
- Jsoup library (`org.jsoup.Jsoup` is used by UI logic)

---

## How to Run

### Option A: NetBeans (recommended)

1. Open project folder in NetBeans.
2. Ensure Jsoup is available in project libraries.
3. Run project (`BudgetWordleGameUI` main class).

### Option B: Ant (CLI)

1. Install JDK + Ant.
2. Ensure `file.reference.jsoup-1.17.2.jar` in `nbproject/project.properties` points to a valid local Jsoup JAR.
3. Build or run:

```bash
ant clean jar
ant run
```

> Tip: In `nbproject/project.properties`, the Jsoup path is machine-specific by default, so you may need to update it after cloning.

---

## Gameplay Notes

- Guesses must be alphabetic only (`a-z`, `A-Z`) and match the selected word length.
- Enter key submits guesses in input fields.
- Defeat reveals the correct answer.
- Victory and defeat dialogs allow replay with same length, choose another length, or return to menu.

---

## Known Limitations / Improvement Ideas

- A large amount of repeated UI/gameplay logic exists per word length; this can be refactored into shared methods.
- `guessChecker` class naming does not follow Java class naming convention (`GuessChecker` would be conventional).
- Jsoup dependency path is currently environment-specific in `nbproject/project.properties`.
- No automated tests are currently present under `test/`.

---

## License

No explicit license file is currently included in this repository.

