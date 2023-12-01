<template>
    <el-container style="background: #eef3ff;padding: 0,0,0,0;margin: 0,0,0,0;">
        <el-header style="font-size:30px;padding:0,0,0,0;background: linear-gradient(to right, #72ade3, #fff);">
            <div style="display: flex;align-items: center;padding-top: 5px;">
                <span>登录My_Blog</span>
            </div>
        </el-header>
        <el-main style="padding: 0,0,0,0;">
            <div>
                <el-row type="flex" class="row-bg" justify="center" align=middle :gutter="20">
                    <el-col :span="10">
                        <div class="grid-content bg-purple">
                            <img src="../resource/R-C.png" style="padding-top: 150px;padding-left: 50px;">
                        </div>
                    </el-col>
                    <el-col :span="10">
                        <div class="grid-content bg-purple-light">
                            <div style="font-size: 30px;padding-left: 275px;padding-top: 180px">欢迎回来!</div>
                            <el-form ref="form" :model="user" label-width="60px">
                                <el-form-item label="用户名" style="padding-top: 20px;padding-left: 100px;">
                                    <el-input v-model="user.username" id="User"></el-input>
                                </el-form-item>
                                <el-form-item label="密码" style="padding-left: 100px;">
                                    <el-input type="password" v-model="user.password" style="width:400px;"></el-input>
                                </el-form-item>
                                <el-form-item style="padding-left: 240px;">
                                    <el-button type="primary" @click="onSubmit" style="width: 100px;">登录</el-button>
                                    <br>
                                    <el-button type="text" style="width:100px;padding-top: 50px;"
                                        @click="onSubscribe">注册新账号</el-button>
                                </el-form-item>
                            </el-form>
                        </div>
                    </el-col>
                </el-row>
            </div>
            <el-dialog title="注册" :visible.sync="Visible">
                <el-form status-icon :rules="rules" ref="subscribe" :model="subscribe">
                    <el-form-item label="用户名" prop="username">
                        <el-input type="text" v-model="subscribe.username" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input type="password" v-model="subscribe.password" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="checkpassword">
                        <el-input type="password" v-model="subscribe.checkpassword" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('subscribe')">提交</el-button>
                        <el-button @click="resetForm('subscribe')">重置</el-button>
                    </el-form-item>
                </el-form>
            </el-dialog>
        </el-main>
    </el-container>
</template>
<style>
html,
body,
.el-container {
    height: 100%;
}

#User {
    width: 400px
}

.el-row {
    margin-top: 100px;
}

.el-col {
    border-radius: 4px;
}

.bg-purple-dark {
    background: #99a9bf;
}

.bg-purple {
    background: #d3dce6;
}

.bg-purple-light {
    background: #e5e9f2;
}

.grid-content {
    border-radius: 4px;
    height: 600px;

}

.row-bg {
    padding: 10px 0;
    height: 700px;
    background-color: #eef3ff;
}
</style>
<script>
export default {
    data() {
        var checkuser = (_rule, value, callback) => {
            if (!value) {
                return callback(new Error('请输入用户名'));
            }
            else{
                callback();
            }
        };
        var validatePass = (_rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.subscribe.checkpassword !== '') {
                    this.$refs.subscribe.validateField('checkpassword');
                }
                callback();
            }
        };
        var validatePass2 = (_rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.subscribe.password) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        return {
            user: {
                username: "",
                password: "",
            },
            Visible: false,
            subscribe: {
                username: "",
                password: "",
                checkpassword: "",
            },
            rules: {
                username: [
                    { validator: checkuser, trigger: 'blur' }
                ],
                password: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                checkpassword: [
                    { validator: validatePass2, trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        onSubmit() {
            console.log("!");
            this.$axios({
                method:'get',
                url:'http://localhost:8080/',
                params:{
                    username:this.user.username,
                    password:this.user.password,
                }
            }).then((result) => {
                if (result.data.data==1) {
                    return this.$router.push({path:'/home',query:{username:this.user.username}});
                }
                else{
                    alert("用户名或密码错误!")
                }
            }).catch((err) => {
                console.log(err);
            });
        },
        onSubscribe() {
            this.Visible = true;
            console.log("?");

        },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    console.log('submit!');
                    this.Visible=false;
                    alert('注册成功!');
                    this.$axios({
                        method:'post',
                        url:'http://localhost:8080/',
                        params:{
                            username:this.subscribe.username,
                            password:this.subscribe.password,
                        }
                    }).then((result) => {
                        console.log(result);
                        return this.$router.push({path:'/home',query:{username:this.subscribe.username}})
                    }).catch((err) => {
                        console.log(err);
                    });
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        }
    }
}
</script>