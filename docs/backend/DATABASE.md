# Modelo de Base de Datos - Portfolio Backend

## 🗄️ Base de Datos

- **Motor:** PostgreSQL 14+
- **ORM:** Spring Data JPA (Hibernate)
- **Migraciones:** Liquibase o Flyway (opcional)

---

## 📊 Tablas

### **1. home_info**

Información del hero/presentación principal.
```sql
CREATE TABLE home_info (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    title VARCHAR(200) NOT NULL,
    tagline TEXT NOT NULL,
    email VARCHAR(100) NOT NULL,
    github_url VARCHAR(255),
    linkedin_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

**Campos:**
- `id`: Identificador único
- `full_name`: José Pereyra
- `title`: Backend Developer | Full Stack
- `tagline`: "Construyendo soluciones escalables desde el aprendizaje continuo"
- `email`: jlpereyra2310@gmail.com
- `github_url`: https://github.com/jose19841
- `linkedin_url`: URL de LinkedIn

**Nota:** Solo debe haber **1 registro** en esta tabla.

---

### **2. about_me**

Información sobre mí / biografía profesional.
```sql
CREATE TABLE about_me (
    id BIGSERIAL PRIMARY KEY,
    biography TEXT NOT NULL,
    professional_title VARCHAR(200) NOT NULL,
    education VARCHAR(255),
    profile_image_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

**Campos:**
- `id`: Identificador único
- `biography`: Descripción profesional extendida
- `professional_title`: Técnico en Desarrollo de Software
- `education`: Detalles académicos
- `profile_image_url`: URL de foto profesional

**Nota:** Solo debe haber **1 registro** en esta tabla.

---

### **3. projects**

Proyectos del portfolio.
```sql
CREATE TABLE projects (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    github_url VARCHAR(255) NOT NULL,
    demo_url VARCHAR(255),
    image_url VARCHAR(255),
    is_fullstack BOOLEAN DEFAULT false,
    is_featured BOOLEAN DEFAULT false,
    display_order INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

**Campos:**
- `id`: Identificador único
- `name`: Nombre del proyecto (ej: "QRest")
- `description`: Descripción detallada
- `github_url`: URL del repositorio
- `demo_url`: URL del demo deployado (opcional)
- `image_url`: Screenshot del proyecto
- `is_fullstack`: true si es fullstack, false si solo backend
- `is_featured`: true para proyectos destacados
- `display_order`: Orden de visualización (menor = primero)

**Datos iniciales:**
```sql
INSERT INTO projects (name, description, github_url, is_fullstack, is_featured, display_order) VALUES
('QRest', 'Sistema de gestión para restaurantes con administración de mesas, pedidos y facturación en tiempo real', 'https://github.com/jose19841/QRest', true, true, 1),
('logiproApp', 'Sistema logístico completo para gestión de entregas y rutas', 'https://github.com/jose19841/logiproApp', true, true, 2),
('Publicidad-Web', 'Web corporativa para empresa de publicidad', 'https://github.com/jose19841/Publicidad-Web', true, false, 3),
('coblan-system-admin-frontend', 'Sistema de administración con panel de control', 'https://github.com/jose19841/coblan-system-admin-fronted', true, false, 4);
```

---

### **4. technologies**

Tecnologías utilizadas en los proyectos (relación muchos a muchos).
```sql
CREATE TABLE technologies (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    category VARCHAR(50) NOT NULL,
    icon_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

**Campos:**
- `id`: Identificador único
- `name`: Nombre de la tecnología (ej: "Java", "Spring Boot")
- `category`: Backend, Frontend, Database, Tools
- `icon_url`: URL del icono/logo

**Datos iniciales:**
```sql
INSERT INTO technologies (name, category) VALUES
-- Backend
('Java', 'Backend'),
('Spring Boot', 'Backend'),
-- Frontend
('React', 'Frontend'),
('TypeScript', 'Frontend'),
('Vite', 'Frontend'),
-- Databases
('PostgreSQL', 'Database'),
('MySQL', 'Database'),
-- Tools
('Git', 'Tools'),
('Maven', 'Tools'),
('Docker', 'Tools');
```

---

### **5. project_technologies**

Relación muchos a muchos entre proyectos y tecnologías.
```sql
CREATE TABLE project_technologies (
    project_id BIGINT NOT NULL,
    technology_id BIGINT NOT NULL,
    PRIMARY KEY (project_id, technology_id),
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    FOREIGN KEY (technology_id) REFERENCES technologies(id) ON DELETE CASCADE
);
```

**Ejemplo de relaciones:**
```sql
-- QRest usa: Java, Spring Boot, React, PostgreSQL
INSERT INTO project_technologies (project_id, technology_id) VALUES
(1, 1), -- Java
(1, 2), -- Spring Boot
(1, 3), -- React
(1, 6); -- PostgreSQL
```

---

### **6. tech_stack**

Stack tecnológico personal (lo que manejas).
```sql
CREATE TABLE tech_stack (
    id BIGSERIAL PRIMARY KEY,
    technology_id BIGINT NOT NULL,
    proficiency_level VARCHAR(20) NOT NULL,
    display_order INT DEFAULT 0,
    FOREIGN KEY (technology_id) REFERENCES technologies(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

**Campos:**
- `id`: Identificador único
- `technology_id`: Referencia a technologies
- `proficiency_level`: Beginner, Intermediate, Advanced, Expert
- `display_order`: Orden de visualización

**Datos iniciales:**
```sql
INSERT INTO tech_stack (technology_id, proficiency_level, display_order) VALUES
(1, 'Advanced', 1),    -- Java
(2, 'Advanced', 2),    -- Spring Boot
(3, 'Advanced', 3),    -- React
(4, 'Advanced', 4),    -- TypeScript
(6, 'Advanced', 5);    -- PostgreSQL
```

---

### **7. contacts**

Mensajes del formulario de contacto.
```sql
CREATE TABLE contacts (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    subject VARCHAR(200),
    message TEXT NOT NULL,
    is_read BOOLEAN DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    read_at TIMESTAMP
);
```

**Campos:**
- `id`: Identificador único
- `name`: Nombre de quien contacta
- `email`: Email de contacto
- `subject`: Asunto (opcional)
- `message`: Mensaje
- `is_read`: Si fue leído por el admin
- `created_at`: Fecha de creación
- `read_at`: Fecha de lectura

---

## 🔗 Diagrama de Relaciones
```
┌────────────┐
│  projects  │
└─────┬──────┘
      │
      │ 1:N
      ↓
┌──────────────────────┐
│ project_technologies │
└──────────┬───────────┘
           │
           │ N:1
           ↓
      ┌──────────────┐
      │ technologies │
      └──────┬───────┘
             │
             │ 1:N
             ↓
        ┌────────────┐
        │ tech_stack │
        └────────────┘

┌───────────┐
│ home_info │
└───────────┘

┌───────────┐
│ about_me  │
└───────────┘

┌──────────┐
│ contacts │
└──────────┘
```

---

## 📝 Scripts Útiles

### **Crear base de datos:**
```sql
CREATE DATABASE portfolio;
```

### **Borrar todas las tablas (desarrollo):**
```sql
DROP TABLE IF EXISTS project_technologies CASCADE;
DROP TABLE IF EXISTS tech_stack CASCADE;
DROP TABLE IF EXISTS contacts CASCADE;
DROP TABLE IF EXISTS projects CASCADE;
DROP TABLE IF EXISTS technologies CASCADE;
DROP TABLE IF EXISTS about_me CASCADE;
DROP TABLE IF EXISTS home_info CASCADE;
```

### **Backup:**
```bash
pg_dump -U postgres portfolio > backup.sql
```

### **Restore:**
```bash
psql -U postgres portfolio < backup.sql
```

---

## 🔧 Configuración en application.properties
```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/portfolio
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true

# Logging
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

---

## ⚠️ Notas Importantes

1. **home_info** y **about_me**: Solo deben tener 1 registro cada una
2. **projects**: Se cargan manualmente los 4 proyectos iniciales
3. **technologies**: Catálogo compartido entre projects y tech_stack
4. **contacts**: Crecerá con cada mensaje del formulario
5. **Índices**: PostgreSQL creará índices automáticos para PKs y FKs

---

## 🚀 Deployment

Para **Supabase** (PostgreSQL gratis):
1. Crear proyecto en supabase.com
2. Obtener connection string
3. Ejecutar scripts de creación de tablas
4. Cargar datos iniciales