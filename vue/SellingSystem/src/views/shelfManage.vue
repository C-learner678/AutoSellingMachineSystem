<template>
  <div>
    <el-col :span="2">&nbsp;</el-col>
    <el-col :span="12">
      <el-table :data="shelfItemList" style="width: 100%" height="500">
        <el-table-column prop="position" label="位置" width="100">
        </el-table-column>
        <el-table-column prop="name" label="商品" width="250">
        </el-table-column>
        <el-table-column prop="num" label="数量" width="100">
        </el-table-column>
        <el-table-column
            fixed="right"
            label="操作"
            width="100">
            <template slot-scope="scope">
                <el-button type="text" @click="handleClick(scope.$index)">修改</el-button>
            </template>
        </el-table-column>
      </el-table>
    </el-col>
    <el-dialog title="货架信息" :visible.sync="dialogVisible">
        位置：{{shelfItemList[selectedRow].position}}<br><br>
        商品：
        <el-select v-model="selectedGoodId" placeholder="请选择">
        <el-option
        v-for="item in goodsList"
        :key="item.name"
        :label="item.name"
        :value="item.id">
        </el-option>
        </el-select>
        <el-button @click="refresh()">刷新商品列表</el-button>
        <br><br>
        数量：
        <el-input-number v-model="goodsNum" :min="0" :max="15"></el-input-number>
        <br><br><el-button type="primary" @click="submit()">确认修改</el-button>
        <el-button type="warning" @click="clear()">清空本位置</el-button>
    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: "ShelfManage",
        data(){
          return{
            dialogVisible:false,
            selectedRow:0,
            selectedGoodId:'',
            goodsNum: 0,
            shelfItemList:[],
            goodsList:[],
          }
        },
        created(){
            this.$store
            .dispatch('GetShelf')
            .then(response => {
                if (response.data !== '') { 
                    this.shelfItemList = response.data;
                }
            });
            this.$store
            .dispatch('GetAllGoods')
            .then(response => {
                if (response.data !== '') { 
                    this.goodsList = response.data;
                }
            });
        },
        methods: {
            handleClick(row){
              this.selectedRow = row
              this.dialogVisible = true
              this.selectedGoodId = this.shelfItemList[this.selectedRow].goodsid
              this.goodsNum = this.shelfItemList[this.selectedRow].num
            },
            submit(){
                this.$store
                .dispatch('SetShelfItem',{
                  position: this.shelfItemList[this.selectedRow].position,
                  goodsid: this.selectedGoodId,
                  num : this.goodsNum
                })
                .then(response => {
                    if (response.data.status == 'success') {
                        this.shelfItemList[this.selectedRow].goodsid = this.selectedGoodId
                        this.shelfItemList[this.selectedRow].num = this.goodsNum
                        for(var i = 0;i<this.goodsList.length;i++){
                          if(this.goodsList[i].id == this.selectedGoodId){
                            this.shelfItemList[this.selectedRow].name = this.goodsList[i].name
                          }
                        }
                        this.dialogVisible = false
                        this.$message({
                            message: '修改货架成功',
                            type: 'success'
                        });
                    }else{
                        this.$message.error('修改失败');
                    }
                }).catch(() => {
                    this.$message.error('修改失败，无法连接到服务器');
                });
            },
            clear(){
                this.$store
                .dispatch('SetShelfItem',{
                  position: this.shelfItemList[this.selectedRow].position,
                  goodsid: -1,
                  num : 0
                })
                .then(response => {
                    if (response.data.status == 'success') {
                        this.shelfItemList[this.selectedRow].goodsid = -1
                        this.shelfItemList[this.selectedRow].num = 0
                        this.shelfItemList[this.selectedRow].name = ''
                        this.dialogVisible = false
                        this.$message({
                            message: '修改货架成功',
                            type: 'success'
                        });
                    }else{
                        this.$message.error('修改失败');
                    }
                }).catch(() => {
                    this.$message.error('修改失败，无法连接到服务器');
                });              
            },
            refresh(){
                this.$store
                .dispatch('GetAllGoods')
                .then(response => {
                    if (response.data !== '') { 
                        this.goodsList = response.data;
                    }
                });                
            }
        }
    }
</script>

<style scoped>

</style>