<template>
  <a-table
    :columns="columns"
    :row-key="record => record.pid"
    :data-source="data"
    :pagination="pagination"
    :loading="loading"

    @change="handleTableChange"

  >
<!--    to="/problems/problemdetail"-->
    <template slot="problem_title" slot-scope="text,record"> <a @click="gotoDetail(record)"> {{ text }}  </a></template>
    <template slot="difficulty" slot-scope="text" ><div v-if="text==='Easy'" style="color: #1DA57A">{{text}}</div>
      <div v-else-if="text==='Medium'" style="color: orange">{{text}}</div>
      <div v-else style="color: red">{{text}}</div>
    </template>
  </a-table>
</template>
<script>
// import reqwest from 'reqwest';
const columns = [
  {
    title: 'Solved'
  },
  {
    title: '#',
    dataIndex: 'pid',
    sorter: true,
    key: 'pid',
    // width: '20%',
    scopedSlots: { customRender: 'pid' },
  },
  {
    title: 'Title',
    dataIndex: 'title',
    sorter: true,
    key: 'title',
    // width: '20%',
    scopedSlots: { customRender: 'problem_title' },
  },
  {
    title: 'Difficulty',
    key: 'difficulty',
    dataIndex: 'difficulty',
    scopedSlots: {customRender: 'difficulty'}
  },
];

export default {
  data() {
    return {
      data: [],
      pagination: {
        'show-quick-jumper': true,
        // '@change': "onChange"
      },
      loading: false,
      columns,
    };
  },
  mounted() {
    this.fetch();
  },
  methods: {
    gotoDetail(value){
      window.console.log(value);
      this.$router.push({path:'/problems/problemdetail', query:{pid: value.pid}});
    },
    handleTableChange(pagination, filters, sorter) {
      console.log(pagination);
      const pager = { ...this.pagination };
      pager.current = pagination.current;
      this.pagination = pager;
      // this.fetch({
      //   results: pagination.pageSize,
      //   page: pagination.current,
      //   sortField: sorter.field,
      //   sortOrder: sorter.order,
      //   ...filters,
      // });
      this.fetch();
    },
    fetch() {
      this.getRequest('/getAllProblems',{
        pageNum: this.pagination.current
      }).then(resp=>{
        this.loading = true;
        if(resp && resp.status == 200){
          this.loading = false;
          const pagination = { ...this.pagination };
          pagination.total = resp.data.obj.total;
          pagination.pageSize = resp.data.obj.pageSize;
          this.data = resp.data.obj.list;
          window.console.log("data===>"+ this.data[0]);
          this.pagination = pagination;
        }
      });






      // console.log('params:', params);
      // this.loading = true;
      // reqwest({
      //   url: 'https://randomuser.me/api',
      //   method: 'get',
      //   data: {
      //     results: 10,
      //     ...params,
      //   },
      //   type: 'json',
      // }).then(data => {
      //   const pagination = { ...this.pagination };
      //   // Read total count from server
      //   // pagination.total = data.totalCount;
      //   pagination.total = 200;
      //   this.loading = false;
      //   this.data = data.results;
      //   this.pagination = pagination;
      // });
    },
  },
};
</script>
