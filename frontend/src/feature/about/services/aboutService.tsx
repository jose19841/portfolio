import api from '@/shared/utils/api';
import type { AboutMe } from '../types/about.types';

export const aboutService = {
    getAboutMe: async (): Promise<AboutMe> => {
        const response = await api.get<AboutMe>('/about-me');
        return response.data;
    },
};