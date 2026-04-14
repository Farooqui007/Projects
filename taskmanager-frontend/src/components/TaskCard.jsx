function TaskCard({ task, onComplete, onDelete }) {
  const priorityColors = { LOW: '#22c55e', MEDIUM: '#f59e0b', HIGH: '#ef4444' };

  return (
    <div className={`task-card ${task.status === 'COMPLETED' ? 'completed' : ''}`}>
      <div className="task-card-header">
        <h3 className="task-title">{task.title}</h3>
        <span
          className="priority-badge"
          style={{ background: priorityColors[task.priority] }}
        >
          {task.priority}
        </span>
      </div>

      {task.description && (
        <p className="task-description">{task.description}</p>
      )}

      <div className="task-card-footer">
        <span className={`status-badge ${task.status.toLowerCase()}`}>
          {task.status}
        </span>
        <div className="task-actions">
          {task.status === 'PENDING' && (
            <button
              className="btn-complete"
              onClick={() => onComplete(task.id)}
            >
              ✓ Complete
            </button>
          )}
          <button
            className="btn-delete"
            onClick={() => onDelete(task.id)}
          >
            🗑 Delete
          </button>
        </div>
      </div>
    </div>
  );
}
export default TaskCard;