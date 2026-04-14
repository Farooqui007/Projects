import TaskCard from './TaskCard';

function TaskList({ tasks, onComplete, onDelete }) {
  if (tasks.length === 0) {
    return <p className="empty-state">No tasks found. Add one above!</p>;
  }

  return (
    <div className="task-list">
      {tasks.map(task => (
        <TaskCard
          key={task.id}
          task={task}
          onComplete={onComplete}
          onDelete={onDelete}
        />
      ))}
    </div>
  );
}
export default TaskList;