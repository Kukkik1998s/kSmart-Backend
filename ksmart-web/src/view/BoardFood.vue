<template>
   <div class="table-responsive" style="margin-top:10%;">
    <table class="table table-striped table-light table-bordered table-hover text-center" id="dataTable" width="100%" cellspacing="0">
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Category</th>
          <th>Phosphate</th>
          <th>Last Update</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="food in foods">
          <td>{{food.id}}</td>  
          <td>{{food.name}}</td>
          <td>{{food.category}}</td>
          <td>{{food.phosphate}}</td>
          <td><router-link :to="{ name: 'food', params: { foodId: food.id } }">
            <div class="btn btn-info btn-lg">View</div> </router-link>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import UserService from '../service/user.service';

export default {
  name: 'food',
  data() {
    return {
      foods: [],      
    };
  },
  mounted() {
    UserService.getFoods().then(
      response => {
        this.foods = response.data;
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
    
  }
};
</script>