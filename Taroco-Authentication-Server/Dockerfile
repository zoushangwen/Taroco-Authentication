FROM java:8
ENV LANG C.UTF-8
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENV JAVA_OPTS="-Xmx2048m -Xms2048m"
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8800
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
