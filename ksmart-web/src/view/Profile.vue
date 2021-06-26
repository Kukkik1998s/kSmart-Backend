<template>
  <div class="container" style="margin-left:0%; margin-top:10%">
    <header>
      <h1>
        <strong>{{currentUser.username}}</strong>'s Profile
      </h1>
    </header>
    <br>
    <!--
    <p>
      <strong>Token:</strong>
      {{currentUser.accessToken.substring(0, 20)}} ... {{currentUser.accessToken.substr(currentUser.accessToken.length - 20)}}
    </p>
    -->
    <p>
      <strong>Id:</strong>
      {{currentUser.id}}
    </p>
    <p>
      <strong>Email:</strong>
      {{currentUser.email}}
    </p>
    <strong>Role:</strong>
    <ul>
      <li v-for="(role,index) in currentUser.roles" :key="index">{{role}}</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'Profile',
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/signin');
    }
  },
   metaInfo: {
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' }
    ]
  }
};
</script>

<style>
.b{
  margin:0
}

.banner {
  padding: 10%;
  margin-left: 0;
  margin-bottom: 5%;
  text-align: center;
  background: #a6b3d9;
  color: white;
  font-size: 30px;
  margin: 30px auto;
}

</style>