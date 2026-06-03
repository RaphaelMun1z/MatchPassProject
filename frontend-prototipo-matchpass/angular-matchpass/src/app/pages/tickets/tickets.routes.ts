import { Routes } from '@angular/router';
import { MeusIngressos } from './meus-ingressos/meus-ingressos';
import { Checkout } from './checkout/checkout';
import { SelecaoSetor } from './selecao-setor/selecao-setor';
import { Transferencia } from './transferencia/transferencia';
import { Avaliacao } from './avaliacao/avaliacao';

export const ticketsRoutes: Routes = [
  {
    path: 'meus-ingressos',
    component: MeusIngressos,
  },
  {
    path: 'checkout',
    component: Checkout,
  },
  {
    path: 'selecao-setor',
    component: SelecaoSetor,
  },
  {
    path: 'transferencia',
    component: Transferencia,
  },
  {
    path: 'avaliacao',
    component: Avaliacao,
  },
];
