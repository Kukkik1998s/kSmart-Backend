<template>
   <div class="table-responsive" style="margin-top:10%;">
    <table class="table table-striped table-light table-bordered table-hover text-center" id="dataTable" width="100%" cellspacing="0">
      <thead>
        <tr>
          <th>ID</th>
          <th>Username</th>
          <th>Firstname</th>
          <th>Lastname</th>
          <th>Gender</th>
          <th>Weight</th>
          <th>Height</th>
          <th>CKD Stage</th>
          <th>View</th>
        </tr>
      </thead>
      <tbody>
      <tr v-for="user in users" v-if="user.name!=null">
      <td>{{user.id}}</td>  
      <td>{{user.user.username}}</td>
			<td>{{user.name}}</td>
      <td>{{user.lastname}}</td>
      <td>{{user.gender}}</td>
      <td>{{user.weight}}</td>
      <td>{{user.height}}</td>
      <td>{{user.ckdPhase}}</td>
      <td><router-link :to="{ name: 'view', params: { username: user.user.username } }">
      <div class="btn btn-info btn-lg">View</div> </router-link></td>
		</tr>
      </tbody>
    </table>
</div>
</template>

<script>
import UserService from '../service/user.service';

export default {
  name: 'Doctor',
  data() {
    return {
      users: [],
      username: '',
    };
  },
  mounted() {
    UserService.getPatient().then(
      response => {
        this.users = response.data;
      },
      error => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
  },
  methods:{
    gatAge: function(birthdate){
      let today = new Date();
      let age = Math.florr((birthdate - today)/31557600000);
      return age;
    }
  }
};
</script>