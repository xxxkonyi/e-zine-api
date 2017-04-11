FROM frekele/gradle:3.4.1-jdk8u121
MAINTAINER konyi <xxxkonyi@gmail.com>

ADD / /tmp/build

#构建应用
RUN cd /tmp/build && gradle build \
   && mv application/build/libs/*.jar /app.jar
#清理编译痕迹
RUN cd / && rm -rf /tmp/build

EXPOSE 8080

ENV JAVA_OPTS=""

RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]