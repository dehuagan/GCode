import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import { Button, Layout, Breadcrumb, Menu, Icon, Space, Card, Avatar, Progress, Row, Col, Divider, Tabs, Select, Dropdown, List, Input, Tag } from "ant-design-vue"

Vue.config.productionTip = false;
Vue.use(Button)
Vue.use(Input)
Vue.use(Layout)
Vue.use(Breadcrumb)
Vue.use(Menu)
Vue.use(Icon)
Vue.use(Space)
Vue.use(Card)
Vue.use(Avatar)
Vue.use(Progress)
Vue.use(Row)
Vue.use(Col)
Vue.use(Divider)
Vue.use(Tabs)
Vue.use(Select)
Vue.use(Dropdown)
Vue.use(List)
Vue.use(Tag)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
