import { useEffect, useState } from 'react';
import { api } from '../api';

function Posts() {
  const [posts, setPosts] = useState([]);
  const [users, setUsers] = useState([]);
  const [projects, setProjects] = useState([]);
  const [authorId, setAuthorId] = useState('');
  const [projectId, setProjectId] = useState('');
  const [content, setContent] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    fetchLists();
  }, []);

  async function fetchLists() {
    try {
      const [postsResponse, usersResponse, projectsResponse] = await Promise.all([
        api.posts.list(),
        api.users.list(),
        api.projects.list(),
      ]);
      setPosts(postsResponse);
      setUsers(usersResponse);
      setProjects(projectsResponse);
    } catch (err) {
      setError(err.message || 'Unable to fetch posts and dependencies');
    }
  }

  async function handleCreate(event) {
    event.preventDefault();
    setError('');
    setMessage('');

    try {
      await api.posts.create({
        content,
        author: authorId ? { id: Number(authorId) } : null,
        project: projectId ? { id: Number(projectId) } : null,
      });
      setMessage('Post created');
      setContent('');
      setAuthorId('');
      setProjectId('');
      fetchLists();
    } catch (err) {
      setError(err.message || 'Unable to create post');
    }
  }

  async function handleDelete(id) {
    try {
      await api.posts.delete(id);
      setMessage('Post deleted');
      fetchLists();
    } catch (err) {
      setError(err.message || 'Unable to delete post');
    }
  }

  return (
    <section className="page-shell">
      <div className="page-title-row">
        <div>
          <p className="eyebrow">Posts</p>
          <h1>Project posts</h1>
        </div>
      </div>

      <div className="panel-row">
        <div className="panel form-panel">
          <h2>New post</h2>
          <form onSubmit={handleCreate} className="form-grid">
            <label>
              Content
              <textarea value={content} onChange={(e) => setContent(e.target.value)} rows="5" required />
            </label>
            <label>
              Author
              <select value={authorId} onChange={(e) => setAuthorId(e.target.value)}>
                <option value="">Select author</option>
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
            <button className="primary-button" type="submit">Create post</button>
          </form>
        </div>

        <div className="panel list-panel">
          <h2>Recent posts</h2>
          {message && <div className="alert success">{message}</div>}
          {error && <div className="alert error">{error}</div>}
          <div className="table-wrap">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Content</th>
                  <th>Author</th>
                  <th>Project</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {posts.map((post) => (
                  <tr key={post.id}>
                    <td>{post.id}</td>
                    <td>{post.content}</td>
                    <td>{post.author?.username || 'Unknown'}</td>
                    <td>{post.project?.name || 'Unassigned'}</td>
                    <td>
                      <button className="ghost-button" onClick={() => handleDelete(post.id)}>
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

export default Posts;
