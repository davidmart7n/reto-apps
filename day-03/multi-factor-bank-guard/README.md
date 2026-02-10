# 🔐 MultiFactor Bank Guard (Backend)

Un sistema de seguridad bancario robusto construido con Spring Boot que implementa autenticación de doble factor (2FA) y arquitectura en capas. Este servicio actúa como la autoridad de seguridad y gestión de datos para la interfaz `digitalguard-bank-ui`.

## 🏗️ Arquitectura & Tecnologías

Este proyecto sigue una **Layered Architecture** estricta para separar la lógica de negocio de la seguridad y el acceso a datos.

* **Core:** Java 21, Spring Boot 3.x
* **Seguridad:** Spring Security 6, JWT (JSON Web Tokens), BCrypt Hashing.
* **Persistencia:** Spring Data JPA, Hibernate, H2 Database (para desarrollo).
* **API:** REST Controllers, Jakarta Validation.

## 🚀 Key Features

### 1. Autenticación en 2 Pasos (2FA)
El flujo de seguridad no es el tradicional. Requiere dos verificaciones:
1.  **Credenciales:** Usuario y Contraseña estándar.
2.  **Security PIN:** Un segundo código numérico que valida la transacción de login antes de emitir el token final.

### 2. Gestión de JWT
- Generación de tokens firmados tras la validación exitosa de los 2 pasos.
- Filtro de seguridad personalizado (`JwtAuthenticationFilter`) para interceptar peticiones y validar el token en cabeceras.

### 3. Registro de Usuarios Seguro
- Endpoint público para registro.
- Encriptación de contraseñas y PINs usando `BCryptPasswordEncoder` antes de guardar en base de datos.

## 📡 API Endpoints

| Método | Endpoint | Descripción | Acceso |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/auth/register` | Registro de nuevo usuario (User + Pass + PIN) | Público |
| `POST` | `/api/auth/login` | Paso 1: Valida credenciales. Retorna `PRE_AUTH_TOKEN` | Público |
| `POST` | `/api/auth/verify-pin` | Paso 2: Valida PIN. Retorna `JWT_ACCESS_TOKEN` | Pre-Auth |
| `GET` | `/api/account/balance` | Consulta de saldo (Demo protegida) | **Privado (JWT)** |

## 🛠️ Cómo ejecutar

1.  Clonar el repositorio.
2.  Ejecutar con Maven: `mvn spring-boot:run`.
3.  El servidor iniciará en el puerto `8080`.
