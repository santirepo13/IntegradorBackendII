# 🚀 Integrador Backend 2

## Descripción del Proyecto

Este es un proyecto académico que implementa una API REST para la gestión de perfiles de estudiantes. La aplicación permite gestionar la información académica y profesional de los estudiantes, incluyendo sus habilidades, proyectos y certificados.

## Endpoints de la API

### Gestión de Perfiles de Estudiantes

#### Obtener Perfil Completo del Estudiante
- **Endpoint:** `GET /perfiles/estudiante/completo/{id}`
- **Descripción:** Recupera el perfil completo de un estudiante con todos sus elementos relacionados (habilidades, proyectos y certificados)
- **Respuesta:** Objeto JSON que contiene:
  - Resumen del perfil del estudiante
  - Lista de habilidades (nombre, nivel, tipo)
  - Lista de proyectos (título, descripción, URL, tecnologías)
  - Lista de certificados (nombre, institución, fecha, URL)

#### Otros Endpoints
- `GET /perfiles/{id}` - Obtener perfil de estudiante por ID
- `POST /perfiles` - Crear un nuevo perfil de estudiante
- `PUT /perfiles/{id}` - Actualizar un perfil de estudiante existente
- `DELETE /perfiles/{id}` - Eliminar un perfil de estudiante

### Gestión de Habilidades
- `GET /habilidades` - Obtener todas las habilidades
- `GET /habilidades/{id}` - Obtener habilidad por ID
- `POST /habilidades` - Crear una nueva habilidad
- `PUT /habilidades/{id}` - Actualizar una habilidad existente
- `DELETE /habilidades/{id}` - Eliminar una habilidad

### Gestión de Proyectos
- `GET /proyectos` - Obtener todos los proyectos
- `GET /proyectos/{id}` - Obtener proyecto por ID
- `POST /proyectos` - Crear un nuevo proyecto
- `PUT /proyectos/{id}` - Actualizar un proyecto existente
- `DELETE /proyectos/{id}` - Eliminar un proyecto

### Gestión de Certificados
- `GET /certificados` - Obtener todos los certificados
- `GET /certificados/{id}` - Obtener certificado por ID
- `POST /certificados` - Crear un nuevo certificado
- `PUT /certificados/{id}` - Actualizar un certificado existente
- `DELETE /certificados/{id}` - Eliminar un certificado

### Exportación de Perfil
- `GET /exportar/{idPerfil}` - Exportar datos del perfil del estudiante

## Modelos de Datos

### Perfil de Estudiante (PerfilEstudiante)
- Resumen (resumen)
- Intereses (intereses)
- Habilidades, proyectos y certificados relacionados

### Habilidades (Habilidad)
- Nombre (nombre)
- Nivel (nivel)
- Tipo (tipoHabilidad)

### Proyectos (Proyecto)
- Título (titulo)
- Descripción (descripcion)
- URL del proyecto (url_proyecto)
- Tecnologías (tecnologias)

### Certificados (Certificado)
- Nombre (nombre)
- Institución (institucion)
- Fecha (fecha)
- URL del archivo (url_archivo)
