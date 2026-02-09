# Portfolio Profesional - José Pereyra

> Portfolio fullstack profesional desarrollado con arquitectura hexagonal y DDD

## 🚀 Descripción

Portfolio profesional que muestra mis proyectos, habilidades técnicas y experiencia como **Backend Developer | Full Stack**.

**Tagline:** *"Construyendo soluciones escalables desde el aprendizaje continuo"*

---

## 🏗️ Arquitectura

Este proyecto está estructurado como un **monorepo** que contiene:

- **Backend:** API RESTful con Spring Boot, Arquitectura Hexagonal y DDD
- **Frontend:** Aplicación React con TypeScript y Vite
- **Docs:** Documentación técnica completa
```
portfolio/
├── frontend/          # React + Vite + TypeScript
├── backend/           # Spring Boot + Hexagonal + DDD
├── docs/              # Documentación
│   ├── frontend/
│   └── backend/
├── .gitignore
└── README.md
```

---

## 🛠️ Stack Tecnológico

### Backend
- **Java 21+**
- **Spring Boot 3.5x**
- **PostgreSQL**
- **Arquitectura Hexagonal**
- **Domain-Driven Design (DDD)**
- **SOLID Principles**
- **Maven**

### Frontend
- **React 18+**
- **TypeScript**
- **Vite**
- **Tailwind CSS**
- **React Router DOM**
- **Framer Motion**

### DevOps & Deployment
- **Git Flow**
- **Frontend:** Vercel
- **Backend:** Railway
- **Database:** Supabase (PostgreSQL)

---

## 📦 Módulos del Backend

El backend está organizado en módulos siguiendo arquitectura hexagonal:

1. **home** - Información del hero/inicio
2. **aboutme** - Información "Sobre mí"
3. **project** - Gestión de proyectos
4. **techstack** - Stack tecnológico
5. **contact** - Formulario de contacto ✅ (backend implementado)
6. **shared** - Código compartido

Cada módulo sigue la estructura:
```
module/
├── application/
│   ├── ports/
│   └── service/
├── domain/
│   ├── exception/
│   └── model/
└── infrastructure/
    ├── mapper/
    ├── persistence/
    └── web/
```

---

## 🎨 Features del Frontend

El frontend está organizado por features:

- **Home** - Presentación principal
- **About Me** - Información personal y profesional
- **Projects** - Portfolio de proyectos
- **Tech Stack** - Tecnologías y habilidades
- **Contact** - Formulario de contacto funcional

---

## 🚀 Quick Start

### Requisitos Previos
- Java 21+
- Node.js 18+
- PostgreSQL
- Maven

### Backend
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

### Frontend
```bash
cd frontend
npm install
npm run dev
```

---

## 📚 Documentación

Documentación detallada disponible en:

- [Backend Architecture](docs/backend/ARCHITECTURE.md)
- [Frontend Architecture](docs/frontend/ARCHITECTURE.md)
- [API Endpoints](docs/backend/API_ENDPOINTS.md)
- [Setup Guide](docs/backend/SETUP.md)

---

## 👨‍💻 Autor

**José Pereyra**  
Backend Developer | Full Stack

- Email: jlpereyra2310@gmail.com
- GitHub: [@jose19841](https://github.com/jose19841)
- LinkedIn: [Próximamente]

---

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

---

## 🔄 Git Flow

Este proyecto utiliza Git Flow para el manejo de ramas:

- `main` - Producción
- `develop` - Desarrollo
- `feature/*` - Nuevas funcionalidades
- `hotfix/*` - Correcciones urgentes
- `release/*` - Preparación de releases