import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";

Vue.use(Router);

export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/login",
      component: () =>
        import(/* webpackChunkName: "login" */ "./views/Login.vue")
    },
    {
      path: "/register",
      component: () =>
        import(/* webpackChunkName: "register" */ "./views/Register.vue")
    },
    {
      path: "/",
      name: "home",
      redirect: "/problems",
      component: Home,
      children:[
        {
          path: "/problems",
          name: "problems",
          component: () =>
            import(/* webpackChunkName: "problems" */ "./views/Problems.vue")
        },
        {
          path: "/discussion",
          name: "discussion",
          component: () =>
            import(/* webpackChunkName: "discussion" */ "./views/Discussion.vue")
        },
        {
          path: "/ranking",
          name: "ranking",
          component: () =>
            import(/* webpackChunkName: "ranking" */ "./views/Ranking.vue")
        },
        {
          path: "/profile",
          name: "profile",
          component: () =>
            import(/* webpackChunkName: "profile" */ "./views/Profile.vue")
        }
      ]
    },
    {
      path: "/about",
      name: "about",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import(/* webpackChunkName: "about" */ "./views/About.vue")
    }
  ]
});
