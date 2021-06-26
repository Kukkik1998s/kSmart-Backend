<template>
  <div id="app">
    <nav class="w3-sidebar w3-bar-block w3-top"  style="background-color: #302E62; z-index:3;width:25%;left:0">
    <br>
      <div class="w3-container w3-display-container w3-padding-16">
      <a href class="navbar-brand" @click.prevent><img src="./assets/kSmart2.png" style="width:150px;height:80px;"> </a>
      </div>
      <div class="w3-padding-64 w3-large w3-text-white"  style="font-weight:bold">
       <!-- <li class="nav-item">
          <router-link to="/home" class="nav-link">
            <font-awesome-icon icon="home" class="w3-xlarge"/><span style="font-size:2vw; color:white" class="fs">Home</span>
          </router-link>
        </li>-->        
        <li class="w3-bar-item w3-button w3-padding w3-hover-blue" v-if="showAdminBoard" >
          <router-link to="/admin" class="nav-link"><span style="font-size:2vw; color:white" class="fs" >Doctor's List</span>
          </router-link>
        </li>
         <li class="w3-bar-item w3-button w3-padding w3-hover-blue" v-if="showAdminBoard">
          <router-link to="/register" class="nav-link"><span style="font-size:2vw; color:white" class="fs" >Add Doctor</span>
          </router-link>
        </li>
        <li class="w3-bar-item w3-button w3-padding w3-hover-blue">
          <router-link v-if="currentUser" to="/user" class="nav-link"><span style="font-size:2vw; color:white" class="fs">Patient's List</span>
          </router-link>
        </li>
        <li class="w3-bar-item w3-button w3-padding w3-hover-blue">
          <router-link to="/foods" class="nav-link"><span style="font-size:2vw; color:white" class="fs">Food's List</span>
          </router-link>
        </li>

        <div v-if="!currentUser" class="navbar-nav ml-auto">
       
        <li class="nav-item">
          <router-link to="/login" class="nav-link">
            <font-awesome-icon icon="sign-in-alt" class="w3-xlarge"/><span style="font-size:2vw; color:white" class="fs">Login</span>
          </router-link>
        </li>
      </div>

      <div v-if="currentUser" class="navbar-nav ml-auto">
        <li class="nav-item">
          <router-link to="/profile" class="nav-link">
            <font-awesome-icon icon="user" class="w3-xlarge"/><span style="font-size:2vw; color:white" class="fs">
            {{ currentUser.username }}</span>
          </router-link>
        </li>
        <li class="nav-item">
          <a class="nav-link" href @click.prevent="logOut">
            <font-awesome-icon icon="sign-out-alt" class="w3-xlarge"/><span style="font-size:2vw; color:white" class="fs">LogOut</span>
          </a>
        </li>
      </div>
      </div>
      
    </nav>

    <div class="container" style="margin-left:25%">
      <router-view />
    </div>
  </div>
</template>

<script>
import styles from './assets/w3.css'

export default {

  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdminBoard() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes('ROLE_ADMIN');
      }

      return false;
    },
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  }
};
</script>
<style>
  li router-link {
  display: block;
  color: #000;
  padding: 8px 16px;
  text-decoration: none;
}

li router-link.active {
  background-color: #71bcdf;
  color: white;
}

li router-link:hover:not(.active) {
  background-color: #c4e5f5;
  color: white;
}

.fs{
  font-family:"Times New Roman", Times, serif;
}
</style>