import { BrowserRouter, NavLink, Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import Users from './pages/Users';
import Projects from './pages/Projects';
import Posts from './pages/Posts';
import Comments from './pages/Comments';
import Requests from './pages/Requests';
import Milestones from './pages/Milestones';
import CelebrationWall from './pages/CelebrationWall';
import { AuthProvider, useAuth } from './AuthContext';
import './App.css';

function CurrentUserDisplay() {
  const { user } = useAuth();

  return (
    <div className="user-badge">
      {user ? (
        <>Logged in as <strong>{user.username}</strong></>
      ) : (
        <>Not logged in</>
      )}
    </div>
  );
}

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <div className="app-shell">
          <header className="topbar">
            <div className="brand">
              <span className="brand-mark">M</span>
              <div>
                <strong>MzansiBuilds</strong>
                <p>collaboration dashboard</p>
              </div>
            </div>

            <nav className="nav-links">
              <NavLink to="/" end className={({ isActive }) => (isActive ? 'nav-link active' : 'nav-link')}>
                Home
              </NavLink>
              <NavLink to="/login" className={({ isActive }) => (isActive ? 'nav-link active' : 'nav-link')}>
                Login
              </NavLink>
              <NavLink to="/users" className={({ isActive }) => (isActive ? 'nav-link active' : 'nav-link')}>
                Users
              </NavLink>
              <NavLink to="/projects" className={({ isActive }) => (isActive ? 'nav-link active' : 'nav-link')}>
                Projects
              </NavLink>
              <NavLink to="/posts" className={({ isActive }) => (isActive ? 'nav-link active' : 'nav-link')}>
                Posts
              </NavLink>
              <NavLink to="/comments" className={({ isActive }) => (isActive ? 'nav-link active' : 'nav-link')}>
                Comments
              </NavLink>
              <NavLink to="/requests" className={({ isActive }) => (isActive ? 'nav-link active' : 'nav-link')}>
                Requests
              </NavLink>
              <NavLink to="/milestones" className={({ isActive }) => (isActive ? 'nav-link active' : 'nav-link')}>
                Milestones
              </NavLink>
              <NavLink to="/celebration" className={({ isActive }) => (isActive ? 'nav-link active' : 'nav-link')}>
                Celebration Wall
              </NavLink>
            </nav>

            <CurrentUserDisplay />
          </header>

          <main className="page-container">
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/login" element={<Login />} />
              <Route path="/users" element={<Users />} />
              <Route path="/projects" element={<Projects />} />
              <Route path="/posts" element={<Posts />} />
              <Route path="/comments" element={<Comments />} />
              <Route path="/requests" element={<Requests />} />
              <Route path="/milestones" element={<Milestones />} />
              <Route path="/celebration" element={<CelebrationWall />} />
            </Routes>
          </main>
        </div>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
