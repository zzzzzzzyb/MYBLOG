<template>
    <el-container style="height: 100%;">
        <el-header style="padding: 0;font-size: 30px;background: linear-gradient(to right, #72ade3, #fff);height: 80px;">
            <div> EDIT BLOG </div>
            <div>{{ title }}</div>
        </el-header>
        <el-main style="padding: 0;height: 100%;">
            <v-md-editor v-model="text" style="height: 100%;" @save="upload" :disabled-menus="[]"></v-md-editor>
        </el-main>
    </el-container>
</template>
<style></style>
<script>
export default {
    data() {
        var title = this.$route.query.title;
        var id = this.$route.query.id;
        return {
            text: '',
            title,
            id
        };
    },
    methods: {
        upload() {
            console.log(this.text);
            console.log(this.id);
            //上传text/title
            this.$axios({
                method: 'post',
                url: 'http://localhost:8080/edit',
                params: {
                    id: this.id,
                    text: this.text,
                }
            }).then((result) => {
                alert(result.data.msg);
            }).catch((err) => {
                console.log(err);
            });
        },
        // handleUploadImage(event, insertImage, files) {

        // },
    },
    mounted() {
        console.log(this.title);
        console.log(this.id);
        console.log("something is logged");
        //id查text
        this.$axios({
            method: 'get',
            url: 'http://localhost:8080/edit',
            params: {
                id: this.id
            }
        }).then((result) => {
            this.text = result.data.data.text
        }).catch((err) => {
            console.log(err);
        });
    },
}
</script>