# Proyecto Práctico Integrador - API REST de Tienda

Este proyecto es una API REST de backend desarrollada en Java con Spring Boot, diseñada para gestionar la información de **Clientes** y **Ventas**. Implementa principios de **Programación Orientada a Objetos**, **Patrones de Diseño**, y utiliza **Spring Data JDBC** como ORM para la interacción con una base de datos **MySQL 8**.

## Características

- API REST con endpoints CRUD (Crear, Leer, Actualizar, Eliminar) para **Clientes** y **Ventas**.
- Arquitectura limpia y organizada en capas (Controller, Service, Repository, Model).
- Uso de **DTOs** para encapsular y transferir datos entre capas.
- Implementación de **Spring Data JDBC** para el acceso a la base de datos.
- **Spring Security** para la seguridad de la API (configurable).
- Documentación de la API con **OpenAPI**.
- Pruebas unitarias utilizando **JUnit** y **Mockito**.

## Requisitos

- Java 11 o superior.
- **Maven** para la gestión de dependencias.
- **MySQL 8** para la base de datos.
- Un cliente REST (por ejemplo, **Postman** o **cURL**) para probar la API.

## Estructura del Proyecto

La estructura del proyecto sigue una arquitectura por capas y se divide de la siguiente manera:

```plaintext
src
├── main
│   ├── java
│   │   └── com.tienda
│   │       ├── config          # Configuración de seguridad y base de datos
│   │       ├── controller      # Controladores REST de la API
│   │       ├── dto             # Clases DTO para encapsulamiento de datos
│   │       ├── exceptions      # Manejo de excepciones personalizadas
│   │       ├── model           # Entidades de negocio
│   │       ├── repository      # Interfaces de acceso a datos
│   │       ├── service         # Lógica de negocio
│   ├── resources
│   │   ├── application.properties  # Configuración de base de datos y seguridad
├── test
│   ├── java
│   │   └── com.tienda
│   │       ├── controller      # Pruebas de los controladores
│   │       ├── repository      # Pruebas de los repositorios
│   │       ├── service         # Pruebas de los servicios
└── README.md                   # Descripción del proyecto y configuración

Configuración del Proyecto
Clonar el repositorio:

bash
Copy code
git clone https://github.com/usuario/proyecto-tienda
cd proyecto-tienda
Configurar la base de datos:

Crear una base de datos MySQL llamada tienda_db y configurar el archivo application.properties en src/main/resources/:

properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/tienda_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
Compilar y ejecutar el proyecto con Maven:

bash
Copy code
mvn clean install
mvn spring-boot:run
Endpoints de la API
Los principales endpoints de la API para las entidades Cliente y Venta son:

Método	Endpoint	Descripción
POST	/api/clientes	Crear un nuevo cliente
GET	/api/clientes	Obtener todos los clientes
GET	/api/clientes/{id}	Obtener un cliente por ID
PUT	/api/clientes/{id}	Actualizar un cliente existente
DELETE	/api/clientes/{id}	Eliminar un cliente
POST	/api/ventas	Crear una nueva venta
GET	/api/ventas	Obtener todas las ventas
GET	/api/ventas/{id}	Obtener una venta por ID
PUT	/api/ventas/{id}	Actualizar una venta existente
DELETE	/api/ventas/{id}	Eliminar una venta
La documentación de los endpoints está disponible mediante OpenAPI. Para acceder, dirigirse a http://localhost:8080/swagger-ui.html.

Pruebas
Este proyecto incluye pruebas unitarias para las capas de Servicio, Controlador, y Repositorio.

Para ejecutar las pruebas:

bash
Copy code
mvn test
Las pruebas están organizadas de la siguiente manera:

service: Pruebas de la lógica de negocio con JUnit y Mockito.
controller: Pruebas de los endpoints REST.
repository: Pruebas de la interacción con la base de datos.
Seguridad
Para habilitar o configurar la seguridad con Spring Security, se puede modificar el archivo de configuración SecurityConfig.java en el paquete config. La autenticación y autorización son opcionales y pueden activarse según los requisitos de la aplicación.

Ejemplo de Uso
Para probar la funcionalidad de la API, se pueden enviar solicitudes HTTP utilizando un cliente como Postman o cURL.
