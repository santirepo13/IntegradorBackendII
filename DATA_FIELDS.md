# Datos necesarios para poblar la base de datos

Este documento recoge, por cada tabla/endpoint del proyecto, los campos obligatorios, el formato esperado, restricciones (UNIQUE, enums), y ejemplos JSON listos para usar con los endpoints REST. También incluye plantillas para generación masiva (curl / xargs) y recomendaciones prácticas.

> Orden de creación (por restricciones FK):
> 1. Usuario
> 2. Estudiante (fk_usuario)
> 3. PerfilEstudiante (fk_estudiante)
> 4. Habilidad, Proyecto, Certificado (fk_perfil)
> 5. Nota, Asistencia (fk_estudiante)

---

## 1) Usuario (POST /usuarios)

- Campos obligatorios:
  - nombre (String, max 50 caracteres)
  - correo (String, max 50 caracteres) — UNIQUE
  - contraseña (String, max 10 caracteres)
  - rol (enum `Roles`) — valores: `Docente`, `Estudiante`, `Empresario`, `Administrador`
  - estado (enum `Estados`) — valores: `Activo`, `Inactivo`, `Especial`

- Ejemplo JSON:
```json src/main/resources/samples/usuario.json
{
  "nombre": "Juan Pérez",
  "correo": "juan.perez1@example.com",
  "contraseña": "secret123",
  "rol": "Estudiante",
  "estado": "Activo"
}
```

- Notas:
  - `correo` es único: genera sufijos o índices para inserciones masivas (p. ej. `usuario+{i}@example.com`).
  - Tabla en BD: `usuarios`

---

## 2) Estudiante (POST /estudiantes)

- Campos esperados por la API (JSON):
  - promedio (Double)
  - fechaNacimiento (LocalDate ISO `yyyy-MM-dd`)
  - programa (enum `Programa`, opcional)
  - semestre (Integer, opcional)
  - usuario: objeto con `id` del usuario creado

- Ejemplo JSON (POST /estudiantes):
```json src/main/resources/samples/estudiante.json
{
  "promedio": 4.3,
  "fechaNacimiento": "2000-05-10",
  "programa": "Ingenieria_de_Sistemas",
  "semestre": 6,
  "usuario": { "id": 1 }
}
```

- Columnas SQL (tabla `estudiantes`):
  - promedio (DOUBLE)
  - fechaNacimiento (DATE)
  - programa (VARCHAR, nullable) — se almacena como string del enum
  - semestre (INTEGER, nullable)
  - fk_usuario (INTEGER) — FK a tabla `usuarios` (id)

- Ejemplo INSERT SQL:
```sql src/main/resources/samples/estudiante_insert.sql
INSERT INTO estudiantes (promedio, fechaNacimiento, programa, semestre, fk_usuario)
VALUES (4.3, '2000-05-10', 'Ingenieria_de_Sistemas', 6, 1);
```

- Notas importantes:
  - Si usas la API, envía `usuario: { "id": <idUsuario> }` y el servicio mapeará al campo `usuario` de la entidad Estudiante.
  - Si insertas por SQL directo, usa las columnas que tu esquema espera (ej. `fechaNacimiento`, `fk_usuario`).

---

## 3) PerfilEstudiante (POST /perfiles)

- Campos (según la entidad):
  - resumen (String)
  - intereses (enum `Intereses`) — `Frontend` o `Backend`
  - experiencia (Integer) — ejemplo: `0`
  - proyectos (String) — texto libre
  - estudiante: objeto con `id` del estudiante

- Ejemplo JSON:
```json src/main/resources/samples/perfil.json
{
  "resumen": "Desarrollador Junior",
  "intereses": "Backend",
  "experiencia": 0,
  "proyectos": "Proyecto A; Proyecto B",
  "estudiante": { "id": 1 }
}
```

- Notas:
  - Verifica que el estudiante no tenga ya un perfil (HU13). El servicio del proyecto lanza conflicto si existe.
  - La tabla en BD se llama `Perfil Estudiante` (ten cuidado con SQL directo).

