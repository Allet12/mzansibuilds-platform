import { useEffect, useState } from 'react';
import { api } from '../api';

function Comments() {
  const [comments, setComments] = useState([]);
  const [users, setUsers] = useState([]);
  const [posts, setPosts] = useState([]);
  const [authorId, setAuthorId] = useState('');
  const [postId, setPostId] = useState('');
  const [text, setText] = useState('');
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    loadData();
  }, []);

  async function loadData() {
    try {
      const [commentsResponse, usersResponse, postsResponse] = await Promise.all([
        api.comments.list(),
        api.users.list(),
        api.posts.list(),
      ]);
      setComments(commentsResponse);
      setUsers(usersResponse);
      setPosts(postsResponse);
    } catch (err) {
      setError(err.message || 'Unable to load comments');
    }
  }

  async function handleCreate(event) {
    event.preventDefault();
    setError('');
    setMessage('');

    try {
      await api.comments.create({
        text,
        author: authorId ? { id: Number(authorId) } : null,
        post: postId ? { id: Number(postId) } : null,
      });
      setMessage('Comment created');
      setText('');
      setAuthorId('');
      setPostId('');
      loadData();
    } catch (err) {
      setError(err.message || 'Unable to create comment');
    }
  }

  async function handleDelete(id) {
    try {
      await api.comments.delete(id);
      setMessage('Comment deleted');
      loadData();
    } catch (err) {
      setError(err.message || 'Unable to delete comment');
    }
  }

  return (
    <section className="page-shell">
      <div className="page-title-row">
        <div>
          <p className="eyebrow">Comments</p>
          <h1>Comment moderation</h1>
        </div>
      </div>

      <div className="panel-row">
        <div className="panel form-panel">
          <h2>Create comment</h2>
          <form onSubmit={handleCreate} className="form-grid">
            <label>
              Comment
              <textarea value={text} onChange={(e) => setText(e.target.value)} rows="4" required />
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
              Post
              <select value={postId} onChange={(e) => setPostId(e.target.value)}>
                <option value="">Select post</option>
                {posts.map((post) => (
                  <option key={post.id} value={post.id}>{post.content.slice(0, 40) || 'Untitled'}</option>
                ))}
              </select>
            </label>
            <button className="primary-button" type="submit">Create comment</button>
          </form>
        </div>

        <div className="panel list-panel">
          <h2>All comments</h2>
          {message && <div className="alert success">{message}</div>}
          {error && <div className="alert error">{error}</div>}
          <div className="table-wrap">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Author</th>
                  <th>Post</th>
                  <th>Text</th>
                  <th>Created At</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {comments.map((comment) => (
                  <tr key={comment.id}>
                    <td>{comment.id}</td>
                    <td>{comment.author?.username || 'Unknown'}</td>
                    <td>{comment.post?.content?.slice(0, 30) || 'Unknown'}</td>
                    <td>{comment.text}</td>
                    <td>{comment.createdAt ? new Date(comment.createdAt).toLocaleString() : '—'}</td>
                    <td>
                      <button className="ghost-button" onClick={() => handleDelete(comment.id)}>
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

export default Comments;
