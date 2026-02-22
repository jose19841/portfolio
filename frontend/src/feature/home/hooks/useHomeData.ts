import { useState, useEffect } from "react";
import axios from "axios";
import { homeService } from "../services/homeService";
import type { HomeInfo } from "../types/home.Types";

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
                const message = axios.isAxiosError(err) && err.response?.data?.message
                    ? err.response.data.message
                    : 'Error al cargar la información de la página principal';
                setError(message);
            } finally {
                setLoading(false);
            }
        };
        fetchData();
    }, []);

    return { data, loading, error };
};

