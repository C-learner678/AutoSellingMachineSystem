import { loginIntoSystem } from "@/api/login"
import { changePassword } from "@/api/login"

const login = {
  actions: {
    LoginIntoSystem ({ commit },psw) {
      return new Promise((resolve, reject) => { 
        loginIntoSystem(psw).then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    ChangePassword ({ commit },data) {
      return new Promise((resolve, reject) => { 
        changePassword(data.oldpsw,data.newpsw).then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
  }
}
export default login
