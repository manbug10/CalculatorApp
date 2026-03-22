# 🚀 Guía Rápida - Subir y Obtener APK

## En 3 pasos:

### 1️⃣ Subir a GitHub

```powershell
cd c:\Users\ManBugX\Documents\Flow\CalculatorApp

git init
git add .
git commit -m "Calculator App"
git branch -M main
git remote add origin https://github.com/TU_USUARIO/CalculatorApp.git
git push -u origin main
```

### 2️⃣ Ir a GitHub Actions

1. Abre tu repositorio en GitHub
2. Click en **Actions**
3. Espera ~5-10 minutos a que termine el build

### 3️⃣ Descargar APK

1. Click en el workflow completado ✅
2. Baja a **Artifacts**
3. Click en **app-debug**
4. Descomprime y obtendrás el APK

---

## 📱 Instalar APK

1. Envía el APK a tu teléfono
2. Activa "Orígenes desconocidos"
3. Instala la app

---

**¡Listo! Tu calculadora está instalada 🎉**

Para más detalles, ver `GITHUB_ACTIONS_GUIDE.md`
