// Utilities
import { defineStore } from 'pinia'

export const useAppStore = defineStore('appStore', {
  state: () => ({
    user: null,
    token: null,
  }),
  actions: {
    setUser(user) {
      this.user = user
      if (!this.user.color) {
        this.user.color = 'blue'
      }
    },
    setToken(token) {
      this.token = token
    },
    logout() {
      this.user = null
      this.token = null
    },
  },
  getters: {
    isAnonymous: (state) => !state.user,
  },
})
