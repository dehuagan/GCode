<template>
  <div style="width: 1000px; margin-left: auto;margin-right: auto">
  <a-layout-content style="padding: 0 50px">
    <a-layout style="padding: 24px 0; background: #fff">
      <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
    <div class="top3-base">
      <div class="top-1">
        <a-badge
          count="1"
          :number-style="{
        backgroundColor: 'yellow',
        color: '#999',
        boxShadow: '0 0 0 1px #d9d9d9 inset',
      }"
        >
          <a-avatar :size="90" icon="user" /></a-badge>
        <div class="name-tag">
        <span class="content">{{ rank[0] }}</span>
      </div>

      </div>
      <div class="top2-3">
        <a-space :size="100">
          <div>
            <a-badge
              count="2"
              :number-style="{
        backgroundColor: 'yellow',
        color: '#999',
        boxShadow: '0 0 0 1px #d9d9d9 inset',
      }"
            >
              <a-avatar :size="90" icon="user" /></a-badge>
            <div class="name-tag">
              <span class="content">{{ rank[1] }}</span>
            </div>
          </div>
          <div>
            <a-badge
              count="3"
              :number-style="{
        backgroundColor: 'yellow',
        color: '#999',
        boxShadow: '0 0 0 1px #d9d9d9 inset',
      }"
            >
              <a-avatar :size="90" icon="user" /></a-badge>
            <div class="name-tag">
              <span class="content">{{ rank[2] }}</span>
            </div>
          </div>
        </a-space>
      </div>

    </div>
        <MyList2></MyList2>
  </a-layout-content>
    </a-layout>
  </a-layout-content>
  </div>
</template>

<script>
import MyList2 from "@/components/MyList2";
export default {
  name: "Ranking",
  components:{
    MyList2
  },
  data(){
    return{
      rank:['a','b','c','d']
    }
  }
};
</script>

<style scoped>
.content{
  color: white;
  display: inline-block;
  max-width: 100px;
  min-width: 30px;
  overflow: hidden;
  -o-text-overflow: ellipsis;
  text-overflow: ellipsis;
  white-space: pre;
  background-color: #1DA57A;
  border-radius: 6px;
  padding-left: 5px;
  padding-right: 5px;
}
.name-tag{
  /*position: absolute;*/
  bottom: -18px;
  left: 0;
  width: 100%;
  text-align: center;
}
.top-1{
  width: 50%;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 20px;
}
.top-1, .top2-3{
  text-align: center;
}
.top3-base{
  text-align: center;
}
</style>