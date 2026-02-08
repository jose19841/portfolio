# Módulos del Backend - Portfolio

## 📦 Estructura Modular

Cada módulo implementa **Arquitectura Hexagonal** de forma independiente.

---

## 1️⃣ Módulo: **home**

### **Responsabilidad:**
Gestionar la información del hero/presentación principal del portfolio.

### **Estructura:**
```
home/
├── application/
│   ├── ports/
│   │   ├── in/
│   │   │   └── GetHomeInfoUseCase.java
│   │   └── out/
│   │       └── HomeRepositoryPort.java
│   └── service/
│       └── HomeService.java
├── domain/
│   ├── exception/
│   │   └── HomeInfoNotFoundException.java
│   └── model/
│       ├── HomeInfo.java
│       └── HomeInfoId.java
└── infrastructure/
    ├── mapper/
    │   └── HomeMapper.java
    ├── persistence/
    │   ├── entity/
    │   │   └── HomeInfoEntity.java
    │   ├── repository/
    │   │   └── HomeInfoJpaRepository.java
    │   └── adapter/
    │       └── HomeRepositoryAdapter.java
    └── web/
        ├── controller/
        │   └── HomeController.java
        └── dto/
            └── HomeInfoResponse.java
```

### **Endpoints:**
```
GET /api/home
```

### **Modelo de Dominio:**
```java
public class HomeInfo {
    private HomeInfoId id;
    private String fullName;
    private String title;
    private String tagline;
    private Email email;
    private String githubUrl;
    private String linkedinUrl;
}
```

### **Reglas de Negocio:**
- Solo debe existir 1 registro de HomeInfo
- Email debe ser válido
- URLs deben tener formato correcto

---

## 2️⃣ Módulo: **aboutme**

### **Responsabilidad:**
Gestionar la información sobre mí / biografía profesional.

### **Estructura:**
```
aboutme/
├── application/
│   ├── ports/
│   │   ├── in/
│   │   │   └── GetAboutMeUseCase.java
│   │   └── out/
│   │       └── AboutMeRepositoryPort.java
│   └── service/
│       └── AboutMeService.java
├── domain/
│   ├── exception/
│   │   └── AboutMeNotFoundException.java
│   └── model/
│       ├── AboutMe.java
│       └── AboutMeId.java
└── infrastructure/
    ├── mapper/
    │   └── AboutMeMapper.java
    ├── persistence/
    │   ├── entity/
    │   │   └── AboutMeEntity.java
    │   ├── repository/
    │   │   └── AboutMeJpaRepository.java
    │   └── adapter/
    │       └── AboutMeRepositoryAdapter.java
    └── web/
        ├── controller/
        │   └── AboutMeController.java
        └── dto/
            └── AboutMeResponse.java
```

### **Endpoints:**
```
GET /api/about-me
```

### **Modelo de Dominio:**
```java
public class AboutMe {
    private AboutMeId id;
    private String biography;
    private String professionalTitle;
    private String education;
    private String profileImageUrl;
}
```

### **Reglas de Negocio:**
- Solo debe existir 1 registro de AboutMe
- Biography no puede estar vacía
- professionalTitle es requerido

---

## 3️⃣ Módulo: **project**

### **Responsabilidad:**
Gestionar los proyectos del portfolio.

### **Estructura:**
```
project/
├── application/
│   ├── ports/
│   │   ├── in/
│   │   │   ├── GetAllProjectsUseCase.java
│   │   │   └── GetProjectByIdUseCase.java
│   │   └── out/
│   │       └── ProjectRepositoryPort.java
│   └── service/
│       └── ProjectService.java
├── domain/
│   ├── exception/
│   │   └── ProjectNotFoundException.java
│   └── model/
│       ├── Project.java
│       ├── ProjectId.java
│       └── Technology.java
└── infrastructure/
    ├── mapper/
    │   └── ProjectMapper.java
    ├── persistence/
    │   ├── entity/
    │   │   ├── ProjectEntity.java
    │   │   └── TechnologyEntity.java
    │   ├── repository/
    │   │   ├── ProjectJpaRepository.java
    │   │   └── TechnologyJpaRepository.java
    │   └── adapter/
    │       └── ProjectRepositoryAdapter.java
    └── web/
        ├── controller/
        │   └── ProjectController.java
        └── dto/
            ├── ProjectResponse.java
            └── TechnologyResponse.java
```

### **Endpoints:**
```
GET /api/projects
GET /api/projects/{id}
GET /api/projects?featured=true
```

### **Modelo de Dominio:**
```java
public class Project {
    private ProjectId id;
    private String name;
    private String description;
    private String githubUrl;
    private String demoUrl;
    private String imageUrl;
    private boolean isFullstack;
    private boolean isFeatured;
    private int displayOrder;
    private List<Technology> technologies;
}

public class Technology {
    private TechnologyId id;
    private String name;
    private String category; // Backend, Frontend, Database, Tools
}
```

### **Reglas de Negocio:**
- name es único
- githubUrl debe ser una URL válida de GitHub
- displayOrder determina el orden de visualización
- Cada proyecto puede tener múltiples tecnologías

---

## 4️⃣ Módulo: **techstack**

### **Responsabilidad:**
Gestionar el stack tecnológico personal (habilidades).

