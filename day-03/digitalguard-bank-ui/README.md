# 🏦 DigitalGuard Bank UI (Frontend)

Interfaz de usuario moderna y reactiva desarrollada en Angular para interactuar con el sistema bancario seguro. Este proyecto demuestra el manejo avanzado de estado, seguridad en el cliente e interceptores HTTP.

## 🎨 Tecnologías & UI

* **Framework:** Angular 17+ (Standalone Components).
* **Estilos:** PrimeNG (Componentes UI), PrimeFlex.
* **Lógica:** RxJS (Observables), Reactive Forms.

## 🛡️ Patrones de Seguridad en Frontend

### 1. HTTP Interceptors
Implementación de un `AuthInterceptor` que inyecta automáticamente el **JWT** en el header `Authorization: Bearer ...` de todas las peticiones salientes hacia el backend, asegurando que el banco nunca rechace una petición legítima.

### 2. Angular Guards
Protección de rutas mediante `CanActivate`.
- Si el usuario no tiene un token válido en `localStorage`, el Guard redirige automáticamente al Login, impidiendo acceso a dashboard o transferencias.

### 3. Servicios y Observables
- **AuthService:** Gestiona el estado de la sesión (Login, Logout, 2FA) mediante `BehaviorSubject` para actualizar la UI en tiempo real.
- Comunicación asíncrona con el backend `multi-factor-bank-guard`.

## 📱 Flujo de la Aplicación

1.  **Login Screen:** Formulario reactivo con validación de tipos.
2.  **2FA Challenge:** Si la contraseña es correcta, un modal o segunda pantalla pide el PIN de seguridad.
3.  **Dashboard:** Área protegida visible solo tras completar los dos pasos. Muestra datos traídos desde el API protegido.

## 🚀 Cómo ejecutar

1.  Asegúrate de tener el backend corriendo en el puerto `8080`.
2.  Instalar dependencias: `npm install`.
3.  Iniciar servidor de desarrollo: `ng serve`.
4.  Abrir navegador en `http://localhost:4200`.