---

## 4) Habilidad (POST /habilidades)

- Campos obligatorios / validados:
  - nombre (String)
  - nivel (Integer) — rango `1` a `10`
  - tipoHabilidad (enum `TipoHabilidad`) — valores: `Blanda`, `Tecnica`
  - idPerfilEstudiante (Long) — id del perfil

- Ejemplo JSON:
```json src/main/resources/samples/habilidad.json
{
  "nombre": "Java",
  "nivel": 8,
  "tipoHabilidad": "Tecnica",
  "idPerfilEstudiante": 1
}
```

- Notas:
  - Servicio valida nombre no vacío y nivel en rango 1-10.
  - Tabla en BD: `Habilidades`

---

## 5) Proyecto (POST /proyectos)

- Campos obligatorios:
  - titulo (String)
  - descripcion (String)
  - url_proyecto (String, max 2083 caracteres) — formato URL (se valida en servicio), UNIQUE
  - tecnologias (enum `Tecnologias`) — valores: `Inteligencia_Aritificial`, `IOT`, `Blockchain`, `Realidad_Aumentada`
  - idPerfilEstudiante (Long)

- Ejemplo JSON:
```json src/main/resources/samples/proyecto.json
{
  "titulo": "WebApp",
  "descripcion": "App ejemplo",
  "url_proyecto": "https://example.com/proyecto_1",
  "tecnologias": "IOT",
  "idPerfilEstudiante": 1
}
```

- Notas:
  - `url_proyecto` es UNIQUE en la entidad: para inserciones masivas genera URLs con índice (p. ej. `.../proyecto_{i}`).
  - El servicio valida formato URL.
  - Tabla en BD: `Proyectos`

---

## 6) Certificado (POST /certificados)

- Campos obligatorios:
  - nombre (String, max 70 caracteres)
  - institucion (enum `Institucion`) — `VEREDAL`, `MUNICIPAL`, `DEPARTAMENTAL`, `NACIONAL`
  - fecha (LocalDateTime ISO `yyyy-MM-ddTHH:mm:ss`)
  - url_archivo (String, max 2083 caracteres) — URL, UNIQUE
  - idPerfilEstudiante (Long)

- Ejemplo JSON:
```json src/main/resources/samples/certificado.json
{
  "nombre": "Curso X",
  "institucion": "NACIONAL",
  "fecha": "2023-06-01T00:00:00",
  "url_archivo": "https://example.com/cert_1.pdf",
  "idPerfilEstudiante": 1
}
```

- Notas:
  - `url_archivo` debe ser único. Genera un sufijo por índice para lotes.
  - Tabla en BD: `Certificados`

---

## 7) Nota (entidad existe, revisa endpoint)

- Campos (según entidad `Nota`):
  - valor (Double)
  - tipo (enum `TipoEvaluacion`) — valores: `Parcial`, `Final`, `Seguimiento`
  - fecha (LocalDate `yyyy-MM-dd`, opcional)
  - fk_estudiante: referencia a Estudiante (objeto o id según endpoint)

- Ejemplo SQL (si no hay endpoint):
```sql src/main/resources/samples/nota.sql
INSERT INTO notas (valor, tipo, fecha, fk_estudiante) VALUES (4.5, 'Parcial', '2023-05-10', 1);
```

- Notas:
  - Tabla en BD: `notas`

---

## 8) Asistencia (POST endpoint según entidad Asistencia)

- Campos:
  - fecha (LocalDate `yyyy-MM-dd`)
  - observacion (String, max 100 caracteres, opcional)
  - estado (enum `EstadosAsistencia`) — `Asistio`, `No_asistio`
  - fk_estudiante (objeto con id)

- Ejemplo JSON:
```json src/main/resources/samples/asistencia.json
{
  "fecha": "2023-09-01",
  "observacion": "Llegó tarde",
  "estado": "Asistio",
  "estudiante": { "id": 1 }
}
```

- Notas:
  - Tabla en BD: `asistencias`

---

