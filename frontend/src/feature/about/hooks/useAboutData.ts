import { useState, useEffect } from 'react';
import { aboutService } from '../services/aboutService';
import type { AboutMe } from '../types/about.types';

export const useAboutData = () => {
    const [data, setData] = useState<AboutMe | null>(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const aboutMe = await aboutService.getAboutMe();
                setData(aboutMe);
            } catch {
                setError('Error al cargar la información de About Me');
            } finally {
                setLoading(false);
            }
        };

        fetchData();
    }, []);

    return { data, loading, error };
};