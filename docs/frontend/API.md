# Integración con API - Frontend Portfolio

## 🌐 Configuración de API

### **Configuración Base**

**Ubicación:** `src/shared/utils/api.ts`
```typescript
import axios from 'axios';

const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Interceptor para logging (desarrollo)
api.interceptors.request.use(
  (config) => {
    console.log(`[API Request] ${config.method?.toUpperCase()} ${config.url}`);
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Interceptor para manejo de errores
api.interceptors.response.use(
  (response) => {
    console.log(`[API Response] ${response.status} ${response.config.url}`);
    return response;
  },
  (error) => {
    console.error('[API Error]', error.response?.data || error.message);
    return Promise.reject(error);
  }
);

export default api;
```

---

## 📦 Services por Feature

### **1. Home Service**

**Ubicación:** `src/features/home/services/homeService.ts`
```typescript
import api from '@/shared/utils/api';
import { HomeInfo } from '../types/home.types';

export const homeService = {
  getHomeInfo: async (): Promise<HomeInfo> => {
    const response = await api.get<HomeInfo>('/home');
    return response.data;
  }
};
```

**Types:**
```typescript
// src/features/home/types/home.types.ts
export interface HomeInfo {
  fullName: string;
  title: string;
  tagline: string;
  email: string;
  githubUrl: string;
  linkedinUrl?: string;
}
```

**Hook:**
```typescript
// src/features/home/hooks/useHomeData.ts
import { useState, useEffect } from 'react';
import { homeService } from '../services/homeService';
import { HomeInfo } from '../types/home.types';

export const useHomeData = () => {
  const [data, setData] = useState<HomeInfo | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const homeInfo = await homeService.getHomeInfo();
        setData(homeInfo);
      } catch (err) {
        setError('Failed to load home information');
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  return { data, loading, error };
};
```

---

### **2. About Service**

**Ubicación:** `src/features/about/services/aboutService.ts`
```typescript
import api from '@/shared/utils/api';
import { AboutMe } from '../types/about.types';

export const aboutService = {
  getAboutMe: async (): Promise<AboutMe> => {
    const response = await api.get<AboutMe>('/about-me');
    return response.data;
  }
};
```

**Types:**
```typescript
// src/features/about/types/about.types.ts
export interface AboutMe {
  biography: string;
  professionalTitle: string;
  education?: string;
  profileImageUrl?: string;
}
```

---

### **3. Projects Service**

**Ubicación:** `src/features/projects/services/projectService.ts`
```typescript
import api from '@/shared/utils/api';
import { Project } from '../types/project.types';

export const projectService = {
  getAllProjects: async (featured?: boolean): Promise<Project[]> => {
    const params = featured ? { featured: true } : {};
    const response = await api.get<Project[]>('/projects', { params });
    return response.data;
  },

  getProjectById: async (id: number): Promise<Project> => {
    const response = await api.get<Project>(`/projects/${id}`);
    return response.data;
  }
};
```

**Types:**
```typescript
// src/features/projects/types/project.types.ts
export interface Technology {
  id: number;
  name: string;
  category: string;
}

export interface Project {
  id: number;
  name: string;
  description: string;
  githubUrl: string;
  demoUrl?: string;
  imageUrl?: string;
  isFullstack: boolean;
  isFeatured: boolean;
  technologies: Technology[];
}
```

**Hook:**
```typescript
// src/features/projects/hooks/useProjects.ts
import { useState, useEffect } from 'react';
import { projectService } from '../services/projectService';
import { Project } from '../types/project.types';

export const useProjects = (featured?: boolean) => {
  const [projects, setProjects] = useState<Project[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchProjects = async () => {
      try {
        const data = await projectService.getAllProjects(featured);
        setProjects(data);
      } catch (err) {
        setError('Failed to load projects');
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchProjects();
  }, [featured]);

  return { projects, loading, error };
};
```

---

### **4. Tech Stack Service**

**Ubicación:** `src/features/techstack/services/techStackService.ts`
```typescript
import api from '@/shared/utils/api';
import { TechStackByCategory } from '../types/techstack.types';

export const techStackService = {
  getTechStack: async (): Promise<TechStackByCategory> => {
    const response = await api.get<TechStackByCategory>('/tech-stack');
    return response.data;
  }
};
```

**Types:**
```typescript
// src/features/techstack/types/techstack.types.ts
export interface TechItem {
  id: number;
  name: string;
  proficiencyLevel: 'Beginner' | 'Intermediate' | 'Advanced' | 'Expert';
  iconUrl?: string;
}

export interface TechStackByCategory {
  Backend: TechItem[];
  Frontend: TechItem[];
  Database: TechItem[];
  Tools: TechItem[];
}
```

---

### **5. Contact Service**

**Ubicación:** `src/features/contact/services/contactService.ts`
```typescript
import api from '@/shared/utils/api';
import { ContactRequest, ContactResponse } from '../types/contact.types';

export const contactService = {
  sendMessage: async (data: ContactRequest): Promise<ContactResponse> => {
    const response = await api.post<ContactResponse>('/contact', data);
    return response.data;
  }
};
```

