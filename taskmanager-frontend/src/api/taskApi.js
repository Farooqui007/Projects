import axios from 'axios';

// Base URL points to your Spring Boot backend
const API = axios.create({
  baseURL: 'http://localhost:8080/api'
});

// Every API call in one place — if the URL changes, you change it here only
export const getAllTasks    = ()         => API.get('/tasks');
export const getStats      = ()         => API.get('/tasks/stats');
export const createTask    = (taskData) => API.post('/tasks', taskData);
export const markComplete  = (id)       => API.patch(`/tasks/${id}/complete`);
export const deleteTask    = (id)       => API.delete(`/tasks/${id}`);