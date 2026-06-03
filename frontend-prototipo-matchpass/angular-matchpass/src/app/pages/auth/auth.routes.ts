import { Routes } from '@angular/router';
import { Login } from './login/login';
import { Cadastro } from './cadastro/cadastro';
import { RecuperarSenha } from './recuperar-senha/recuperar-senha';

export const authRoutes: Routes = [
  {
    path: 'login',
    component: Login,
  },
  {
    path: 'cadastro',
    component: Cadastro,
  },
  {
    path: 'recuperar-senha',
    component: RecuperarSenha,
  },
];
