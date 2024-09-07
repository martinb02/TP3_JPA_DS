# TP3 JPA - Trabajo practico de JPA

Este proyecto es una implementación de un sistema de facturación en Java utilizando **JPA** (Java Persistence API) para la persistencia en base de datos, y **Lombok** para reducir el código repetitivo de getters, setters, constructores, etc.

## Tecnologías Usadas
- **Java**: Lenguaje de programación.
- **JPA**: Para la persistencia en la base de datos.
- **Lombok**: Para simplificar el código de las entidades.
- **H2 Database**: Base de datos embebida (si decides usar H2).
- **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.

## Requisitos Previos
- JDK 8 o superior
- Maven
- Editor de código como IntelliJ IDEA o Eclipse

## Estructura del Proyecto

### 1. `Articulo`
Esta clase representa un artículo que puede ser facturado. Cada artículo puede estar asociado a varias categorías a través de una relación **Many-to-Many**.

- **Atributos**:
  - `id`: Identificador único del artículo.
  - `cantidad`: Cantidad disponible del artículo.
  - `denominacion`: Nombre del artículo.
  - `precio`: Precio del artículo.
  - `categorias`: Lista de categorías asociadas al artículo.

### 2. `Categoria`
Clase que representa una categoría de artículos. Un artículo puede pertenecer a varias categorías.

- **Atributos**:
  - `id`: Identificador único de la categoría.
  - `denominacion`: Nombre de la categoría.

### 3. `Cliente`
Clase que representa a un cliente en el sistema. Cada cliente tiene un domicilio asociado.

- **Atributos**:
  - `id`: Identificador único del cliente.
  - `nombre`: Nombre del cliente.
  - `apellido`: Apellido del cliente.
  - `dni`: Número de documento del cliente.
  - `domicilio`: Domicilio asociado al cliente (**One-to-One** con la clase `Domicilio`).

### 4. `Domicilio`
Clase que representa el domicilio del cliente.

- **Atributos**:
  - `id`: Identificador único del domicilio.
  - `nombreCalle`: Nombre de la calle.
  - `numero`: Número de la dirección.

### 5. `DetalleFactura`
Clase que representa los detalles de una factura, es decir, las líneas de artículos que componen la factura.

- **Atributos**:
  - `id`: Identificador único del detalle de factura.
  - `cantidad`: Cantidad del artículo comprado.
  - `subtotal`: Subtotal calculado para el artículo.
  - `articulo`: Artículo correspondiente al detalle de la factura (**Many-to-One** con la clase `Articulo`).

### 6. `Factura`
Clase que representa una factura generada para un cliente. Una factura tiene una lista de detalles de factura, un cliente y un total.

- **Atributos**:
  - `id`: Identificador único de la factura.
  - `fecha`: Fecha de emisión de la factura.
  - `numero`: Número de la factura.
  - `total`: Total de la factura.
  - `cliente`: Cliente asociado a la factura (**Many-to-One** con la clase `Cliente`).
  - `detalles`: Lista de detalles de factura (**One-to-Many** con la clase `DetalleFactura`).

### 7. `Main`
Clase principal que inicia la aplicación y persiste una factura de ejemplo en la base de datos. En este archivo se instancian varios objetos como `Cliente`, `Articulo`, `Categoria`, `DetalleFactura` y `Factura` para mostrar cómo se relacionan entre sí y cómo se persisten.

- **Funcionalidad**:
  - Se crea un cliente con domicilio.
  - Se crean tres artículos y tres detalles de factura.
  - Se crea una factura con esos detalles y se persiste en la base de datos.

## Configuración de la Base de Datos

El archivo `persistence.xml` en `src/main/resources/META-INF/` debe configurarse con las propiedades de conexión a la base de datos. Si estás utilizando una base de datos embebida como **H2**, la configuración sería algo como esto:

```xml
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence
             http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
             version="2.0">
    <persistence-unit name="example-unit">
        <properties>
            <property name="javax.persistence.jdbc.url" value="jdbc:h2:mem:testdb"/>
            <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/>
            <property name="javax.persistence.jdbc.user" value="sa"/>
            <property name="javax.persistence.jdbc.password" value=""/>
            <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.show_sql" value="true"/>
        </properties>
    </persistence-unit>
</persistence>
```

¿Cómo Ejecutar?
1. Clona este repositorio en tu máquina local.
2. Asegúrate de que tienes Maven instalado y configurado.
3. Navega al directorio del proyecto y ejecuta el siguiente comando para compilar y ejecutar el proyecto:
  mvn clean install
4. Puedes ejecutar el archivo Main directamente desde tu IDE o con Maven.
