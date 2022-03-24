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
}];

const config = {
    name: "msgaudit",
    menu: menu,
};

// module.exports.name = "Qadmin";
// module.exports.menu = menu;
