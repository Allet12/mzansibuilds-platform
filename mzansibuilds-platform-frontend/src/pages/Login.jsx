import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { api } from '../api';
import { useAuth } from '../AuthContext';

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();
  const { login } = useAuth();

  async function handleSubmit(event) {
    event.preventDefault();
    setError('');
    setMessage('');

    try {
      const result = await api.users.login({ username, password });
      login(username);
      setMessage(result.message || 'Login successful');
      navigate('/projects');
    } catch (err) {
      setError(err.message || 'Login failed');
    }
  }

  return (
    <section className="page-shell">
      <div className="panel centered-panel">
        <h1>Login</h1>
        <p>Sign in to manage your users, projects, posts and review collaboration requests.</p>

        <form onSubmit={handleSubmit} className="form-grid">
          <label>
            Username
            <input value={username} onChange={(e) => setUsername(e.target.value)} required />
          </label>
          <label>
            Password
            <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
          </label>
          <button className="primary-button" type="submit">Login</button>
        </form>

        {message && <div className="alert success">{message}</div>}
        {error && <div className="alert error">{error}</div>}
      </div>
    </section>
  );
}

export default Login;
