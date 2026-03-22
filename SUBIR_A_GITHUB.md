## 📤 SUBIR A GITHUB - manbug10

### Paso 1: Abre PowerShell en la carpeta CalculatorApp

```powershell
cd c:\Users\ManBugX\Documents\Flow\CalculatorApp
```

### Paso 2: Ejecuta estos comandos en orden

```powershell
# Inicializar git
git init

# Agregar todos los archivos
git add .

# Crear commit
git commit -m "Initial commit - Calculator App"

# Cambiar a rama main
git branch -M main

# Conectar con GitHub
git remote add origin https://github.com/manbug10/CalculatorApp.git

# Subir código
git push -u origin main
```

### Paso 3: Ve a GitHub Actions

Abre este link en tu navegador:
👉 **https://github.com/manbug10/CalculatorApp/actions**

### Paso 4: Espera el build

- Verás el workflow ejecutándose (círculo amarillo 🟡)
- Cuando termine estará verde ✅ (~5-10 minutos)

### Paso 5: Descarga el APK

1. Click en el workflow completado
2. Baja hasta **Artifacts**
3. Click en **app-debug**
4. Se descarga un ZIP
5. Descomprime y obtienes `app-debug.apk`

---

## 🔗 Links directos

| Recurso | URL |
|---------|-----|
| Repositorio | https://github.com/manbug10/CalculatorApp |
| Actions (builds) | https://github.com/manbug10/CalculatorApp/actions |

---

## ⚠️ Si tienes errores

### Error: "remote origin already exists"
```powershell
git remote remove origin
git remote add origin https://github.com/manbug10/CalculatorApp.git
git push -u origin main
```

### Error: "Authentication failed"
- Necesitas un **Personal Access Token** de GitHub
- Ve a: Settings → Developer settings → Personal access tokens
- O usa GitHub Desktop: https://desktop.github.com/

### Error: "src refspec main does not match any"
```powershell
git branch -M main
git push -u origin main
```

---

## 📱 Una vez descargado el APK

1. Copia `app-debug.apk` a tu teléfono
2. En el teléfono: Ajustes → Seguridad → Activar "Orígenes desconocidos"
3. Abre el APK e instala
4. ¡Disfruta tu calculadora! 🎉
