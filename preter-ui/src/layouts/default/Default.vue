<template>
  <v-layout class="rounded rounded-md">
    <v-app-bar :color="profile?.color ? profile.value.color : '#1976d2'">
    <template #prepend>
      <v-toolbar-title>
        <router-link to="/" class="text-decoration-none text-white">
          Prêter
        </router-link>
        </v-toolbar-title>
    </template>

      <v-btn icon>
        <v-icon icon="fas fa-search"></v-icon>
      </v-btn>

    </v-app-bar>

    <v-navigation-drawer
      color="#e8effa"
      v-if="isAuthenticated"
      expand-on-hover
      rail
    >

      <v-list>
        <v-list-item
          :prepend-avatar="profile?.images.sm"
          :title="profile?.username"
          :subtitle="profile?.email"
        ></v-list-item>
      </v-list>

      <v-divider></v-divider>

      <v-list density="compact" nav>
        <v-list-item prepend-icon="fas fa-user-friends" title="Communities" value="communities" to="Community"></v-list-item>
        <v-list-item prepend-icon="fas fa-shapes" title="Objects" value="objects" to="Object"></v-list-item>
        <v-list-item prepend-icon="fas fa-handshake" title="Borrows" value="borrows" to="Borrow"></v-list-item>
      </v-list>

      <v-divider></v-divider>
      <v-list density="compact" nav>
        <v-list-item prepend-icon="fas fa-id-card" title="Profile" value="profile" to="Profile"></v-list-item>
        <v-list-item prepend-icon="fas fa-sign-out-alt" title="Logout" value="logout"></v-list-item>
      </v-list>

    </v-navigation-drawer>

    <v-main style="min-height: 300px;">
      <router-view/>
    </v-main>
  </v-layout>
</template>

<script setup>
import {ref} from "vue";
import {useAppStore} from "@/store/app";
import "@fortawesome/fontawesome-free/css/all.css";

const appStore = useAppStore()
const profile = ref(appStore.user)
const isAuthenticated = ref(!appStore.isAnonymous)


</script>
