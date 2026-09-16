# 💰 Finanzas App - Semana4-Movies

Aplicación móvil nativa para Android diseñada para llevar el control de finanzas personales. Permite registrar ingresos y gastos, visualizar el balance total en tiempo real y consultar el historial de movimientos recientes de manera sencilla e intuitiva.

## 📱 Características Principales

*   **Balance Total:** Cálculo en tiempo real del saldo disponible (Ingresos - Gastos).
*   **Registro de Movimientos:** Permite añadir transacciones con descripción (ej. "comida", "salario"), método de pago (Efectivo, Tarjeta) y monto.
*   **Historial Reciente:** Lista de los últimos movimientos con indicadores visuales:
    *   🟢 **Verde:** Ingresos (+)
    *   🔴 **Rojo:** Gastos (-)
*   **Interfaz Moderna:** Diseño basado en [Jetpack Compose / Vistas XML] con soporte nativo para Modo Oscuro (Dark Mode).
*   **Persistencia de Datos:** [Indica aquí si usas Room, SQLite, DataStore o si los datos son solo en memoria por ahora].

## 🛠️ Stack Tecnológico

*   **Lenguaje:** Kotlin
*   **UI:** [Jetpack Compose / XML Layouts]
*   **Arquitectura:** [MVVM / MVI / Clean Architecture]
*   **Inyección de Dependencias:** [Hilt / Koin / Manual]
*   **Asincronía:** Corrutinas y Flow
*   **Base de Datos:** [Room / SQLite / Firebase]
*   **Build System:** Gradle (Kotlin DSL) con **Configuration Cache** habilitado para optimizar los tiempos de compilación.

## ⚙️ Configuración del Proyecto

*   **Compilación:** El proyecto utiliza Gradle Configuration Cache (`org.gradle.configuration-cache=true` en `gradle.properties`).
*   **Java Toolchain:** Utiliza el plugin `foojay-resolver` para gestionar la versión de Java automáticamente.
*   **Módulos:** Proyecto de un solo módulo (`:app`).
*   **Version Catalog:** Las dependencias se gestionan a través del archivo `libs.versions.toml` (configurado en el `build.gradle.kts` raíz).

## 🚀 Cómo ejecutar el proyecto

1.  Clona este repositorio:
    ```bash
    git clone https://github.com/oliveraseguraluisalejandro-jpg/Semana4-Movies.git
