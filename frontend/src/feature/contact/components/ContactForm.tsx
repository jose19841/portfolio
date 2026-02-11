import { useState } from "react";
import { useContactForm } from "../hooks/useContactForm";
import type { ContactRequest } from "../types/contact.types";

export const ContactForm = () => {
    const { loading, success, error, sendMessage } = useContactForm();

    const [formData, setFormData] = useState<ContactRequest>({
        name: '',
        email: '',
        subject: '',
        message: '',
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = async(e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        try {
            await sendMessage(formData);
            setFormData({name: '', email: '', subject: '', message: ''});
        } catch {
            // Error Manejado en el Hook
        }
    };

    return (
        <div className="max-w-2xl mx-auto p-6">
            <h2 className="text-3xl font-bold mb-6">Contacto</h2>
            {success && (
                <div className="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-4">
                    !Mensaje enviado exitosamente! Te respondere pronto.
        </div>
    )}

    {error && (
        <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
            {error}
        </div>
    )}

    <form onSubmit={handleSubmit} className="space-y-4">
        <div>
            <label htmlFor="name" className="block text-sm font-medium mb-1">Nombre *</label>
            <input
            type="text"
            id="name"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
            className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
        </div>
    
        <div>
            <label htmlFor="email" className="block text-sm font-medium mb-1">Email *

            </label>
            <input
            type="email"
            id="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
            className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            />
            </div>

            <div>
                <label htmlFor="subject" className="block text-sm font-medium mb-1">
                Asunto
                </label>
                <input
                type="text"
                id="subject"
                name="subject"
                value={formData.subject || ''}
                onChange={handleChange}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
            </div>

            <div>
                <label htmlFor="message" className="block text-sm font-medium mb-1">
                Mensaje *
                </label>
                <textarea
                id="message"
                name="message"
                value={formData.message}
                onChange={handleChange}
                required
                rows={5}
                className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
            </div>

            <button
            type="submit"
            disabled={loading}
            className="w-full bg-blue-600 text-white py-3  rounded-lg font-semibold hover:bg-blue-700 disabled:opacity-50 disabled:bg-gray-400 disabled:cursor-not-allowed"
            >
                {loading ? 'Enviando...' : 'Enviar Mensaje'}
            </button>
    </form>
    </div>
    );  
};
