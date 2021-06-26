<template>
  
  <tr>
    <td>
  </tr>

</template>

<script>
import roleForm from './role_form';
export default {
  props: [
    'user'
  ],
  computed: {
    avatar: function () {
      let baseUrl = this.$baseURL + '/storage/users/';
      if (!!this.user.avatar) {
        return baseUrl + this.user.avatar;
      }
      return  baseUrl + 'avatar.svg';
    }
  },
  methods: {
    assignRoleForm() {
      this.$store.dispatch('modal/update', {
        action: 'update',
        formData: this.user,
        title: 'Assign Role to User ' + this.user.username,
        component: roleForm
      });
    },
    assignRole(role) {
      let user = this.user;
      this.$store.dispatch('users/assignRole', {
        user: user,
        role: role
      })
      .then(() => {
        this.$store.dispatch('message/update', {
          title: user.username,
          body: `${user.username} user has been updated successfully`,
          class: 'info',
          confirm: false
        }, { root: true });
      })
      .catch((error) => {
        let response = error.response;
        // send error message
        this.$store.dispatch('message/update', {
          title: user.title,
          body: response.data.message,
          itemsErrors: response.data.errors,
          class: 'danger',
          confirm: false
        }, { root: true });
      });
    },
    deleteUser() {
      this.$store.dispatch('message/update', {
        title: this.user.username,
        body: 'Are you sure you want to delete this User?',
        class: 'danger',
        confirm: true
      });
      this.$bus.$off('proceed');
      this.$bus.$once('proceed', () => {
        this.delete(this.user);
        this.$store.dispatch('message/close');
      });
    },
    delete(user) {
      this.$store.dispatch('users/deleteUser', user)
      .then((response) => {
        // send successful message
        this.$store.dispatch('message/update', {
          title: user.username,
          body: response.data.message,
          class: 'success',
          confirm: false
        }, { root: true });
      })
      .catch((error) => {
        // send error message
        this.$store.dispatch('message/update', {
          title: user.username,
          class: 'danger',
          body: error.response.data.message,
          errors: error.response.data.errors,
          confirm: false
        }, { root: true });
      });
    }
  }
}
</script>