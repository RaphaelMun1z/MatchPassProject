import { Component, input, output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-input-field',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="form-group">
      <label *ngIf="label()" [for]="id()">{{ label() }}</label>
      <input
        [id]="id()"
        [type]="type()"
        [placeholder]="placeholder()"
        [value]="value()"
        (input)="onInput.emit($event)"
        [disabled]="disabled()"
        [required]="required()"
        class="input-field"
      />
      <small *ngIf="error()" class="error">{{ error() }}</small>
    </div>
  `,
  styles: [
    `
      .form-group {
        display: flex;
        flex-direction: column;
        gap: 0.5rem;
        margin-bottom: 1rem;
      }

      label {
        font-weight: 500;
        color: #1f2937;
        font-size: 0.875rem;
      }

      .input-field {
        padding: 0.75rem;
        border: 1px solid #d1d5db;
        border-radius: 4px;
        font-family: inherit;
        font-size: 1rem;
        transition: border-color 0.3s ease;

        &:focus {
          outline: none;
          border-color: #6366f1;
          box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.1);
        }

        &:disabled {
          background-color: #f3f4f6;
          cursor: not-allowed;
        }
      }

      .error {
        color: #ef4444;
        font-size: 0.875rem;
      }
    `,
  ],
})
export class InputFieldComponent {
  id = input<string>('');
  label = input<string>('');
  type = input<string>('text');
  placeholder = input<string>('');
  value = input<string>('');
  disabled = input(false);
  required = input(false);
  error = input<string>('');
  onInput = output<Event>();
}
