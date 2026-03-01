import { Link } from "react-router-dom";
import { useHomeData } from "../hooks/useHomeData";
import { AnimatedSection } from "@/shared/components/ui/AnimatedSection";
import { motion } from "framer-motion";

export const Home = () => {
  const { data, loading, error } = useHomeData();

  if (loading) {
    return (
      <div className="container mx-auto px-4 py-16 text-center">
        <div className="w-12 h-12 border-4 border-blue-600 border-t-transparent rounded-full animate-spin mx-auto"></div>
      </div>
    );
  }

  if (error) return <div className="container mx-auto px-4 py-16 text-center text-red-600">
        {error}
      </div>

  if (!data) return null;

  return (
    <div className="min-h-[calc(100vh-200px)] flex items-center bg-gradient-to-br from-blue-50 via-purple-50 to-pink-50">
      <div className="max-w-4xl mx-auto px-4 py-16">
        <div className="max-w w-4xl mx-auto text-center">
          <AnimatedSection delay={0}>
            <h1 className="text-6xl font-bold mb-6 bg-gradient-to-r from-blue-600 to-purple-600 bg-clip-text text-transparent">
              {data.fullName}
              </h1>
          </AnimatedSection>

          <AnimatedSection delay={0.2}>
            <p className="text-3xl text-gray-700 mb-4 font-semibold">
              {data.title}
              </p>
          </AnimatedSection>

          <AnimatedSection delay={0.4}>
            <p className="text-xl text-gray-600 mb-12 max-w-2xl mx-auto">
              {data.tagline}
              </p>
          </AnimatedSection>

          <AnimatedSection delay={0.6} className="flex justify-center gap-6">
          <motion.a
          href={data.github}
          target="_blank"
          rel="noopener noreferrer"
          whileHover={{ scale: 1.05, y: -2 }}
          whileTap={{ scale: 0.95 }}
          className="px-6 py-4 bg-gray-900 text-white rounded-lg font-semibold shadow-lg hover:shadow-xl transition-all duration-300" 
          >
            GitHub
          </motion.a>

           <motion.div whileHover={{ scale: 1.05, y: -2 }} whileTap={{ scale: 0.95 }}>
            <Link to="/contact"
             className="px-8 py-4 bg-gradient-to-r from-blue-600 to-purple-600
              text-white rounded-lg font-semibold shadow-lg
               hover:shadow-xl transition-all duration-300 inline-block">
              Contacto
              </Link>
           </motion.div>
          </AnimatedSection>
        </div>
        </div>  
        </div>
        );
        };