<template>

  <div>
    <a-layout-content style="padding: 0 50px">
      <a-layout style="padding: 24px 0; background: #fff">
        <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
          <div class="problem-category">
          <a-space :size="space_size">
            <a-button :size="size" shape="round" style="background-color:#359dc7; border-color: #359dc7; color: white">30/100 Solved</a-button>
            <a-button :size="size" type="primary" shape="round">easy 30</a-button>
            <a-button :size="size"  shape="round" style="background-color: orange;border-color: orange; color: white">medium 40</a-button>
            <a-button :size="size" type="danger" shape="round">hard 30</a-button>
          </a-space>
            <a-button :size="random" style="margin-left: 300px" >Pick One </a-button>
          </div>
          <a-divider />
          <a-row>
            <a-col :span="12"><a-input-search placeholder="input search" loading /></a-col>
            <a-col :span="3"></a-col>
            <a-col :span="3">
              <a-dropdown>
              <a class="ant-dropdown-link" @click="e => e.preventDefault()" >
                Difficulty <a-icon type="down" />
              </a>
              <a-menu slot="overlay" @click="onClickDifficulty">
                <a-menu-item key="Easy">
                  <a href="javascript:;">Easy</a>
                </a-menu-item>
                <a-menu-item key="Medium">
                  <a href="javascript:;">Medium</a>
                </a-menu-item>
                <a-menu-item key="Hard">
                  <a href="javascript:;">Hard</a>
                </a-menu-item>
              </a-menu>
            </a-dropdown>
            </a-col>
            <a-col :span="3">
              <a-dropdown>
                <a class="ant-dropdown-link" @click="e => e.preventDefault()">
                  Status <a-icon type="down" />
                </a>
                <a-menu slot="overlay" @click="onStatusClick">
                  <a-menu-item key="Solved">
                    <a href="javascript:;">Solved</a>
                  </a-menu-item>
                  <a-menu-item key="Todo">
                    <a href="javascript:;">Todo</a>
                  </a-menu-item>
                </a-menu>
              </a-dropdown>
            </a-col>
            <a-col :span="3">
              <a-dropdown>
                <a class="ant-dropdown-link" @click="e => e.preventDefault()">
                  Tags <a-icon type="down" />
                </a>
                <a-menu slot="overlay" @click="onTagsClick">
                  <a-menu-item>
                    <a href="javascript:;">1st menu item</a>
                  </a-menu-item>
                  <a-menu-item>
                    <a href="javascript:;">2nd menu item</a>
                  </a-menu-item>
                  <a-menu-item>
                    <a href="javascript:;">3rd menu item</a>
                  </a-menu-item>
                </a-menu>
              </a-dropdown>
            </a-col>
          </a-row>
          <div style="height: 15px;"></div>
          <div class="tags">
          <template v-for="(tag) in tags">
            <a-tag :key="tag" :closable="index !== 0" @close="() => handleClose(tag)">{{tag}}</a-tag>
          </template>
          </div>
          <a-divider />
        <MyList></MyList>
        </a-layout-content>
        <a-layout-sider width="300" style="background: #fff;">
          <Progress></Progress>

        </a-layout-sider>

      </a-layout>
    </a-layout-content>
  </div>
</template>

<script>
import Progress from "../components/Progress"
import MyList from "@/components/MyList";
export default {
  name: "Problems",
  components:{
    Progress,
    MyList
  },
  data() {
    return {
      difficulty: [],
      status: [],
      problem_tags:[],
      tags: [],
      size: 'small',
      space_size: 'middle',
      random: 'normal'
    };
  },
  methods: {
    // todo
    handleClose(removeTag) {
      const tags = this.tags.filter(tag => tag !== removeTag);
      window.console.log(tags);
      if(this.difficulty.indexOf(removeTag) !== -1){
        this.difficulty.pop();
        window.console.log('handleClose -> difficulty'+this.difficulty);
      }else if(this.status.indexOf(removeTag) !== -1){
        this.status.pop();
        window.console.log('handleClose -> status'+this.status);
      }else if(this.problem_tags.indexOf(removeTag) !== -1){
        this.problem_tags.pop();
        window.console.log('handleClose -> problem_tags'+this.problem_tags);
      }
      this.tags = tags;
    },
    onClickDifficulty(e){
      window.console.log(e);
      if(this.difficulty.length !== 0){
        this.difficulty.pop();
        this.tags.splice(0,1);
        this.tags.splice(0,0,e.key);
      } else{
        this.addTag(e.key);
      }
      this.difficulty.push(e.key);
      window.console.log('difficulty ---> '+this.difficulty);
    },
    onStatusClick(e){
      window.console.log(e);
      if(this.status.length !== 0){
        this.status.pop();
        this.tags[1] = e.key;
      } else{
        this.addTag(e.key);
      }
      this.status.push(e.key);
      window.console.log('status ---> '+this.status);
    },
    onTagsClick(e){
      window.console.log(e);
      if(this.problem_tags.length !== 0){
        this.problem_tags.pop();
        this.tags[2] = e.key;
      } else{
        this.addTag(e.key);
      }
      this.problem_tags.push(e.key);
      window.console.log('tags ---> '+this.problem_tags);
    },
    addTag(tag){
      this.tags.push(tag);
      window.console.log('add tag --->'+this.tags);
    }
  }
};
</script>

<style scoped>

.problem-category, .tags{
  text-align: justify;
}
button{
  font-weight: bold;
}
</style>