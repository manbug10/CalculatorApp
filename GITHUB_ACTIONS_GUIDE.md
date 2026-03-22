## 📤 Cómo Subir a GitHub y Obtener tu APK

### Paso 1: Crear repositorio en GitHub

1. Ve a https://github.com/new
2. Nombre: `CalculatorApp`
3. Visibility: **Public** o **Private** (tu elección)
4. **NO** marques "Add README"
5. Click **Create repository**

### Paso 2: Subir el código

Abre PowerShell en la carpeta `CalculatorApp`:

```powershell
cd c:\Users\ManBugX\Documents\Flow\CalculatorApp

# Inicializar git
git init

# Agregar todos los archivos
git add .

# Crear commit
git commit -m "Initial commit - Calculator App"

# Cambiar a rama main
git branch -M main

# Conectar con GitHub (REEMPLAZA con tu usuario)
git remote add origin https://github.com/TU_USUARIO/CalculatorApp.git

# Subir código
git push -u origin main
```

### Paso 3: Esperar el build

1. Ve a tu repositorio en GitHub
2. Click en la pestaña **Actions**
3. Verás el workflow ejecutándose (toma ~5-10 minutos)
4. Cuando esté en verde ✅, el APK está listo

### Paso 4: Descargar APK

1. En la página del workflow, click en el run más reciente
2. Baja hasta **Artifacts**
3. Click en **app-debug** para descargar
4. Descomprime el ZIP y obtendrás `app-debug.apk`

### Paso 5: Instalar en tu Android

1. Copia el APK a tu teléfono
2. En el teléfono: Settings → Security → Enable "Install from unknown sources"
3. Abre el APK e instala

---

## 🔧 Comandos útiles

```powershell
# Ver estado de git
git status

# Ver cambios
git diff

# Subir cambios después de modificar
git add .
git commit -m "Descripción de cambios"
git push

# Forzar nuevo build en GitHub Actions
git commit --allow-empty -m "Trigger build"
git push
```

---

## ⚙️ Configuración adicional

### Build automático en cada push

El workflow está configurado para ejecutarse automáticamente cuando:
- Haces push a `main` o `master`
- Creas un Pull Request
- Manualmente desde la pestaña Actions (workflow_dispatch)

### Retener APKs por más tiempo

Por defecto los APKs se eliminan a los 30 días. Para cambiar:

Edita `.github/workflows/android-build.yml`:
```yaml
- uses: actions/upload-artifact@v4
  with:
    retention-days: 90  # Cambia a 90 días
```

---

## 📱 Especificaciones del APK

| Propiedad | Valor |
|-----------|-------|
| Min Android | 5.0 (API 21) |
| Target Android | 14 (API 34) |
| Tamaño aprox | ~15-25 MB |
| Arquitectura | Universal (todos los dispositivos) |

---

## 🐛 Problemas comunes

### El build falla
- Verifica que todos los archivos estén en el repositorio
- Revisa el log en GitHub Actions para ver el error

### El APK no se instala
- Activa "Orígenes desconocidos" en tu Android
- Verifica que tu Android sea versión 5.0 o superior

### El workflow no se ejecuta
- Verifica que estés en la rama `main`
- Revisa Settings → Actions → General → Allow all actions
