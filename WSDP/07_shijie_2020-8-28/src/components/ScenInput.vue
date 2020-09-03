<template>
  <el-form-item :label="label">
    <el-autocomplete
      v-model="form.name"
      :fetch-suggestions="querySearchAsync"
      :placeholder="starHolder"
      @select="handleSelect"
      :clearable="true"
    ></el-autocomplete>
  </el-form-item>
</template>

<script>
export default {
  props: ['label', 'starHolder', 'api'],
  data () {
    return {
      form: {
        name: ''
      }
    }
  },
  methods: {
    async querySearchAsync (queryString, callback) {
      // 调用远程接口 将返回数据封装成 [{value:xxx,key2:value2},{value:xxx,key2:value2}]这样的形式，其中value关键字是必须的，检索框要根据该字段显示，其余的根据需求而定
      const res = await this.api()
      let list = []
      res.data.fuzzySceneList.forEach(item => {
        list.push({
          value: item
        })
      })
      callback(list)
    },

    // @select="handleSelect"  是选中某一列触发的事件,在这里item为选中字段所在的对象
    handleSelect (item) {
    }
  }
}
</script>

<style>
</style>
