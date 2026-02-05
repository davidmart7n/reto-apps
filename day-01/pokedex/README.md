# 🔴 Pokedex Java - Clean Architecture

Este proyecto es una aplicación de consola en Java que implementa una **Pokedex** funcional. El sistema permite "capturar" Pokémon consultando una API externa y persistiendo la información en un repositorio local.

## 🚀 Características

* **Consumo de API:** Integración con la [PokeApi](https://pokeapi.co/) mediante un cliente HTTP.
* **Persistencia en JSON:** Guardado de datos en un archivo local (`pokemons.json`) utilizando la librería Jackson.
* **Concurrencia Segura:** El repositorio utiliza el modificador `synchronized` para garantizar la integridad de los datos en entornos multi-hilo.
* **Arquitectura Limpia:** Separación total de responsabilidades siguiendo el patrón de capas:
  * **Domain:** Entidades puras de negocio.
  * **Client:** Infraestructura de comunicación externa.
  * **Repository:** Gestión de datos (abstracción mediante interfaces).
  * **Service:** Orquestación de la lógica de negocio.

---

## 🛠️ Tecnologías y Herramientas

* **Java 17+**
* **Maven:** Gestión de dependencias y ciclo de vida.
* **Jackson Databind:** Serialización y deserialización de JSON.
* **JUnit 5 & Mockito:** Testing unitario y simulación de dependencias (Mocks).

---

## 📂 Estructura del Proyecto



```text
src/main/java/io/github/davidmart7n/
├── client/      # PokeApiClient (Llamadas HTTP)
├── domain/      # Pokemon (Modelo de datos)
├── repository/  # Interfaces y FilePokemonRepository
├── service/     # PokemonService y su implementación
└── Main.java    # Clase principal
````
## ## ⚙️ Instalación y Uso

1. 1. ** **Clonar el repositorio:** **
   ```bash
   git clone https://github.com/davidmart7n/pokedex-java.git
   ``` 

2.  **Compilar el proyecto:**
   ``bash
   mvn clean install
   ``

3.  **Ejecutar la aplicación:** 
    ``bash
   mvn exec:java -Dexec.mainClass="io.github.davidmart7n.Main"
   ``

--- 

## 🧪 Testing

El proyecto aplica el patrón **AAA (Arrange, Act, Assert)** y utiliza la anotación `@ @TempDir` ` de JUnit 5 para realizar pruebas sobre el sistema de archivos de forma aislada y segura.

Para ejecutar la suite de pruebas completa:
 ``bash
mvn test
``

--- 

## 📝 Notas de Diseño

* *  **Inversión de Dependencias:**   `PokemonServiceImpl`  depende de la interfaz  `PokemonRepository` . Esto permite intercambiar el almacenamiento (por ejemplo, pasar de un archivo JSON a una base de datos SQL) sin tocar una sola línea de la lógica de negocio.
* *  **Manejo de JSON:** Se utiliza `JsonNode` para una extracción de datos dinámica desde la API, permitiendo un acceso seguro mediante el método `.path()`  sin riesgo de `NullPointerException` inmediatos.

--- 
> > Creado por [[davidmart7n]](https://github.com/davidmart7n)
