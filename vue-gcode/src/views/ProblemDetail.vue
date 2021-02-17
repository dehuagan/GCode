<template>
  <div class="content">
    <div :style="{height:colLeft}" class="column-left">
      <div class="resize-bar"></div>
      <div class="resize-line"><span>. . .</span></div>
      <div class="resize-save">
        <a-menu
          style="width: 100%"
          mode="horizontal"
          :default-selected-keys="['1']"
        >
              <a-menu-item key="1">
                <router-link @click.native="showContent(true)" :to="{path:'/problems/problemdetail', query:{pid: this.pid}}">
                <a-icon type="read" />
                Description
                </router-link>
              </a-menu-item>
              <a-menu-item key="2">
                <router-link @click.native="showContent(false)" :to="{path: '/problems/problemdetail/discussion',query:{pid:this.pid}}">
                <a-icon type="message" />
                Discussion
                </router-link>
              </a-menu-item>
              <a-menu-item key="3">
                <router-link @click.native="showContent(false)" :to="{path: '/problems/problemdetail/submissions',query:{pid:this.pid}}">
                <a-icon type="container" />
                Submissions
                </router-link>
              </a-menu-item>
          </a-menu>

<!--        <div style="white-space: normal"><pre>{{ content }}</pre></div>-->
        <div id="description">

          <div v-if="isContent"><div class="title"><h2>{{title}}</h2></div><pre>{{ content }}</pre></div>
          <div v-else><router-view></router-view></div>
        </div>

      </div>
    </div>
    <div :style="{height:colRight}"  class="column-right">
      <div  class="choose-bar">
        <a-menu
          mode="horizontal"
          :default-selected-keys="['1']"
        >
          <a-select style="width: 120px" v-model="selectedLang">
            <a-select-option :value="optionLang.value" v-for="(optionLang,index) in optionLangs" :key="index">
              {{ optionLang.text }}
            </a-select-option>

          </a-select>


        </a-menu>
      </div>
        <div :style="{height:codeHeight}" class="code-panel">
          <codemirror :style="{height:codeHeight}" class="code-edit" ref="mycode" :value="curCode" :options="cmOptions"></codemirror>
        </div>
      <div :style="{height: configHeight+'px'}" class="config-bar">
        <div v-if="showTestCase" class="test-case" ><a-textarea :row="6" v-model="testCase" style="height: 100%"/></div>
        <div class="footer">

          <a-button @click="showCase">Console</a-button>
            <a-button>Run</a-button>
            <a-button>Submit</a-button>

        </div>

      </div>

    </div>

  </div>


</template>

<script>
import { codemirror } from 'vue-codemirror';
//codeMirror核心样式
import 'codemirror/lib/codemirror.css';
//不同语言对应的不同高亮js，vue中使用无法直接加载，只好在组件中再次引入，想要设置哪些高亮就添加对应mode的js文件
require("codemirror/mode/clike/clike.js")
require("codemirror/mode/javascript/javascript.js")
require("codemirror/mode/xml/xml.js")
require("codemirror/mode/vue/vue.js")
require("codemirror/mode/python/python.js")
require("codemirror/mode/css/css.js")
require("codemirror/mode/sql/sql.js")
require("codemirror/mode/shell/shell.js")

export default {
  name: "ProblemDetail",
  data() {
    return {
      isContent:true,
      // description: 'a',
      pid: this.$route.query.pid,
      // flag: true,
      testCase: '',
      configHeight: 70,
      showTestCase: false,
      curCode: "String str = \"hello world\";\nstr.replace();\nfor(int i=0;i<3;i++){\n\tprint();\n}",
      cmOptions: { //codeMirror的配置
        value: '', //编辑器的初始值（文本）
        mode: "text/javascript", //以什么格式进行代码高亮
        theme: "darcula",// ambiance, foo bar, darcula //配置编辑器的主题样式
        indentUnit: 4, //缩进单位
        smartIndent: true, // 自动缩进
        tabSize: 4, //tab字符的宽度
        lineNumbers: true, // 是否显示行
        firstLineNumber: 1, //第一行的行号
        showCursorWhenSelecting: true, // 在选择时是否显示光标
        readOnly: false //是否只读,不能获取焦点
      },
      fileContent: '',

      fullHeight: document.documentElement.clientHeight,
      title: '',
      content: '',
      selectedLang: "java",
      optionLangs: [
        {
          text: "Java",
          value: "java"
        },
        {
          text: "Python",
          value: "python"
        },
        {
          text: "JavaScript",
          value: "javascript"
        }
      ],

    };
  },
  computed: {
    colLeft: function (){
      return (this.fullHeight-70)+'px';

    },
    colRight: function (){
      return (this.fullHeight-70)+'px';
    },
    codeHeight: function(){
      return (this.fullHeight-195)+'px';
    },

    editor() {
      // get current editor object
      return this.$refs.mycode.codemirror;
    }
  },
  mounted() {

    var _this = this;
    _this.isContent = true;
    window.console.log("iscontent "+ _this.isContent)
    const that = this
    window.onresize = () => {
      return (() => {
        window.fullHeight = document.documentElement.clientHeight
        that.fullHeight = window.fullHeight
      })()
    }
    this.fetchData();
    // use editor object...

    // window.console.log("this is current editor object", this.editor.getValue());
  },
  components: {
    codemirror
  },
  methods: {
    showContent(val){
      this.isContent = val;
      window.console.log('iscontent'+val)
    },
    showCase(){
      if(this.showTestCase===true){
        this.showTestCase=false;
        this.configHeight = 70;
      }
      else{
        this.showTestCase = true;
        this.configHeight = 160;
      }
    },
    submission(){
      window.console.log(this.$refs.mycode.codemirror.getValue());
    },
    fetchData() {

      this.getRequest('/getProblemDetail',{
        pid:this.pid
      }).then(resp=>{
        if(resp && resp.status == 200){
          window.console.log(resp.data.obj);
          var res = resp.data.obj;
          this.title = res.title;
          this.content = res.content;
        }
      });
    }
  },
  watch:{
    isContent(val){
      window.console.log('iscontent'+val)
    },
    fullHeight (val) {
      if(!this.timer) {
        this.fullHeight = val
        window.console.log(this.fullHeight+'----')
        this.timer = true
        let that = this
        setTimeout(function (){
          that.timer = false
        },400)
      }
    }
  }
};
</script>

