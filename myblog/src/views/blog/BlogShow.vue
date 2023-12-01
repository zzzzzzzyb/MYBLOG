<template>
    <el-container>
        <el-header style="padding: 0;font-size: 50px;background: linear-gradient(to right, #72ade3, #fff);height: 80px;">
            {{ title }}
        </el-header>
        <el-container>
            <el-aside style="height: 100%;">
                <span style="font-size: 20px;width:100px">所有评论</span>
                <el-button type="text" icon="el-icon-edit" style="font-size: 15px;padding-left: 120px;"
                    @click="addComment">添加评论</el-button>
                <br>
                <div v-for="comment in comments" :key="comment.commentId">
                    <span style="font-size: 25px;">{{ comment.username }}</span>
                    <span style="font-size: 15px;color: rgb(114, 124, 124);float: right;padding-right: 10px;padding-top: 5px;">{{ comment.createTime }}</span>
                    <br>
                    <span style="font-size:18px;display: inline-block;word-break: break-all;white-space: normal;">
                        {{ comment.comment }}
                    </span>
                    <hr>
                </div>
            </el-aside>
            <el-divider style="height: 100em;" direction="vertical"></el-divider>
            <el-main>
                <v-md-preview :text="text"></v-md-preview>
            </el-main>
        </el-container>
    </el-container>
</template>
<style>
.el-divider--vertical {
    display: inline-block;
    width: 1px;
    height: 100%;
    margin: 0 8px;
    vertical-align: middle;
    position: relative;
}
</style>
<script>
export default {
    data() {
        var id = this.$route.query.id;
        var username = this.$route.query.username;
        var title;
        var text;
        var comments;
        return {
            title,
            text,
            id,
            username,
            comments
        }
    },
    methods: {
        addComment() {
            this.$prompt('请输入评论内容', '添加评论', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                inputPattern: /^.+$/,
                inputErrorMessage: '请输入评论内容!'
            }).then(({ value }) => {
                this.$message({
                    type: 'success',
                    message: '评论成功'
                });
                this.$axios({
                    method: 'post',
                    url: 'http://localhost:8080/show',
                    params: {
                        blog_id: this.id,
                        comment: value,
                        username: this.username
                    }
                }).then(() => {
                    location.reload();
                }).catch((err) => {
                    console.log(err);
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消评论'
                });
            });
        }
    },
    //根据id查text
    mounted() {
        this.$axios({
            method: 'get',
            url: 'http://localhost:8080/show',
            params: {
                id: this.id,
            }
        }).then((result) => {
            console.log(this.id);
            this.text = result.data.data[0].text;
            this.title = result.data.data[0].title;
            this.comments = result.data.data[1];
            console.log(this.comments);
        }).catch((err) => {
            console.log(err)
        });
    },
}
</script>