<template>
  <div id="app">
    <!-- <blog-login>

    </blog-login> -->
    <!-- <blog-main>

    </blog-main> -->
    <router-link to="/edit"></router-link>
    <router-link to="/login"></router-link>
    <router-link to="/"></router-link>
    <router-view/>
  </div>
</template>

<style>
html,
body,
#app,
.el-container{
  height: 100%;
  padding: 0px;
  margin: 0px;
}
</style>
<script>
// import BlogLogin from './views/blog/BlogLogin.vue'
//import BlogMain from './views/blog/BlogMain.vue';
  
  export default{
  //components: { BlogMain },
   data() {
    return {
    }
   },
   methods:{

   }
  }
</script>
