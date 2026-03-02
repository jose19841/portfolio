# Guía de Setup - Frontend Portfolio

## 🛠️ Requisitos Previos

### Software Necesario:
- **Node.js 18+** (incluye npm)
- **Git**
- **Editor de código:** VS Code (recomendado)

### Verificar instalación:
```bash
node -v
npm -v
git --version
```

---

## 📦 Instalación Paso a Paso

### **1. Clonar el Repositorio**
```bash
git clone https://github.com/jose19841/portfolio.git
cd portfolio/frontend
```

---

### **2. Instalar Dependencias**
```bash
npm install
```

Esto instalará:
- React 18+
- TypeScript
- Vite
- React Router DOM
- Tailwind CSS
- Axios
- Framer Motion

---

### **3. Configurar Variables de Entorno**

Crear archivo `.env` en la raíz de `/frontend`:
```env
VITE_API_URL=http://localhost:8080/api
```

**Para producción (Vercel):**
```env
VITE_API_URL=https://tu-api.railway.app/api
```

---

### **4. Iniciar en Modo Desarrollo**
```bash
npm run dev
```

La aplicación estará disponible en: `http://localhost:5173`

---

## 🏗️ Creación del Proyecto desde Cero

Si necesitás recrear el proyecto:

### **1. Crear proyecto con Vite**
```bash
npm create vite@latest frontend -- --template react-ts
cd frontend
npm install
```

---

### **2. Instalar Dependencias**
```bash
# Routing
npm install react-router-dom

# HTTP Client
npm install axios

# Animaciones
npm install framer-motion

# Tailwind CSS
npm install -D tailwindcss postcss autoprefixer
npx tailwindcss init -p
```

---

### **3. Configurar Tailwind CSS**

**tailwind.config.js:**
```javascript
/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        primary: '#2563eb',
        secondary: '#475569',
      },
    },
  },
  plugins: [],
}
```

**src/index.css:**
```css
@tailwind base;
@tailwind components;
@tailwind utilities;
```

---

### **4. Configurar Path Aliases**

**vite.config.ts:**
```typescript
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'path'

export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src'),
    },
  },
})
```

**tsconfig.json:**
```json
{
  "compilerOptions": {
    "target": "ES2020",
    "useDefineForClassFields": true,
    "lib": ["ES2020", "DOM", "DOM.Iterable"],
    "module": "ESNext",
    "skipLibCheck": true,
    "moduleResolution": "bundler",
    "allowImportingTsExtensions": true,
    "resolveJsonModule": true,
    "isolatedModules": true,
    "noEmit": true,
    "jsx": "react-jsx",
    "strict": true,
    "noUnusedLocals": true,
    "noUnusedParameters": true,
    "noFallthroughCasesInSwitch": true,
    "baseUrl": ".",
    "paths": {
      "@/*": ["./src/*"]
    }
  },
  "include": ["src"],
  "references": [{ "path": "./tsconfig.node.json" }]
}
```

---

### **5. Crear Estructura de Carpetas**
```bash
mkdir -p src/features/{home,about,projects,techstack,contact}
mkdir -p src/shared/{components/ui,components/layout,hooks,utils,types}
mkdir -p src/context
mkdir -p src/routes
mkdir -p src/layouts
```

---

## 📜 Scripts Disponibles

### **Desarrollo:**
```bash
npm run dev          # Iniciar servidor de desarrollo
```

### **Build:**
```bash
npm run build        # Build para producción
npm run preview      # Preview del build
```

### **Linting:**
```bash
npm run lint         # Ejecutar ESLint
```

### **Type Check:**
```bash
npx tsc --noEmit     # Verificar tipos TypeScript
```

---

## 🧪 Testing (Opcional)

### **Instalar Vitest y Testing Library:**
```bash
npm install -D vitest @testing-library/react @testing-library/jest-dom jsdom
```

**vite.config.ts (agregar):**
```typescript
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  test: {
    globals: true,
    environment: 'jsdom',
    setupFiles: './src/test/setup.ts',
  },
})
```

**src/test/setup.ts:**
```typescript
import '@testing-library/jest-dom'
```

