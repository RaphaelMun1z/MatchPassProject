import { Routes } from '@angular/router';
import { Perfil } from './perfil/perfil';
import { Suporte } from './suporte/suporte';

export const userRoutes: Routes = [
  {
    path: 'perfil',
    component: Perfil,
  },
  {
    path: 'suporte',
    component: Suporte,
  },
];
