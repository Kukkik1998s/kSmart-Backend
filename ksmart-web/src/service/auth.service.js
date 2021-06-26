//Authentication Service

import axios from 'axios';
import authHeader from './auth-header';

//const API_URL = 'http://localhost:8080/api/auth/';
//const API_URL = 'https://ksmart-springboot-mongodb.herokuapp.com/api/auth/';
const API_URL = '/api/auth/';

class AuthService {
  login(user) {
    return axios
      .post(API_URL + 'signin', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(API_URL + 'adduser', {
      username: user.username,
      email: user.email,
      password: user.password,
      roles: user.role
    }, {headers: authHeader()});
  }
}

export default new AuthService();