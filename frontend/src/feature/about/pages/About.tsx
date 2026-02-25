import { useAboutData } from "../hooks/useAboutData";

export const About = () => {
  const { data, loading, error } = useAboutData();

  if (loading) {
    return (
      <div className="container mx-auto px-4 py-16 text-center">
        <p className="text-gray-600">Cargando...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className="container mx-auto px-4 py-16 text-center">
        <p className="text-red-600">{error}</p>
      </div>
    );
  }

  if (!data) {
    return null;
  }

  return (
    <div className="container mx-auto px-4 py-16">
      <div className="max-w-4xl mx-auto">
        <h1 className="text-4xl font-bold mb-8">Sobre Mi</h1>

        <div className="bg-white rounded-lg shadow-md p-8 mb-6">
          <h2 className="text-2xl font-semibold mb-4 text-blue-600">
            {data.professionalTitle}
          </h2>
          <p className="text-lg text-gray-700 leading-relaxed mb-6">
            {data.biography}
          </p>
          {data.education && (
            <div className="border-t pt-6">
              <h3 className="text-xl font-semibold mb-2">Educación</h3>
              <p className="text-gray-600">{data.education}</p>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};
