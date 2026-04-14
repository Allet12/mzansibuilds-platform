import { useEffect, useState } from 'react';
import { api } from '../api';

function Requests() {
  const [requests, setRequests] = useState([]);
  const [users, setUsers] = useState([]);
  const [projects, setProjects] = useState([]);
  const [requesterId, setRequesterId] = useState('');
  const [projectId, setProjectId] = useState('');
  const [messageText, setMessageText] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    loadData();
  }, []);

  async function loadData() {
    try {
      const [requestsResponse, usersResponse, projectsResponse] = await Promise.all([
        api.collaboration.list(),
        api.users.list(),
        api.projects.list(),
      ]);
      setRequests(requestsResponse);
      setUsers(usersResponse);
      setProjects(projectsResponse);
    } catch (err) {
      setError(err.message || 'Unable to load requests');
    }
  }

  async function handleCreate(event) {
    event.preventDefault();
    setError('');
    setMessage('');

    try {
      await api.collaboration.create({
        requester: requesterId ? { id: Number(requesterId) } : null,
        project: projectId ? { id: Number(projectId) } : null,
        message: messageText,
        approved: false,
      });
      setMessage('Request submitted');
      setRequesterId('');
      setProjectId('');
      setMessageText('');
      loadData();
    } catch (err) {
      setError(err.message || 'Unable to submit request');
    }
  }

  async function handleToggleApprove(item) {
    try {
      await api.collaboration.update({
        ...item,
        approved: !item.approved,
      });
      setMessage('Request updated');
      loadData();
    } catch (err) {
      setError(err.message || 'Unable to update request');
    }
  }

  async function handleDelete(id) {
    try {
      await api.collaboration.delete(id);
      setMessage('Request deleted');
      loadData();
    } catch (err) {
      setError(err.message || 'Unable to delete request');
    }
  }

  return (
    <section className="page-shell">
      <div className="page-title-row">
        <div>
          <p className="eyebrow">Collaboration</p>
          <h1>Collaboration requests</h1>
        </div>
      </div>

      <div className="panel-row">
        <div className="panel form-panel">
          <h2>Send request</h2>
          <form onSubmit={handleCreate} className="form-grid">
            <label>
              Requester
              <select value={requesterId} onChange={(e) => setRequesterId(e.target.value)}>
                <option value="">Select requester</option>
                {users.map((user) => (
                  <option key={user.id} value={user.id}>{user.username}</option>
                ))}
              </select>
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
              Message
              <textarea value={messageText} onChange={(e) => setMessageText(e.target.value)} rows="4" required />
            </label>
            <button className="primary-button" type="submit">Submit request</button>
          </form>
        </div>

        <div className="panel list-panel">
          <h2>Requests</h2>
          {message && <div className="alert success">{message}</div>}
          {error && <div className="alert error">{error}</div>}
          <div className="table-wrap">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Requester</th>
                  <th>Project</th>
                  <th>Message</th>
                  <th>Approved</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {requests.map((request) => (
                  <tr key={request.id}>
                    <td>{request.id}</td>
                    <td>{request.requester?.username || 'Unknown'}</td>
                    <td>{request.project?.name || 'Unknown'}</td>
                    <td>{request.message}</td>
                    <td>{request.approved ? 'Yes' : 'No'}</td>
                    <td>
                      <button className="ghost-button" onClick={() => handleToggleApprove(request)}>
                        {request.approved ? 'Revoke' : 'Approve'}
                      </button>
                      <button className="ghost-button" onClick={() => handleDelete(request.id)}>
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

export default Requests;
