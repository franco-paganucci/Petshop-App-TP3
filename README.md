# 🐾 Petshop App

Aplicación móvil desarrollada en **Kotlin** con **Jetpack Compose**, pensada para gestionar productos de una tienda de mascotas. Permite a los usuarios registrarse, explorar productos, gestionar su cuenta y realizar compras, todo dentro de una interfaz moderna y responsiva.

---

## 📱 Características principales

- 🐶 Navegación fluida entre pantallas con `Navigation Compose`
- 👤 Registro y gestión de cuenta de usuario `Firebase`
- 🛍️ Carrito de compras con persistencia local (`Room`)
- 💳 Pantallas para selección y confirmación de métodos de pago
- ⚙️ Pantallas de configuración y seguridad
- 🎨 UI completamente desarrollada con `Material3` y `Jetpack Compose`
- 🔐 Inyección de dependencias con `Hilt`

---

## 🛠️ Tecnologías y librerías

- **Lenguaje**: Kotlin
- **UI Toolkit**: Jetpack Compose
- **Persistencia local**: Room
- **Inyección de dependencias**: Hilt
- **Consumo de API REST**: Retrofit

---

## 🚀 Cómo levantar el proyecto

1. **Clonar el repositorio**
   ```basç
   git clone https://github.com/tu_usuario/petshop-app.git
   ```
2. **Abrir en Android Studio**
Asegurate de tener instalado:
- Android Studio Dolphin o superior
- JDK 11
- Android SDK con API 35 

3. **Sincronizar gradle**
Android Studio lo hará automáticamente al abrir el proyecto. Si no:
```
./gradlew build
```

4. **Ejecutar en un emulador o dispositivo físico**