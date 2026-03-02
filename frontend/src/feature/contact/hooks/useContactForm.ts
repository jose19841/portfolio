    import { useState } from "react";
    import { contactService } from "../services/contactService";
    import type { ContactRequest, ContactResponse } from "../types/contact.types";

    export const useContactForm = () => {
        const [loading, setLoading] = useState(false);
        const [success, setSuccess] = useState<ContactResponse | null>(null);
        const [error, setError] = useState<string | null>(null);

        const sendMessage = async (data: ContactRequest) => {
            setLoading(true);
            setError(null);
            setSuccess(null);

            try {
                const response = await contactService.sendMessage(data);
                setSuccess(response);
                return response;
            } catch (err: any) {
                const message = 
                err?.response?.data?.message ||
                err?.message ||
                'Error al enviar el mensaje';
                setError(message);
                throw err;
            } finally {
                setLoading(false);
            }
        };

        const resetState = () => {
            setLoading(false);
            setError(null);
            setSuccess(null);
        };

        return {
            loading,
            success,
            error,
            sendMessage,
            resetState,
        };
    };