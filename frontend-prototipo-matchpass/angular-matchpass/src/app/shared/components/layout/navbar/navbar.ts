import { Component, output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <nav class="navbar">
      <div class="navbar-container">
        <div class="navbar-brand">
          <span class="logo">MatchPass</span>
        </div>
        <ul class="navbar-menu">
          <li><a routerLink="/home">Home</a></li>
          <li><a routerLink="/club">Clubes</a></li>
          <li><a routerLink="/meus-ingressos">Meus Ingressos</a></li>
          <li><a routerLink="/perfil">Perfil</a></li>
          <li><a (click)="onLogout.emit()" class="logout">Sair</a></li>
        </ul>
      </div>
    </nav>
  `,
  styles: [
    `
      .navbar {
        background: white;
        box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
        position: sticky;
        top: 0;
        z-index: 100;
      }

      .navbar-container {
        display: flex;
        justify-content: space-between;
        align-items: center;
        max-width: 1200px;
        margin: 0 auto;
        padding: 1rem 1.5rem;
      }

      .navbar-brand {
        font-size: 1.5rem;
        font-weight: 700;
      }

      .logo {
        background: linear-gradient(135deg, #6366f1 0%, #8b5cf6 100%);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
        background-clip: text;
      }

      .navbar-menu {
        display: flex;
        list-style: none;
        gap: 2rem;
        margin: 0;
        padding: 0;

        li a {
          text-decoration: none;
          color: #1f2937;
          font-weight: 500;
          transition: color 0.3s ease;
          cursor: pointer;

          &:hover {
            color: #6366f1;
          }

          &.logout {
            color: #ef4444;

            &:hover {
              color: #dc2626;
            }
          }
        }
      }

      @media (max-width: 768px) {
        .navbar-menu {
          gap: 1rem;
          font-size: 0.875rem;
        }
      }
    `,
  ],
})
export class NavbarComponent {
  onLogout = output<void>();
}
