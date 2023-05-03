<template>
  <div>
    <div style="text-align: left"><span>欢迎您，admin</span></div>
    <el-tabs tab-position="left" @tab-click="handleTabClick">
      <el-tab-pane label="商品管理">
        <goods-manage>
        </goods-manage>
      </el-tab-pane>
      <el-tab-pane label="货架管理">
        <shelf-manage>
        </shelf-manage>
      </el-tab-pane>
      <el-tab-pane label="货币管理">
        <money-manage>
        </money-manage>
      </el-tab-pane>
      <el-tab-pane label="修改密码">
        <el-row>
          <el-col :span="8">&nbsp;</el-col>
          <el-col :span="6">
            <el-form :model="loginForm" ref="loginForm" :rules="rules" label-width="150px">
                <div style="text-align: center">
                    修改密码<br><br>
                </div>
                <el-form-item label="原密码" prop="oldPassword">
                    <el-input type="password" v-model="loginForm.oldPassword"></el-input>
                </el-form-item>
                <el-form-item label="新密码" prop="password">
                    <el-input type="password" v-model="loginForm.password" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="再次输入新密码" prop="password2">
                    <el-input type="password" v-model="loginForm.password2" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button
                        type="primary" 
                        @click="submitForm('loginForm')"
                    >提交</el-button>
                </el-form-item>
            </el-form>
          </el-col>
        </el-row>
      </el-tab-pane>
      <el-tab-pane label="退出登录">
      </el-tab-pane>
    </el-tabs> 
  </div>
</template>

<script>
import goodsManage from './goodsManage.vue'
import moneyManage from './moneyManage.vue'
import shelfManage from './shelfManage.vue'

    export default {
        components:{moneyManage, shelfManage, goodsManage},
        name: "Admin",
        data(){
          return{
            loginForm:{
              oldPassword:'',
              password:'',
              password2:'',
            },
            rules:{
              oldPassword:[{
                required: true
              }],
              password:[{
                required: true
              }],
              password2:[{
                required: true
              }]
            }
          }
        },
        methods: {
          submitForm(form){
            this.$refs[form].validate((valid)=>{
                if(valid){
                    if(this.loginForm.password != this.loginForm.password2){
                        this.$message.error('两次输入密码不一致');
                    }else{
                        this.$store
                        .dispatch('ChangePassword',{
                          oldpsw:this.loginForm.oldPassword,
                          newpsw:this.loginForm.password
                        })
                        .then(response => {
                            if (response.data.status == 'success') {
                                this.$message({
                                    message: '密码修改成功！',
                                    type: 'success'
                                }); 
                            }else{
                              this.$message.error('修改密码失败，原密码错误');
                            }
                        }).catch(() => {
                            this.$message.error('修改密码失败，无法连接到服务器');
                        });
                    }
                }
            })
          },
          handleTabClick(tab, event){
            if(tab.label == "退出登录"){
              this.$confirm('你确定要退出登录吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                sessionStorage.setItem("token", 'false');
                this.$router.push("/login");
              }).catch(() => { 
              });
            }
          }
        }
    }
</script>

<style scoped>
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>