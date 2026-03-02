import { motion } from 'framer-motion';
import type { ReactNode } from 'react';

interface AnimatedSectionProps {
    children: ReactNode;
    delay?: number;
    className?: string;
}

export const AnimatedSection: React.FC<AnimatedSectionProps> = ({ children, delay = 0.1, className = '' }) => {
    return (
        <motion.div
        initial={{ opacity: 0, y: 30 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.6, delay }}
        className={className}
        >
            {children}
        </motion.div>
    );
};