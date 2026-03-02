# API Endpoints - Portfolio Backend

## 🌐 Base URL

**Desarrollo:** `http://localhost:8080/api`  
**Producción:** `https://tu-api.railway.app/api`

---

## 📡 Endpoints

### **1. Home Info**

#### `GET /api/home`

Obtener información del hero/presentación principal.

**Response:**
```json
{
  "fullName": "José Pereyra",
  "title": "Backend Developer | Full Stack",
  "tagline": "Construyendo soluciones escalables desde el aprendizaje continuo",
  "email": "jlpereyra2310@gmail.com",
  "githubUrl": "https://github.com/jose19841",
  "linkedinUrl": "https://linkedin.com/in/..."
}
```

**Status Codes:**
- `200 OK` - Éxito
- `404 Not Found` - No hay información configurada

---

### **2. About Me**

#### `GET /api/about-me`

Obtener información sobre mí / biografía profesional.

**Response:**
```json
{
  "biography": "Desarrollador Backend con experiencia en arquitectura hexagonal y DDD...",
  "professionalTitle": "Técnico en Desarrollo de Software",
  "education": "Técnico en Desarrollo de Software - [Institución]",
  "profileImageUrl": "https://tu-cdn.com/profile.jpg"
}
```

**Status Codes:**
- `200 OK` - Éxito
- `404 Not Found` - No hay información configurada

---

### **3. Projects**

#### `GET /api/projects`

Listar todos los proyectos del portfolio.

**Query Parameters:**
- `featured` (optional): `true` para obtener solo proyectos destacados

**Response:**
```json
[
  {
    "id": 1,
    "name": "QRest",
    "description": "Sistema de gestión para restaurantes con administración de mesas, pedidos y facturación en tiempo real",
    "githubUrl": "https://github.com/jose19841/QRest",
    "demoUrl": null,
    "imageUrl": "/images/qrest.png",
    "isFullstack": true,
    "isFeatured": true,
    "technologies": [
      {
        "id": 1,
        "name": "Java",
        "category": "Backend"
      },
      {
        "id": 2,
        "name": "Spring Boot",
        "category": "Backend"
      },
      {
        "id": 3,
        "name": "React",
        "category": "Frontend"
      },
      {
        "id": 6,
        "name": "PostgreSQL",
        "category": "Database"
      }
    ]
  },
  {
    "id": 2,
    "name": "logiproApp",
    "description": "Sistema logístico completo para gestión de entregas y rutas",
    "githubUrl": "https://github.com/jose19841/logiproApp",
    "demoUrl": null,
    "imageUrl": "/images/logipro.png",
    "isFullstack": true,
    "isFeatured": true,
    "technologies": [...]
  }
]
```

**Status Codes:**
- `200 OK` - Éxito
- `204 No Content` - No hay proyectos

---

#### `GET /api/projects/{id}`

Obtener un proyecto específico por ID.

**Path Parameters:**
- `id` (required): ID del proyecto

**Response:**
```json
{
  "id": 1,
  "name": "QRest",
  "description": "Sistema de gestión para restaurantes...",
  "githubUrl": "https://github.com/jose19841/QRest",
  "demoUrl": null,
  "imageUrl": "/images/qrest.png",
  "isFullstack": true,
  "isFeatured": true,
  "technologies": [...]
}
```

**Status Codes:**
- `200 OK` - Éxito
- `404 Not Found` - Proyecto no encontrado

---

### **4. Tech Stack**

#### `GET /api/tech-stack`

Obtener stack tecnológico personal agrupado por categorías.

