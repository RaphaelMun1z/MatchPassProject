import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';

export interface User {
  id: string;
  name: string;
  email: string;
  phone?: string;
  cpf?: string;
  address?: string;
  birthDate?: string;
  profileImage?: string;
  createdAt: string;
  updatedAt: string;
}

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private apiService: ApiService) {}

  getProfile(): Observable<User> {
    return this.apiService.get('/user/profile');
  }

  updateProfile(data: Partial<User>): Observable<User> {
    return this.apiService.put('/user/profile', data);
  }

  uploadProfileImage(file: File): Observable<any> {
    const formData = new FormData();
    formData.append('image', file);
    return this.apiService.post('/user/profile/image', formData);
  }

  changePassword(oldPassword: string, newPassword: string): Observable<any> {
    return this.apiService.post('/user/change-password', { oldPassword, newPassword });
  }

  getContactInfo(): Observable<any> {
    return this.apiService.get('/user/contact');
  }

  updateContactInfo(data: any): Observable<any> {
    return this.apiService.put('/user/contact', data);
  }

  deleteAccount(): Observable<any> {
    return this.apiService.delete('/user/account');
  }

  getUserStats(): Observable<any> {
    return this.apiService.get('/user/stats');
  }
}
