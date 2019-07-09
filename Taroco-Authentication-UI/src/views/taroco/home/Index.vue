<template>
  <a-card>
    {{ username }} 恭喜你登录成功, {{ welcome }}
  </a-card>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'Home',
  computed: {
    ...mapState({
      username: state => state.user.name,
      welcome: state => state.user.welcome
    })
  }
}
</script>
