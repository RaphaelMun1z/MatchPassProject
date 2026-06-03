import { Routes } from '@angular/router';
import { Error403 } from './error-403/error-403';
import { Error404 } from './error-404/error-404';
import { Error500 } from './error-500/error-500';
import { Error503 } from './error-503/error-503';

export const errorRoutes: Routes = [
  {
    path: 'error-403',
    component: Error403,
  },
  {
    path: 'error-404',
    component: Error404,
  },
  {
    path: 'error-500',
    component: Error500,
  },
  {
    path: 'error-503',
    component: Error503,
  },
];
