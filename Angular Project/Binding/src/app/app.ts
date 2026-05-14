import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Login } from './login/login';
import { Events } from './event/event';

@Component({
  selector: 'app-root',
  imports: [],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  age = 0;
  updateAge(val : string){
    this.age = Number(val);
  }
  items = ['apple' , 'banana' , 'cherry' , 'mango' , 'dates'];
  removeItem(i : number){
    this.items.splice(i,1);
  }
  
}
