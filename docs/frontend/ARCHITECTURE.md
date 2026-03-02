# Arquitectura Frontend - Portfolio

## 🏗️ Arquitectura por Features

Este frontend está organizado por **features** (módulos funcionales), donde cada feature es una carpeta independiente que contiene todo lo relacionado a esa funcionalidad.

### **Filosofía:**
- Cada feature es autónomo
- Bajo acoplamiento entre features
- Alto cohesión dentro de cada feature
- Código compartido vive en `/shared`

---

## 📐 Estructura General
```
src/
├── features/              # Features del portfolio
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
├── layouts/               # Layouts reutilizables
├── App.tsx                # Componente raíz
├── main.tsx               # Entry point
└── index.css              # Estilos globales (Tailwind)
```

---

## 🎯 Anatomía de un Feature

Cada feature sigue esta estructura:
```
feature/
├── components/            # Componentes específicos del feature
│   ├── FeatureCard.tsx
│   ├── FeatureList.tsx
│   └── FeatureHeader.tsx
├── hooks/                 # Custom hooks
│   ├── useFeatureData.ts
│   └── useFeatureForm.ts
├── services/              # Llamadas a API
│   └── featureService.ts
├── types/                 # TypeScript types
│   └── feature.types.ts
└── Feature.tsx            # Página principal (index)
```

---

## 📦 Features en Detalle

### **1. home/**

Hero section y presentación principal.
```
home/
├── components/
│   ├── Hero.tsx           # Sección hero completa
│   ├── HeroContent.tsx    # Contenido del hero
│   └── HeroActions.tsx    # CTAs y botones
├── hooks/
│   └── useHomeData.ts     # Fetch info del backend
├── services/
│   └── homeService.ts     # API calls
└── Home.tsx               # Página principal
```

**Responsabilidad:**
- Mostrar nombre, título, tagline
- Links a GitHub, LinkedIn
- CTA principal

---

### **2. about/**

Información sobre mí / biografía.
```
about/
├── components/
│   ├── AboutContent.tsx   # Biografía y descripción
│   ├── AboutImage.tsx     # Foto profesional
│   └── AboutEducation.tsx # Educación y títulos
├── hooks/
│   └── useAboutData.ts
├── services/
│   └── aboutService.ts
└── About.tsx
```

**Responsabilidad:**
- Biografía profesional
- Educación y títulos
- Foto de perfil

---

### **3. projects/**

Portfolio de proyectos.
```
projects/
├── components/
│   ├── ProjectCard.tsx    # Card individual de proyecto
│   ├── ProjectList.tsx    # Grid de proyectos
│   ├── ProjectFilter.tsx  # Filtros (featured, fullstack)
│   └── ProjectDetail.tsx  # Modal o página de detalle
├── hooks/
│   ├── useProjects.ts     # Fetch proyectos
│   └── useProjectFilter.ts
├── services/
│   └── projectService.ts
├── types/
│   └── project.types.ts
├── Projects.tsx           # Lista de proyectos
└── ProjectDetailPage.tsx  # Detalle de proyecto (opcional)
```

**Responsabilidad:**
- Mostrar grid de proyectos
- Filtrar por featured/fullstack
- Links a GitHub y demos
- Tecnologías por proyecto

---

### **4. techstack/**

Stack tecnológico personal.
```
techstack/
├── components/
│   ├── TechCard.tsx       # Card de tecnología
│   ├── TechGrid.tsx       # Grid por categoría
│   └── TechCategory.tsx   # Sección de categoría
├── hooks/
│   └── useTechStack.ts
├── services/
│   └── techStackService.ts
└── TechStack.tsx
```

**Responsabilidad:**
- Mostrar tecnologías agrupadas por categoría
- Backend, Frontend, Database, Tools
- Nivel de proficiencia (opcional)

---

### **5. contact/**

Formulario de contacto.
```
contact/
├── components/
│   ├── ContactForm.tsx    # Formulario
│   ├── ContactInfo.tsx    # Info de contacto
│   └── ContactSuccess.tsx # Mensaje de éxito
├── hooks/
│   ├── useContactForm.ts  # Manejo del formulario
│   └── useFormValidation.ts
├── services/
│   └── contactService.ts
├── types/
│   └── contact.types.ts
└── Contact.tsx
```

**Responsabilidad:**
- Formulario funcional
- Validación de campos
- Envío a backend
- Feedback al usuario

---

## 🔄 Shared (Código Compartido)

### **shared/components/**

Componentes reutilizables entre features.
```
shared/components/
├── ui/                    # Componentes base
│   ├── Button.tsx
│   ├── Input.tsx
│   ├── Card.tsx
│   ├── Spinner.tsx
│   └── Modal.tsx
├── layout/                # Layout components
│   ├── Header.tsx
│   ├── Footer.tsx
│   ├── Navbar.tsx
│   └── Layout.tsx
└── ErrorBoundary.tsx      # Error handling
```

