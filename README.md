# Calculator App for Android

A modern, feature-rich calculator application for Android built with Kotlin and Jetpack Compose.

## 🚀 Quick Start

### Build APK via GitHub Actions

1. **Push to GitHub**
   ```bash
   git init
   git add .
   git commit -m "Initial commit - Calculator App"
   git branch -M main
   git remote add origin https://github.com/manbug10/CalculatorApp.git
   git push -u origin main
   ```

2. **Download APK**
   - Go to: https://github.com/manbug10/CalculatorApp/actions
   - Select the workflow run
   - Scroll to **Artifacts**
   - Click `app-debug` to download the APK

## Features

### Basic Calculator
- ✅ Basic arithmetic (+, -, ×, ÷)
- ✅ Percentage, negation
- ✅ Clear/Clear Entry

### Scientific Calculator
- ✅ Trigonometric (sin, cos, tan)
- ✅ Logarithmic (log, ln)
- ✅ Powers & Roots (x², x³, √, ∛, x^y)
- ✅ Constants (π, e)
- ✅ Factorial (n!)
- ✅ Memory functions (M+, M-, MR, MC, MS)

### UI Features
- ✅ Material Design 3
- ✅ Dark/Light theme
- ✅ Calculation history
- ✅ Responsive layout

## Technical Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: MVVM + Clean Architecture
- **DI**: Hilt
- **Min SDK**: 21 (Android 5.0)
- **Target SDK**: 34 (Android 14)

## Build Locally

### Prerequisites
- Android Studio Hedgehog or newer
- JDK 17

### Commands
```bash
# Debug APK
./gradlew assembleDebug

# Release APK (unsigned)
./gradlew assembleRelease

# Run tests
./gradlew test
```

## Project Structure

```
app/
├── src/main/
│   ├── java/com/flow/calculator/
│   │   ├── data/          # Models, Repositories
│   │   ├── domain/        # Business logic, Use cases
│   │   ├── ui/            # Compose UI, ViewModel
│   │   ├── di/            # Dependency Injection
│   │   └── MainActivity.kt
│   └── res/               # Resources, strings, themes
```

## License

Open source for educational purposes.
