<template>
    <el-container style="height: 100%;">
        <el-header style="padding: 0;font-size: 50px;background: linear-gradient(to right, #72ade3, #fff);height: 80px;">
            <div>{{ username }},Welcome to Home Page!</div>
        </el-header>
        <el-container>
            <el-aside>
                <el-button type="text" icon="el-icon-thumb" style="font-size: 25px;" @click="viewall">所有博客</el-button>
                <br>
                <el-button type="text" icon="el-icon-plus" style="font-size: 25px;" @click="newBlog">添加博客</el-button>
            </el-aside>
            <el-main style="padding: 0;height: 100%;overflow: hidden;">
                <div>
                    <div v-for="blog in Blogs" :key="blog.id" style="padding-left: 300px;">
                        <div style="height: 100px;width: 400px;background-color: rgb(237, 242, 244);text-align: center;">
                            <span style="font-size: 20px;">{{ blog.title }}</span>
                            <br>
                            <span>最近更新:{{ blog.updateTime }}</span>
                            <br>
                            <el-button type="info" icon="el-icon-view" @click="view(blog.id,blog.username)">查看</el-button>
                            <el-button icon="el-icon-edit" @click="Edit(blog.id,blog.title,blog.username)">修改</el-button>
                            <el-button type="danger" icon="el-icon-delete" @click="Delete(blog.id,blog.title)">删除</el-button>
                        </div>
                        <br>
                    </div>
                </div>
                <el-dialog title="添加博客" :visible.sync="Visible">
                    <el-form :rules="rules" ref="addBlog" :model="New">
                        <el-form-item label="博客标题" prop="title">
                            <el-input type="text" auto-complete="off" v-model="New.title"></el-input>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="primary" @click="blogconfirm('addBlog')">确认</el-button>
                        </el-form-item>
                    </el-form>
                </el-dialog>
            </el-main>
        </el-container>
    </el-container>
</template>
<style></style>
<script>
export default {
    data() {
        var checktitle = (_rule, value, callback) => {
            if (!value) {
                return callback(new Error('标题不能为空!'));
            }
            else {
                callback();
            }
        };
        var Blogs;
        var id;
        var username=this.$route.query.username;
        return {
            Visible: false,
            New: { title: '' },
            rules: {
                title: [{ validator: checktitle, trigger: 'blur' }]
            },
            Blogs,
            id,
            username
        }
    },
    methods: {
        newBlog() {
            this.Visible = true;
        },
        blogconfirm(formName) {
            this.$refs[formName].validate((valid) => {
                var Id;
                if (valid) {
                    this.Visible = false;
                    alert('添加成功');
                    //跳转并传后端
                    console.log(this.New.title);
                    this.$axios({
                        method: 'post',
                        url: 'http://localhost:8080/home',
                        params: {
                            title: this.New.title,
                            username:this.username
                        }
                    }).then((result) => {
                        this.id = result.data;
                        Id=result.data;
                        return this.$router.push({ path: '/edit', query: { title: this.New.title, id: Id,username:this.username } });
                    }).catch((err) => {
                        alert(err);
                    });
                } else {
                    return false;
                }
            });
        },
        view(id,username) {
            return this.$router.push({ path: '/show', query: { id,username } })
        },
        Delete(id) {
            this.$axios({
                method: 'delete',
                url: 'http://localhost:8080/home',
                params: {
                    id
                }
            }).then((result) => {
                alert("已删除" + result.data.data + "!");
                location.reload();
            }).catch((err) => {
                console.log("error!");
                console.log(err);
            });
        },
        Edit(id,title,username){
            return this.$router.push({path:'/edit',query:{id,title,username}})
        },
        viewall(){
            return this.$router.push({path:'/all',query:{username:this.username}})
        }
    },
    mounted() {   
        this.$axios({
            method: 'get',
            url: 'http://localhost:8080/home',
            params:{
                username:this.username
            }
        }).then((result) => {
            console.log(result.data.data);
            this.Blogs = result.data.data;
        }).catch((err) => {
            console.log(err);
        });
        console.log("being created");
    },
}
</script>