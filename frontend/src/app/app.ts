import { Component } from '@angular/core';
import { TestComponent } from './components/test/test.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [TestComponent],
  template: `<app-test></app-test>`
})
export class App {}