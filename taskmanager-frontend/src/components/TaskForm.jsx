import { useState } from 'react';

function TaskForm({ onSubmit }) {
  const [form, setForm] = useState({
    title: '',
    description: '',
    priority: 'MEDIUM'
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();                    // stops page refresh
    if (!form.title.trim()) return;        // basic validation
    onSubmit(form);                        // calls App.jsx handleCreate
    setForm({ title: '', description: '', priority: 'MEDIUM' }); // reset form
  };

  return (
    <form className="task-form" onSubmit={handleSubmit}>
      <h2>Add New Task</h2>
      <div className="form-row">
        <input
          name="title"
          placeholder="Task title *"
          value={form.title}
          onChange={handleChange}
          required
        />
        <select name="priority" value={form.priority} onChange={handleChange}>
          <option value="LOW">Low</option>
          <option value="MEDIUM">Medium</option>
          <option value="HIGH">High</option>
        </select>
      </div>
      <textarea
        name="description"
        placeholder="Description (optional)"
        value={form.description}
        onChange={handleChange}
        rows={2}
      />
      <button type="submit" className="btn-primary">Add Task</button>
    </form>
  );
}
export default TaskForm;