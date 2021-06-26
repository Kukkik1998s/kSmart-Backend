import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'https://ksmart-springboot-mongodb.herokuapp.com/api/test/';
const API_BASE = 'http://ksmart-springboot-mongodb.herokuapp.com/api/';



class PatientService {
    getPatientMeal(username,start,end){

        return axios.get(API_BASE+'meal/getmeal/range?username='+username+"&startdate="+start+"&enddate"+end, {headers: authHeader()});
      }
    
}

export default new PatientService();