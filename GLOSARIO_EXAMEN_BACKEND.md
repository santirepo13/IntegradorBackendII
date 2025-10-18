# 📚 GLOSARIO COMPLETO - BACKEND 2 (Spring Boot)
## 🎓 Preparación para Examen de Teoría

---

## 🔧 **1. CONFIGURACIÓN DEL PROYECTO**

### **POM.XML**
- **Definición**: Archivo de configuración de Maven (Project Object Model)
- **Función**: Define dependencias, plugins y configuración del proyecto
- **Elementos clave en tu proyecto**:
  ```xml
  <parent>spring-boot-starter-parent</parent> <!-- Hereda configuración de Spring Boot -->
  <java.version>17</java.version> <!-- Versión de Java -->
  ```

### **DEPENDENCIAS PRINCIPALES**
- **spring-boot-starter-web**: Para crear APIs REST
- **spring-boot-starter-data-jpa**: Para persistencia de datos con JPA
- **mysql-connector-j**: Driver para conectar con MySQL
- **h2**: Base de datos en memoria para pruebas
- **mapstruct**: Para mapeo entre entidades y DTOs
- **spring-boot-devtools**: Herramientas de desarrollo (hot reload)

---

## 🏗️ **2. ARQUITECTURA DEL PROYECTO**

### **PATRÓN MVC (Modelo-Vista-Controlador)**
Tu proyecto implementa una **arquitectura en capas**:

```
┌─────────────────┐
│   CONTROLADOR   │ ← Maneja peticiones HTTP
├─────────────────┤
│    SERVICIO     │ ← Lógica de negocio
├─────────────────┤
│   REPOSITORIO   │ ← Acceso a datos
├─────────────────┤
│     MODELO      │ ← Entidades de base de datos
└─────────────────┘
```

### **ESTRUCTURA DE PAQUETES**
```
com.example.FrankySabado/
├── ayudas/          → Enums y clases auxiliares
├── controladores/   → Controllers (REST endpoints)
├── modelos/         → Entidades JPA
│   ├── dtos/        → Data Transfer Objects
│   └── mapas/       → Mappers (MapStruct)
├── repositorios/    → Interfaces de acceso a datos
└── servicios/       → Lógica de negocio
```

---

## 🎯 **3. ANOTACIONES PRINCIPALES**

### **ANOTACIONES DE SPRING BOOT**
- **@SpringBootApplication**: 
  - **Ubicación**: Clase principal
  - **Función**: Combina @Configuration, @EnableAutoConfiguration y @ComponentScan
  
- **@RestController**: 
  - **Ubicación**: Clases controladoras
  - **Función**: Combina @Controller + @ResponseBody
  - **Ejemplo**: UsuarioControlador.java

- **@Service**: 
  - **Ubicación**: Clases de servicio
  - **Función**: Marca una clase como servicio de negocio
  - **Ejemplo**: UsuarioServicios.java

- **@Repository**: 
  - **Ubicación**: Interfaces de repositorio
  - **Función**: Marca interfaz como repositorio de datos
  - **Ejemplo**: IUsuarioRepositorio.java

- **@Autowired**: 
  - **Función**: Inyección de dependencias automática
  - **Ejemplo**: private UsuarioServicios servicio;

### **ANOTACIONES DE MAPEO HTTP**
- **@RequestMapping("/usuarios")**: Mapeo base del controlador
- **@PostMapping**: Maneja peticiones POST (crear)
- **@GetMapping**: Maneja peticiones GET (leer)
- **@GetMapping("/{id}")**: GET con parámetro de ruta
- **@PathVariable**: Extrae variables de la URL
- **@RequestBody**: Convierte JSON a objeto Java

### **ANOTACIONES JPA**
- **@Entity**: Marca clase como entidad de base de datos
- **@Table(name = "usuarios")**: Especifica nombre de tabla
- **@Id**: Marca campo como clave primaria
- **@GeneratedValue(strategy = GenerationType.IDENTITY)**: Auto-incremento
- **@Column**: Configura columna de base de datos
- **@Enumerated(EnumType.STRING)**: Mapea enum como string
- **@OneToOne**: Relación uno a uno
- **@JsonBackReference**: Evita referencia circular en JSON

---

## 📊 **4. MODELO DE DATOS**

### **ENTIDAD USUARIO**
```java
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "correo", unique = true, nullable = false)
    private String correo;
    
    @Enumerated(EnumType.STRING)
    private Roles rol;
}
```

**Características**:
- **PK**: id (auto-increment)
- **Unique**: correo (no duplicados)
- **Enum**: rol y estado (valores predefinidos)
- **Relaciones**: OneToOne con Estudiante y Empresario

