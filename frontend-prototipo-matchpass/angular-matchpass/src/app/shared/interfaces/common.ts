export interface ApiResponse<T = any> {
  success: boolean;
  data?: T;
  error?: string;
  message?: string;
  timestamp: string;
}

export interface ApiError {
  status: number;
  message: string;
  details?: any;
}

export interface PaginatedResponse<T> {
  data: T[];
  total: number;
  page: number;
  pageSize: number;
  totalPages: number;
}

export interface TicketFilters {
  status?: string;
  eventId?: string;
  startDate?: string;
  endDate?: string;
  sortBy?: string;
  order?: 'asc' | 'desc';
}

export interface EventFilters {
  search?: string;
  category?: string;
  startDate?: string;
  endDate?: string;
  city?: string;
  sortBy?: string;
}

export interface CheckoutData {
  eventId: string;
  sectorId: string;
  quantity: number;
  ticketType: 'standard' | 'vip' | 'half' | 'free';
  email: string;
  fullName: string;
  cpf: string;
  paymentMethod: 'credit_card' | 'debit_card' | 'pix';
}
