# Portfolio Backend

> API RESTful con Spring Boot, Arquitectura Hexagonal y Domain-Driven Design

## 🏗️ Arquitectura

Este backend implementa **Arquitectura Hexagonal (Ports & Adapters)** combinada con **Domain-Driven Design (DDD)** y principios **SOLID**.

### Capas de la Arquitectura
```
application/
├── ports/
│   ├── in/      # Casos de uso (interfaces)
│   └── out/     # Puertos de salida (repositories interfaces)
└── service/     # Implementación de casos de uso

domain/
├── exception/   # Excepciones de dominio
└── model/       # Entidades, Value Objects, Agregados

infrastructure/
├── mapper/      # Mapeo Entity <-> Domain
├── persistence/ # Adaptadores de persistencia (JPA)
└── web/         # Adaptadores de entrada (Controllers, DTOs)
```

### Flujo de Dependencias
```
Controller (infrastructure/web)
    ↓
UseCase Interface (application/ports/in)
    ↓
Service (application/service)
    ↓
Repository Interface (application/ports/out)
    ↓
Repository Adapter (infrastructure/persistence)
```

**Regla de Oro:**
- `domain/` NO depende de nadie
- `application/` depende de `domain/`
- `infrastructure/` depende de `application/` y `domain/`

---

## 📦 Módulos

### 1. **home**
- Información del hero/inicio
- Datos personales principales

### 2. **aboutme**
- Biografía profesional
- Descripción detallada

### 3. **project**
- Información de proyectos del portfolio
- GET endpoints (read-only)
- Datos: nombre, descripción, tecnologías, URLs (GitHub, demo)
- Screenshots e imágenes

### 4. **techstack**
- Stack tecnológico
- Habilidades y herramientas

### 5. **contact** ✅
- Formulario de contacto
- Almacenamiento de mensajes en PostgreSQL
- `POST /api/contact` - Crear mensaje con validación (nombre, email, subject, message)
- Value Object `Email` con validación de dominio

### 6. **shared**
- Configuración global
- Excepciones compartidas
- Utilidades comunes

---

## 🛠️ Stack Tecnológico

- **Java 21**
- **Spring Boot 3.5x**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **Lombok**
- **MapStruct** (para mappers)

---

## 🚀 Configuración y Ejecución

### Requisitos
- Java 17+
- Maven 3.8+
- PostgreSQL 14+

### Variables de Entorno

Crear archivo `application.properties` o `application.yml`:
```properties
# Database
spring.datasource.url=jdbc:postgresql://localhost:5432/portfolio
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server
server.port=8080

# CORS
cors.allowed-origins=http://localhost:5173
```

### Ejecutar
```bash
# Instalar dependencias
mvn clean install

# Ejecutar aplicación
mvn spring-boot:run

# Ejecutar tests
mvn test
```

La API estará disponible en: `http://localhost:8080`

---

## 📡 Endpoints Principales
```
GET    /api/home           - Información del hero
GET    /api/about-me       - Información sobre mí
GET    /api/projects       - Listar todos los proyectos
GET    /api/projects/{id}  - Obtener proyecto específico
GET    /api/tech-stack     - Obtener stack tecnológico
POST   /api/contact        - Enviar mensaje
GET    /api/contact        - Listar mensajes (admin)
```

Documentación completa: [API_ENDPOINTS.md](../docs/backend/API_ENDPOINTS.md)

---

## 📚 Documentación Adicional

- [Arquitectura Detallada](../docs/backend/ARCHITECTURE.md)
- [Guía de Setup](../docs/backend/SETUP.md)
- [Modelo de Base de Datos](../docs/backend/DATABASE.md)
- [Documentación de Módulos](../docs/backend/MODULES.md)

---

## 🧪 Testing
```bash
# Ejecutar todos los tests
mvn test

# Ejecutar tests de un módulo específico
mvn test -Dtest=ContactServiceTest

# Coverage
mvn clean test jacoco:report
```

---

## 🔄 Git Flow

Crear nueva feature:
```bash
git flow feature start backend-contact-module
# ... trabajar ...
git flow feature finish backend-contact-module
```

---

## 📝 Convenciones

### Commits
```
feat: nueva funcionalidad
fix: corrección de bug
docs: cambios en documentación
refactor: refactorización
test: agregar tests
chore: tareas de mantenimiento
```

### Nombres de clases
```
UseCase: CreateContactUseCase
Service: ContactService
Port: ContactRepositoryPort
Adapter: ContactRepositoryAdapter
Entity: ContactEntity
Domain Model: Contact
VO: Email, ContactId
```

---

## 👨‍💻 Autor

**José Pereyra**  
Backend Developer | Full Stack