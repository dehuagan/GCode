<template>
  <a-layout-header class="header">

    <div class="logo" />
    <a-menu v-model="current" mode="horizontal" class="menu">
                <a-menu-item key="problems"><router-link to="/Problems"> <a-icon type="appstore" />Problems </router-link></a-menu-item>

<!--                <a-menu-item key="discussion"> <router-link to="/Discussion"><a-icon type="message" />Discussion </router-link></a-menu-item>-->
                <a-menu-item key="ranking" ><router-link to="/Ranking"> <a-icon type="ordered-list" />Ranking</router-link> </a-menu-item>
                <a-menu-item key="profile" ><router-link to="/Profile"> <a-icon type="user" />Profile</router-link> </a-menu-item>

              </a-menu>
  </a-layout-header>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      current: ['problems'],
    };
  }
};
</script>

<style scoped>
.logo {
  width: 120px;
  height: 31px;
  background: green;
  /*margin: 16px 0 0px 0;*/
  float: left;
  background-image: url("../assets/logo.png");
}
.header{
  background-color: white;
}
</style>