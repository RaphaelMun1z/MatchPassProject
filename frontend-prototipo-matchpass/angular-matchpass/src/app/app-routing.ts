import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { Index } from './pages/index/index';
import { RouteIndex } from './pages/route-index/route-index';
import { Scanner } from './pages/scanner/scanner';
import { authRoutes } from './pages/auth/auth.routes';
import { eventsRoutes } from './pages/events/events.routes';
import { ticketsRoutes } from './pages/tickets/tickets.routes';
import { userRoutes } from './pages/user/user.routes';
import { adminRoutes } from './pages/admin/admin.routes';
import { feedbackRoutes } from './pages/feedback/feedback.routes';
import { errorRoutes } from './pages/errors/error.routes';
import { statusRoutes } from './pages/status/status.routes';
import { AuthGuard, AdminGuard } from './core/guards';

export const routes: Routes = [
  {
    path: '',
    component: RouteIndex,
  },
  {
    path: 'map',
    component: RouteIndex,
  },
  {
    path: 'index',
    component: Index,
  },
  {
    path: 'home',
    component: Home,
    canActivate: [AuthGuard],
  },
  {
    path: 'scanner',
    component: Scanner,
  },
  // Auth Routes
  {
    path: '',
    children: authRoutes,
  },
  // Events Routes
  {
    path: '',
    children: eventsRoutes,
    canActivate: [AuthGuard],
  },
  // Tickets Routes
  {
    path: '',
    children: ticketsRoutes,
    canActivate: [AuthGuard],
  },
  // User Routes
  {
    path: 'user',
    children: userRoutes,
    canActivate: [AuthGuard],
  },
  // Admin Routes
  {
    path: 'admin',
    children: adminRoutes,
    canActivate: [AuthGuard, AdminGuard],
  },
  // Feedback Routes
  {
    path: '',
    children: feedbackRoutes,
  },
  // Error Routes
  {
    path: '',
    children: errorRoutes,
  },
  // Status Routes
  {
    path: '',
    children: statusRoutes,
  },
  // Wildcard - Must be last
  {
    path: '**',
    redirectTo: 'error-404',
  },
];