**package.json (agregar script):**
```json
{
  "scripts": {
    "test": "vitest",
    "test:ui": "vitest --ui",
    "test:coverage": "vitest --coverage"
  }
}
```

---

## 🐛 Troubleshooting

### **Error: "Cannot find module '@/...'"**

**Solución:**
- Verificar que `vite.config.ts` y `tsconfig.json` tengan configurado el alias `@`
- Reiniciar el servidor de desarrollo

---

### **Error: "Tailwind classes not working"**

**Solución:**
```bash
# Verificar que tailwind.config.js tenga el content correcto
# Verificar que index.css importe las directivas de Tailwind
# Reiniciar servidor
npm run dev
```

---

### **Error: "CORS policy blocking API calls"**

**Solución:**
- Verificar que el backend tenga CORS configurado
- Verificar que `VITE_API_URL` en `.env` sea correcta
- Verificar que el backend esté corriendo

---

### **Error: "Port 5173 already in use"**

**Solución:**
```bash
# Matar el proceso
lsof -i :5173
kill -9 <PID>

# O cambiar puerto en vite.config.ts
export default defineConfig({
  server: {
    port: 3000
  }
})
```

---

## 🚀 Build y Deploy

### **Build para Producción:**
```bash
npm run build
```

Esto genera la carpeta `dist/` con los archivos optimizados.

---

### **Deploy en Vercel:**

**Opción 1: CLI**
```bash
npm install -g vercel
vercel
```

**Opción 2: GitHub Integration**
1. Conectar repo en vercel.com
2. Configurar:
   - **Framework Preset:** Vite
   - **Root Directory:** `frontend`
   - **Build Command:** `npm run build`
   - **Output Directory:** `dist`
3. Agregar variable de entorno:
   - `VITE_API_URL` = URL de tu backend en Railway

---

### **Deploy en Netlify:**

**netlify.toml:**
```toml
[build]
  base = "frontend"
  command = "npm run build"
  publish = "dist"

[[redirects]]
  from = "/*"
  to = "/index.html"
  status = 200
```

---

## 🔧 Configuración de VS Code

### **Extensiones Recomendadas:**
```json
{
  "recommendations": [
    "dbaeumer.vscode-eslint",
    "esbenp.prettier-vscode",
    "bradlc.vscode-tailwindcss",
    "dsznajder.es7-react-js-snippets"
  ]
}
```

### **Settings (opcional):**

**.vscode/settings.json:**
```json
{
  "editor.formatOnSave": true,
  "editor.codeActionsOnSave": {
    "source.fixAll.eslint": true
  },
  "typescript.tsdk": "node_modules/typescript/lib"
}
```

---

## 📊 Estructura del Proyecto Completo
```
frontend/
├── public/
│   └── assets/
│       └── images/
├── src/
│   ├── features/
│   │   ├── home/
│   │   ├── about/
│   │   ├── projects/
│   │   ├── techstack/
│   │   └── contact/
│   ├── shared/
│   │   ├── components/
│   │   ├── hooks/
│   │   ├── types/
│   │   └── utils/
│   ├── context/
│   ├── routes/
│   ├── layouts/
│   ├── App.tsx
│   ├── main.tsx
│   └── index.css
├── .env
├── .gitignore
├── index.html
├── package.json
├── tsconfig.json
├── vite.config.ts
└── tailwind.config.js
```

---

## ✅ Checklist de Setup Completo

- [ ] Node.js 18+ instalado
- [ ] Dependencias instaladas (`npm install`)
- [ ] `.env` configurado con `VITE_API_URL`
- [ ] Tailwind CSS configurado
- [ ] Path aliases funcionando (`@/...`)
- [ ] Servidor de desarrollo corriendo (`npm run dev`)
- [ ] Backend API corriendo y accesible
- [ ] No hay errores de CORS
- [ ] Hot reload funcionando

---

## 📚 Recursos Adicionales

- [Vite Documentation](https://vitejs.dev/)
- [React Documentation](https://react.dev/)
- [TypeScript Documentation](https://www.typescriptlang.org/docs/)
- [Tailwind CSS Documentation](https://tailwindcss.com/docs)
- [React Router Documentation](https://reactrouter.com/)
- [Framer Motion Documentation](https://www.framer.com/motion/)