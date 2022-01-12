const menu = [{
    "name": "首页",
    "icon": "&#xe68e;",
    "url": "index.html",
    "hidden": false,
    "list": []
}, {
    "name": "基本组件",
    "icon": "&#xe653;",
    "url": "",
    "hidden": false,
    "list": [{
        "name": "layui基本组件",
        "url": "pages_component.html",
    }, {
        "name": "layui内置模块",
        "url": "pages_model.html"
    }, {
        "name": "提示框",
        "url": "pages_msg.html"
    }]
}, {
    "name": "用户管理",
    "icon": "&#xe612;",
    "url": "",
    "hidden": false,
    "list": [{
        "name": "用户列表",
        "url": "user_index.html"
    }, {
        "name": "添加用户",
        "url": "user_add.html"
    }]
}, {
    "name": "文章管理",
    "icon": "&#xe609;",
    "url": "",
    "hidden": false,
    "list": [{
        "name": "栏目管理",
        "url": "type_index.html"
    }, {
        "name": "文章管理",
        "url": "article_index.html"
    }]
}, {
    "name": "系统设置",
    "icon": "&#xe620;",
    "url": "",
    "hidden": false,
    "list": [{
        "name": "网站设置",
        "url": "web_index.html"
    }, {
        "name": "友情连接",
        "url": "flink_index.html"
    }, {
        "name": "导航管理",
        "url": "nav_index.html"
    }, {
        "name": "修改密码",
        "url": "web_pwd.html"
    }, {
        "name": "清除缓存",
        "url": "web_cache.html"
    }, {
        "name": "登录页",
        "url": "login.html"
    }]
}, {
    "name": "数据库",
    "icon": "&#xe857;",
    "url": "",
    "hidden": false,
    "list": [{
        "name": "备份数据库",
        "url": "db_backup.html"
    }, {
        "name": "还原数据库",
        "url": "db_reduction.html"
    }]
}, {
    "name": "退出登录",
    "icon": "&#xe65c;",
    "url": "out.html",
    "list": []
}, {
    "name": "开发者官网",
    "icon": "&#xe65f;",
    "url": "http://www.qadmin.net/",
    "target": "_blank",
    "list": []
}, {
    "name": "开发文档",
    "icon": "&#xe655;",
    "url": "http://docs.qadmin.net/",
    "target": "_blank",
    "list": []
}];

const config = {
    name: "Qadmin",
    menu: menu,
};

// module.exports.name = "Qadmin";
// module.exports.menu = menu;
