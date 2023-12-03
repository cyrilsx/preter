<script setup>
import {ref} from 'vue'
import {useField, useForm} from 'vee-validate'
import {UserService} from "@/api/service";
import router from "@/router";

const {handleSubmit, handleReset} = useForm({
  validationSchema: {
    username(value) {
      if (value?.length >= 2) return true

      return 'Name needs to be at least 2 characters.'
    },
    phone(value) {
      if (value?.length > 9 && /[0-9-]+/.test(value)) return true

      return 'Phone number needs to be at least 9 digits.'
    },
    email(value) {
      if (/^[a-z.-]+@[a-z.-]+\.[a-z]+$/i.test(value)) return true

      return 'Must be a valid e-mail.'
    },
    password(value) {
      if (value?.length >= 8) return true

      return 'Password needs to be at least 8 characters.'
    },
    passwordConfirmation(value) {
      if (value?.length >= 8 && value.trim() === password?.value?.value.trim()) return true

      return 'Password needs to be equals'
    },
  },
})
const username = useField('username')
const phone = useField('phone')
const email = useField('email')
const password = useField('password')
const passwordConfirmation = useField('passwordConfirmation')

const showPassword = ref(false)
const showConfirmationPassword = ref(false)


const submit = handleSubmit(values => {
  alert(JSON.stringify(values, null, 2))
  UserService.registerUser(values).
    then((response) => {
      if (response.ok) {
        response.text().then((data) => {
          router.push({name: 'Login'});
        });
      } else {
        alert("Registration failed. ")
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
        <h2 class="flex-fill">Register</h2>
        <v-divider/>
      </v-list-item>
      <v-list-item>
        <form @submit.prevent="submit">
          <v-text-field
            v-model="username.value.value"
            :counter="10"
            :error-messages="username.errorMessage.value"
            label="Username"
          ></v-text-field>

          <v-text-field
            v-model="phone.value.value"
            :counter="10"
            :error-messages="phone.errorMessage.value"
            label="Phone Number"
          ></v-text-field>

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

          <v-text-field
            v-model="passwordConfirmation.value.value"
            :error-messages="passwordConfirmation.errorMessage.value"
            :type="showConfirmationPassword ? 'text' : 'password'"
            label="Confirm Password"
            append-inner-icon="fas fa-eye"
            :on-click:append-inner="() => (showConfirmationPassword = !showConfirmationPassword)"
          ></v-text-field>


          <v-btn
            class="me-4"
            type="submit"
          >
            submit
          </v-btn>

          <v-btn @click="handleReset">
            clear
          </v-btn>
        </form>
      </v-list-item>
    </v-list>
  </div>
</template>

<style scoped>

</style>
