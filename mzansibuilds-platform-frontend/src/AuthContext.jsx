import { createContext, useContext, useEffect, useState } from 'react';

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    const stored = window.localStorage.getItem('mzansibuilds-user');
    if (!stored) return null;

    try {
      return JSON.parse(stored);
    } catch (error) {
      console.warn('Clearing invalid auth state from localStorage:', error);
      window.localStorage.removeItem('mzansibuilds-user');
      return null;
    }
  });

  useEffect(() => {
    if (user) {
      window.localStorage.setItem('mzansibuilds-user', JSON.stringify(user));
    } else {
      window.localStorage.removeItem('mzansibuilds-user');
    }
  }, [user]);

  const login = (username) => {
    setUser({ username });
  };

  const logout = () => {
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  return useContext(AuthContext);
}
