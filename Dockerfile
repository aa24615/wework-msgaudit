FROM daocloud.io/library/java
COPY ./ /app/wework
COPY ./lib/libWeWorkFinanceSdk_Java.so /usr/lib/libWeWorkFinanceSdk_Java.so
WORKDIR /app/wework
RUN java -jar target/wework-msgaudit-1.0.jar