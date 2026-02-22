import { Link } from "react-router-dom";
import { useHomeData } from "../hooks/useHomeData";
export const Home = () => {
  const { data, loading, error } = useHomeData();

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
      <div className="max-w-4xl mx-auto text-center">
        <h1 className="text-5xl font-bold mb-6">{data.fullName}</h1>
        <p className="text-2xl text-gray-600 mb-4">{data.title}</p>
        <p className="text-lg text-gray-500 mb-8">{data.tagline}</p>
        <div className="flex justify-center gap-4">
          <a
            href={data.github}
            target="_blank"
            rel="noopener noreferrer"
            className="px-6 py-3 bg-gray-900 text-white rounded-lg hover:bg-gray-800 transition-colors"
          >
            GitHub
          </a>
          <Link
            to="/contact"
            className="px-6 py-3 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition-colors"
          >
            Contacto
          </Link>
        </div>
      </div>
    </div>
  );
};