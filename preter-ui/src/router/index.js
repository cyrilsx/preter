// Composables
import { createRouter, createWebHistory } from 'vue-router'
import {useAppStore} from "@/store/app";

const routes = [
  {
    path: '/',
    component: () => import('@/layouts/default/Default.vue'),
    children: [
      {
        path: '',
        name: 'Home',
        // route level code-splitting
        // this generates a separate chunk (Home-[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('@/views/Home.vue'),
      }, {
        path: '/community',
        name: 'Community',
        component: () => import('@/views/Community.vue'),
      }, {
        path: '/object',
        name: 'Object',
        component: () => import('@/views/Object.vue'),
      }, {
        path: '/borrow',
        name: 'Borrow',
        component: () => import('@/views/Borrow.vue'),
      }, {
        path: '/profile',
        name: 'Profile',
        component: () => import('@/views/Profile.vue'),
      }, {
        path: '/account',
        name: 'Account',
        component: () => import('@/views/Account.vue'),
      }, {
        path: '/register',
        name: 'Register',
        component: () => import('@/views/Register.vue'),
      }, {
        path: '/login',
        name: 'Login',
        component: () => import('@/views/Login.vue'),
      },
    ],
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

router.beforeEach(async (to, from) => {
  if (useAppStore().isAnonymous && to.name !== 'Login' && to.name !== 'Register') {
    // redirect the user to the login page
    return { name: 'Login' }
  }
})

export default router
