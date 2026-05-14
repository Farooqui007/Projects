import { Component, ChangeDetectorRef , signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-hello',
  standalone: true,
  imports: [],
  templateUrl: './hello.html',
  styleUrl: './hello.css',
})
export class Hello {
  result = signal('');
  message = 'Hello World';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.http.get("http://localhost:8080/data", { responseType: 'text' })
      .subscribe({
        next: (data) => {
          console.log("SUCCESS:", data);
          this.result.set(data);
        },
        error: (err) => {
          console.error("ERROR:", err);
        }
      });
  }
}