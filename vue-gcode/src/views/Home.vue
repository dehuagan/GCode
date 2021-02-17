<template>
  <div class="home">
    <Header></Header>
<!--    <a-layout id="components-layout-demo-top-side">-->
    <router-view></router-view>
<!--    </a-layout>-->
<!--    <Footer></Footer>-->
  </div>
</template>

<script>
import Header from "../components/Header"
import Footer from "../components/Footer"
export default {
  name: "home",
  components: {
    Header,
    Footer
  }
};
</script>
<style scoped>

#components-layout-demo-top-side{
  background-color: white;
  font-weight: bold;
}
button{
  font-weight: bold;
}
/*.home{*/
/*  height: 100%; padding: 0; box-sizing: border-box ; position: relative;*/
/*}*/
</style>