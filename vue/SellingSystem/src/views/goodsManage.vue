<template>
  <div>
    <el-col :span="2">&nbsp;</el-col>
    <el-col :span="14">
        <el-table
        :data="goodsList"
        style="width: 100%">
            <el-table-column
                prop="name"
                label="名称"
                width="250">
            </el-table-column>
            <el-table-column
                prop="price"
                label="价格"
                width="100">
            </el-table-column>
            <el-table-column
                fixed="right"
                label="操作"
                width="300">
                <template slot-scope="scope">
                    <el-button type="text" @click="addImage(scope.$index)">上传图片</el-button>
                    <el-button type="text" @click="showImage(scope.$index)">查看图片</el-button>
                    <el-button type="text" @click="showModifyGoodsDialog(scope.$index)">修改信息</el-button>
                    <el-button type="text" @click="deleteGoods(scope.$index)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <br>
        <el-button type="primary" @click="Dialog1Visible = true">添加商品</el-button>
    </el-col>
    <el-dialog :visible.sync="imageVisible">
        <goods-picture :goodsid="goodsid" :key="timer" style="text-align: center"> //key用于重新加载
        </goods-picture>
    </el-dialog>
    <el-dialog :visible.sync="Dialog1Visible">
        <el-row>
          <el-col :span="12">
            名称：<br><br><el-input v-model="goodsName" type="text"></el-input><br><br>
            价格（元）：<br><br><el-input-number v-model="goodsPrice" :min="0.5" :step="0.5" step-strictly></el-input-number><br><br>
            <el-button type="primary" @click="insertGoods()">添加</el-button>
          </el-col>
        </el-row>
    </el-dialog>
    <el-dialog :visible.sync="Dialog2Visible">
        <el-row>
          <el-col :span="12">
            名称：<br><br><el-input v-model="goodsName2" type="text"></el-input><br><br>
            价格（元）：<br><br><el-input-number v-model="goodsPrice2" :min="0.5" :step="0.5" step-strictly></el-input-number><br><br>
            <el-button type="primary" @click="modifyGoods()">修改</el-button>
          </el-col>
        </el-row>
    </el-dialog>
    <el-dialog :visible.sync="Dialog3Visible">
        <upload-image :key="timer"></upload-image>
    </el-dialog>
  </div>
</template>

<script>
import goodsPicture from './goodsPicture.vue';
import uploadImage from './uploadImage.vue'

    export default {
    components: { goodsPicture, uploadImage },
        name: "goodsManage",
        data(){
          return{
            goodsid: 1,
            imageVisible: false,
            Dialog1Visible: false,
            Dialog2Visible: false,
            Dialog3Visible: false,
            goodsList:[],
            goodsName: '',
            goodsPrice: 1.0,
            goodsName2: '',
            goodsPrice2: 1.0,
            timer: ''
          }
        },
        created(){
            this.$store
            .dispatch('GetAllGoods')
            .then(response => {
                if (response.data !== '') { 
                    this.goodsList = response.data;
                }
            });
        },
        methods:{
            addImage(row){
                this.$store
                    .dispatch('SetImageId', this.goodsList[row].id)
                    .then(response => {      
                });
                this.timer = new Date().getTime() //让子组件重新加载
                this.Dialog3Visible = true    
            },
            showImage(row){
                this.timer = new Date().getTime() //让子组件重新加载
                this.goodsid = this.goodsList[row].id
                this.imageVisible = true
            },
            insertGoods(){
                this.$store
                .dispatch('InsertGoods',{
                    name: this.goodsName,
                    price: this.goodsPrice
                })
                .then(response => {
                    if (response.data.status == 'success') {
                        this.$message({
                            message: '添加成功！',
                            type: 'success'
                        });
                        this.$store
                        .dispatch('GetAllGoods')
                        .then(response => {
                            if (response.data !== '') { 
                                this.goodsList = response.data;
                            }
                        });
                    }else{
                        this.$message.error('添加失败');
                    }
                }).catch(() => {
                    this.$message.error('添加失败，无法连接到服务器');
                });                
            },
            showModifyGoodsDialog(row){
                this.Dialog2Visible = true
                this.goodsid = this.goodsList[row].id
                this.goodsName2 = this.goodsList[row].name
                this.goodsPrice2 = this.goodsList[row].price
            },
            modifyGoods(){
                this.$store
                .dispatch('ModifyGoods',{
                    id: this.goodsid,
                    name: this.goodsName2,
                    price: this.goodsPrice2
                })
                .then(response => {
                    if (response.data.status == 'success') {
                        this.$message({
                            message: '修改成功！',
                            type: 'success'
                        });
                        this.$store
                        .dispatch('GetAllGoods')
                        .then(response => {
                            if (response.data !== '') { 
                                this.goodsList = response.data;
                            }
                        });
                    }else{
                        this.$message.error('修改失败');
                    }
                }).catch(() => {
                    this.$message.error('修改失败，无法连接到服务器');
                });  
            },
            deleteGoods(row){
                this.$store
                .dispatch('DeleteGoods',this.goodsList[row].id)
                .then(response => {
                    if (response.data.status == 'success') {
                        this.$message({
                            message: '删除成功！',
                            type: 'success'
                        });
                        this.$store
                        .dispatch('GetAllGoods')
                        .then(response => {
                            if (response.data !== '') { 
                                this.goodsList = response.data;
                            }
                        });
                    }else{
                        this.$message.error('删除失败');
                    }
                }).catch(() => {
                    this.$message.error('删除失败，无法连接到服务器');
                });
            },
        }
    }
</script>

<style scoped>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>