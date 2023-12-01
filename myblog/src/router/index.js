import Vue from 'vue'
import VueRouter from 'vue-router'
//import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)
const originaPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originaPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: '/home',
    name: 'home',
    component: ()=>import('../views/blog/BlogPage.vue')
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path:'/edit',
    name:'Edit',
    component:()=>import('../views/blog/BlogMain.vue')
  },
  {
    path:'/show',
    name:'Show',
    component:()=>import('../views/blog/BlogShow.vue')
  },
  {
    path:'/',
    name:'Login',
    component:()=>import('../views/blog/BlogLogin.vue')
  },
  {
    path:'/all',
    name:'All',
    component:()=>import('../views/blog/BlogShowAll.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