**Response:**
```json
{
  "Backend": [
    {
      "id": 1,
      "name": "Java",
      "proficiencyLevel": "Advanced",
      "iconUrl": null
    },
    {
      "id": 2,
      "name": "Spring Boot",
      "proficiencyLevel": "Advanced",
      "iconUrl": null
    }
  ],
  "Frontend": [
    {
      "id": 3,
      "name": "React",
      "proficiencyLevel": "Advanced",
      "iconUrl": null
    },
    {
      "id": 4,
      "name": "TypeScript",
      "proficiencyLevel": "Advanced",
      "iconUrl": null
    }
  ],
  "Database": [
    {
      "id": 6,
      "name": "PostgreSQL",
      "proficiencyLevel": "Advanced",
      "iconUrl": null
    },
    {
      "id": 7,
      "name": "MySQL",
      "proficiencyLevel": "Advanced",
      "iconUrl": null
    }
  ],
  "Tools": [
    {
      "id": 8,
      "name": "Git",
      "proficiencyLevel": "Advanced",
      "iconUrl": null
    }
  ]
}
```

**Status Codes:**
- `200 OK` - Éxito
- `204 No Content` - No hay tecnologías configuradas

---

### **5. Contact** ✅

#### `POST /api/contact`

Enviar mensaje de contacto. *(Implementado)*

**Request Body:**
```json
{
  "name": "Juan Pérez",
  "email": "juan@example.com",
  "subject": "Consulta sobre proyecto",
  "message": "Hola José, me interesa conocer más sobre tu trabajo..."
}
```

**Validaciones:**
- `name`: Requerido, máximo 100 caracteres
- `email`: Requerido, formato email válido
- `subject`: Opcional, máximo 200 caracteres
- `message`: Requerido, mínimo 10 caracteres

**Response:**
```json
{
  "id": 1,
  "name": "Juan Pérez",
  "email": "juan@example.com",
  "subject": "Consulta sobre proyecto",
  "message": "Hola José...",
  "isRead": false,
  "createdAt": "2025-02-08T10:30:00"
}
```

**Status Codes:**
- `201 Created` - Mensaje enviado exitosamente
- `400 Bad Request` - Datos inválidos

**Errores de validación:**
```json
{
  "timestamp": "2025-02-08T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "errors": [
    {
      "field": "email",
      "message": "Email format is invalid"
    },
    {
      "field": "message",
      "message": "Message must be at least 10 characters"
    }
  ]
}
```

---

#### `GET /api/contact` (Admin - Futuro)

Listar todos los mensajes recibidos.

**Query Parameters:**
- `unread` (optional): `true` para obtener solo no leídos

**Response:**
```json
[
  {
    "id": 1,
    "name": "Juan Pérez",
    "email": "juan@example.com",
    "subject": "Consulta sobre proyecto",
    "message": "Hola José...",
    "isRead": false,
    "createdAt": "2025-02-08T10:30:00",
    "readAt": null
  }
]
```

**Status Codes:**
- `200 OK` - Éxito
- `204 No Content` - No hay mensajes
- `401 Unauthorized` - No autenticado (cuando se implemente auth)

---

## 🔒 CORS Configuration

El backend permite requests desde:
- `http://localhost:5173` (desarrollo frontend)
- Tu dominio de producción en Vercel
```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173", "https://tu-portfolio.vercel.app")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

---

## 🧪 Testing con cURL

### **Get Home Info:**
```bash
curl http://localhost:8080/api/home
```

### **Get Projects:**
```bash
curl http://localhost:8080/api/projects
```

### **Get Project by ID:**
```bash
curl http://localhost:8080/api/projects/1
```

### **Send Contact Message:**
```bash
curl -X POST http://localhost:8080/api/contact \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test User",
    "email": "test@example.com",
    "subject": "Test",
    "message": "This is a test message"
  }'
```

---

## 📊 Status Codes Summary

| Code | Meaning | Uso |
|------|---------|-----|
| 200  | OK | Request exitoso |
| 201  | Created | Recurso creado (POST contact) |
| 204  | No Content | Sin datos para devolver |
| 400  | Bad Request | Validación fallida |
| 404  | Not Found | Recurso no encontrado |
| 500  | Internal Server Error | Error del servidor |

---

## 🚀 Próximas Features (Futuro)

- `PUT /api/projects/{id}` - Actualizar proyecto (admin)
- `DELETE /api/projects/{id}` - Eliminar proyecto (admin)
- `PATCH /api/contact/{id}/read` - Marcar mensaje como leído (admin)
- Autenticación con JWT para endpoints de admin