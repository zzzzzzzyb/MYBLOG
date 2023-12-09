FROM swr.cn-north-1.myhuaweicloud.com/codeci/special_base_image:centos7-base-1.0.2
USER build
COPY ./target/MYBLOG-0.0.1-SNAPSHOT.jar /demo/app.jar