### **Estructura:**
```
techstack/
├── application/
│   ├── ports/
│   │   ├── in/
│   │   │   └── GetTechStackUseCase.java
│   │   └── out/
│   │       └── TechStackRepositoryPort.java
│   └── service/
│       └── TechStackService.java
├── domain/
│   ├── exception/
│   │   └── TechStackNotFoundException.java
│   └── model/
│       ├── TechStack.java
│       ├── TechStackId.java
│       └── ProficiencyLevel.java (Enum)
└── infrastructure/
    ├── mapper/
    │   └── TechStackMapper.java
    ├── persistence/
    │   ├── entity/
    │   │   └── TechStackEntity.java
    │   ├── repository/
    │   │   └── TechStackJpaRepository.java
    │   └── adapter/
    │       └── TechStackRepositoryAdapter.java
    └── web/
        ├── controller/
        │   └── TechStackController.java
        └── dto/
            └── TechStackResponse.java
```

### **Endpoints:**
```
GET /api/tech-stack
```

### **Modelo de Dominio:**
```java
public class TechStack {
    private TechStackId id;
    private Technology technology;
    private ProficiencyLevel proficiencyLevel;
    private int displayOrder;
}

public enum ProficiencyLevel {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED,
    EXPERT
}
```

### **Reglas de Negocio:**
- Cada tecnología aparece una sola vez en el tech stack
- proficiencyLevel es requerido
- Los resultados se agrupan por categoría (Backend, Frontend, etc.)

---

## 5️⃣ Módulo: **contact**

### **Responsabilidad:**
Gestionar el formulario de contacto y mensajes recibidos.

### **Estructura:**
```
contact/
├── application/
│   ├── ports/
│   │   ├── in/
│   │   │   ├── CreateContactUseCase.java
│   │   │   └── GetAllContactsUseCase.java
│   │   └── out/
│   │       └── ContactRepositoryPort.java
│   └── service/
│       └── ContactService.java
├── domain/
│   ├── exception/
│   │   ├── InvalidEmailException.java
│   │   └── ContactNotFoundException.java
│   └── model/
│       ├── Contact.java
│       ├── ContactId.java
│       └── Email.java (Value Object)
└── infrastructure/
    ├── mapper/
    │   └── ContactMapper.java
    ├── persistence/
    │   ├── entity/
    │   │   └── ContactEntity.java
    │   ├── repository/
    │   │   └── ContactJpaRepository.java
    │   └── adapter/
    │       └── ContactRepositoryAdapter.java
    └── web/
        ├── controller/
        │   └── ContactController.java
        └── dto/
            ├── ContactRequest.java
            └── ContactResponse.java
```

### **Endpoints:**
```
POST /api/contact
GET  /api/contact (admin - futuro)
```

### **Modelo de Dominio:**
```java
public class Contact {
    private ContactId id;
    private String name;
    private Email email;
    private String subject;
    private String message;
    private boolean isRead;
    private LocalDateTime createdAt;
    private LocalDateTime readAt;
    
    public void markAsRead() {
        this.isRead = true;
        this.readAt = LocalDateTime.now();
    }
}

public class Email {
    private String value;
    
    public Email(String value) {
        if (!isValid(value)) {
            throw new InvalidEmailException("Invalid email format");
        }
        this.value = value;
    }
    
    private boolean isValid(String email) {
        // Validación de formato
    }
}
```

### **Reglas de Negocio:**
- Email debe ser válido (Value Object con validación)
- Message debe tener al menos 10 caracteres
- Subject es opcional
- createdAt se asigna automáticamente
- isRead por defecto es false

---

## 6️⃣ Módulo: **shared**

### **Responsabilidad:**
Código compartido entre todos los módulos.

### **Estructura:**
```
shared/
├── domain/
│   └── exception/
│       ├── DomainException.java
│       ├── NotFoundException.java
│       └── ValidationException.java
└── infrastructure/
    ├── config/
    │   ├── CorsConfig.java
    │   ├── SecurityConfig.java
    │   └── BeanConfiguration.java
    └── exception/
        ├── GlobalExceptionHandler.java
        └── ErrorResponse.java
```

### **Contenido:**

#### **Excepciones Base:**
```java
public abstract class DomainException extends RuntimeException {
    protected DomainException(String message) {
        super(message);
    }
}

public class NotFoundException extends DomainException {
    public NotFoundException(String message) {
        super(message);
    }
}
```

#### **Global Exception Handler:**
```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(404).body(
            new ErrorResponse(404, ex.getMessage())
        );
    }
    
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidation(ValidationException ex) {
        return ResponseEntity.status(400).body(
            new ErrorResponse(400, ex.getMessage())
        );
    }
}
```

#### **CORS Configuration:**
```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
```

---

## 🔄 Comunicación Entre Módulos

Los módulos son **independientes** y NO se comunican directamente entre sí.

### **Ejemplo:**
- `project` necesita información de `technologies`
- Solución: Comparten la tabla `technologies` a través de sus propios repositorios
- NO hay dependencia directa entre módulos

---

## 🧪 Testing por Módulo

Cada módulo tiene sus propios tests:
```
module/
├── test/
│   ├── domain/
│   │   └── ModuleTest.java
│   ├── application/
│   │   └── ModuleServiceTest.java
│   └── infrastructure/
│       ├── ModuleRepositoryAdapterTest.java
│       └── ModuleControllerTest.java
```

---

## 📊 Diagrama de Módulos
```
┌──────────┐  ┌──────────┐  ┌──────────┐
│   home   │  │ aboutme  │  │ project  │
└──────────┘  └──────────┘  └──────────┘

┌──────────┐  ┌──────────┐  ┌──────────┐
│techstack │  │ contact  │  │  shared  │
└──────────┘  └──────────┘  └──────────┘
                                  ↑
                    ┌─────────────┴─────────────┐
                    │ Todos dependen de shared  │
                    └───────────────────────────┘
```

---

## 🎯 Próximas Implementaciones

- **Admin module**: Autenticación y gestión
- **Analytics module**: Tracking de visitas
- **Email module**: Notificaciones por email