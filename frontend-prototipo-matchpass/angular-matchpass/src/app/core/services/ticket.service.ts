import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';

export interface Ticket {
  id: string;
  code: string;
  eventId: string;
  eventName: string;
  eventDate: string;
  eventLocation: string;
  sectorId: string;
  sectorName: string;
  ticketType: 'standard' | 'vip' | 'half' | 'free';
  price: number;
  purchasedAt: string;
  validatedAt?: string;
  transferredAt?: string;
  status: 'valid' | 'used' | 'cancelled' | 'transferred';
  qrCode: string;
}

export interface Event {
  id: string;
  name: string;
  description: string;
  date: string;
  location: string;
  artists?: string[];
  image: string;
  capacity: number;
  availableTickets: number;
}

export interface Sector {
  id: string;
  name: string;
  capacity: number;
  availableTickets: number;
  prices: {
    standard: number;
    vip: number;
    half: number;
    free: number;
  };
}

@Injectable({
  providedIn: 'root',
})
export class TicketService {
  constructor(private apiService: ApiService) {}

  // Events
  getAllEvents(): Observable<Event[]> {
    return this.apiService.get('/events');
  }

  getEventById(eventId: string): Observable<Event> {
    return this.apiService.get(`/events/${eventId}`);
  }

  searchEvents(query: string): Observable<Event[]> {
    return this.apiService.get(`/events/search?q=${query}`);
  }

  // Sectors
  getEventSectors(eventId: string): Observable<Sector[]> {
    return this.apiService.get(`/events/${eventId}/sectors`);
  }

  getSectorById(sectorId: string): Observable<Sector> {
    return this.apiService.get(`/sectors/${sectorId}`);
  }

  // Tickets - Purchase
  purchaseTickets(data: {
    eventId: string;
    sectorId: string;
    quantity: number;
    ticketType: string;
    paymentMethod: string;
  }): Observable<any> {
    return this.apiService.post('/tickets/purchase', data);
  }

  validateCheckout(data: any): Observable<any> {
    return this.apiService.post('/tickets/validate-checkout', data);
  }

  // Tickets - User
  getUserTickets(): Observable<Ticket[]> {
    return this.apiService.get('/user/tickets');
  }

  getTicketById(ticketId: string): Observable<Ticket> {
    return this.apiService.get(`/tickets/${ticketId}`);
  }

  downloadTicket(ticketId: string): Observable<Blob> {
    return this.apiService.get(`/tickets/${ticketId}/download`);
  }

  // Tickets - Management
  validateTicket(code: string): Observable<any> {
    return this.apiService.post('/tickets/validate', { code });
  }

  transferTicket(ticketId: string, recipientEmail: string): Observable<any> {
    return this.apiService.post(`/tickets/${ticketId}/transfer`, { recipientEmail });
  }

  rateEvent(eventId: string, rating: number, comment: string): Observable<any> {
    return this.apiService.post(`/events/${eventId}/rate`, { rating, comment });
  }

  cancelTicket(ticketId: string): Observable<any> {
    return this.apiService.post(`/tickets/${ticketId}/cancel`, {});
  }

  getTicketHistory(): Observable<Ticket[]> {
    return this.apiService.get('/user/tickets/history');
  }
}