<style scoped>
pre {
  white-space: pre-wrap;
  word-wrap: break-word;
}
/*.content{*/
/*  height: 100%;*/
/*  padding: 0;*/
/*  box-sizing: border-box ;*/
/*  position: relative;*/
/*}*/


mark {
  margin-top: 0;
  margin-bottom: 1em;
  overflow: auto;
}
.resize-save {
  position: absolute;
  top: 0;
  right: 5px;
  bottom: 0;
  left: 0;
  padding: 16px;
  overflow: hidden;
}

.resize-bar {
  min-width: 50px;
  width: 470px;
  max-width: 470px;
  height: inherit;
  resize: horizontal;
  cursor: ew-resize;
  opacity: 0;
  overflow: scroll;
}

.resize-line {
  width: 10px;
  position: absolute;
  right: 0;
  top: 22px;
  bottom: 0;
  border-right: 2px solid #eee;
  border-left: 1px solid #eee;
  pointer-events: none;
}

.resize-bar:hover~.resize-line,
.resize-bar:active~.resize-line {
  border-left: 1px dashed skyblue;
}

.resize-bar::-webkit-scrollbar {
  width: 200px;
  height: inherit;
}
@supports (-moz-user-select: none) {

  .resize-bar:hover~.resize-line,
  .resize-bar:active~.resize-line {
    border-left: 1px solid #eee;
  }

  .resize-bar:hover~.resize-line::after,
  .resize-bar:active~.resize-line::after {
    content: '';
    position: absolute;
    width: 16px;
    height: 16px;
    bottom: 0;
    right: -8px;
    /*background: url(./resize.svg);*/
    background-size: 100% 100%;
  }
}
.column-left {
  /*height: 400px;*/
  background-color: #fff;
  position: relative;
  float: left;
  overflow-y: scroll;
}


.column-right {
  /*height: 400px;*/
  /*padding: 16px;*/
  /*margin-top: 50px;*/
  background-color: #fff;
  box-sizing: border-box;
  overflow: hidden;
}

.code-panel{
  z-index: 9998;
  width: 100%;
  /*height: 85vh;*/
  background-color: #1DA57A;
  overflow-y: scroll;
  margin-bottom: 0;
}


.footer {
  /*z-index: 9999;*/
  position: fixed;
  bottom: 0px;
  width: 100%;
  height: 70px;
  background-color: #aaaaaa;
  display: flex;
  align-items: center;
  /*justify-content: space-between;*/
}
.footer #left{
  width: 100px;
  /*height:100px;*/
}
.footer #right{
  width: 200px;
  /*height:200px;*/
  background-color: red;
}

.choose-bar{
  margin-top: 17px;
  position: relative;
  height: 47px;
  width: 100%;
  /*margin-bottom: 0;*/
  /*border-bottom: 1px solid #e8e8e8;*/
}
.test-case{
  height: 90px;
  z-index: 9999;
  width: 100%;
  position: relative;
  margin-bottom: 5px;


  /*margin-bottom: 1px;*/
}
.config-bar{
  z-index: 9999;
  position: fixed;
  bottom: 0px;
  width: 100%;
  background-color: #aaa;
}
#description{
  height: 100%;
  overflow-y: scroll;
}
.title{
  width: 100%;
  padding: 10px;
  /*height: 50px;*/
}
</style>