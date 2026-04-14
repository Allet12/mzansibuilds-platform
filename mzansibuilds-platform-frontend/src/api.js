const API_BASE =
  import.meta.env.VITE_API_BASE || 'http://localhost:9091/mzansibuild';

async function request(path, options = {}) {
  const response = await fetch(`${API_BASE}${path}`, {
    method: options.method || 'GET',
    headers: {
      'Content-Type': 'application/json',
      ...(options.headers || {}),
    },
    body: options.body ? JSON.stringify(options.body) : undefined,
  });

  const contentType = response.headers.get('content-type');

  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(errorText || response.statusText);
  }

  // Handle no-content responses
  if (response.status === 204) {
    return null;
  }

  // Handle JSON safely
  if (contentType && contentType.includes('application/json')) {
    return response.json();
  }

  return response.text();
}

export const api = {
  users: {
    list: () => request('/user'),
    create: (user) => request('/user/create', { method: 'POST', body: user }),
    update: (user) => request('/user/update', { method: 'PUT', body: user }),
    delete: (id) => request(`/user/delete/${id}`, { method: 'DELETE' }),
    login: (credentials) =>
      request('/user/login', { method: 'POST', body: credentials }),
  },

  projects: {
    list: () => request('/project'),
    create: (project) =>
      request('/project/create', { method: 'POST', body: project }),
    update: (project) =>
      request('/project/update', { method: 'PUT', body: project }),
    delete: (id) => request(`/project/delete/${id}`, { method: 'DELETE' }),
  },

  posts: {
    list: () => request('/post'),
    create: (post) => request('/post/create', { method: 'POST', body: post }),
    update: (post) => request('/post/update', { method: 'PUT', body: post }),
    delete: (id) => request(`/post/delete/${id}`, { method: 'DELETE' }),
  },

  comments: {
    list: () => request('/comment'),
    create: (comment) =>
      request('/comment/create', { method: 'POST', body: comment }),
    update: (comment) =>
      request('/comment/update', { method: 'PUT', body: comment }),
    delete: (id) => request(`/comment/delete/${id}`, { method: 'DELETE' }),
  },

  collaboration: {
    list: () => request('/collaboration'),
    create: (req) =>
      request('/collaboration/create', { method: 'POST', body: req }),
    update: (req) =>
      request('/collaboration/update', { method: 'PUT', body: req }),
    delete: (id) =>
      request(`/collaboration/delete/${id}`, { method: 'DELETE' }),
  },
  milestones: {
    list: () => request('/milestone'),
    create: (milestone) => request('/milestone/create', { method: 'POST', body: milestone }),
    update: (milestone) => request('/milestone/update', { method: 'PUT', body: milestone }),
    delete: (id) => request(`/milestone/delete/${id}`, { method: 'DELETE' }),
    getByProject: (projectId) => request(`/milestone/project/${projectId}`),
  },
};