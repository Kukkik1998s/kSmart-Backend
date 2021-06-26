<template>

 <div class="table-responsive" style="margin-left:0% margin-top:15%">
    <table class="table table-striped table-light table-bordered table-hover text-center" id="dataTable" width="100%" cellspacing="0">
      <thead>
        <tr>
          <th>Username</th>
          <th>Email</th>
          <th>Role</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="user in users">
			<td>{{user.username}}</td>
			<td>{{user.email}}</td>
			<td>{{user.roles}}</td>
		</tr>
      </tbody>
    </table>
</div>
</template>

<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}
</style>

<script>
import UserService from '../service/user.service';

export default {
  name: 'Admin',
  data() {
    return {
      users: []
    };
  },
  mounted() {
    UserService.getDoctor().then(
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
  }
};
</script>