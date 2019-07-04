import router from './router'

import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { setDocumentTitle, domTitle } from '@/utils/domUtil'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

router.beforeEach((to, from, next) => {
  NProgress.start() // start progress bar
  to.meta && (typeof to.meta.title !== 'undefined' && setDocumentTitle(`${to.meta.title} - ${domTitle}`))
  // const redirect = decodeURIComponent(from.query.redirect || to.path)
  next()
})

router.afterEach(() => {
  NProgress.done() // finish progress bar
})
