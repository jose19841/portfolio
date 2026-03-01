import { useAboutData } from "../hooks/useAboutData";
import { AnimatedSection } from "@/shared/components/ui/AnimatedSection"; 

export const About = () => {
  const { data, loading, error } = useAboutData();

  if (loading) {
    return (
      <div className="container mx-auto px-4 py-16 text-center">
        <div className="w-12 h-12 border-4 border-blue-600 border-t-transparent rounded-full animate-spin mx-auto"/>
      </div>
    );
  }

  if (error) return <div className="container mx-auto px-4 py-16 text-center text-red-600">{error}</div>
   
  if (!data) return null;

  return (
    <div className="min-h-[calc(100vh-200px)] gb-gradient-to-br from-blue-50 via-purple-50 to-pink-50 py-16">
      <div className="container mx-auto px-4">
        <div className="maxx-w-4xl mx-auto">
          <AnimatedSection delay={0}> 
            <h1 className="text-5xl font-bold mb-12 bg-gradient-to-r from-blue-600 to-purple-600 bg-clip-text text-transparent">
              Sobre Mi
            </h1>
          </AnimatedSection>

          <AnimatedSection delay={0.2}>
            <div className="bg-white rounded-2xl shadow-xl p-8 hover:shadow-2xl transition-shadow duration-300">
              <h2 className="text-3xl font-semibold mb-6 text-blue-600">
                {data.professionalTitle}
              </h2>
              <p className="text-lg text-gray-700 leading-relaxed mb-8">
                {data.biography}
              </p>

              {data.education && (
                <div className="border-t border-gray-200 pt-6">
                  <h3 className="text-xl font-semibold mb-3 flex-item-center gap-2">
                    <span className="text-2xl">🎓</span> Educación
                    </h3>
                    <p className="text-gray-600 text-lg">{data.education}</p>
                </div>
              )}
              </div>
          </AnimatedSection>
        
        </div>
      </div>
    </div>
  );
};
