import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-calculator',
  imports: [FormsModule,CommonModule],
  templateUrl: './calculator.html',
  styleUrl: './calculator.css',
})
export class Calculator {
  num1: number = 0;
  num2: number = 0;

  result: any = null;

  constructor(private http: HttpClient) {}

  calculate(operation: string) {

    this.http.post(
      `http://localhost:8080/calculate/${operation}?num1=${this.num1}&num2=${this.num2}`,
      {}
    ).subscribe(data => {

      this.result = data;

    });

  }
}
