import { Component, input } from '@angular/core';

@Component({
  selector: 'app-card',
  standalone: true,
  template: `
    <div class="card" [class.card-hoverable]="hoverable()">
      <ng-content></ng-content>
    </div>
  `,
  styles: [
    `
      .card {
        background: white;
        border-radius: 8px;
        box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
        padding: 1.5rem;
        transition: box-shadow 0.3s ease;

        &.card-hoverable:hover {
          box-shadow: 0 10px 30px rgba(0, 0, 0, 0.15);
        }
      }
    `,
  ],
})
export class CardComponent {
  hoverable = input(false);
}
