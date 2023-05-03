<template>
  <div>
      <br><br><br>
      <el-row>
        <el-col :span="2">&nbsp;</el-col>
        <el-col :span="3" v-for="i in num1" :key="i">
            <el-card :body-style="{ padding: '5px' }" style="text-align: center">
                <goods-picture :goodsid="shelfItemList[i].goodsid"></goods-picture>
                <div v-if="shelfItemList[i].goodsid > 0">
                    <span>{{shelfItemList[i].name}}</span><br>
                    <span>{{shelfItemList[i].price}}元</span><br>
                    <span>剩余{{shelfItemList[i].num}}个</span>
                    <div v-if="shelfItemList[i].num != 0">
                        <el-button type="text" class="button" @click="buy(i)">购买</el-button>
                    </div>
                    <div v-else>
                        <el-button type="text" class="button" disabled>购买</el-button>
                    </div>
                </div>
                <div v-else>
                    <span>无商品</span><br>
                    <span>0元</span><br>
                    <span>剩余0个</span>
                    <div>
                        <el-button type="text" class="button" disabled>购买</el-button>
                    </div>
                </div>
            </el-card>
        </el-col>
        <el-col :span="4" style="text-align: center">
            <br><br><br><br><br><el-button type="primary" @click="insertCoinButton">投币</el-button>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="2">&nbsp;</el-col>
        <el-col :span="3" v-for="i in num2" :key="i">
            <el-card :body-style="{ padding: '5px' }" style="text-align: center">
                <goods-picture :goodsid="shelfItemList[i].goodsid"></goods-picture>
                <div v-if="shelfItemList[i].goodsid > 0">
                    <span>{{shelfItemList[i].name}}</span><br>
                    <span>{{shelfItemList[i].price}}元</span><br>
                    <span>剩余{{shelfItemList[i].num}}个</span>
                    <div v-if="shelfItemList[i].num != 0">
                        <el-button type="text" class="button" @click="buy(i)">购买</el-button>
                    </div>
                    <div v-else>
                        <el-button type="text" class="button" disabled>购买</el-button>
                    </div>
                </div>
                <div v-else>
                    <span>无商品</span><br>
                    <span>0元</span><br>
                    <span>剩余0个</span>
                    <div>
                        <el-button type="text" class="button" disabled>购买</el-button>
                    </div>
                </div>
            </el-card>
        </el-col>
        <el-col :span="4" style="text-align: center">
            <span>余额：{{userMoney}}元</span>
            <br><br><br><br><el-button type="primary" @click="returnMoney()">退币</el-button>
        </el-col>
    </el-row>
    <el-dialog
        title="投币"
        :visible.sync="insertCoinDialogVisible"
        width="30%">
        <el-row>
          <el-col :span="12"><el-button @click="insertCoin(0)">投入0.5元硬币</el-button></el-col>
          <el-col :span="12"><el-button @click="insertCoin(1)">投入1元硬币</el-button></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-button @click="insertCoin(2)">投入1元纸币</el-button></el-col>
          <el-col :span="12"><el-button @click="insertCoin(3)">投入5元纸币</el-button></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-button @click="insertCoin(4)">投入10元纸币</el-button></el-col>
          <el-col :span="12"><el-button @click="insertCoin(5)">投入20元纸币</el-button></el-col>
        </el-row>
        <el-row>
          <el-col :span="12"><el-button @click="insertCoin(6)">投入50元纸币</el-button></el-col>
          <el-col :span="12"><el-button @click="insertCoin(7)">投入100元纸币</el-button></el-col>
        </el-row>
        <span slot="footer" class="dialog-footer">
            <el-button @click="insertCoinDialogVisible = false">取消</el-button>
        </span>
    </el-dialog>
  </div>
</template>

