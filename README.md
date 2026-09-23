# 💰 Finanzas App - Semana4-Movies

Aplicación móvil nativa para Android diseñada para llevar el control de finanzas personales. Permite registrar ingresos y gastos, visualizar el balance total en tiempo real y consultar el historial de movimientos recientes de manera sencilla e intuitiva.

## 👥 Integrantes del Equipo

| Olivera Segura, Luis Alejandro |  Líder de Proyecto / Scrum Master |
| Espinal Morillas, Sergio Antonio Sebastian | Desarrollador UI/UX (Diseño de Interfaz) |
| Arquiñigo Rojas, Abad Junior |  Administrador de Base de Datos (Backend Local) |
| Landeo Castillo, Félix Rubén | Desarrollador de Lógica de Negocio |
| Vela Bravo, Glen Galahad |  Desarrollador de Integración y Pruebas (QA) |
| Zarate Mamani, Junior Del Piero | Documentador Técnico / DevOps |

## 📝 Descripción del Proyecto (¿Qué se hizo?)

Este proyecto fue desarrollado como parte de la **Semana 4** del curso Desarrollo de Aplicaciones Móviles. El objetivo principal fue crear una aplicación Android funcional que permita al usuario gestionar sus finanzas personales de manera local.

### Actividades realizadas:
1. **Análisis de Requerimientos:** Se definió la estructura de datos necesaria para representar un movimiento financiero (descripción, monto, método de pago y tipo: ingreso/gasto).
2. **Diseño de Interfaz (UI/UX):** Se implementó un diseño minimalista con modo oscuro, tarjetas para cada movimiento y un botón flotante para acciones rápidas.
3. **Lógica de Negocio:** Se programó el cálculo automático del balance total (suma de ingresos menos suma de gastos) y la actualización dinámica de la lista de movimientos.
4. **Implementación Técnica:** Se utilizó [Jetpack Compose / XML] para la interfaz, [Room / SQLite / Memoria] para la persistencia de datos y [MVVM] como arquitectura de software.
5. **Pruebas y Despliegue:** Se realizaron pruebas en emulador y dispositivo físico, verificando la correcta visualización de colores (verde/rojo) y el cálculo del saldo.

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
    git clone https://github.com/oliveraseguraluisalejandro-jpg/Semana4-Moviles.git
