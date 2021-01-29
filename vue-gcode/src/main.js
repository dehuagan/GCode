import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import {getRequest} from "./utils/api"
import {postRequest } from "./utils/api";
import {putRequest } from "./utils/api";
import {deleteRequest } from "./utils/api";
// import {CodeMirror} from 'vue-codemirror-lite'
import { Button, Layout, Breadcrumb, Menu, Rate, Descriptions, Checkbox, Table, Modal, Upload, Form, Slider, Icon, Space, Card, Avatar, Progress, Badge, Drawer, Row, Col, Divider, Tabs, Select, Skeleton, Dropdown, List, Spin, Input, Tag } from "ant-design-vue"

Vue.config.productionTip = false;
Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.deleteRequest = deleteRequest;
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
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
