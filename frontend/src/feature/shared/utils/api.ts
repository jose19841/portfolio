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
    api.interceptors.response.use(
        (response) => response,
        (error) => {
            return Promise.reject(error);
        }
    );

export default api;