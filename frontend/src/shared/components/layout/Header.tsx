import { Link } from "react-router-dom"

export const Header = () => {
    return (
        <header className="bg-white shadow-sm">
            <div className="container mx-auto px-4 py-4">
                <div className="flex items-center justify-between">
                    <h1 className="text-2xl font-bold text-gray-900"> José Pereyra</h1>
                    <nav className="flex gap-6">
                        <Link to="/" className="text-gray-600 hover:text-blue-600 transition-colors">
                        Home
                        </Link>
                        <Link to="/about" className="text-gray-600 hover:text-blue-600 transition-colors">
                        About
                        </Link>
                        <Link to="/projects" className="text-gray-600 hover:text-blue-600 transition-colors">
                        Projects
                        </Link>
                        <Link to="/tech-stack" className="text-gray-600 hover:text-blue-600 transition-colors">
                        Tech Stack
                        </Link>
                        <Link to="/contact" className="text-gray-600 hover:text-blue-600 transition-colors">
                        Contact
                        </Link>
                    </nav>
            </div>
            </div>  
            </header>
    );
};
