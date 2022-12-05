FROM openjdk:8

EXPOSE 9090

ADD target/springangularcrudapplication.jar springangularcrudapplication.jar

ENTRYPOINT [ "java","-jar","springangularcrudapplication.jar" ]
