import { useEffect, useState } from 'react';
import { api } from '../api';

function Projects() {
  const [projects, setProjects] = useState([]);
  const [users, setUsers] = useState([]);
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [stage, setStage] = useState('');
  const [supportRequired, setSupportRequired] = useState('');
  const [userId, setUserId] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    fetchProjects();
    fetchUsers();
  }, []);

  async function fetchProjects() {
    try {
      setProjects(await api.projects.list());
    } catch (err) {
      setError(err.message || 'Unable to fetch projects');
    }
  }

  async function fetchUsers() {
    try {
      setUsers(await api.users.list());
    } catch (err) {
      setError(err.message || 'Unable to fetch users');
    }
  }

  async function handleCreate(event) {
    event.preventDefault();
    setError('');
    setMessage('');

    try {
      await api.projects.create({
        name,
        description,
        stage,
        supportRequired,
        completed: false,
        user: userId ? { id: Number(userId) } : null,
      });
      setMessage('Project created');
      setName('');
      setDescription('');
      setStage('');
      setSupportRequired('');
      setUserId('');
      fetchProjects();
    } catch (err) {
      setError(err.message || 'Unable to create project');
    }
  }

  async function handleDelete(id) {
    try {
      await api.projects.delete(id);
      setMessage('Project deleted');
      fetchProjects();
    } catch (err) {
      setError(err.message || 'Unable to delete project');
    }
  }

  async function handleToggleCompleted(project) {
    try {
      await api.projects.update({
        ...project,
        completed: !project.completed,
      });
      setMessage('Project updated');
      fetchProjects();
    } catch (err) {
      setError(err.message || 'Unable to update project');
    }
  }

  return (
    <section className="page-shell">
      <div className="page-title-row">
        <div>
          <p className="eyebrow">Projects</p>
          <h1>Project workspace</h1>
        </div>
      </div>

      <div className="panel-row">
        <div className="panel form-panel">
          <h2>Create project</h2>
          <form onSubmit={handleCreate} className="form-grid">
            <label>
              Name
              <input value={name} onChange={(e) => setName(e.target.value)} required />
            </label>
            <label>
              Description
              <textarea value={description} onChange={(e) => setDescription(e.target.value)} rows="4" />
            </label>
            <label>
              Stage
              <select value={stage} onChange={(e) => setStage(e.target.value)}>
                <option value="">Select stage</option>
                <option value="Planning">Planning</option>
                <option value="Development">Development</option>
                <option value="Testing">Testing</option>
                <option value="Completed">Completed</option>
              </select>
            </label>
            <label>
              Support Required
              <textarea value={supportRequired} onChange={(e) => setSupportRequired(e.target.value)} rows="3" placeholder="Describe any support or collaboration needed" />
            </label>
            <label>
              Owner
              <select value={userId} onChange={(e) => setUserId(e.target.value)}>
                <option value="">Select owner</option>
                {users.map((user) => (
                  <option key={user.id} value={user.id}>{user.username}</option>
                ))}
              </select>
            </label>
            <button className="primary-button" type="submit">Create project</button>
          </form>
        </div>

        <div className="panel list-panel">
          <h2>Project list</h2>
          {message && <div className="alert success">{message}</div>}
          {error && <div className="alert error">{error}</div>}
          <div className="table-wrap">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Name</th>
                  <th>Stage</th>
                  <th>Owner</th>
                  <th>Completed</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {projects.map((project) => (
                  <tr key={project.id}>
                    <td>{project.id}</td>
                    <td>{project.name}</td>
                    <td>{project.stage || 'Not set'}</td>
                    <td>{project.user?.username || 'Unassigned'}</td>
                    <td>{project.completed ? 'Yes' : 'No'}</td>
                    <td>
                      <button className="ghost-button" onClick={() => handleToggleCompleted(project)}>
                        {project.completed ? 'Mark Incomplete' : 'Mark Completed'}
                      </button>
                      <button className="ghost-button" onClick={() => handleDelete(project.id)}>
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

export default Projects;
