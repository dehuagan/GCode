import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import {getRequest} from "./utils/api"
import {postRequest } from "./utils/api";
import {putRequest } from "./utils/api";
import {deleteRequest } from "./utils/api";
import md5 from 'js-md5';
// import {CodeMirror} from 'vue-codemirror-lite'
import { Button, Layout, Breadcrumb, Menu, Rate, Descriptions, Checkbox, Table, Modal, Upload, Form, Slider, Icon, Space, Card, Avatar, Progress, Badge, Drawer, Row, Col, Divider, Tabs, Select, Skeleton, Dropdown, List, Spin, Input, Tag } from "ant-design-vue"

Vue.config.productionTip = false;
Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.$md5 = md5;

// Vue.use(CodeMirror)
Vue.use(Button)
Vue.use(Input)
Vue.use(Layout)
Vue.use(Breadcrumb)
Vue.use(Menu)
Vue.use(Icon)
Vue.use(Slider)
Vue.use(Space)
Vue.use(Card)
Vue.use(Avatar)
Vue.use(Progress)
Vue.use(Row)
Vue.use(Col)
Vue.use(Divider)
Vue.use(Descriptions)
Vue.use(Tabs)
Vue.use(Select)
Vue.use(Checkbox)
Vue.use(Dropdown)
Vue.use(Upload)
Vue.use(List)
Vue.use(Rate)
Vue.use(Modal)
Vue.use(Tag)
Vue.use(Badge)
Vue.use(Form)
Vue.use(Spin)
Vue.use(Skeleton)
Vue.use(Table)
Vue.use(Drawer)

router.beforeEach((to, from, next)=>{
  // if(to.path == '/register'){
  //   next({path:'/register'})
  // }
  if(to.path == '/login'){
    sessionStorage.removeItem('token');
  }
  let user = sessionStorage.getItem('token');
  // let user = this.$store
  if(!user && to.path != '/login'){
    if(to.path == '/register'){
      next();
    }else
      next({path:'/login'})
  } else if(user && to.path != '/login' && to.path != '/404' && to.path != '/'){
    window.console.log("enter the to page");
    let allow = true
    if(to.path === '/problems'){
      allow = true
    }
    if (allow) {
      console.log('有权限进入' + to.path);
      next()
    } else {
      console.log('没有权限进入' + to.path);
      next({ path: '/404' })
    }
  } else{
    next()
  }
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
