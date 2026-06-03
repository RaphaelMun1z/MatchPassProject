## Guia da Nova Estrutura do Projeto MatchPass

### 📁 Estrutura Reorganizada

O projeto foi reorganizado para seguir uma arquitetura modular profissional, facilitando manutenção, escalabilidade e reutilização de código.

### 🏗️ Core Module (`src/app/core/`)

**Propósito:** Serviços singleton, guards, interceptors e configuração global.

#### Services

- `api.service.ts` - Serviço base para requisições HTTP
- `auth.service.ts` - Autenticação, login, registro, tokens
- `user.service.ts` - Gerenciamento de perfil do usuário
- `ticket.service.ts` - Operações com ingressos e eventos

**Uso:**

```typescript
import { AuthService } from '@app/core/services';

constructor(private auth: AuthService) {}
```

#### Guards

- `auth.guard.ts` - Protege rotas autenticadas
- `admin.guard.ts` - Protege rotas de admin

**Uso em rotas:**

```typescript
{
  path: 'admin',
  children: adminRoutes,
  canActivate: [AuthGuard, AdminGuard]
}
```

### 🎨 Shared Module (`src/app/shared/`)

**Propósito:** Componentes, pipes, directives e utilitários compartilhados.

#### Componentes (`shared/components/`)

**Layout:**

- `navbar/` - Navegação principal com logout

**Common:**

- `button/` - Botão reutilizável com variantes (primary, secondary, danger, success)
- `card/` - Card com shadow e hover effect
- `loading-spinner/` - Spinner de carregamento

**Forms:**

- `input-field/` - Input com label, erro e placeholder

**Uso:**

```typescript
import { ButtonComponent, CardComponent, InputFieldComponent } from '@app/shared/components';

@Component({
  imports: [ButtonComponent, CardComponent],
})
export class MyComponent {}
```

#### Constantes (`shared/constants.ts`)

- Rotas da aplicação
- Tipos de ingresso, status, métodos de pagamento
- Regex patterns para validação
- Mensagens de toast

#### Interfaces (`shared/interfaces/`)

- Modelos de dados globais
- Response types

### 📑 Pages Module (`src/app/pages/`)

**Propósito:** Features organizadas por contexto (auth, events, tickets, etc).

#### Estrutura por Contexto

```
pages/
├── auth/
│   ├── login/
│   ├── cadastro/
│   ├── recuperar-senha/
│   └── auth.routes.ts
├── events/
│   ├── show/
│   ├── club/
│   ├── ingresso/
│   └── events.routes.ts
├── tickets/
│   ├── meus-ingressos/
│   ├── checkout/
│   ├── selecao-setor/
│   ├── transferencia/
│   ├── avaliacao/
│   └── tickets.routes.ts
├── admin/
│   ├── admin-dashboard/
│   ├── admin-configuracoes/
│   └── admin.routes.ts
├── user/
│   ├── perfil/
│   ├── suporte/
│   └── user.routes.ts
├── feedback/
│   ├── sucesso/
│   └── feedback.routes.ts
├── errors/
│   ├── error-403/
│   ├── error-404/
│   ├── error-500/
│   ├── error-503/
│   └── error.routes.ts
├── status/
│   ├── loading/
│   └── status.routes.ts
├── home/
├── index/
└── app-routing.ts
```

### 🎯 Roteamento

O roteamento foi reorganizado em módulos feature com rotas lazy-loadable:

```typescript
// app-routing.ts
{
  path: '',
  children: authRoutes,
},
{
  path: '',
  children: ticketsRoutes,
  canActivate: [AuthGuard],
}
```

### 🎨 Estilos

**Arquivo Global:** `src/app/styles/global.scss`

Inclui:

- Variáveis de cor (primária, secundária, cores de estado)
- Tipografia padrão
- Utilitários de spacing (m, p, mt, mb, etc)
- Utilitários de layout (flex, grid, container)
- Responsividade base

**Uso de Variáveis:**

```scss
background: var(--primary);
color: var(--text-secondary);
```

### ✨ Componentes Principais

#### Button

```typescript
<app-button
  variant="primary"
  size="md"
  [disabled]="isLoading"
  (onClick)="handleClick()"
>
  Clique aqui
</app-button>
```

#### Card

```typescript
<app-card [hoverable]="true">
  <h3>Título do Card</h3>
  <p>Conteúdo do card</p>
</app-card>
```

#### InputField

```typescript
<app-input-field
  id="email"
  label="Email"
  type="email"
  placeholder="seu@email.com"
  [value]="email"
  [error]="emailError"
  (onInput)="handleEmailChange($event)"
></app-input-field>
```

#### LoadingSpinner

```typescript
<app-loading-spinner
  size="lg"
  message="Carregando ingressos..."
></app-loading-spinner>
```

### 🔐 Autenticação & Guards

```typescript
// Usando AuthGuard em rotas protegidas
{
  path: 'meus-ingressos',
  component: MeusIngressos,
  canActivate: [AuthGuard]
}

// Usando AdminGuard para áreas admin
{
  path: 'admin',
  children: adminRoutes,
  canActivate: [AuthGuard, AdminGuard]
}
```

### 📡 Serviços de API

```typescript
// AuthService
this.auth.login(email, password).subscribe((user) => {
  this.auth.setUser(user);
});

// TicketService
this.ticket.getUserTickets().subscribe((tickets) => {
  // Usar tickets
});

// UserService
this.user.updateProfile(newData).subscribe((updated) => {
  // Perfil atualizado
});
```

### 🎓 Boas Práticas

1. **Componentes Standalone:** Todos os componentes usam `standalone: true`
2. **Signals:** Use `signal()` para estado reativo
3. **Modularização:** Features em pastas separadas com suas rotas
4. **Serviços:** Lógica de negócio em serviços, não em componentes
5. **Tipos:** Sempre use interfaces tipadas
6. **Imports:** Use barrel exports (index.ts) para simplicidade

### 🔧 Para Adicionar Nova Feature

1. Criar pasta em `pages/seu-contexto/sua-feature/`
2. Criar component: `sua-feature.ts`
3. Adicionar route em seu contexto `.routes.ts`
4. Registrar rota em `app-routing.ts`

Exemplo:

```typescript
// pages/tickets/novo-ticket/novo-ticket.ts
@Component({
  selector: 'app-novo-ticket',
  imports: [CommonModule, ButtonComponent],
  template: `...`
})
export class NovoTicket {}

// pages/tickets/tickets.routes.ts
{
  path: 'novo-ticket',
  component: NovoTicket,
}
```

### 📦 Estrutura de Barrel Exports

Para facilitar imports:

```typescript
// shared/components/index.ts
export * from './common';
export * from './layout/navbar/navbar';
export * from './forms/input-field/input-field';

// Uso
import { ButtonComponent, CardComponent } from '@app/shared/components';
```

---

**Data de Reorganização:** Junho 2026
**Versão:** 1.0
