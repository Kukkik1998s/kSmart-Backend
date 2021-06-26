//data service

import axios from 'axios';
import authHeader from './auth-header';

//const API_URL = 'http://localhost:8080/api/test/';
//const API_URL = 'https://ksmart-springboot-mongodb.herokuapp.com/api/test/';
const API_URL = '/api/test/';
//const API_GET = 'http://localhost:8080/api/get/';
//const API_GET = 'https://ksmart-springboot-mongodb.herokuapp.com/api/get/';
const API_GET = '/api/get/';
//const BASE_URL = 'https://ksmart-springboot-mongodb.herokuapp.com/api/';
const BASE_URL = '/api/';
class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'all');
  }

  getUserBoard() {
    return axios.get(API_URL + 'doctor', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'admin', { headers: authHeader() });
  }

  getPatient(){
    return axios.get(API_GET+'patients', {headers: authHeader()});
  }

  getDoctor(){
    return axios.get(API_GET+'doctors', {headers: authHeader()});
  }

  getPatientDetail(username){
    return axios.get(API_GET+'this/patient?username='+username, {headers: authHeader()});
  }

  getFoods() {
    return axios.get(BASE_URL+"meal/foods",{headers: authHeader()});
  }
  
  getFood(id) {
    return axios.get(BASE_URL+"meal/foods/"+id,{headers: authHeader()});
  }

  saveFood(food) {
    return axios.put(BASE_URL+"meal/foods/"+food.id,food,{headers: authHeader()});
  }
  
  getPatientMeal(username,start,end){

    return axios.get(BASE_URL+'meal/getmeal/range?username='+username+"&start="+start+"&end="+end, {headers: authHeader()});
  }

  getPatientMedMeal(username,start,end){
    return axios.get(BASE_URL+'med/get/record/'+username+"/range?&start="+start+"&end="+end, {headers: authHeader()});    
  }

  testGet() {
    return axios.get(BASE_URL+'test/patient', {headers: authHeader()});
  }

  authHeaderWithMultiPart() {
    let user = JSON.parse(localStorage.getItem('user'));
  
    if (user && user.accessToken) {
      return { Authorization: 'Bearer ' + user.accessToken, 'Content-Type': 'multipart/form-data', };
    } else {
      return {};
    }
  }

  updateFoodImage(id,formData)
  {

    return axios.put('/api/image/update/' + id, 
                formData,
                {headers: this.authHeaderWithMultiPart(),});
                
  }

}

export default new UserService();