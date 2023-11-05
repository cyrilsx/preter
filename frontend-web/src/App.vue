<template>
  <va-navbar class="mb-2" color="#8FA6CB">
    <template #left>
      <va-navbar-item><router-link to="/"><font-awesome-icon icon="fa-home" /></router-link></va-navbar-item>
      <va-navbar-item><router-link to="/article"><font-awesome-icon icon="fa-shop" /></router-link></va-navbar-item>
      <va-navbar-item><router-link to="/about"><font-awesome-icon icon="fa-circle-user" /></router-link></va-navbar-item>
    </template>
    <template #center>
      <va-navbar-item><span class="motive-text">Let's steal (and notify) stuff (and give back)!</span></va-navbar-item>
    </template>
    <template #right>
      <va-navbar-item class="right">
        <va-input
          class="col-last"
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

  .va-navbar {
    padding: 0 0 0 10px;
    //height: fit-content;
  }
}

nav {

  a {
    font-weight: bold;
    color: #D5F9DE;

    &.router-link-exact-active {
      color: #8AA399;
    }
  }
  .right {
    padding-top: 10px;
  }

  .motive-text {
    font-family: Cousine;
  }
}
</style>
