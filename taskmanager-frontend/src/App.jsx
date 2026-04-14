import { useState, useEffect } from 'react';
import { getAllTasks, getStats, createTask, markComplete, deleteTask } from './api/taskApi';
import TaskForm from './components/TaskForm';
import TaskList from './components/TaskList';
import StatsBar from './components/StatsBar';
import './App.css';

function App() {
  // State — React re-renders whenever these change
  const [tasks, setTasks]   = useState([]);
  const [stats, setStats]   = useState({ total: 0, pending: 0, completed: 0 });
  const [loading, setLoading] = useState(true);
  const [filter, setFilter] = useState('ALL'); // ALL | PENDING | COMPLETED

  // Runs once on mount — loads tasks and stats from Spring Boot API
  useEffect(() => {
    loadData();
  }, []);

  const loadData = async () => {
    try {
      setLoading(true);
      const [tasksRes, statsRes] = await Promise.all([
        getAllTasks(),
        getStats()
      ]);
      setTasks(tasksRes.data);
      setStats(statsRes.data);
    } catch (err) {
      console.error('Failed to load data:', err);
    } finally {
      setLoading(false);
    }
  };

  const handleCreate = async (taskData) => {
    try {
      await createTask(taskData);
      loadData(); // reload everything after creating
    } catch (err) {
      console.error('Failed to create task:', err);
    }
  };

  const handleComplete = async (id) => {
    try {
      await markComplete(id);
      loadData();
    } catch (err) {
      console.error('Failed to mark complete:', err);
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteTask(id);
      loadData();
    } catch (err) {
      console.error('Failed to delete task:', err);
    }
  };

  // Filter tasks on the frontend — no extra API call needed
  const filteredTasks = tasks.filter(task => {
    if (filter === 'ALL') return true;
    return task.status === filter;
  });

  return (
    <div className="app">
      <header className="app-header">
        <h1>Task Manager</h1>
        <p>Spring Boot + MongoDB + React</p>
      </header>

      <main className="app-main">
        <StatsBar stats={stats} />

        <TaskForm onSubmit={handleCreate} />

        {/* Filter buttons */}
        <div className="filter-bar">
          {['ALL', 'PENDING', 'COMPLETED'].map(f => (
            <button
              key={f}
              className={`filter-btn ${filter === f ? 'active' : ''}`}
              onClick={() => setFilter(f)}
            >
              {f}
            </button>
          ))}
        </div>

        {loading
          ? <p className="loading">Loading tasks...</p>
          : <TaskList
              tasks={filteredTasks}
              onComplete={handleComplete}
              onDelete={handleDelete}
            />
        }
      </main>
    </div>
  );
}

export default App;