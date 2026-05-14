import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TaskService } from '../../services/task';
import { Task } from '../../models/task.model';

@Component({
  selector: 'app-test',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './test.html'
})
export class TestComponent implements OnInit {

  tasks: Task[] = [];

  constructor(private taskService: TaskService) {}

  ngOnInit() {
    this.taskService.getAllTasks().subscribe({
      next: (data: Task[]) => {   // 👈 FIXED TYPE
        this.tasks = data;
        console.log("DATA:", data);
      },
      error: (err: any) => {      // 👈 FIXED TYPE
        console.error("ERROR:", err);
      }
    });
  }
}