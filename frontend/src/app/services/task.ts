  import { Injectable } from '@angular/core';
  import { HttpClient } from '@angular/common/http';
  import { Observable } from 'rxjs';
  import { Task } from '../models/task.model';

  @Injectable({
    providedIn: 'root'
  })
  export class TaskService {

    private baseUrl = 'http://localhost:8080';

    constructor(private http: HttpClient) {}

    // GET by ID
    getTask(id: number): Observable<Task> {
      return this.http.get<Task>(`${this.baseUrl}/task/${id}`);
    }

    // CREATE
    createTask(task: Task): Observable<any> {
      return this.http.post(`${this.baseUrl}/task`, task);
    }

    // UPDATE
    updateTask(task: Task): Observable<Task> {
      return this.http.put<Task>(`${this.baseUrl}/task`, task);
    }

    // DELETE
    deleteTask(id: number): Observable<any> {
      return this.http.delete(`${this.baseUrl}/task/${id}`);
    }

    // GET ALL (⚠️ tabhi chalega jab backend me enable karega)
    getAllTasks(): Observable<Task[]> {
      return this.http.get<Task[]>(`${this.baseUrl}/tasks`);
    }
  }
