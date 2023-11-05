<script setup>
import { ref } from 'vue'

const registerText = 'Register'
const loginText = 'Login'
const displayRegister = false

const email = ref('')
const password = ref('')
const password2 = ref('')
const errEmailLogin = ref('')
const errPwdLogin = ref('')

function validateEmail () {
  const validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/
  if (this.email.match(validRegex) == null) {
    errEmailLogin.value = 'Email not valid'
  } else {
    errEmailLogin.value = ''
  }
}

function validatePwd () {
  if (this.password.length < 8) {
    errPwdLogin.value = 'Password not valid'
  } else {
    errPwdLogin.value = ''
  }
}

function login () {
  if (errEmailLogin.value.length > 0 && errPwdLogin.value.length > 0) {
    alert('Form not valid')
  } else {
    alert('Form Valid')
  }
}

</script>

<template>

  <div>
    <h1>{{ loginText }}</h1>
     {{ email }}
    {{ errEmailLogin }}
    <div class="form-card">
      <va-form>
        <va-input
          v-model="email"
          class="mb-6"
          label="Email"
          placeholder="Email"
          autofocus
          required
          error-messages="errEmailLogin"
          @focusout="validateEmail()"
        />
        {{errPwdLogin}}
        <va-input
          v-model="password"
          label="Password"
          type="password"
          placeholder="Password"
          required
          @focusout="validatePwd()"
        />
        <va-button preset="primary" v-on:click="login">Go</va-button>
      </va-form>
    </div>
  </div>
  <p>
    Need a account? <a href="#" v-on:click="displayRegister=true">Register...</a>
  </p>
  <div v-show="displayRegister">
    <h1>{{ registerText }}</h1>
    <div class="form-card">
      <va-form>
        <va-input
          v-model="email"
          class="mb-6"
          label="Email"
          placeholder="Email"
          required
        />
        <va-input
          v-model="password"
          class="mb-6"
          label="Password"
          type="password"
          placeholder="Password"
          required/>
        <va-input
          v-model="password2"
          class="mb-6"
          label="Password"
          type="password"
          placeholder="Repeat Password"/>
        <va-button preset="secondary" class="button-login" v-on:click="login">Go</va-button>
      </va-form>
    </div>
  </div>

  <!--  </div>-->
</template>

<style scoped>
.form-card {
  display: flex;
  align-items: center;
//border: 3px solid green; justify-content: center;
  padding: 20px;

}

.button-login {
  background-color: #2c3e50;
}

.field-error {
  background: red;
}

</style>
