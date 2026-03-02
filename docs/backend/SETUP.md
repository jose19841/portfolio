# Guía de Setup - Backend Portfolio

## 🛠️ Requisitos Previos

### Software Necesario:
- **Java 17 o superior**
- **Maven 3.8+**
- **PostgreSQL 14+**
- **Git**
- **IDE:** IntelliJ IDEA / Eclipse / VS Code con extensiones Java

### Verificar instalación:
```bash
java -version
mvn -version
psql --version
git --version
```

---

## 📦 Instalación Paso a Paso

### **1. Clonar el Repositorio**
```bash
git clone https://github.com/jose19841/portfolio.git
cd portfolio/backend
```

---

### **2. Configurar Base de Datos**

#### **Opción A: PostgreSQL Local**

**Crear base de datos:**
```bash
# Conectar a PostgreSQL
psql -U postgres

# Crear base de datos
CREATE DATABASE portfolio;

# Crear usuario (opcional)
CREATE USER portfolio_user WITH PASSWORD 'tu_password';
GRANT ALL PRIVILEGES ON DATABASE portfolio TO portfolio_user;

# Salir
\q
```

#### **Opción B: PostgreSQL con Docker**
```bash
docker run --name portfolio-postgres \
  -e POSTGRES_DB=portfolio \
  -e POSTGRES_USER=portfolio_user \
  -e POSTGRES_PASSWORD=tu_password \
  -p 5432:5432 \
  -d postgres:14
```

#### **Opción C: Supabase (Cloud - Gratis)**

