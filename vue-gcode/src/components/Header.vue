<template>
  <a-layout-header class="header">
    <div >
<!--    <div class="logo" />-->
      <a-button class="logo"><router-link to="/Problems"><a-icon></a-icon>hhh</router-link></a-button>
    <a-menu v-model="current" mode="horizontal" class="menu">
<!--      <a-menu-item><a-button class="logo"><a-icon></a-icon>hhh</a-button></a-menu-item>-->
        <a-menu-item key="problems"><router-link to="/Problems"> <a-icon type="appstore" />Problems </router-link></a-menu-item>
        <a-menu-item key="ranking" ><router-link to="/Ranking"> <a-icon type="ordered-list" />Ranking</router-link> </a-menu-item>
        <a-menu-item key="profile" ><router-link to="/Profile"> <a-icon type="user" />Profile</router-link> </a-menu-item>
    </a-menu>

    </div>
  </a-layout-header>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      cur_path: this.$route.path.substr(1).toLowerCase(),
      current: [],
    };
  },
  beforeMount() {
    this.setCur();
  },
  methods:{
    setCur(){
      this.current.push(this.cur_path);
    }
  }
};
</script>

<style scoped>
.logo {
  width: 100px;
  height: 40px;
  background: green;
  margin-top: 5px;
  margin-right: 50px;
  /*margin: 16px 0 0px 0;*/
  float: left;
  background-image: url("../assets/logo.png");
}
.header{
  background-color: white;
}
/*.menu{*/
/*  margin-left: 300px;*/
/*}*/
</style>