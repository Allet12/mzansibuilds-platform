import { useEffect, useState } from 'react';
import { api } from '../api';

function CelebrationWall() {
  const [completedProjects, setCompletedProjects] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchCompletedProjects();
  }, []);

  async function fetchCompletedProjects() {
    try {
      const projects = await api.projects.list();
      setCompletedProjects(projects.filter(project => project.completed));
    } catch (err) {
      setError(err.message || 'Unable to fetch completed projects');
    }
  }

  return (
    <section className="page-shell">
      <div className="page-title-row">
        <div>
          <p className="eyebrow">Celebration Wall</p>
          <h1>Completed projects</h1>
          <p>Congratulations to all developers who have built in public!</p>
        </div>
      </div>

      {error && <div className="alert error">{error}</div>}

      <div className="projects-grid">
        {completedProjects.length === 0 ? (
          <div className="panel centered-panel">
            <p>No completed projects yet. Keep building!</p>
          </div>
        ) : (
          completedProjects.map((project) => (
            <article key={project.id} className="project-card">
              <div className="project-header">
                <h3>{project.name}</h3>
                <span className="badge completed">Completed</span>
              </div>
              <p className="project-description">{project.description}</p>
              <div className="project-meta">
                <span>By {project.user?.username || 'Anonymous'}</span>
                <span>Stage: {project.stage}</span>
              </div>
              {project.supportRequired && (
                <div className="support-note">
                  <strong>Support received:</strong> {project.supportRequired}
                </div>
              )}
            </article>
          ))
        )}
      </div>
    </section>
  );
}

export default CelebrationWall;