### **ENUMS (Paquete ayudas)**
- **Roles**: Define tipos de usuario (ESTUDIANTE, EMPRESARIO, ADMIN)
- **Estados**: Define estados (ACTIVO, INACTIVO)
- **Departamentos**: Departamentos de Colombia
- **Especialidad**: Especialidades académicas
- **TipoHabilidad**: Tipos de habilidades

---

## 🔄 **5. PATRÓN DTO (Data Transfer Object)**

### **¿QUE ES UN DTO?**
- **Definición**: Objeto que transporta datos entre capas
- **Propósito**: 
  - Ocultar campos sensibles (ej: contraseña)
  - Reducir datos transferidos
  - Desacoplar modelo interno del API

### **EJEMPLO EN TU PROYECTO**
```java
public class UsuarioGenericoDTO {
    private String nombre;
    private String correo;
    private Roles rol;
    // NO incluye contraseña ni id
}
```

### **MAPSTRUCT**
- **Función**: Convierte automáticamente entre Entity y DTO
- **Interface**: IMapaUsuario
- **Métodos**:
  - convertir_a_dto(Usuario): Entity → DTO
  - convertir_lista_a_dto(List<Usuario>): List<Entity> → List<DTO>

---

## 🌐 **6. CAPA DE CONTROLADORES**

### **RESPONSABILIDADES**
1. **Recibir peticiones HTTP**
2. **Validar datos de entrada**
3. **Llamar servicios de negocio**
4. **Retornar respuestas HTTP**

### **ESTRUCTURA DE RESPUESTA**
```java
return ResponseEntity
    .status(HttpStatus.OK)           // Código HTTP
    .body(this.servicio.metodo());   // Cuerpo de respuesta
```

### **MANEJO DE ERRORES**
```java
try {
    // Lógica exitosa
    return ResponseEntity.status(HttpStatus.OK).body(resultado);
} catch (Exception error) {
    // Manejo de errores
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
}
```

### **ENDPOINTS EN TU PROYECTO**
- **POST /usuarios**: Crear usuario
- **GET /usuarios**: Listar todos los usuarios
- **GET /usuarios/{id}**: Buscar usuario por ID

---

## ⚙️ **7. CAPA DE SERVICIOS**

### **RESPONSABILIDADES**
1. **Lógica de negocio**
2. **Validaciones complejas**
3. **Coordinar repositorios**
4. **Manejo de excepciones**

### **MÉTODOS EN UsuarioServicios**
- **guardarUsuarioGenerico()**: Crear nuevo usuario
- **buscarUsuarioPorId()**: Buscar por ID
- **buscarUsuarioPorCorreo()**: Buscar por email
- **buscarTodosLosUsuarios()**: Listar todos
- **buscarUsuariosPorNombre()**: Buscar por nombre

### **MANEJO DE EXCEPCIONES**
```java
try {
    // Lógica del servicio
} catch (Exception ex) {
    throw new Exception(MensajeError.ERROR_GENERAL_API.getDescripcion() + ex.getMessage());
}
```

---

## 🗄️ **8. CAPA DE REPOSITORIOS**

### **SPRING DATA JPA**
- **Interface base**: JpaRepository<Usuario, Integer>
- **Métodos automáticos**:
  - save(): Guardar/actualizar
  - findById(): Buscar por ID
  - findAll(): Buscar todos
  - deleteById(): Eliminar

### **QUERIES PERSONALIZADAS**
```java
// Spring Data genera automáticamente la implementación
List<Usuario> findByNombre(String nombre);
Optional<Usuario> findByCorreo(String correo);
```

### **CONVENCIONES DE NOMBRES**
- **findBy**: Buscar por campo
- **findByNombreAndCorreo**: Buscar por múltiples campos
- **findByNombreContaining**: Buscar con LIKE
- **Optional<>**: Puede o no contener resultado

---

## 📝 **9. CONFIGURACIÓN (application.properties)**

### **CONFIGURACIÓN DE BASE DE DATOS**
```properties
spring.application.name=FrankySabado
spring.datasource.url=jdbc:mysql://localhost/bd_sabado
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create
```

### **PROPIEDADES CLAVE**
- **spring.datasource.url**: URL de conexión a BD
- **spring.jpa.hibernate.ddl-auto=create**: 
  - create: Recrea tablas en cada inicio
  - update: Actualiza esquema sin perder datos
  - validate: Solo valida esquema
  - none: No hace nada

---

## 🚨 **10. MANEJO DE ERRORES**

