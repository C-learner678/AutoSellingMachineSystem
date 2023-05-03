<template>
  <div>
    <el-col :span="2">&nbsp;</el-col>
    <el-col :span="10">
        <el-table
        :data="tableData"
        style="width: 100%">
            <el-table-column
                prop="value"
                label="货币"
                width="180">
            </el-table-column>
            <el-table-column
                prop="num"
                label="数量"
                width="100">
            </el-table-column>
            <el-table-column
                fixed="right"
                label="操作"
                width="100">
                <template slot-scope="scope">
                    <el-button type="text" @click="handleClick(scope.$index)">修改数量</el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-col>
    <el-dialog title="货币数量" :visible.sync="dialogVisible">
        {{tableData[selectedRow].value}}：
        <el-input-number v-model="moneyNum" @change="handleChange()" :min="0" :max="100"></el-input-number>
        <br><br>{{text}}
        <br><br><el-button type="primary" @click="submit()">确定</el-button>
    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: "MoneyManage",
        data(){
          return{
            dialogVisible: false,
            selectedRow: 0,
            moneyNum: 0,
            text:'',
            tableData:[{
              value:'0.5元硬币',
              num:0,
            },{
              value:'1元硬币',
              num:0,
            },{
              value:'1元纸币',
              num:0,
            },{
              value:'5元纸币',
              num:0,
            },{
              value:'10元纸币',
              num:0,
            },{
              value:'20元纸币',
              num:0,
            },{
              value:'50元纸币',
              num:0,
            },{
              value:'100元纸币',
              num:0,
            },
            ]
          }
        },
        created(){
            this.$store
            .dispatch('GetCoin')
            .then(response => {
                if (response.data != '') { 
                    for(var i=0;i<response.data.length;i++){
                        if(response.data[i].value == 0.5){
                            this.tableData[0].num = response.data[i].num
                        }else if(response.data[i].value == 1.0){
                            this.tableData[1].num = response.data[i].num
                        }
                    }
                }
            })
            this.$store
            .dispatch('GetPaperMoney')
            .then(response => {
                if (response.data != '') { 
                    for(var i=0;i<response.data.length;i++){
                        if(response.data[i].value == 1.0){
                            this.tableData[2].num = response.data[i].num
                        }else if(response.data[i].value == 5.0){
                            this.tableData[3].num = response.data[i].num
                        }else if(response.data[i].value == 10.0){
                            this.tableData[4].num = response.data[i].num
                        }else if(response.data[i].value == 20.0){
                            this.tableData[5].num = response.data[i].num
                        }else if(response.data[i].value == 50.0){
                            this.tableData[6].num = response.data[i].num
                        }else if(response.data[i].value == 100.0){
                            this.tableData[7].num = response.data[i].num
                        }
                    }
                }
            })
        },
        methods: {
          handleClick(row){
            this.dialogVisible = true
            this.selectedRow = row
            this.moneyNum = this.tableData[row].num
            this.text = ''
          },
          handleChange(){
            if(this.moneyNum > this.tableData[this.selectedRow].num){
                this.text='投入'+(this.moneyNum-this.tableData[this.selectedRow].num)+'个/张'
            }else if(this.moneyNum < this.tableData[this.selectedRow].num){
                this.text='取出'+(-this.moneyNum+this.tableData[this.selectedRow].num)+'个/张'
            }else{
                this.text=''
            }
          },
          submit(){
            if(this.selectedRow == 0 || this.selectedRow == 1){
                var value1 = 0.0;
                if(this.selectedRow == 0){
                    value1 = 0.5
                }else{
                    value1 = 1.0
                }
                this.$store
                .dispatch('SetCoin',{
                  value : value1,
                  num : this.moneyNum
                })
                .then(response => {
                    if (response.data.status == 'success') {
                        this.tableData[this.selectedRow].num = this.moneyNum
                        this.dialogVisible = false
                        this.$message({
                            message: '修改货币数量成功',
                            type: 'success'
                        });
                    }else{
                        this.$message.error('修改失败');
                    }
                }).catch(() => {
                    this.$message.error('修改失败，无法连接到服务器');
                });
            }else if(this.selectedRow == 2 || this.selectedRow == 3 || this.selectedRow == 4 
            || this.selectedRow == 5 || this.selectedRow == 6 || this.selectedRow == 7){
                var value1 = 0.0;
                if(this.selectedRow == 2){
                    value1 = 1.0
                }else if(this.selectedRow == 3){
                    value1 = 5.0
                }else if(this.selectedRow == 4){
                    value1 = 10.0
                }else if(this.selectedRow == 5){
                    value1 = 20.0
                }else if(this.selectedRow == 6){
                    value1 = 50.0
                }else{
                    value1 = 100.0
                }
                this.$store
                .dispatch('SetPaperMoney',{
                  value : value1,
                  num : this.moneyNum
                })
                .then(response => {
                    if (response.data.status == 'success') {
                        this.tableData[this.selectedRow].num = this.moneyNum
                        this.dialogVisible = false
                        this.$message({
                            message: '修改货币数量成功',
                            type: 'success'
                        });
                    }else{
                        this.$message.error('修改失败');
                    }
                }).catch(() => {
                    this.$message.error('修改失败，无法连接到服务器');
                });
            }
          }
        }
    }
</script>

<style scoped>

</style>