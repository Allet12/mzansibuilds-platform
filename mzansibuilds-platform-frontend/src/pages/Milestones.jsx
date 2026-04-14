import { useEffect, useState } from 'react';
import { api } from '../api';

function Milestones() {
  const [milestones, setMilestones] = useState([]);
  const [projects, setProjects] = useState([]);
  const [projectId, setProjectId] = useState('');
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [achieved, setAchieved] = useState(false);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    fetchMilestones();
    fetchProjects();
  }, []);

  async function fetchMilestones() {
    try {
      setMilestones(await api.milestones.list());
    } catch (err) {
      setError(err.message || 'Unable to fetch milestones');
    }
  }

  async function fetchProjects() {
    try {
      setProjects(await api.projects.list());
    } catch (err) {
      setError(err.message || 'Unable to fetch projects');
    }
  }

  async function handleCreate(event) {
    event.preventDefault();
    setError('');
    setMessage('');

    try {
      await api.milestones.create({
        title,
        description,
        achieved,
        project: projectId ? { id: Number(projectId) } : null,
      });
      setMessage('Milestone created');
      setTitle('');
      setDescription('');
      setAchieved(false);
      setProjectId('');
      fetchMilestones();
    } catch (err) {
      setError(err.message || 'Unable to create milestone');
    }
  }

  async function handleToggleAchieved(milestone) {
    try {
      await api.milestones.update({
        ...milestone,
        achieved: !milestone.achieved,
      });
      setMessage('Milestone updated');
      fetchMilestones();
    } catch (err) {
      setError(err.message || 'Unable to update milestone');
    }
  }

  async function handleDelete(id) {
    try {
      await api.milestones.delete(id);
      setMessage('Milestone deleted');
      fetchMilestones();
    } catch (err) {
      setError(err.message || 'Unable to delete milestone');
    }
  }

  return (
    <section className="page-shell">
      <div className="page-title-row">
        <div>
          <p className="eyebrow">Milestones</p>
          <h1>Project milestones</h1>
        </div>
      </div>

      <div className="panel-row">
        <div className="panel form-panel">
          <h2>Add milestone</h2>
          <form onSubmit={handleCreate} className="form-grid">
            <label>
              Title
              <input value={title} onChange={(e) => setTitle(e.target.value)} required />
            </label>
            <label>
              Description
              <textarea value={description} onChange={(e) => setDescription(e.target.value)} rows="3" />
            </label>
            <label>
              Project
              <select value={projectId} onChange={(e) => setProjectId(e.target.value)}>
                <option value="">Select project</option>
                {projects.map((project) => (
                  <option key={project.id} value={project.id}>{project.name}</option>
                ))}
              </select>
            </label>
            <label>
              <input type="checkbox" checked={achieved} onChange={(e) => setAchieved(e.target.checked)} />
              Achieved
            </label>
            <button className="primary-button" type="submit">Create milestone</button>
          </form>
        </div>

        <div className="panel list-panel">
          <h2>All milestones</h2>
          {message && <div className="alert success">{message}</div>}
          {error && <div className="alert error">{error}</div>}
          <div className="table-wrap">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Title</th>
                  <th>Project</th>
                  <th>Achieved</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {milestones.map((milestone) => (
                  <tr key={milestone.id}>
                    <td>{milestone.id}</td>
                    <td>{milestone.title}</td>
                    <td>{milestone.project?.name || 'Unknown'}</td>
                    <td>{milestone.achieved ? 'Yes' : 'No'}</td>
                    <td>
                      <button className="ghost-button" onClick={() => handleToggleAchieved(milestone)}>
                        {milestone.achieved ? 'Mark Incomplete' : 'Mark Achieved'}
                      </button>
                      <button className="ghost-button" onClick={() => handleDelete(milestone.id)}>
                        Delete
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </section>
  );
}

export default Milestones;