<template>
<div class="profile">
  <a-layout-content style="padding: 0 50px">
    <a-layout style="padding: 24px 0; background: #f5f5f5">
      <a-layout-sider width="40%" style="background: #f5f5f5;">
        <div>
          <div :class="card">
          <a-card title="User Profile" style="border-radius:15px; width: 95%; margin-left: 20px; height: 180px;">
            <a slot="extra" href="#"><a-button :size="btn_size" @click="showDrawer">
              <a-icon type="edit" />
            </a-button></a>
            <div class="avatar" style="float: left; width: 110px">
            <Avatar></Avatar> </div>
              <h2>nihao</h2>
              <a-rate  :default-value="3" disabled />


          </a-card>
          </div>
          <a-drawer width="540" placement="right" :closable="false" :visible="visible" @close="onClose">
            <p :style="[pStyle, pStyle2]">
              User Profile
            </p>

            <a-row style="text-align: center">
              <a-input placeholder="reset user name" allow-clear @change="onChange" />
              <br />
              <br />
              <br />
              <a-input-password placeholder="reset password" />
              <br />
              <br />
              <br />
              <a-input placeholder="reset user email" allow-clear @change="onChange" />
              <br /><br /><br />
              <a-button type="primary">confirm</a-button>
            </a-row>





          </a-drawer>

        </div>
        <Progress style="border-radius:15px; width: 95%; margin: 20px; height: 180px;" />

      </a-layout-sider>
      <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
        <MyList2 style="margin-bottom: 20px;" title="Most Recent Submissions"></MyList2>
        <MyList2 title="Recent Discussions"></MyList2>
      </a-layout-content>

    </a-layout>
  </a-layout-content>

</div>
</template>

<script>
import Avatar from "@/components/Avatar";
import Progress from "@/components/Progress";
import MyList2 from "@/components/MyList2";
export default {
  name: "Profile",
  data() {
    return {
      btn_size: 'small',

      visible: false,
      card:"",
      pStyle: {
        fontSize: '16px',
        color: 'rgba(0,0,0,0.85)',
        lineHeight: '24px',
        display: 'block',
        marginBottom: '16px',
      },
      pStyle2: {
        marginBottom: '24px',
      },
    };
  },
  methods: {
    afterVisibleChange(val) {
      console.log('visible', val);
    },
    showDrawer() {
      this.visible = true;
    },
    onClose() {
      this.visible = false;
    },
  },
  components:{
    Avatar,
    Progress,
    MyList2
  }
};
</script>

<style scoped>
.profile{
  width: 1000px;
  margin-left: auto;
  margin-right: auto
}
</style>