export const APP_ROUTES = {
  HOME: '/',
  INDEX: '/index',
  LOGIN: '/login',
  CADASTRO: '/cadastro',
  RECUPERAR_SENHA: '/recuperar-senha',
  HOME_PAGE: '/home',
  CLUB: '/club',
  SHOW: '/show',
  INGRESSO: '/ingresso',
  CHECKOUT: '/checkout',
  MEUS_INGRESSOS: '/meus-ingressos',
  PERFIL: '/perfil',
  TRANSFERENCIA: '/transferencia',
  SELECAO_SETOR: '/selecao-setor',
  SUCESSO: '/sucesso',
  AVALIACAO: '/avaliacao',
  SUPORTE: '/suporte',
  LOADING: '/loading',
  ADMIN_DASHBOARD: '/admin/dashboard',
  ADMIN_CONFIGURACOES: '/admin/configuracoes',
  ERROR_403: '/error-403',
  ERROR_404: '/error-404',
  ERROR_500: '/error-500',
  ERROR_503: '/error-503',
};

export const TICKET_TYPES = {
  STANDARD: 'standard',
  VIP: 'vip',
  HALF: 'half',
  FREE: 'free',
};

export const TICKET_STATUS = {
  VALID: 'valid',
  USED: 'used',
  CANCELLED: 'cancelled',
  TRANSFERRED: 'transferred',
};

export const PAYMENT_METHODS = {
  CREDIT_CARD: 'credit_card',
  DEBIT_CARD: 'debit_card',
  PIX: 'pix',
};

export const USER_ROLES = {
  USER: 'user',
  ADMIN: 'admin',
};

export const TOAST_CONFIG = {
  SUCCESS: {
    type: 'success',
    duration: 3000,
  },
  ERROR: {
    type: 'error',
    duration: 5000,
  },
  INFO: {
    type: 'info',
    duration: 3000,
  },
  WARNING: {
    type: 'warning',
    duration: 4000,
  },
};

export const API_TIMEOUT = 30000; // 30 seconds

export const STORAGE_KEYS = {
  AUTH_TOKEN: 'auth_token',
  USER_DATA: 'user_data',
  THEME: 'theme',
  LANGUAGE: 'language',
};

export const REGEX_PATTERNS = {
  EMAIL: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
  PHONE: /^\(\d{2}\)\s?\d{4,5}-\d{4}$/,
  CPF: /^\d{3}\.\d{3}\.\d{3}-\d{2}$/,
  PASSWORD: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/,
  URL: /^(https?:\/\/)?([\w.-]+)(\.\w+)+([/?].*)?$/,
};
