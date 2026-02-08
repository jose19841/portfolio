# Arquitectura Backend - Portfolio

## 🏗️ Arquitectura Hexagonal (Ports & Adapters)

Este backend implementa **Arquitectura Hexagonal** combinada con **Domain-Driven Design (DDD)** y principios **SOLID**.

### Filosofía

La arquitectura hexagonal separa la lógica de negocio (dominio) de los detalles técnicos (infraestructura), permitiendo:

- ✅ Independencia de frameworks
- ✅ Independencia de UI
- ✅ Independencia de base de datos
- ✅ Testabilidad
- ✅ Mantenibilidad

---

## 📐 Estructura de Capas

### **1. Domain (Núcleo)**

**Responsabilidad:** Lógica de negocio pura

**No depende de:** Nada (ni application, ni infrastructure)

**Contiene:**
- **model/**: Entidades, Value Objects, Agregados
- **exception/**: Excepciones de dominio
```java
// Ejemplo: domain/model/Contact.java
public class Contact {
    private ContactId id;
    private Email email;
    private String name;
    private String message;
    private LocalDateTime createdAt;
    
    // Lógica de negocio aquí
}
```

---

### **2. Application (Casos de Uso)**

**Responsabilidad:** Orquestar el flujo de datos entre domain e infrastructure

**Depende de:** domain

**Contiene:**
- **ports/in/**: Interfaces de casos de uso (lo que la aplicación puede hacer)
- **ports/out/**: Interfaces de puertos de salida (lo que la aplicación necesita)
- **service/**: Implementación de casos de uso
```java
// Puerto de entrada (use case)
public interface CreateContactUseCase {
    ContactResponse execute(ContactRequest request);
}

// Puerto de salida (repository)
public interface ContactRepositoryPort {
    Contact save(Contact contact);
    Optional<Contact> findById(ContactId id);
}

// Servicio que implementa el use case
@Service
public class ContactService implements CreateContactUseCase {
    private final ContactRepositoryPort repository;
    
    @Override
    public ContactResponse execute(ContactRequest request) {
        // Lógica del caso de uso
    }
}
```

---

### **3. Infrastructure (Adaptadores)**

**Responsabilidad:** Detalles técnicos y conexión con el mundo exterior

**Depende de:** domain y application

**Contiene:**
- **web/**: Adaptadores de entrada (Controllers, DTOs)
- **persistence/**: Adaptadores de persistencia (JPA, Repositories)
- **mapper/**: Conversión entre capas
```java
// Adaptador de entrada (REST Controller)
@RestController
@RequestMapping("/api/contact")
public class ContactController {
    private final CreateContactUseCase createContactUseCase;
    
    @PostMapping
    public ResponseEntity<ContactResponse> create(@RequestBody ContactRequest request) {
        return ResponseEntity.ok(createContactUseCase.execute(request));
    }
}

// Adaptador de salida (Repository)
@Component
public class ContactRepositoryAdapter implements ContactRepositoryPort {
    private final ContactJpaRepository jpaRepository;
    private final ContactMapper mapper;
    
    @Override
    public Contact save(Contact contact) {
        ContactEntity entity = mapper.toEntity(contact);
        ContactEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }
}
```

---

## 🔄 Flujo de Datos
```
1. HTTP Request
   ↓
2. Controller (infrastructure/web)
   ↓
3. UseCase Interface (application/ports/in)
   ↓
4. Service (application/service) - Orquesta la lógica
   ↓
5. Domain Model - Aplica reglas de negocio
   ↓
6. Repository Interface (application/ports/out)
   ↓
7. Repository Adapter (infrastructure/persistence)
   ↓
8. JPA Repository
   ↓
9. Database
```

---

## 🎯 Principios SOLID Aplicados

### **Single Responsibility Principle (SRP)**
Cada clase tiene una única responsabilidad:
- `ContactController`: Recibir HTTP requests
- `ContactService`: Lógica de caso de uso
- `ContactRepositoryAdapter`: Persistencia

### **Open/Closed Principle (OCP)**
Abierto a extensión, cerrado a modificación:
- Puedes cambiar la implementación del repository sin tocar el service

### **Liskov Substitution Principle (LSP)**
Las implementaciones pueden ser sustituidas:
- `ContactRepositoryAdapter` puede ser reemplazado por `ContactMongoAdapter`

### **Interface Segregation Principle (ISP)**
Interfaces específicas:
- `CreateContactUseCase` vs `GetContactsUseCase` (no un mega-interface)

### **Dependency Inversion Principle (DIP)**
Depender de abstracciones:
- `ContactService` depende de `ContactRepositoryPort` (interfaz), no de la implementación

---

## 📦 Estructura de Módulos

Cada módulo sigue la misma estructura hexagonal:
```
module/
├── application/
│   ├── ports/
│   │   ├── in/
│   │   │   ├── CreateModuleUseCase.java
│   │   │   └── GetModuleUseCase.java
│   │   └── out/
│   │       └── ModuleRepositoryPort.java
│   └── service/
│       └── ModuleService.java
├── domain/
│   ├── exception/
│   │   └── ModuleException.java
│   └── model/
│       ├── Module.java
│       └── ModuleId.java (Value Object)
└── infrastructure/
    ├── mapper/
    │   └── ModuleMapper.java
    ├── persistence/
    │   ├── entity/
    │   │   └── ModuleEntity.java
    │   ├── repository/
    │   │   └── ModuleJpaRepository.java
    │   └── adapter/
    │       └── ModuleRepositoryAdapter.java
    └── web/
        ├── controller/
        │   └── ModuleController.java
        └── dto/
            ├── ModuleRequest.java
            └── ModuleResponse.java
```

---

## 🧪 Beneficios para Testing

### **Domain:**
- Test unitarios puros (sin mocks)
- Rápidos y confiables

### **Application:**
- Test de casos de uso
- Mock solo los puertos (interfaces)

### **Infrastructure:**
- Test de integración
- Testcontainers para DB
```java
// Test de dominio (puro)
@Test
void shouldCreateValidContact() {
    Contact contact = new Contact(
        new ContactId(),
        new Email("test@example.com"),
        "John Doe",
        "Hello"
    );
    
    assertTrue(contact.isValid());
}

// Test de servicio (con mocks)
@Test
void shouldSaveContact() {
    when(repository.save(any())).thenReturn(contact);
    
    ContactResponse response = service.execute(request);
    
    assertNotNull(response);
}
```

---

## 🔧 Configuración

### **Inyección de Dependencias**

Spring Boot maneja la inyección mediante `@Component`, `@Service`, etc.
```java
@Configuration
public class BeanConfiguration {
    
    @Bean
    public CreateContactUseCase createContactUseCase(
        ContactRepositoryPort repository
    ) {
        return new ContactService(repository);
    }
}
```

---

## 📊 Diagrama de Dependencias
```
┌─────────────────────────────────────┐
│         Infrastructure              │
│  (Controllers, JPA, Mappers)        │
└──────────────┬──────────────────────┘
               │ depende de
               ↓
┌─────────────────────────────────────┐
│          Application                │
│   (Services, Ports/UseCase)         │
└──────────────┬──────────────────────┘
               │ depende de
               ↓
┌─────────────────────────────────────┐
│            Domain                   │
│    (Entities, Value Objects)        │
│         NO DEPENDE DE NADA          │
└─────────────────────────────────────┘
```

---

## 🎓 Referencias

- [Hexagonal Architecture by Alistair Cockburn](https://alistair.cockburn.us/hexagonal-architecture/)
- [Domain-Driven Design by Eric Evans](https://www.domainlanguage.com/ddd/)
- [Clean Architecture by Robert C. Martin](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)