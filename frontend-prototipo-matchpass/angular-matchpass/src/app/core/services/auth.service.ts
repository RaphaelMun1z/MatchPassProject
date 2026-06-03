import { Injectable, signal } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { ApiService } from './api.service';

export interface AuthUser {
  id: string;
  name: string;
  email: string;
  role: 'user' | 'admin';
  token: string;
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private currentUser = signal<AuthUser | null>(null);
  private authSubject = new BehaviorSubject<boolean>(false);

  constructor(private apiService: ApiService) {
    this.loadUserFromStorage();
  }

  login(email: string, password: string): Observable<AuthUser> {
    return this.apiService.post('/auth/login', { email, password });
  }

  register(data: any): Observable<AuthUser> {
    return this.apiService.post('/auth/register', data);
  }

  logout(): void {
    this.currentUser.set(null);
    localStorage.removeItem('authToken');
    this.authSubject.next(false);
  }

  setUser(user: AuthUser): void {
    this.currentUser.set(user);
    localStorage.setItem('authToken', user.token);
    this.authSubject.next(true);
  }

  getUser() {
    return this.currentUser;
  }

  isAuthenticated(): Observable<boolean> {
    return this.authSubject.asObservable();
  }

  getAuthToken(): string | null {
    return this.currentUser()?.token || localStorage.getItem('authToken');
  }

  private loadUserFromStorage(): void {
    const token = localStorage.getItem('authToken');
    if (token) {
      this.authSubject.next(true);
    }
  }

  isAdmin(): boolean {
    return this.currentUser()?.role === 'admin';
  }

  resetPassword(email: string): Observable<any> {
    return this.apiService.post('/auth/reset-password', { email });
  }

  confirmResetPassword(token: string, password: string): Observable<any> {
    return this.apiService.post('/auth/confirm-reset', { token, password });
  }
}
