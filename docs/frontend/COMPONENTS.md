# Componentes Compartidos - Frontend Portfolio

## 🎨 Librería de Componentes UI

Componentes reutilizables construidos con **React + TypeScript + Tailwind CSS**.

---

## 📦 UI Components

### **Button**

Botón reutilizable con múltiples variantes.

**Ubicación:** `src/shared/components/ui/Button.tsx`

**Props:**
```typescript
interface ButtonProps {
  variant?: 'primary' | 'secondary' | 'ghost' | 'outline';
  size?: 'sm' | 'md' | 'lg';
  fullWidth?: boolean;
  disabled?: boolean;
  loading?: boolean;
  children: React.ReactNode;
  onClick?: () => void;
  type?: 'button' | 'submit' | 'reset';
}
```

**Uso:**
```tsx
import { Button } from '@/shared/components/ui/Button';

<Button variant="primary" size="md" onClick={handleClick}>
  Click Me
</Button>

<Button variant="outline" fullWidth>
  Full Width Button
</Button>

<Button loading>
  Loading...
</Button>
```

**Implementación:**
```tsx
export const Button: React.FC<ButtonProps> = ({
  variant = 'primary',
  size = 'md',
  fullWidth = false,
  disabled = false,
  loading = false,
  children,
  onClick,
  type = 'button'
}) => {
  const baseClasses = 'font-semibold rounded-lg transition-colors duration-200 disabled:opacity-50 disabled:cursor-not-allowed';
  
  const variantClasses = {
    primary: 'bg-blue-600 text-white hover:bg-blue-700',
    secondary: 'bg-gray-600 text-white hover:bg-gray-700',
    ghost: 'bg-transparent hover:bg-gray-100 text-gray-900',
    outline: 'border-2 border-blue-600 text-blue-600 hover:bg-blue-50'
  };
  
  const sizeClasses = {
    sm: 'px-3 py-1.5 text-sm',
    md: 'px-4 py-2 text-base',
    lg: 'px-6 py-3 text-lg'
  };
  
  const widthClass = fullWidth ? 'w-full' : '';
  
  return (
    <button
      type={type}
      className={`${baseClasses} ${variantClasses[variant]} ${sizeClasses[size]} ${widthClass}`}
      onClick={onClick}
      disabled={disabled || loading}
    >
      {loading ? (
        <span className="flex items-center justify-center">
          <Spinner size="sm" />
          <span className="ml-2">Loading...</span>
        </span>
      ) : (
        children
      )}
    </button>
  );
};
```

---

### **Input**

Campo de entrada de texto.

**Ubicación:** `src/shared/components/ui/Input.tsx`

**Props:**
```typescript
interface InputProps {
  type?: 'text' | 'email' | 'password' | 'number';
  placeholder?: string;
  value: string;
  onChange: (value: string) => void;
  error?: string;
  disabled?: boolean;
  required?: boolean;
  label?: string;
}
```

**Uso:**
```tsx
import { Input } from '@/shared/components/ui/Input';

<Input
  label="Email"
  type="email"
  placeholder="tu@email.com"
  value={email}
  onChange={setEmail}
  error={emailError}
  required
/>
```

**Implementación:**
```tsx
export const Input: React.FC<InputProps> = ({
  type = 'text',
  placeholder,
  value,
  onChange,
  error,
  disabled = false,
  required = false,
  label
}) => {
  return (
    <div className="flex flex-col gap-1">
      {label && (
        <label className="text-sm font-medium text-gray-700">
          {label}
          {required && <span className="text-red-500 ml-1">*</span>}
        </label>
      )}
      <input
        type={type}
        placeholder={placeholder}
        value={value}
        onChange={(e) => onChange(e.target.value)}
        disabled={disabled}
        required={required}
        className={`
          px-4 py-2 border rounded-lg focus:outline-none focus:ring-2 
          ${error 
            ? 'border-red-500 focus:ring-red-500' 
            : 'border-gray-300 focus:ring-blue-500'
          }
          ${disabled ? 'bg-gray-100 cursor-not-allowed' : ''}
        `}
      />
      {error && (
        <span className="text-sm text-red-500">{error}</span>
      )}
    </div>
  );
};
```

---

### **Card**

