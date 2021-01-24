<template>
  <div class="progress">
  <a-card hoverable style="width: 100%; border-radius: 30px;" :headStyle="headStyle" title="Your Progress">
    <a-progress type="circle" :percent="75" :width="100" strokeColor="green"/>
    <a-divider />
    <a-row>
      <a-col :span="12">
        <h3 style="color: green; font-weight: bold">Solved</h3>
        30/100
      </a-col>
      <a-col :span="12">
        <h3 style="color: #359dc7; font-weight: bold">Todo</h3>
        70
      </a-col>
    </a-row>
  </a-card>
  </div>
</template>

<script>
export default {
  name: "Progress",
  data(){
    return{
      'headStyle':{'font-weight': 'bold'}
    }
  }
};
</script>

<style scoped>
.progress{
  text-align: center;
}
.ant-card-actions{
  border-radius: 30px;
}

</style>