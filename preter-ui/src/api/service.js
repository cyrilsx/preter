import {ApiConfig} from "@/api/config";
import {useAppStore} from "@/store/app";

export const UserService = {

  registerUser: (user) => {
    return fetch(ApiConfig.baseUrl + ApiConfig.endpoints.register, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(user),
    });
  },


  login: (values) => {
    return fetch(ApiConfig.baseUrl + ApiConfig.endpoints.login, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(values),
    });
  },

  user: (values) => {
    return fetch(ApiConfig.baseUrl + ApiConfig.endpoints.user, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Bearer': 'Bearer ' + useAppStore().token,
      },
      body: JSON.stringify(values),
    });
  }
}
