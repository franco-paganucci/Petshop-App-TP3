# 🐾 Petshop App

Aplicación móvil desarrollada en **Kotlin** con **Jetpack Compose**, pensada para gestionar productos de una tienda de mascotas. Permite a los usuarios registrarse, explorar productos, gestionar su cuenta y realizar compras, todo dentro de una interfaz moderna y responsiva.

---

## 📱 Características principales

- 🐶 Navegación fluida entre pantallas con `Navigation Compose`
- 👤 Registro y gestión de cuenta de usuario con **Firebase Authentication**
- 🛍️ Carrito de compras con persistencia local (`Room`)
- 💳 Pantallas para selección y confirmación de métodos de pago, con **almacenamiento de métodos de pago en Firestore**
- 🛒 Procesamiento de compra y **guardado de historial de checkouts en Firestore**
- ⚙️ Pantallas de configuración y seguridad
- 🎨 UI completamente desarrollada con `Material3` y `Jetpack Compose`
- 🔐 Inyección de dependencias con `Hilt`

---

## 🛠️ Tecnologías y librerías

- **Lenguaje**: Kotlin
- **UI Toolkit**: Jetpack Compose
- **Backend como servicio (BaaS)**: Firebase (Authentication, Firestore)
- **Persistencia local**: Room
- **Inyección de dependencias**: Hilt
- **Consumo de API REST**: Retrofit

---

## 🚀 Cómo levantar el proyecto

1. **Clonar el repositorio**
   ```bash
   git clone [https://github.com/tu_usuario/petshop-app.git](https://github.com/tu_usuario/petshop-app.git)
   ```
2. **Abrir en Android Studio**
Asegurate de tener instalado:
- Android Studio Dolphin o superior
- JDK 11
- Android SDK con API 35 

3.  **Configuración de Firebase**
    Este proyecto ofrece dos opciones para la configuración de Firebase:

   * **Opción A: Usar la configuración de Firebase incluida (recomendado para evaluación y demostración)**
     El archivo `google-services.json` necesario para la conexión a un proyecto de Firebase preconfigurado **ya está incluido** en la carpeta `app/`. No necesitas realizar ninguna configuración adicional de Firebase para ejecutar la aplicación.

   * **Opción B: Conectar a tu propio proyecto de Firebase (para desarrollo o personalización)**
     Si deseas conectar la aplicación a tu propio proyecto de Firebase:
      1.  Crea un nuevo proyecto en la consola de Firebase.
      2.  Sigue las instrucciones para agregar una aplicación Android a tu proyecto Firebase. Asegúrate de registrar el `applicationId` (que es `com.example.petshopapptp3` por defecto, pero puedes cambiarlo en `build.gradle` si lo necesitas) y tu clave SHA-1.
      3.  Descarga el archivo `google-services.json` generado por tu proyecto de Firebase.
      4.  **Reemplaza el archivo `app/google-services.json` existente** con el que acabas de descargar.
      5.  Asegúrate de habilitar los siguientes servicios en tu proyecto de Firebase:
         * **Firebase Authentication** (al menos el método de Email/Contraseña).
         * **Cloud Firestore**.


4. **Sincronizar gradle**
Android Studio lo hará automáticamente al abrir el proyecto. Si no:
```
./gradlew build
```

5. **Ejecutar en un emulador o dispositivo físico**