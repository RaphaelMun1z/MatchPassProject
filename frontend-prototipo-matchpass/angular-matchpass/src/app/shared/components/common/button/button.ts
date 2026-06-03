import { Component, input, output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-button',
  standalone: true,
  imports: [CommonModule],
  template: `
    <button
      [class]="'btn btn-' + variant + ' btn-' + size"
      [disabled]="disabled()"
      [type]="type()"
      (click)="onClick.emit()"
    >
      <ng-content></ng-content>
    </button>
  `,
  styles: [
    `
      .btn {
        display: inline-flex;
        align-items: center;
        justify-content: center;
        gap: 0.5rem;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-weight: 600;
        transition: all 0.3s ease;
        font-family: inherit;

        &:disabled {
          opacity: 0.5;
          cursor: not-allowed;
        }

        &:not(:disabled):active {
          transform: scale(0.98);
        }
      }

      .btn-primary {
        background: #6366f1;
        color: white;

        &:not(:disabled):hover {
          background: #4f46e5;
          box-shadow: 0 4px 12px rgba(99, 102, 241, 0.4);
        }
      }

      .btn-secondary {
        background: #e5e7eb;
        color: #1f2937;

        &:not(:disabled):hover {
          background: #d1d5db;
        }
      }

      .btn-danger {
        background: #ef4444;
        color: white;

        &:not(:disabled):hover {
          background: #dc2626;
        }
      }

      .btn-success {
        background: #10b981;
        color: white;

        &:not(:disabled):hover {
          background: #059669;
        }
      }

      .btn-sm {
        padding: 0.5rem 1rem;
        font-size: 0.875rem;
      }

      .btn-md {
        padding: 0.75rem 1.5rem;
        font-size: 1rem;
      }

      .btn-lg {
        padding: 1rem 2rem;
        font-size: 1.125rem;
      }
    `,
  ],
})
export class ButtonComponent {
  variant = input<'primary' | 'secondary' | 'danger' | 'success'>('primary');
  size = input<'sm' | 'md' | 'lg'>('md');
  disabled = input(false);
  type = input<'button' | 'submit' | 'reset'>('button');
  onClick = output<void>();
}