### **CLASE MensajeError**
```java
public enum MensajeError {
    ERROR_GENERAL_API("Error general en la API: ");
    
    private String descripcion;
    
    public String getDescripcion() {
        return descripcion;
    }
}
```

### **CÓDIGOS HTTP**
- **200 OK**: Operación exitosa
- **400 BAD REQUEST**: Error en petición
- **404 NOT FOUND**: Recurso no encontrado
- **500 INTERNAL SERVER ERROR**: Error interno

---

## 🔄 **11. CICLO DE VIDA DE UNA PETICIÓN**

### **FLUJO COMPLETO**
```
1. Cliente → POST /usuarios
2. UsuarioControlador.activarPeticionGuardar()
3. UsuarioServicios.guardarUsuarioGenerico()
4. IUsuarioRepositorio.save()
5. Base de datos MySQL
6. IMapaUsuario.convertir_a_dto()
7. ResponseEntity con UsuarioGenericoDTO
8. Cliente recibe JSON
```

---

## 🏃‍♂️ **12. SPRING BOOT - CONCEPTOS CLAVE**

### **INVERSIÓN DE CONTROL (IoC)**
- Spring maneja la creación de objetos
- @Autowired inyecta dependencias automáticamente

### **AUTO-CONFIGURACIÓN**
- Spring Boot configura automáticamente según dependencias
- Ejemplo: Detecta MySQL y configura DataSource

### **STARTER DEPENDENCIES**
- Paquetes pre-configurados
- spring-boot-starter-web incluye Tomcat, Jackson, Spring MVC

---

## 💡 **13. BUENAS PRÁCTICAS EN TU PROYECTO**

### ✅ **QUE HACE BIEN**
- **Separación de capas**: Controlador → Servicio → Repositorio
- **DTOs**: No expone entidades completas
- **Manejo de excepciones**: Try-catch en todas las capas
- **Inyección de dependencias**: Usa @Autowired

### 🔍 **PATRONES IDENTIFICADOS**
- **Repository Pattern**: Abstrae acceso a datos
- **Service Layer**: Centraliza lógica de negocio
- **DTO Pattern**: Transferencia de datos segura
- **Dependency Injection**: Bajo acoplamiento

---

## 📚 **14. TÉRMINOS TÉCNICOS IMPORTANTES**

### **SPRING FRAMEWORK**
- **Bean**: Objeto gestionado por Spring
- **Container**: Gestor de beans de Spring
- **Context**: Entorno de ejecución de Spring

### **JPA/HIBERNATE**
- **Entity**: Clase que mapea tabla de BD
- **Persistence Context**: Contexto de persistencia
- **Transaction**: Unidad de trabajo en BD

### **REST API**
- **Endpoint**: URL que acepta peticiones
- **Resource**: Entidad expuesta por API
- **Stateless**: Sin estado entre peticiones

---

## 🎯 **15. PREGUNTAS TÍPICAS DE EXAMEN**

### **ARQUITECTURA**
- ¿Qué patrón arquitectónico usa tu proyecto? **MVC en capas**
- ¿Cuál es la responsabilidad de cada capa? **Ver sección 2**

### **ANOTACIONES**
- ¿Qué hace @SpringBootApplication? **Auto-configuración + scan**
- ¿Diferencia entre @Controller y @RestController? **RestController = Controller + ResponseBody**

### **JPA**
- ¿Qué hace @Entity? **Marca clase como tabla de BD**
- ¿Para qué sirve @GeneratedValue? **Auto-incremento de PK**

### **SPRING DATA**
- ¿Cómo funciona findByNombre()? **Query automática por convención**
- ¿Por qué usar Optional<>? **Manejo seguro de valores nulos**

---

## 🚀 **RESUMEN EJECUTIVO PARA EL EXAMEN**

Tu proyecto es una **API REST** construida con **Spring Boot** que implementa:

1. **Arquitectura en capas** (MVC)
2. **Persistencia con JPA/Hibernate**
3. **Base de datos MySQL**
4. **DTOs para transferencia segura**
5. **MapStruct para mapeo automático**
6. **Manejo centralizado de excepciones**
7. **Inyección de dependencias**
8. **Endpoints RESTful**

**Tecnologías clave**: Java 17, Spring Boot 3.5.4, MySQL, Maven, JPA, MapStruct

---

## 🎓 **¡ÉXITO EN TU EXAMEN!**

**Tip final**: Enfócate en explicar el **flujo completo** de una petición, desde el controlador hasta la base de datos y de vuelta al cliente. ¡Eso demuestra comprensión integral del proyecto!
