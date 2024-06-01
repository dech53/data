import { createApp } from 'vue'
import App from './App.vue'

import router from './router/router'
import pinia from './pinia'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css';

const app = createApp(App)
app.use(router)
app.use(pinia)
app.use(ElementPlus)
app.mount('#app')