## Enums y valores permitidos (exactos, case-sensitive)
- Roles: `Docente`, `Estudiante`, `Empresario`, `Administrador`
- Estados: `Activo`, `Inactivo`, `Especial`
- Intereses: `Frontend`, `Backend`
- Tecnologias: `Inteligencia_Aritificial`, `IOT`, `Blockchain`, `Realidad_Aumentada`
- TipoHabilidad: `Blanda`, `Tecnica`
- Institucion: `VEREDAL`, `MUNICIPAL`, `DEPARTAMENTAL`, `NACIONAL`
- EstadosAsistencia: `Asistio`, `No_asistio`
- TipoEvaluacion: `Parcial`, `Final`, `Seguimiento`
- Programa: `Ingenieria_de_Sistemas`, `Ingenieria_Electronica`, `Ingenieria_Industrial`, `Ingenieria_Civil`, `Ingenieria_Mecanica`, `Ingenieria_Quimica`, `Ingenieria_Ambiental`, `Ingenieria_Biomedica`, `Ingenieria_de_Software`, `Ingenieria_de_Telecomunicaciones`, `Administracion_de_Empresas`, `Contaduria_Publica`, `Economia`, `Derecho`, `Medicina`, `Enfermeria`, `Psicologia`, `Trabajo_Social`, `Comunicacion_Social`, `Arquitectura`, `Diseño_Grafico`, `Diseño_Industrial`, `Matematicas`, `Fisica`, `Quimica`, `Biologia`, `Geologia`, `Agronomia`, `Veterinaria`, `Nutricion_y_Dietetica`, `Educacion`, `Literatura`, `Filosofia`, `Historia`, `Sociologia`, `Antropologia`, `Ciencia_Politica`, `Relaciones_Internacionales`, `Turismo`, `Hoteleria`, `Gastronomia`, `Marketing`, `Publicidad`, `Artes_Plasticas`, `Musica`, `Teatro`, `Danza`, `Educacion_Fisica`, `Deporte`, `Estadistica`, `Bibliotecologia_y_Ciencia_de_la_Informacion`

---

## Plantillas para inserción masiva (bash / curl)

- Insertar 1000 proyectos (usar `idPerfilEstudiante=1` como ejemplo, ajustar `-P` según concurrencia):
```bash scripts/bulk_proyectos.sh
seq 1 1000 | xargs -n1 -P50 -I{} curl -s -o /dev/null -w "%{http_code} {}\n" -X POST http://localhost:8080/proyectos \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Proyecto {}","descripcion":"Desc {}","url_proyecto":"https://example.com/proyecto_{}","tecnologias":"IOT","idPerfilEstudiante":1}'
```

- Insertar 1000 habilidades (idPerfilEstudiante=1):
```bash scripts/bulk_habilidades.sh
seq 1 1000 | xargs -n1 -P50 -I{} curl -s -o /dev/null -w "%{http_code} {}\n" -X POST http://localhost:8080/habilidades \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Skill {}","nivel":5,"tipoHabilidad":"Tecnica","idPerfilEstudiante":1}'
```

- Notas sobre concurrencia:
  - Ajusta `-P50` a valores más bajos si el servidor o la BD se saturan (p. ej. `-P10`).
  - Para Windows usa PowerShell con `Invoke-RestMethod` en lugar de `curl`/`xargs`.

---

## Recomendaciones finales
- Testea primero con lotes pequeños (10–100) antes de subir 1000 registros.
- Genera campos `UNIQUE` con índices para evitar conflictos (e.g. `url_proyecto_{i}`).
- Si prefieres poblar por BD, considera `src/main/resources/data.sql` con inserts (se carga al arrancar Spring Boot en dev).
- Revisa mensajes de error devueltos por endpoints para ajustar payloads si el servicio aplica validaciones adicionales.

---

Si quieres, puedo:
- Generar un `data.sql` de ejemplo con N registros (indica N y qué tablas prefieres), o
- Añadir scripts bash/powershell concretos en el repo para lanzar los `curl` en lote.

Indica qué prefieres y preparo los archivos correspondientes.
