<template>
  <div>
    <el-upload
        action="http://localhost:8082/image/uploadImage"
        multiple
        :limit="1"
        :file-list="fileList">
        <el-button size="small" type="primary">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">只能上传jpg文件</div>
    </el-upload>
  </div>
</template>

<script>
    export default {
    components: {  },
        name: "uploadImage",
        data(){
          return{
            fileList: [],
            actionUrl: '',
          }
        },
        created(){
        },
        methods:{

        }
    }
</script>

<style scoped>
</style>