Contenedor con estilo de tarjeta.

**Ubicación:** `src/shared/components/ui/Card.tsx`

**Props:**
```typescript
interface CardProps {
  children: React.ReactNode;
  padding?: 'sm' | 'md' | 'lg';
  shadow?: 'sm' | 'md' | 'lg';
  hoverable?: boolean;
}
```

**Uso:**
```tsx
import { Card } from '@/shared/components/ui/Card';

<Card padding="lg" shadow="md" hoverable>
  <h3>Card Title</h3>
  <p>Card content goes here</p>
</Card>
```

**Implementación:**
```tsx
export const Card: React.FC<CardProps> = ({
  children,
  padding = 'md',
  shadow = 'md',
  hoverable = false
}) => {
  const paddingClasses = {
    sm: 'p-3',
    md: 'p-6',
    lg: 'p-8'
  };
  
  const shadowClasses = {
    sm: 'shadow-sm',
    md: 'shadow-md',
    lg: 'shadow-lg'
  };
  
  const hoverClass = hoverable ? 'hover:shadow-xl transition-shadow duration-300' : '';
  
  return (
    <div className={`bg-white rounded-lg ${paddingClasses[padding]} ${shadowClasses[shadow]} ${hoverClass}`}>
      {children}
    </div>
  );
};
```

---

### **Spinner**

Indicador de carga.

**Ubicación:** `src/shared/components/ui/Spinner.tsx`

**Props:**
```typescript
interface SpinnerProps {
  size?: 'sm' | 'md' | 'lg';
  color?: 'primary' | 'white';
}
```

**Uso:**
```tsx
import { Spinner } from '@/shared/components/ui/Spinner';

<Spinner size="md" color="primary" />
```

**Implementación:**
```tsx
export const Spinner: React.FC<SpinnerProps> = ({
  size = 'md',
  color = 'primary'
}) => {
  const sizeClasses = {
    sm: 'w-4 h-4',
    md: 'w-8 h-8',
    lg: 'w-12 h-12'
  };
  
  const colorClasses = {
    primary: 'border-blue-600',
    white: 'border-white'
  };
  
  return (
    <div className={`${sizeClasses[size]} border-4 ${colorClasses[color]} border-t-transparent rounded-full animate-spin`} />
  );
};
```

---

### **Modal**

Ventana modal.

**Ubicación:** `src/shared/components/ui/Modal.tsx`

**Props:**
```typescript
interface ModalProps {
  isOpen: boolean;
  onClose: () => void;
  title?: string;
  children: React.ReactNode;
  size?: 'sm' | 'md' | 'lg';
}
```

**Uso:**
```tsx
import { Modal } from '@/shared/components/ui/Modal';

<Modal 
  isOpen={isOpen} 
  onClose={handleClose}
  title="Project Details"
  size="lg"
>
  <p>Modal content</p>
</Modal>
```

**Implementación:**
```tsx
export const Modal: React.FC<ModalProps> = ({
  isOpen,
  onClose,
  title,
  children,
  size = 'md'
}) => {
  if (!isOpen) return null;
  
  const sizeClasses = {
    sm: 'max-w-md',
    md: 'max-w-2xl',
    lg: 'max-w-4xl'
  };
  
  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center">
      {/* Backdrop */}
      <div 
        className="fixed inset-0 bg-black bg-opacity-50"
        onClick={onClose}
      />
      
      {/* Modal */}
      <div className={`relative bg-white rounded-lg shadow-xl ${sizeClasses[size]} w-full mx-4 p-6`}>
        {/* Header */}
        {title && (
          <div className="flex items-center justify-between mb-4">
            <h3 className="text-xl font-semibold">{title}</h3>
            <button 
              onClick={onClose}
              className="text-gray-500 hover:text-gray-700"
            >
              ✕
            </button>
          </div>
        )}
        
        {/* Content */}
        <div>
          {children}
        </div>
      </div>
    </div>
  );
};
```

---

## 🧭 Layout Components

### **Header**

Cabecera con navegación.

**Ubicación:** `src/shared/components/layout/Header.tsx`

