import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const token = localStorage.getItem('jwt_token');

  if (token) {
    return true; // Acceso permitido
  }

  // Si no hay token, redirigir al login
  router.navigate(['/login']);
  return false;
};