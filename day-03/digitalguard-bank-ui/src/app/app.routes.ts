
import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  { 
    path: 'login', 
    loadComponent: () => import('./features/login/login').then(m => m.LoginComponent) 
  },
  { 
    path: 'dashboard', 
    canActivate: [authGuard], // <-- Aquí se aplica el guard
    loadComponent: () => import('./features/dashboard/dashboard').then(m => m.DashboardComponent) 
  },
  { path: '', redirectTo: 'login', pathMatch: 'full' }
];