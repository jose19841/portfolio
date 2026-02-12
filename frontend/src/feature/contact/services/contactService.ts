import api from "../../../shared/utils/api";
import type { ContactRequest, ContactResponse } from "../types/contact.types";   

export const contactService = {
    sendMessage: async (data: ContactRequest): Promise<ContactResponse> => {
        const response = await api.post<ContactResponse>('/contact', data);
        return response.data;
    },
};