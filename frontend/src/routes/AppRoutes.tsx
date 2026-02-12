import { Routes, Route } from "react-router-dom";
import { Home } from "../feature/home/pages/Home";
import { About } from "../feature/about/About";
import { Projects } from "../feature/projects/pages/Projects";
import { TechStack } from "../feature/techStack/pages/TechStack";
import { Contact } from "../feature/contact/pages/Contact";
import { MainLayout } from "../layouts/MainLayout";

export const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<MainLayout />}>
        <Route index element={<Home />} />
        <Route path="about" element={<About />} />
        <Route path="projects" element={<Projects />} />
        <Route path="tech-stack" element={<TechStack />} />
        <Route path="contact" element={<Contact />} />
      </Route>
    </Routes>
  );
};
