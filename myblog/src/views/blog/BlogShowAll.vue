<template>
    <el-container style="height: 100%;">
        <el-header style="padding: 0;font-size: 50px;background: linear-gradient(to right, #72ade3, #fff);height: 80px;">
            <div>{{ username }},View All the Blogs!</div>
        </el-header>
        <el-container>
            <el-main style="padding: 0;">
                <div>
                    <div v-for="blog_arrays in Blogs" :key="blog_arrays[0].id" style="padding-left: 440px;">
                        <div v-for="blog in blog_arrays" :key="blog.id" style="display: inline-block;padding-left: 10px;text-align: center;">
                            <div style="height: 120px;width: 400px;background-color: rgb(237, 242, 244);">
                                <span style="font-size: 20px;">{{ blog.title }}</span>
                                <br>
                                <span>最近更新:{{ blog.updateTime }}</span>
                                <br>
                                <span>创建者:{{ blog.username }}</span>
                                <br>
                                <el-button type="info" icon="el-icon-view" @click="view(blog.id)">查看</el-button>
                            </div>
                        </div>
                        <br>
                        <br>
                    </div>
                </div>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
export default {
    data() {
        var Blogs;
        var id;
        var username = this.$route.query.username;
        return {
            Visible: false,
            Blogs,
            id,
            username
        }
    },
    methods: {
        view(id) {
            return this.$router.push({ path: '/show', query: { id, username: this.username } })
        },
    },
    mounted() {
        this.$axios({
            method: 'get',
            url: 'http://localhost:8080/all',
        }).then((result) => {
            console.log(result.data.data);
            var Blogs_raw = result.data.data;
            let index = 0;
            this.Blogs = [];
            while (index < Blogs_raw.length) {
                this.Blogs.push(Blogs_raw.slice(index, index + 2))
                index += 2;
            }
        }).catch((err) => {
            console.log(err);
        });
        console.log("being created");
    },
}
</script>

<style></style>