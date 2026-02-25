import api from '@/shared/utils/api';
import type { HomeInfo } from "../types/home.Types";

export const homeService = {
    getHomeInfo: async (): Promise<HomeInfo> => {
        const response = await api.get<HomeInfo>('/home');
        return response.data;
    },
};

export default homeService;