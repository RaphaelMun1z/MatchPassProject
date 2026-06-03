import { Component, input } from '@angular/core';

@Component({
  selector: 'app-loading-spinner',
  standalone: true,
  template: `
    <div class="spinner-container">
      <div class="spinner" [class]="'spinner-' + size()"></div>
      @if (message()) {
        <p class="spinner-message">{{ message() }}</p>
      }
    </div>
  `,
  styles: [
    `
      .spinner-container {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        padding: 2rem;
      }

      .spinner {
        border: 4px solid #e5e7eb;
        border-top-color: #6366f1;
        border-radius: 50%;
        animation: spin 1s linear infinite;

        &.spinner-sm {
          width: 32px;
          height: 32px;
        }

        &.spinner-md {
          width: 48px;
          height: 48px;
        }

        &.spinner-lg {
          width: 64px;
          height: 64px;
        }
      }

      @keyframes spin {
        to {
          transform: rotate(360deg);
        }
      }

      .spinner-message {
        margin-top: 1rem;
        color: #6b7280;
        font-size: 0.875rem;
      }
    `,
  ],
})
export class LoadingSpinnerComponent {
  size = input<'sm' | 'md' | 'lg'>('md');
  message = input<string>('');
}
