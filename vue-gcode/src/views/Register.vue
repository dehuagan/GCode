<template>
  <a-form :form="form" @submit="handleSubmit" class="login-form">
    <h2 class="title"><img style="height: 50px;" :src="img"></h2>
    <a-form-item v-bind="formItemLayout" label="Username">
      <a-input  allow-clear
        v-decorator="[
          'username',
          {
            rules: [
              {
                required: true,
                message: 'Please input your username!',
              },
              {
                validator: validateUsername,
              },
            ],
          },
        ]"
      />
    </a-form-item>
    <a-form-item v-bind="formItemLayout" label="E-mail">
      <a-input allow-clear
        v-decorator="[
          'email',
          {
            rules: [
              {
                type: 'email',
                message: 'The input is not valid E-mail!',
              },
              {
                required: false,
                message: 'Please input your E-mail!',
              },
            ],
          },
        ]"
      />
    </a-form-item>
    <a-form-item v-bind="formItemLayout" label="Password" has-feedback>
      <a-input allow-clear
        v-decorator="[
          'password',
          {
            rules: [
              {
                required: true,
                message: 'Please input your password!',
              },
              {
                validator: validateToNextPassword,
              },
            ],
          },
        ]"
        type="password"
      />
    </a-form-item>
    <a-form-item v-bind="formItemLayout" label="Confirm Password" has-feedback>
      <a-input allow-clear
        v-decorator="[
          'confirm',
          {
            rules: [
              {
                required: true,
                message: 'Please confirm your password!',
              },
              {
                validator: compareToFirstPassword,
              },
            ],
          },
        ]"
        type="password"
        @blur="handleConfirmBlur"
      />
    </a-form-item>
    <a-form-item v-bind="tailFormItemLayout">
      <a-checkbox v-decorator="['agreement', { valuePropName: 'checked',
       rules: [
              {
                required: true,
                message: 'Please confirm your agreement!',
              }
            ],}]">
        I have read the
        <a href="">
          agreement
        </a>
      </a-checkbox>
    </a-form-item>
    <a-form-item v-bind="tailFormItemLayout">
      <a-button type="primary" html-type="submit">
        Register
      </a-button>
      <router-link to="/login">
        go back to sign in
      </router-link>
    </a-form-item>
  </a-form>
</template>

<script>
import logo from "../assets/logo.png";
const residences = [
  {
    value: 'zhejiang',
    label: 'Zhejiang',
    children: [
      {
        value: 'hangzhou',
        label: 'Hangzhou',
        children: [
          {
            value: 'xihu',
            label: 'West Lake',
          },
        ],
      },
    ],
  },
  {
    value: 'jiangsu',
    label: 'Jiangsu',
    children: [
      {
        value: 'nanjing',
        label: 'Nanjing',
        children: [
          {
            value: 'zhonghuamen',
            label: 'Zhong Hua Men',
          },
        ],
      },
    ],
  },
];

export default {
  data() {
    return {
      img:logo,
      confirmDirty: false,
      // residences,
      autoCompleteResult: [],
      formItemLayout: {
        labelCol: {
          xs: { span: 24 },
          sm: { span: 6 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 13 },
        },
      },
      tailFormItemLayout: {
        wrapperCol: {
          xs: {
            span: 24,
            offset: 0,
          },
          sm: {
            span: 16,
            offset: 8,
          },
        },
      },
    };
  },
  beforeCreate() {
    this.form = this.$form.createForm(this, { name: 'register' });
  },
  methods: {


    handleSubmit(e) {
      e.preventDefault();
      window.console.log("click register btn "+e.username);
      // var _this = this;
      // this.postRequest('/register',{
      //
      // }
      this.form.validateFieldsAndScroll((err, values) => {
        if (!err) {
          var _this = this;
          this.postRequest('/register',{
            username: values.username,
            password: this.$md5(values.password),
            email: values.email
          }).then(resp=>{
            window.console.log("yes");
            _this.loading = false;
            if(resp && resp.status == 200){
              _this.$router.replace({path: '/login'});
              // var data = resp.data;
              // _this.$store.commit('login',data.obj);

            }
          });
          window.console.log('Received values of form: ', values.password);
        }
      });
    },
    handleConfirmBlur(e) {
      const value = e.target.value;
      this.confirmDirty = this.confirmDirty || !!value;
    },
    compareToFirstPassword(rule, value, callback) {
      const form = this.form;
      if (value && value !== form.getFieldValue('password')) {
        callback('Two passwords that you enter is inconsistent!');
      } else {
        callback();
      }
    },
    validateToNextPassword(rule, value, callback) {
      const form = this.form;
      if (value && this.confirmDirty) {
        form.validateFields(['confirm'], { force: true });
      }
      callback();
    },
    validateUsername(rule, value, callback){
      window.console.log("value--->"+value);
      this.postRequest('/validate_username',{
        username: value
      }).then(resp=>{
        window.console.log(resp);
        if(resp.data){
          callback('username exists');
        }else{
          callback();
        }
      });
    },
    handleWebsiteChange(value) {
      let autoCompleteResult;
      if (!value) {
        autoCompleteResult = [];
      } else {
        autoCompleteResult = ['.com', '.org', '.net'].map(domain => `${value}${domain}`);
      }
      this.autoCompleteResult = autoCompleteResult;
    },
  },
};
</script>
<style scoped>
.title {

  margin-top: 100px;
  text-align: center;
}
.login-form {
  width: 850px;
  height: 372px;
  margin: 0 auto;
  /*background: url("../assets/houTaiKuang.png");*/
  padding: 50px 110px;
}

</style>