import { Component } from '@angular/core';

@Component({
  selector: 'app-events',
  imports: [],
  templateUrl: './event.html',
  styleUrl: './event.css',
})
export class Events {
  count =0;
  increment(){
    this.count++;
  }
}
