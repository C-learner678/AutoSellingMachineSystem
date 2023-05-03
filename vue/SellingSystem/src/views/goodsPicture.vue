<template>
  <div>
    <div v-if="goodsid>0">
      <el-image
          style="height: 100px"
          :src="src">
      </el-image>
    </div>
    <div v-else style="height: 103px">
    </div>
  </div>
</template>

<script>
    export default {
        name: "goodsPicture",
        props:['goodsid'],
        data: function(){
          return{
            src:''
          }
        },
        created(){
          if(this.goodsid > 0){
            this.$store
            .dispatch('GetImage', this.goodsid)
            .then(response => {
                if (response.data !== '') {
                    this.src =
                    "data:image/jpg;base64," +
                    btoa(
                        new Uint8Array(response.data).reduce(
                        (data, byte) => data + String.fromCharCode(byte),
                        ""
                        )
                    );
                }
            })
          }else{
            this.src = ''
          }
        }
    }
</script>

<style scoped>

</style>