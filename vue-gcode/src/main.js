import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
// import {CodeMirror} from 'vue-codemirror-lite'
import { Button, Layout, Breadcrumb, Menu, Rate, Descriptions, Table, Modal, Upload, Slider, Icon, Space, Card, Avatar, Progress, Badge, Drawer, Row, Col, Divider, Tabs, Select, Skeleton, Dropdown, List, Spin, Input, Tag } from "ant-design-vue"

Vue.config.productionTip = false;
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
Vue.use(Dropdown)
Vue.use(Upload)
Vue.use(List)
Vue.use(Rate)
Vue.use(Modal)
Vue.use(Tag)
Vue.use(Badge)
Vue.use(Spin)
Vue.use(Skeleton)
Vue.use(Table)
Vue.use(Drawer)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
