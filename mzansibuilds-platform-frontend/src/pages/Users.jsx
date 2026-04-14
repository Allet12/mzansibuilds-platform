import { useEffect, useState } from 'react';
import { api } from '../api';

function Users() {
  const [users, setUsers] = useState([]);
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    fetchUsers();
  }, []);

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
      await api.users.create({ username, email, password });
      setMessage('User created successfully');
      setUsername('');
      setEmail('');
      setPassword('');
      fetchUsers();
    } catch (err) {
      setError(err.message || 'Failed to create user');
    }
  }

  async function handleDelete(id) {
    try {
      await api.users.delete(id);
      setMessage('User deleted');
      fetchUsers();
    } catch (err) {
      setError(err.message || 'Unable to delete user');
    }
  }

  return (
    <section className="page-shell">
      <div className="page-title-row">
        <div>
          <p className="eyebrow">Users</p>
          <h1>User management</h1>
        </div>
      </div>

      <div className="panel-row">
        <div className="panel form-panel">
          <h2>Create user</h2>
          <form onSubmit={handleCreate} className="form-grid">
            <label>
              Username
              <input value={username} onChange={(e) => setUsername(e.target.value)} required />
            </label>
            <label>
              Email
              <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
            </label>
            <label>
              Password
              <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
            </label>
            <button className="primary-button" type="submit">Create user</button>
          </form>
        </div>

        <div className="panel list-panel">
          <h2>Existing users</h2>
          {message && <div className="alert success">{message}</div>}
          {error && <div className="alert error">{error}</div>}
          <div className="table-wrap">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Username</th>
                  <th>Email</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {users.map((user) => (
                  <tr key={user.id}>
                    <td>{user.id}</td>
                    <td>{user.username}</td>
                    <td>{user.email}</td>
                    <td>
                      <button className="ghost-button" onClick={() => handleDelete(user.id)}>
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

export default Users;
