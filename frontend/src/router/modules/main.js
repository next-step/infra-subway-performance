import MainPage from '@/views/main/MainPage'

const mainRoutes = [
  {
    path: '/',
    component: () => import(/* webpackChunkName: "main" */ '@/views/main/MainPage')
  }
]
export default mainRoutes
