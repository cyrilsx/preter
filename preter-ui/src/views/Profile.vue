<script setup>

</script>

<template>
  <div class="d-flex align-center justify-center">
    <v-list class="w-50">
      <v-list-item>
        <h2 class="flex-fill">Profile</h2>
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
