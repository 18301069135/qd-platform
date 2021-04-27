import Vue from 'vue'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en' // lang i18n

import '@/styles/index.scss' // global css

import App from './App.vue'
import store from './store'
import router from './router'

import '@/icons' // icon

// set ElementUI lang to EN
Vue.use(ElementUI, { locale })
// 如果想要中文版 element-ui，按如下方式声明
// Vue.use(ElementUI)

Vue.config.productionTip = false

router.beforeEach((to, from,next)=>{
  if(to.path=='/'){
       next();
  }else if(sessionStorage.getItem('user') != null && sessionStorage.getItem('user')){
       next();
  }else if(sessionStorage.getItem('user')==null){
       next({path: '/'});
  }else{
       next({path: '/'});
  }
});

const dev = require('../config/env');
Vue.prototype.BASE_URL = dev.hosturl

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')

