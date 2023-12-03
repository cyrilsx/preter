<script setup>
import {ref} from 'vue'
import {useField, useForm} from 'vee-validate'
import {UserService} from "@/api/service";
import {useAppStore} from "@/store/app";
import router from "@/router";

const {handleSubmit, handleReset} = useForm({
  validationSchema: {
    email(value) {
      if (/^[a-z.-]+@[a-z.-]+\.[a-z]+$/i.test(value)) return true

      return 'Must be a valid e-mail.'
    },
    password(value) {
      if (value?.length >= 8) return true

      return 'Password needs to be at least 8 characters.'
    },
  },
})
const email = useField('email')
const password = useField('password')

const token = ref('')

const showPassword = ref(false)
const appStore = useAppStore();


function loadUser() {
  UserService.user()
    .then((response) => {
      if (response.ok) {
        response.text().then((data) => {
          appStore.setUser(JSON.parse(data));
        });
      } else {
        alert("Server not available. ")
      }
    }).catch((error) => {
    console.log(error)
  });
}

const submit = handleSubmit(values => {
  alert(JSON.stringify(values, null, 2))
  UserService.login(values).
    then((response) => {
      if (response.ok) {
        response.text().then((data) => {
          appStore.setToken(JSON.parse(data).token);
          loadUser();
          token.value = JSON.parse(data).token;
          router.push({path: '/', replace: true});
        });
      } else {
        alert("Authentification failed. ")
      }
    }).
    catch((error) => {
      console.log(error)
    });
})
</script>

<template>
  <div class="d-flex align-center justify-center">
    <v-list class="w-50">
      <v-list-item>
        <h2 class="flex-fill">Login</h2>
        <v-divider/>
        {{ token }}
      </v-list-item>
      <v-list-item>
        <form @submit.prevent="submit">

          <v-text-field
            v-model="email.value.value"
            :error-messages="email.errorMessage.value"
            label="E-mail"
          ></v-text-field>

          <v-text-field
            v-model="password.value.value"
            :error-messages="password.errorMessage.value"
            :type="showPassword ? 'text' : 'password'"
            label="Password"
            append-inner-icon="fas fa-eye"
            :on-click:append-inner="() => (showPassword = !showPassword)"
          ></v-text-field>

          <v-btn
            class="me-4"
            type="submit"
          >
            Let's go!
          </v-btn>
          <router-link class="text-decoration-none text-black" to="Register">No account yet?</router-link>
        </form>
      </v-list-item>
    </v-list>
  </div>
</template>

<style scoped>

</style>
