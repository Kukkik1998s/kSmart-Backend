import Vue from 'vue';
import Router from 'vue-router';
import Home from './view/Home.vue';
import Login from './view/Login.vue';

Vue.use(Router);

export const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/home',
      component: Home
    },
    {
      path: '/login',
      component: Login
    },
    /*{
      path: '/register',
      component: Register
    },*/
    {
      path: '/profile',
      name: 'profile',
      // lazy-loaded
      component: () => import('./view/Profile.vue')
    },
    {
      path: '/admin',
      name: 'admin',
      // lazy-loaded
      component: () => import('./view/BoardAdmin.vue')
    },
    {
      path: '/user',
      name: 'user',
      // lazy-loaded
      component: () => import('./view/BoardUser.vue')
    },
    {
      path: '/register',
      name: 'register',
      // lazy-loaded
      component: () => import('./view/Register.vue')
    },
    {
      path:'/view/:username',
      name: 'view',
      component:() => import('./view/UserDetail.vue')
    },
    {
      path: '/foods',
      name: 'foods',
      // lazy-loaded
      component: () => import('./view/BoardFood.vue')
    },
    
    {
      path:'/food/:foodId',
      name: 'food',
      component:() => import('./view/FoodDetail.vue')
    },
    
  ]
});

router.beforeEach((to, from, next) => {
    const publicPages = ['/login', '/home'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');
  
    // trying to access a restricted page + not logged in
    // redirect to login page
    if (authRequired && !loggedIn) {
      next('/login');
    } else {
      next();
    }
  });