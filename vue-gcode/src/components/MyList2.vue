<template>
  <div
    v-infinite-scroll="handleInfiniteOnLoad"

    :infinite-scroll-disabled="busy"
    :infinite-scroll-distance="10"
  >
    <a-skeleton :loading="skeleton_loading">
  <a-list bordered :data-source="data" style="background-color: white; border-radius: 15px; ">
    <a-list-item slot="renderItem" slot-scope="item, index">
      {{ item.email }}
    </a-list-item>
    <div slot="header">
      {{title}}
    </div>

  </a-list>
    </a-skeleton>
  </div>
</template>

<script>
import reqwest from 'reqwest';
import infiniteScroll from 'vue-infinite-scroll';
const fakeDataUrl = 'https://randomuser.me/api/?results=20&inc=name,gender,email,nat&noinfo';

export default {
  directives: { infiniteScroll },
  name: "MyList2",
  props: ['title'],
  data(){
    return {
      skeleton_loading: true,
      data: [],
      loading: false
    }
  },
  beforeMount() {
    this.fetchData(res => {
      this.data = res.results;
    });
  },
  methods: {
    fetchData(callback) {
      reqwest({
        url: fakeDataUrl,
        type: 'json',
        method: 'get',
        contentType: 'application/json',
        success: res => {
          callback(res);
        },
      });
    },
    handleInfiniteOnLoad() {
      const data = this.data;
      this.loading = true;
      if (data.length > 14) {
        this.$message.warning('Infinite List loaded all');
        this.busy = true;
        this.loading = false;
        this.skeleton_loading = false;
        return;
      }
      this.fetchData(res => {
        this.data = data.concat(res.results);
        this.loading = false;
        this.skeleton_loading = false;
      });
    },
  },
};
</script>

<style scoped>

</style>