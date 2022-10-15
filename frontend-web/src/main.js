import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { VuesticPlugin } from 'vuestic-ui'
import 'vuestic-ui/dist/vuestic-ui.css'
import { library } from '@fortawesome/fontawesome-svg-core'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import axios from 'axios'
import VueAxios from 'vue-axios'

library.add(fas)

const app = createApp(App).use(router).use(VuesticPlugin)
  .component('font-awesome-icon', FontAwesomeIcon)

axios.defaults.baseURL = 'http://localhost:8080/api'

app.use(VueAxios, axios)
  .provide('axios', app.config.globalProperties.axios)
  .mount('#app')
