<template>
  <a-form
    id="components-form-demo-normal-login"
    :form="form"
    class="login-form"
    @submit="handleSubmit"
  >
    <h2 class="title"><img style="height: 50px;" :src="img"></h2>
    <a-form-item>
      <a-input allow-clear
        v-decorator="[
          'username',
          { rules: [{ required: true, message: 'Please input your username!' }] },
        ]"
        placeholder="Username"
      >
        <a-icon slot="prefix" type="user" style="color: rgba(0,0,0,.25)" />
      </a-input>
    </a-form-item>
    <a-form-item>
      <a-input
        v-decorator="[
          'password',
          { rules: [{ required: true, message: 'Please input your Password!' }] },
        ]"
        type="password"
        placeholder="Password"
      >
        <a-icon slot="prefix" type="lock" style="color: rgba(0,0,0,.25)" />
      </a-input>

    </a-form-item>
    <a-form-item>
      <a-button type="primary" html-type="submit" class="login-form-button">
        Log in
      </a-button>
      Or
      <router-link to="/register">
        register now!
      </router-link>
      <a class="login-form-forgot" href="">
        Forgot password
      </a>


    </a-form-item>
  </a-form>
</template>

<script>
import logo from "../assets/logo.png";
import Qs from 'qs'
export default {
  data(){
    return{
      img:logo
    }
  },
  beforeCreate() {
    this.form = this.$form.createForm(this, { name: 'normal_login' });
  },
  methods: {
    handleSubmit(e) {
      e.preventDefault();
      window.console.log("click login btn "+e.username);
      this.form.validateFieldsAndScroll((err, values) => {
        if (!err) {
          var _this = this;
          this.postRequest('/login',{
            username: values.username,
            password: this.$md5(values.password),

          }).then(resp=>{
            window.console.log("yes");
            _this.loading = false;
            if(resp && resp.status == 200){
              window.console.log("login --> username " + resp.data.obj.username);
              // window.sessionStorage.setItem('user', resp.data.obj.username);
              window.sessionStorage.setItem('token', resp.data.obj);
              _this.$store.commit('login',resp.data.obj);
              _this.$router.replace({path: '/'});
              // var data = resp.data;
              // _this.$store.commit('login',data.obj);

            }
          });
          window.console.log('Received values of form: ', values.password);
        }
      });
    },
  }


};
</script>
<style scoped>
.title {

  margin-top: 180px;
  text-align: center;
}
.login-form {
  width: 565px;
  height: 372px;
  margin: 0 auto;
  /*background: url("../assets/houTaiKuang.png");*/
  padding: 40px 110px;
}
#components-form-demo-normal-login .login-form {
  max-width: 300px;
}
#components-form-demo-normal-login .login-form-forgot {
  float: right;
}
#components-form-demo-normal-login .login-form-button {
  width: 100%;
}
</style>