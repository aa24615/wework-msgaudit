const http = axios.create({
    baseURL: 'http://127.0.0.1:8080'
});


// 添加请求拦截器
http.interceptors.request.use(
    function (config) {
        // 在发送请求之前做些什么
        return config;
    },
    function (error) {
        // 对请求错误做些什么
        return Promise.reject(error);
    }
);

// 添加响应拦截器
http.interceptors.response.use(
    function (response) {

        if (response.status == 200) {
            return response.data;
        } else {
            layer.closeAll();
            layer.msg('请求异常', function () {
            });
            return Promise.reject('请求异常');
        }
    },
    function (error) {
        // 对响应错误做点什么
        return Promise.reject(error);
    }
);



