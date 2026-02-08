# Portfolio Frontend

> Aplicación React con TypeScript, Vite y arquitectura basada en features

## 🏗️ Arquitectura

Este frontend está organizado por **features** (módulos funcionales), donde cada feature contiene todo lo relacionado a esa funcionalidad.

### Estructura de Carpetas
```
src/
├── features/              # Módulos por funcionalidad
│   ├── home/
│   ├── about/
│   ├── projects/
│   ├── techstack/
│   └── contact/
├── shared/                # Código compartido
│   ├── components/
│   ├── hooks/
│   ├── types/
│   └── utils/
├── context/               # Context API (Theme, API)
├── routes/                # Configuración de rutas
├── layouts/               # Layouts de página
├── App.tsx
└── main.tsx
```

### Anatomía de un Feature
```
feature/
├── components/            # Componentes específicos del feature
│   ├── FeatureCard.tsx
│   └── FeatureList.tsx
├── hooks/                 # Hooks personalizados
│   └── useFeatureData.ts
├── services/              # Llamadas a API
│   └── featureService.ts
├── types/                 # Types de TypeScript
│   └── feature.types.ts
└── Feature.tsx            # Página principal del feature
```

---

## 🎨 Features

### 1. **Home**
- Hero section con presentación
- Tagline: "Construyendo soluciones escalables desde el aprendizaje continuo"
- Links rápidos a secciones

### 2. **About**
- Información profesional
- Técnico en Desarrollo de Software
- Biografía y experiencia

### 3. **Projects**
- Grid de proyectos del portfolio
- Cards con información de cada proyecto
- Links a GitHub y demos
- Tecnologías utilizadas
- Screenshots

### 4. **Tech Stack**
- Categorías: Backend, Frontend, Databases, Tools
- Visualización de tecnologías
- Nivel de experiencia (opcional)

### 5. **Contact**
- Formulario funcional
- Validación de campos
- Integración con backend
- Información de contacto

---

## 🛠️ Stack Tecnológico

- **React 18+**
- **TypeScript**
- **Vite** (build tool)
- **React Router DOM** (enrutamiento)
- **Tailwind CSS** (estilos)
- **Framer Motion** (animaciones)
- **Axios** (HTTP client)

---

## 🚀 Configuración y Ejecución

### Requisitos
- Node.js 18+
- npm o yarn

### Variables de Entorno

Crear archivo `.env`:
```env
VITE_API_URL=http://localhost:8080/api
```

### Instalación y Ejecución
```bash
# Instalar dependencias
npm install

# Modo desarrollo
npm run dev

# Build para producción
npm run build

# Preview del build
npm run preview

# Linting
npm run lint
```

La aplicación estará disponible en: `http://localhost:5173`

---

## 📡 Integración con Backend

El frontend consume la API del backend mediante servicios:
```typescript
// Ejemplo: src/features/projects/services/projectService.ts
import axios from 'axios';

const API_URL = import.meta.env.VITE_API_URL;

export const getProjects = async () => {
  const response = await axios.get(`${API_URL}/projects`);
  return response.data;
};
```

---

## 🎨 Diseño

### Principios de Diseño
- **Minimalista:** Espacios en blanco generosos
- **Profesional:** Paleta de colores limitada (2-3 colores)
- **Responsive:** Mobile-first approach
- **Accesible:** Contraste adecuado, navegación por teclado

### Paleta de Colores
- Primary: `#...` (por definir)
- Secondary: `#...` (por definir)
- Background: `#...` (por definir)
- Text: `#...` (por definir)

### Tipografía
- Títulos: (por definir)
- Texto: (por definir)

---

## 📚 Documentación Adicional

- [Arquitectura Detallada](../docs/frontend/ARCHITECTURE.md)
- [Guía de Setup](../docs/frontend/SETUP.md)
- [Componentes Compartidos](../docs/frontend/COMPONENTS.md)
- [Integración con API](../docs/frontend/API.md)

---

## 🧪 Testing
```bash
# Ejecutar tests (cuando se implementen)
npm run test

# Coverage
npm run test:coverage
```

---

## 🔄 Git Flow

Crear nueva feature:
```bash
git flow feature start frontend-home
# ... trabajar ...
git flow feature finish frontend-home
```

---

## 📝 Convenciones

### Commits
```
feat: nueva funcionalidad
fix: corrección de bug
docs: cambios en documentación
style: cambios de estilo visual (no afecta lógica)
refactor: refactorización
test: agregar tests
chore: tareas de mantenimiento
```

### Nombres de Archivos
```
PascalCase para componentes: Button.tsx, ProjectCard.tsx
camelCase para hooks: useProjects.ts, useContactForm.ts
camelCase para servicios: projectService.ts, contactService.ts
```

### Estructura de Imports
```typescript
// 1. React y librerías externas
import React, { useState } from 'react';
import { Link } from 'react-router-dom';

// 2. Componentes compartidos
import { Button } from '@/shared/components/ui/Button';

// 3. Componentes locales
import { ProjectCard } from './components/ProjectCard';

// 4. Hooks y utils
import { useProjects } from './hooks/useProjects';

// 5. Types
import { Project } from './types/project.types';
```

### Estilos con Tailwind
Los estilos se aplican directamente con clases de Tailwind en el JSX:
```typescript
<div className="flex flex-col gap-4 p-6 bg-white rounded-lg shadow-md">
  <h2 className="text-2xl font-bold text-gray-900">Título</h2>
</div>
```

---

## 👨‍💻 Autor

**José Pereyra**  
Backend Developer | Full Stack