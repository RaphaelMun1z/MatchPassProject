import { Routes } from '@angular/router';
import { Show } from './show/show';
import { Club } from './club/club';
import { Ingresso } from './ingresso/ingresso';

export const eventsRoutes: Routes = [
  {
    path: 'show',
    component: Show,
  },
  {
    path: 'club',
    component: Club,
  },
  {
    path: 'ingresso',
    component: Ingresso,
  },
];
