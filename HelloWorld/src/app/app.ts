import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Hello } from './hello/hello';

@Component({
  selector: 'app-root',
  standalone: true,imports: [Hello],     
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('HelloWorld');

}