<script>
import goodsPicture from './goodsPicture.vue';

    export default {
        components: { goodsPicture },
        name: "Selling",
        data(){
          return{
              shelfItemList:[],
              num1: [0,1,2,3,4,5],
              num2: [6,7,8,9,10,11],
              userMoney: 0.0,
              insertCoinDialogVisible: false,
              insertCoinValue: 0.0,
              insertPaperMoneyValue: 0.0,
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
        },
        methods: {
            insertCoinButton(){
                this.insertCoinDialogVisible = true
            },
            insertCoin(money){
                this.insertCoinValue = 0
                this.insertPaperMoneyValue = 0
                if (money == 7){
                    this.insertPaperMoneyValue = 100
                }else if(money == 6){
                    this.insertPaperMoneyValue = 50
                }else if(money == 5){
                    this.insertPaperMoneyValue = 20
                }else if(money == 4){
                    this.insertPaperMoneyValue = 10
                }else if(money == 3){
                    this.insertPaperMoneyValue = 5
                }else if(money == 2){
                    this.insertPaperMoneyValue = 1
                }else if(money == 1){
                    this.insertCoinValue = 1
                }else if(money == 0){
                    this.insertCoinValue = 0.5
                }
                if(this.insertCoinValue > 0){
                    this.$store
                    .dispatch('InsertCoin', this.insertCoinValue)
                    .then(response => {
                        if (response.data.status == 'success') { 
                            this.$message({
                                message: '投币成功',
                                type: 'success'
                            });
                            this.userMoney += this.insertCoinValue
                        }else{
                            this.$message.error('投币失败，请联系管理员');
                        }
                    }).catch(() => {
                        this.$message.error('投币失败，请联系管理员');
                    }); 
                }else if(this.insertPaperMoneyValue > 0){
                    this.$store
                    .dispatch('InsertPaperMoney', this.insertPaperMoneyValue)
                    .then(response => {
                        if (response.data.status == 'success') { 
                            this.$message({
                                message: '投币成功',
                                type: 'success'
                            });
                            this.userMoney += this.insertPaperMoneyValue
                        }else{
                            this.$message.error('投币失败，请联系管理员');
                        }
                    }).catch(() => {
                        this.$message.error('投币失败，请联系管理员');
                    });                   
                }
                this.insertCoinDialogVisible = false
            },
            buy(item){
                if(this.shelfItemList[item].num > 0){
                    if(this.userMoney >= this.shelfItemList[item].price){
                        this.$store
                        .dispatch('Buy', item)
                        .then(response => {
                            if (response.data.status == 'success') { 
                                this.$message({
                                    message: '购买成功！弹出商品：'+this.shelfItemList[item].name,
                                    type: 'success'
                                });
                                this.userMoney -= this.shelfItemList[item].price
                                this.shelfItemList[item].num -= 1
                            }else{
                                this.$message.error('购买失败，请联系管理员');
                            }
                        }).catch(() => {
                            this.$message.error('购买失败，请联系管理员');
                        });
                    }else{
                        this.$message.error('投入货币不足，无法购买！');
                    }
                }
            },
            returnMoney(){
                if(this.userMoney > 0){
                   this.$store
                        .dispatch('ReturnMoney', this.userMoney)
                        .then(response => {
                            if (response.data != '') { 
                                console.log(response.data)
                                this.$message({
                                    dangerouslyUseHTMLString: true,
                                    message: '退币成功！退出了：<br>' 
                                        + response.data[0] + '个0.5元硬币<br>'
                                        + response.data[1] + '个1元硬币<br>'
                                        + response.data[2] + '张1元纸币<br>'
                                        + response.data[3] + '张5元纸币<br>'
                                        + response.data[4] + '张10元纸币<br>'
                                        + response.data[5] + '张20元纸币<br>'
                                        + response.data[6] + '张50元纸币<br>'
                                        + response.data[7] + '张100元纸币'
                                    ,
                                    type: 'success'
                                });
                                this.userMoney = 0.0
                            }else{
                                this.$message.error('退币失败，请联系管理员');
                            }
                        }).catch(() => {
                            this.$message.error('退币失败，请联系管理员');
                        }); 
                }else{
                    this.$message.error('余额为0元');
                }
            }
        }
    }
</script>

<style scoped>

</style>