**Ejemplo: Button.tsx**
```typescript
interface ButtonProps {
  variant?: 'primary' | 'secondary' | 'ghost';
  size?: 'sm' | 'md' | 'lg';
  children: React.ReactNode;
  onClick?: () => void;
}

export const Button: React.FC<ButtonProps> = ({
  variant = 'primary',
  size = 'md',
  children,
  onClick
}) => {
  const baseClasses = 'font-semibold rounded-lg transition';
  const variantClasses = {
    primary: 'bg-blue-600 text-white hover:bg-blue-700',
    secondary: 'bg-gray-600 text-white hover:bg-gray-700',
    ghost: 'bg-transparent hover:bg-gray-100'
  };
  const sizeClasses = {
    sm: 'px-3 py-1.5 text-sm',
    md: 'px-4 py-2 text-base',
    lg: 'px-6 py-3 text-lg'
  };

  return (
    <button
      className={`${baseClasses} ${variantClasses[variant]} ${sizeClasses[size]}`}
      onClick={onClick}
    >
      {children}
    </button>
  );
};
```

---

### **shared/hooks/**

Custom hooks reutilizables.
```
shared/hooks/
├── useApi.ts              # Hook genérico para API calls
├── useScroll.ts           # Detectar scroll
├── useTheme.ts            # Theme switcher
└── useDebounce.ts         # Debounce para inputs
```

**Ejemplo: useApi.ts**
```typescript
export const useApi = <T>(url: string) => {
  const [data, setData] = useState<T | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(url);
        setData(response.data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [url]);

  return { data, loading, error };
};
```

---

### **shared/utils/**

Utilidades y helpers.
```
shared/utils/
├── api.ts                 # Configuración de axios
├── validators.ts          # Validaciones
└── constants.ts           # Constantes globales
```

**Ejemplo: api.ts**
```typescript
import axios from 'axios';

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Interceptor para manejo de errores global
api.interceptors.response.use(
  (response) => response,
  (error) => {
    console.error('API Error:', error);
    return Promise.reject(error);
  }
);

export default api;
```

---

## 🛣️ Routing

### **routes/AppRoutes.tsx**
```typescript
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { Layout } from '@/layouts/Layout';
import { Home } from '@/features/home/Home';
import { About } from '@/features/about/About';
import { Projects } from '@/features/projects/Projects';
import { TechStack } from '@/features/techstack/TechStack';
import { Contact } from '@/features/contact/Contact';

export const AppRoutes = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="about" element={<About />} />
          <Route path="projects" element={<Projects />} />
          <Route path="tech-stack" element={<TechStack />} />
          <Route path="contact" element={<Contact />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
};
```

---

## 🎨 Context API

### **context/ThemeContext.tsx**
```typescript
interface ThemeContextType {
  theme: 'light' | 'dark';
  toggleTheme: () => void;
}

const ThemeContext = createContext<ThemeContextType | undefined>(undefined);

export const ThemeProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [theme, setTheme] = useState<'light' | 'dark'>('light');

  const toggleTheme = () => {
    setTheme(prev => prev === 'light' ? 'dark' : 'light');
  };

  return (
    <ThemeContext.Provider value={{ theme, toggleTheme }}>
      {children}
    </ThemeContext.Provider>
  );
};

export const useTheme = () => {
  const context = useContext(ThemeContext);
  if (!context) throw new Error('useTheme must be used within ThemeProvider');
  return context;
};
```

---

## 📱 Layouts

### **layouts/Layout.tsx**
```typescript
import { Outlet } from 'react-router-dom';
import { Header } from '@/shared/components/layout/Header';
import { Footer } from '@/shared/components/layout/Footer';

export const Layout = () => {
  return (
    <div className="min-h-screen flex flex-col">
      <Header />
      <main className="flex-grow">
        <Outlet />
      </main>
      <Footer />
    </div>
  );
};
```

---

## 🎯 Flujo de Datos
```
User Action
    ↓
Component
    ↓
Custom Hook (useProjects)
    ↓
Service (projectService.ts)
    ↓
API Call (axios)
    ↓
Backend API
    ↓
Response
    ↓
Update State
    ↓
Re-render Component
```

---

## 🧪 Testing Strategy

### **Unit Tests:**
- Componentes individuales
- Custom hooks
- Utilidades

### **Integration Tests:**
- Features completos
- Flujo de usuario

### **E2E Tests (opcional):**
- Cypress / Playwright

---

## 📊 Diagrama de Arquitectura
```
┌─────────────────────────────────────┐
│            App.tsx                  │
│    (ThemeProvider, Router)          │
└────────────┬────────────────────────┘
             │
    ┌────────┴────────┐
    │     Layout      │
    │ (Header/Footer) │
    └────────┬────────┘
             │
    ┌────────┴────────────────┐
    │       Features          │
    ├─────────────────────────┤
    │ home     │  about       │
    │ projects │  techstack   │
    │ contact  │              │
    └──────────┴──────────────┘
             │
    ┌────────┴────────┐
    │     Shared      │
    ├─────────────────┤
    │ components      │
    │ hooks           │
    │ utils           │
    └─────────────────┘
```

---

## 🚀 Principios de Diseño

1. **Separation of Concerns:** Cada feature es independiente
2. **DRY (Don't Repeat Yourself):** Código compartido en `/shared`
3. **Single Responsibility:** Cada componente una responsabilidad
4. **Composición:** Componentes pequeños y componibles
5. **Type Safety:** TypeScript en todo el proyecto