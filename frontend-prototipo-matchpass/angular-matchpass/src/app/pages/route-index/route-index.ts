import { Component } from '@angular/core';

type RouteGroup = {
  title: string;
  links: {
    label: string;
    path: string;
    isProtected?: boolean;
  }[];
};

@Component({
  selector: 'app-route-index',
  imports: [],
  templateUrl: './route-index.html',
  styleUrl: './route-index.scss',
})
export class RouteIndex {
  readonly routeGroups: RouteGroup[] = [
    {
      title: 'Principais',
      links: [
        { label: 'Landing / Index', path: '/index' },
        { label: 'Home', path: '/home', isProtected: true },
        { label: 'Scanner', path: '/scanner' },
      ],
    },
    {
      title: 'Autenticacao',
      links: [
        { label: 'Login', path: '/login' },
        { label: 'Cadastro', path: '/cadastro' },
        { label: 'Recuperar senha', path: '/recuperar-senha' },
      ],
    },
    {
      title: 'Eventos',
      links: [
        { label: 'Show', path: '/show', isProtected: true },
        { label: 'Club', path: '/club', isProtected: true },
        { label: 'Ingresso', path: '/ingresso', isProtected: true },
      ],
    },
    {
      title: 'Ingressos',
      links: [
        { label: 'Meus ingressos', path: '/meus-ingressos', isProtected: true },
        { label: 'Checkout', path: '/checkout', isProtected: true },
        { label: 'Selecao de setor', path: '/selecao-setor', isProtected: true },
        { label: 'Transferencia', path: '/transferencia', isProtected: true },
        { label: 'Avaliacao', path: '/avaliacao', isProtected: true },
      ],
    },
    {
      title: 'Usuario',
      links: [
        { label: 'Perfil', path: '/user/perfil', isProtected: true },
        { label: 'Suporte', path: '/user/suporte', isProtected: true },
      ],
    },
    {
      title: 'Admin',
      links: [
        { label: 'Dashboard', path: '/admin/dashboard', isProtected: true },
        { label: 'Configuracoes', path: '/admin/configuracoes', isProtected: true },
      ],
    },
    {
      title: 'Feedback e status',
      links: [
        { label: 'Sucesso', path: '/sucesso' },
        { label: 'Loading', path: '/loading' },
      ],
    },
    {
      title: 'Erros',
      links: [
        { label: '403', path: '/error-403' },
        { label: '404', path: '/error-404' },
        { label: '500', path: '/error-500' },
        { label: '503', path: '/error-503' },
      ],
    },
  ];
}