1. Ir a [supabase.com](https://supabase.com)
2. Crear cuenta
3. Crear nuevo proyecto
4. Obtener connection string en Settings > Database

---

### **3. Configurar application.properties**

Crear archivo `src/main/resources/application.properties`:
```properties
# Server
server.port=8080
spring.application.name=portfolio-backend

# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/portfolio
spring.datasource.username=portfolio_user
spring.datasource.password=tu_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true

# CORS
cors.allowed-origins=http://localhost:5173,https://tu-portfolio.vercel.app

# Logging
logging.level.root=INFO
logging.level.com.josepereyra.portfolio=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

**Para Supabase:**
```properties
spring.datasource.url=jdbc:postgresql://db.xxxxx.supabase.co:5432/postgres?user=postgres&password=tu_password
```

---

### **4. Instalar Dependencias**
```bash
mvn clean install
```

Si hay errores, verificar `pom.xml` y conexión a internet.

---

### **5. Ejecutar la Aplicación**

#### **Opción A: Maven**
```bash
mvn spring-boot:run
```

#### **Opción B: JAR**
```bash
mvn clean package
java -jar target/portfolio-backend-0.0.1-SNAPSHOT.jar
```

#### **Opción C: IDE**
- Abrir proyecto en IntelliJ/Eclipse
- Ejecutar `PortfolioApplication.java`

**La aplicación estará disponible en:** `http://localhost:8080`

---

### **6. Verificar que Funciona**

#### **Health Check:**
```bash
curl http://localhost:8080/actuator/health
```

#### **Test Endpoints:**
```bash
# Home info (puede dar 404 si no hay datos)
curl http://localhost:8080/api/home

# Projects (puede dar 204 si no hay datos)
curl http://localhost:8080/api/projects
```

---

### **7. Cargar Datos Iniciales**

Conectar a PostgreSQL y ejecutar:
```sql
-- Home Info
INSERT INTO home_info (full_name, title, tagline, email, github_url, linkedin_url) 
VALUES (
  'José Pereyra',
  'Backend Developer | Full Stack',
  'Construyendo soluciones escalables desde el aprendizaje continuo',
  'jlpereyra2310@gmail.com',
  'https://github.com/jose19841',
  'https://linkedin.com/in/...'
);

-- About Me
INSERT INTO about_me (biography, professional_title, education) 
VALUES (
  'Desarrollador Backend especializado en arquitectura hexagonal y DDD...',
  'Técnico en Desarrollo de Software',
  'Técnico en Desarrollo de Software - [Institución]'
);

-- Technologies
INSERT INTO technologies (name, category) VALUES
('Java', 'Backend'),
('Spring Boot', 'Backend'),
('React', 'Frontend'),
('TypeScript', 'Frontend'),
('Vite', 'Frontend'),
('PostgreSQL', 'Database'),
('MySQL', 'Database'),
('Git', 'Tools'),
('Maven', 'Tools');

-- Projects
INSERT INTO projects (name, description, github_url, is_fullstack, is_featured, display_order) VALUES
('QRest', 'Sistema de gestión para restaurantes con administración de mesas, pedidos y facturación en tiempo real', 'https://github.com/jose19841/QRest', true, true, 1),
('logiproApp', 'Sistema logístico completo para gestión de entregas y rutas', 'https://github.com/jose19841/logiproApp', true, true, 2),
('Publicidad-Web', 'Web corporativa para empresa de publicidad', 'https://github.com/jose19841/Publicidad-Web', true, false, 3),
('coblan-system-admin-frontend', 'Sistema de administración con panel de control', 'https://github.com/jose19841/coblan-system-admin-fronted', true, false, 4);

-- Project Technologies (ejemplo para QRest)
INSERT INTO project_technologies (project_id, technology_id) VALUES
(1, 1), -- Java
(1, 2), -- Spring Boot
(1, 3), -- React
(1, 6); -- PostgreSQL

-- Tech Stack
INSERT INTO tech_stack (technology_id, proficiency_level, display_order) VALUES
(1, 'Advanced', 1),  -- Java
(2, 'Advanced', 2),  -- Spring Boot
(3, 'Advanced', 3),  -- React
(4, 'Advanced', 4),  -- TypeScript
(6, 'Advanced', 5);  -- PostgreSQL
```

---

### **8. Verificar Datos Cargados**
```bash
curl http://localhost:8080/api/home
curl http://localhost:8080/api/about-me
curl http://localhost:8080/api/projects
curl http://localhost:8080/api/tech-stack
```

---

## 🧪 Ejecutar Tests
```bash
# Todos los tests
mvn test

# Tests de un módulo específico
mvn test -Dtest=ContactServiceTest

# Tests con coverage
mvn clean test jacoco:report
```

Ver reporte de coverage en: `target/site/jacoco/index.html`

---

## 🐛 Troubleshooting

### **Error: "Connection refused" al conectar a PostgreSQL**

**Solución:**
```bash
# Verificar que PostgreSQL esté corriendo
sudo systemctl status postgresql

# Iniciar PostgreSQL
sudo systemctl start postgresql
```

### **Error: "Table doesn't exist"**

**Solución:**
- Verificar que `spring.jpa.hibernate.ddl-auto=update` esté en application.properties
- O ejecutar los scripts SQL manualmente

### **Error: "Port 8080 already in use"**

**Solución:**
```bash
# Ver qué está usando el puerto
lsof -i :8080

# Matar el proceso
kill -9 <PID>

# O cambiar puerto en application.properties
server.port=8081
```

### **Error: "Maven dependencies not resolved"**

**Solución:**
```bash
# Limpiar cache de Maven
mvn clean
mvn dependency:purge-local-repository
mvn clean install
```

---

## 🔧 Variables de Entorno (Producción)

Para **Railway/Render**, configurar estas variables:
```
DATABASE_URL=jdbc:postgresql://host:5432/database
DB_USERNAME=usuario
DB_PASSWORD=password
CORS_ALLOWED_ORIGINS=https://tu-portfolio.vercel.app
```

Actualizar `application.properties` para usar variables de entorno:
```properties
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
cors.allowed-origins=${CORS_ALLOWED_ORIGINS}
```

---

## 📊 Monitoreo (Opcional)

Agregar Spring Boot Actuator para monitoreo:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Endpoints disponibles:
- `http://localhost:8080/actuator/health`
- `http://localhost:8080/actuator/metrics`
- `http://localhost:8080/actuator/info`

---

## 🚀 Deployment

Ver guías específicas de deployment:
- [Railway](https://docs.railway.app/guides/spring-boot)
- [Render](https://render.com/docs/deploy-spring-boot)
- [AWS Elastic Beanstalk](https://docs.aws.amazon.com/elasticbeanstalk/)

---

## 📚 Recursos Adicionales

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [Maven Documentation](https://maven.apache.org/guides/)

---

## ✅ Checklist de Setup Completo

- [ ] Java 17+ instalado
- [ ] Maven instalado
- [ ] PostgreSQL instalado y corriendo
- [ ] Base de datos `portfolio` creada
- [ ] application.properties configurado
- [ ] Dependencias instaladas (`mvn clean install`)
- [ ] Aplicación corriendo (`mvn spring-boot:run`)
- [ ] Datos iniciales cargados
- [ ] Endpoints funcionando
- [ ] Tests pasando (`mvn test`)