**Types:**
```typescript
// src/features/contact/types/contact.types.ts
export interface ContactRequest {
  name: string;
  email: string;
  subject?: string;
  message: string;
}

export interface ContactResponse {
  id: number;
  name: string;
  email: string;
  subject?: string;
  message: string;
  createdAt: string;
}
```

**Hook:**
```typescript
// src/features/contact/hooks/useContactForm.ts
import { useState } from 'react';
import { contactService } from '../services/contactService';
import { ContactRequest } from '../types/contact.types';

export const useContactForm = () => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [success, setSuccess] = useState(false);

  const submitForm = async (data: ContactRequest) => {
    setLoading(true);
    setError(null);
    setSuccess(false);

    try {
      await contactService.sendMessage(data);
      setSuccess(true);
    } catch (err: any) {
      setError(err.response?.data?.message || 'Failed to send message');
    } finally {
      setLoading(false);
    }
  };

  const reset = () => {
    setError(null);
    setSuccess(false);
  };

  return { submitForm, loading, error, success, reset };
};
```

---

## 🔄 Hook Genérico para API

**Ubicación:** `src/shared/hooks/useApi.ts`
```typescript
import { useState, useEffect } from 'react';
import axios, { AxiosError } from 'axios';

interface UseApiOptions {
  skip?: boolean;
}

export const useApi = <T>(
  fetcher: () => Promise<T>,
  options?: UseApiOptions
) => {
  const [data, setData] = useState<T | null>(null);
  const [loading, setLoading] = useState(!options?.skip);
  const [error, setError] = useState<string | null>(null);

  const refetch = async () => {
    setLoading(true);
    setError(null);
    
    try {
      const result = await fetcher();
      setData(result);
    } catch (err) {
      const axiosError = err as AxiosError;
      setError(axiosError.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (!options?.skip) {
      refetch();
    }
  }, [options?.skip]);

  return { data, loading, error, refetch };
};
```

**Uso:**
```typescript
import { useApi } from '@/shared/hooks/useApi';
import { projectService } from '../services/projectService';

// En tu componente
const { data: projects, loading, error } = useApi(() => 
  projectService.getAllProjects()
);
```

---

## ⚠️ Manejo de Errores

### **Error Types**
```typescript
// src/shared/types/api.types.ts
export interface ApiError {
  timestamp: string;
  status: number;
  error: string;
  message: string;
  errors?: ValidationError[];
}

export interface ValidationError {
  field: string;
  message: string;
}
```

### **Error Handler Utility**
```typescript
// src/shared/utils/errorHandler.ts
import { AxiosError } from 'axios';
import { ApiError } from '../types/api.types';

export const handleApiError = (error: unknown): string => {
  if (axios.isAxiosError(error)) {
    const axiosError = error as AxiosError<ApiError>;
    
    if (axiosError.response) {
      // Server responded with error
      const apiError = axiosError.response.data;
      
      if (apiError.errors && apiError.errors.length > 0) {
        // Validation errors
        return apiError.errors.map(e => e.message).join(', ');
      }
      
      return apiError.message || 'Server error occurred';
    }
    
    if (axiosError.request) {
      // Request made but no response
      return 'No response from server. Please check your connection.';
    }
  }
  
  return 'An unexpected error occurred';
};
```

**Uso:**
```typescript
try {
  await contactService.sendMessage(formData);
} catch (err) {
  const errorMessage = handleApiError(err);
  setError(errorMessage);
}
```

---

## 🔒 Variables de Entorno

**Archivo:** `.env`
```env
# Development
VITE_API_URL=http://localhost:8080/api

# Production (Vercel)
# VITE_API_URL=https://tu-api.railway.app/api
```

**Acceso:**
```typescript
const API_URL = import.meta.env.VITE_API_URL;
```

---

## 🧪 Testing API Calls

### **Mock de axios**
```typescript
// src/features/projects/__tests__/projectService.test.ts
import { projectService } from '../services/projectService';
import api from '@/shared/utils/api';

jest.mock('@/shared/utils/api');
const mockedApi = api as jest.Mocked<typeof api>;

describe('projectService', () => {
  it('should fetch all projects', async () => {
    const mockProjects = [
      { id: 1, name: 'QRest', description: '...' }
    ];
    
    mockedApi.get.mockResolvedValueOnce({ data: mockProjects });
    
    const projects = await projectService.getAllProjects();
    
    expect(projects).toEqual(mockProjects);
    expect(mockedApi.get).toHaveBeenCalledWith('/projects', { params: {} });
  });
});
```

---

## 📊 Flujo de Datos
```
Component
    ↓
Custom Hook (useProjects)
    ↓
Service (projectService)
    ↓
Axios Instance (api)
    ↓
HTTP Request
    ↓
Backend API
    ↓
Response
    ↓
Update State
    ↓
Re-render
```

---

## ✅ Best Practices

1. **Separar lógica:** Services para API, hooks para state management
2. **Type Safety:** Definir interfaces para requests y responses
3. **Error Handling:** Capturar y mostrar errores apropiadamente
4. **Loading States:** Mostrar spinners durante requests
5. **Retry Logic:** Considerar reintentos en caso de fallos de red
6. **Caching:** Usar React Query o SWR para caching avanzado (opcional)