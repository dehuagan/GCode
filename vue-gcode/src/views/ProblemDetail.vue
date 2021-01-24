
<template>

  <div style="margin-left: auto; margin-right: auto">
    <a-layout-content >

      <a-layout style=" background: #fff">
        <a-layout-sider width="30%" style="background: #fff; margin-right: 10px; height: 100%;">

            <a-menu
              style="width: 100%"
              mode="horizontal"
              :default-selected-keys="['1']"
            >
              <a-menu-item key="1">
                <a-icon type="read" />Description
              </a-menu-item>
              <a-menu-item key="2">
                <a-icon type="message" />Discussion
              </a-menu-item>
              <a-menu-item key="3">
                <a-icon type="container" />Submissions
              </a-menu-item>
            </a-menu>
          <a-descriptions style="margin-top: 10px; padding-left: 20px; " title="User Info" layout="vertical">
            <a-descriptions-item label="UserName">
              Zhou Maomao
            </a-descriptions-item>
            <a-descriptions-item label="Telephone">
              1810000000
            </a-descriptions-item>
            <a-descriptions-item label="Live">
              Hangzhou, Zhejiang
            </a-descriptions-item>
            <a-descriptions-item label="Address" span="2">
              No. 18, Wantang Road, Xihu District, Hangzhou, Zhejiang, China
            </a-descriptions-item>
            <a-descriptions-item label="Remark">
              empty
            </a-descriptions-item>
          </a-descriptions>
        </a-layout-sider>
        <a-layout-content style="height:100%;">
          <a-menu
            mode="horizontal"
            :default-selected-keys="['1']"
          >

              <a-select  style="width: 120px" v-model="selectedLang">
                <a-select-option :value="optionLang.value" v-for="(optionLang,index) in optionLangs" :key="index">{{optionLang.text}}</a-select-option>

              </a-select>


          </a-menu>

          <codemirror  :value="code"></codemirror>
        </a-layout-content>
      </a-layout>
    </a-layout-content>
  </div>




</template>

<script>
import { codemirror } from 'vue-codemirror-lite'

export default {
  name: "ProblemDetail",
  data () {
    return {
      selectedLang: 'java',
      optionLangs: [
        {
          text:'Java',
          value:'java'
        },
        {
          text:'Python',
          value:'python'
        },
        {
          text:'JavaScript',
          value:'javascript'
        }
      ],
      code: 'String str = "hello world";\nstr.replace();\nfor(int i=0;i<3;i++){\n\tprint();\n}'
    }
  },
  computed: {
    editor() {
      // get current editor object
      return this.$refs.myEditor.editor
    }
  },
  mounted() {
    // use editor object...
    this.editor.focus()
    console.log('this is current editor object', this.editor)
  },
  components: {
    codemirror
  }
};
</script>

<style scoped>


</style>