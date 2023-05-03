<template>
  <div>
    <br><br><br><br><br><br>
    <el-row>
      <el-col :span="8">&nbsp;</el-col>
      <el-col :span="6">
        <el-form :model="loginForm" ref="loginForm" :rules="rules" label-width="100px">
            <div style="text-align: center">
                管理员登录<br><br>
            </div>
            <el-form-item label="用户名" prop="name">
                <el-input v-model="loginForm.name"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="loginForm.password" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button
                    type="primary" 
                    @click="submitForm('loginForm')"
                >登录</el-button>
            </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script>
    export default {
        name: "Login",
        data(){
          return{
            loginForm:{
              name:'',
              password:'',
            },
            rules:{
              name:[{
                required: true
              }],
              password:[{
                required: true
              }]
            }
          }
        },
        methods: {
          submitForm(form){
            this.$refs[form].validate((valid)=>{
                if(valid){
                    if(this.loginForm.name != 'admin'){
                        this.$message.error('登录失败，用户名或密码错误');
                    }else{
                        this.$store
                        .dispatch('LoginIntoSystem', this.loginForm.password)
                        .then(response => {
                            if (response.data.status == 'success') {
                                sessionStorage.setItem("token", 'true'); //登录权限
                                this.$router.replace({path: '/admin'});
                            }else{
                                this.$message.error('登录失败，用户名或密码错误');
                            }
                        }).catch(() => {
                            this.$message.error('登录失败，无法连接到服务器');
                        });
                    }
                }
            })
          }
        }
    }
</script>

<style scoped>

</style>