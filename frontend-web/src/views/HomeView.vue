<template>
  <va-navbar color="blue" shape class="mb-2">
    <template #left>
      <va-navbar-item><font-awesome-icon icon="fa-home-user" /></va-navbar-item>
      <va-navbar-item><font-awesome-icon icon="fa-user-secret" /></va-navbar-item>
      <va-navbar-item>LOGO HERE</va-navbar-item>
    </template>
    <template #center>
      <va-navbar-item>Lend it to Me, plzzz!</va-navbar-item>
    </template>
    <template #right>
      <va-navbar-item class="middle">
        <va-input
          class="mb-4"
          v-model="value"
          placeholder="Search"
        />

      </va-navbar-item>
    </template>
  </va-navbar>

  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  }
}
</script>

<style>
.middle {
 padding-top: 20px;
}

</style>
