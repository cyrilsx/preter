<template>
  <va-navbar color="blue" class="mb-2">
    <template #left>
      <va-navbar-item><router-link to="/"><font-awesome-icon icon="fa-home" /></router-link></va-navbar-item>
      <va-navbar-item><router-link to="/article"><font-awesome-icon icon="fa-shop" /></router-link></va-navbar-item>
      <va-navbar-item><router-link to="/about"><font-awesome-icon icon="fa-circle-user" /></router-link></va-navbar-item>
    </template>
    <template #center>
      <va-navbar-item><span class="motive-text">Let's steal (and notify) stuff (and give back)!</span></va-navbar-item>
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
  <router-view/>
</template>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
  .middle {
    padding-top: 20px;
  }

  .motive-text {
    font-family: Cousine;
    padding-left: 10px;
  }
}
</style>
