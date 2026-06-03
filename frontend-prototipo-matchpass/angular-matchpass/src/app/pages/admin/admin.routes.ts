import { Routes } from '@angular/router';
import { AdminDashboard } from './admin-dashboard/admin-dashboard';
import { AdminConfiguracoes } from './admin-configuracoes/admin-configuracoes';

export const adminRoutes: Routes = [
  {
    path: 'dashboard',
    component: AdminDashboard,
  },
  {
    path: 'configuracoes',
    component: AdminConfiguracoes,
  },
];
