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