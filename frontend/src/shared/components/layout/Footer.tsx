export const Footer = () => {
    return (
        <footer className="bg-gray-900 text-white py-8 mt-auto">
            <div className="container mx-auto px-4">
                <div className="flex flex-col items-center gap-4">
                    <p className="text-gray-400">
                        @ {new Date().getFullYear()} José Pereyra. All rights reserved.
                    </p>
                    <div className="flex gap-6">
                        <a href="https://github.com/jose19841"
                         target="_blank" 
                         rel="noopener noreferrer"
                          className="text-gray-400 hover:text-white transition-colors">
                            Github
                        </a>
                        <a href="mailto:jlpereyra2310@gmail.com"
                         className="text-gray-400 hover:text-white transition-colors">
                            Email
                        </a>
                    </div>
                </div>
            </div>
        </footer>
    );
};