**Implementación:**
```tsx
import { Link } from 'react-router-dom';
import { Navbar } from './Navbar';

export const Header = () => {
  return (
    <header className="bg-white shadow-sm sticky top-0 z-40">
      <div className="container mx-auto px-4 py-4 flex items-center justify-between">
        <Link to="/" className="text-2xl font-bold text-blue-600">
          JP
        </Link>
        <Navbar />
      </div>
    </header>
  );
};
```

---

### **Navbar**

Barra de navegación.

**Ubicación:** `src/shared/components/layout/Navbar.tsx`

**Implementación:**
```tsx
import { NavLink } from 'react-router-dom';

export const Navbar = () => {
  const links = [
    { path: '/', label: 'Home' },
    { path: '/about', label: 'About' },
    { path: '/projects', label: 'Projects' },
    { path: '/tech-stack', label: 'Tech Stack' },
    { path: '/contact', label: 'Contact' }
  ];
  
  return (
    <nav className="flex gap-6">
      {links.map((link) => (
        <NavLink
          key={link.path}
          to={link.path}
          className={({ isActive }) => 
            `font-medium transition-colors ${
              isActive 
                ? 'text-blue-600' 
                : 'text-gray-600 hover:text-blue-600'
            }`
          }
        >
          {link.label}
        </NavLink>
      ))}
    </nav>
  );
};
```

---

### **Footer**

Pie de página.

**Ubicación:** `src/shared/components/layout/Footer.tsx`

**Implementación:**
```tsx
export const Footer = () => {
  return (
    <footer className="bg-gray-900 text-white py-8">
      <div className="container mx-auto px-4 text-center">
        <p className="text-gray-400">
          © {new Date().getFullYear()} José Pereyra. All rights reserved.
        </p>
        <div className="flex justify-center gap-4 mt-4">
          <a 
            href="https://github.com/jose19841" 
            target="_blank" 
            rel="noopener noreferrer"
            className="hover:text-blue-400 transition-colors"
          >
            GitHub
          </a>
          <a 
            href="mailto:jlpereyra2310@gmail.com"
            className="hover:text-blue-400 transition-colors"
          >
            Email
          </a>
        </div>
      </div>
    </footer>
  );
};
```

---

## 🛡️ Error Handling

### **ErrorBoundary**

Captura errores de React.

**Ubicación:** `src/shared/components/ErrorBoundary.tsx`

**Implementación:**
```tsx
import React, { Component, ErrorInfo, ReactNode } from 'react';

interface Props {
  children: ReactNode;
}

interface State {
  hasError: boolean;
  error?: Error;
}

export class ErrorBoundary extends Component<Props, State> {
  constructor(props: Props) {
    super(props);
    this.state = { hasError: false };
  }

  static getDerivedStateFromError(error: Error): State {
    return { hasError: true, error };
  }

  componentDidCatch(error: Error, errorInfo: ErrorInfo) {
    console.error('Error caught by boundary:', error, errorInfo);
  }

  render() {
    if (this.state.hasError) {
      return (
        <div className="min-h-screen flex items-center justify-center bg-gray-100">
          <div className="text-center">
            <h1 className="text-4xl font-bold text-gray-900 mb-4">
              Oops! Something went wrong
            </h1>
            <p className="text-gray-600 mb-6">
              {this.state.error?.message || 'An unexpected error occurred'}
            </p>
            <button
              onClick={() => window.location.reload()}
              className="px-6 py-3 bg-blue-600 text-white rounded-lg hover:bg-blue-700"
            >
              Reload Page
            </button>
          </div>
        </div>
      );
    }

    return this.props.children;
  }
}
```

---

## 📋 Component Index

Export barrel para fácil importación.

**Ubicación:** `src/shared/components/ui/index.ts`
```typescript
export { Button } from './Button';
export { Input } from './Input';
export { Card } from './Card';
export { Spinner } from './Spinner';
export { Modal } from './Modal';
```

**Uso:**
```tsx
import { Button, Input, Card } from '@/shared/components/ui';
```

---

## 🎨 Tailwind Configuration

Las clases de Tailwind se aplican directamente en JSX. Para customización, editar `tailwind.config.js`:
```javascript
export default {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    extend: {
      colors: {
        primary: '#2563eb',
        secondary: '#475569',
      },
    },
  },
  plugins: [],
};
```