import router from './router'
import store from './store'

import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { setDocumentTitle, domTitle } from '@/utils/domUtil'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

router.beforeEach((to, from, next) => {
  // start progress bar
  NProgress.start()
  // 设置文档标题
  to.meta && (typeof to.meta.title !== 'undefined' && setDocumentTitle(`${to.meta.title} - ${domTitle}`))
  if (to.path === '/user/login') {
    // 登录界面直接放行
    next()
    NProgress.done()
  } else {
    store.dispatch('GetInfo').then(res => {
      next()
    }).catch(() => {
      next({ path: '/user/login' })
    })
  }
})

router.afterEach(() => {
  NProgress.done() // finish progress bar
})
