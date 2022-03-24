const menu = [{
    "name": "首页",
    "icon": "&#xe68e;",
    "url": "index.html",
    "hidden": false,
    "active":false,
    "list": []
}, {
    "name": "企业管理",
    "icon": "&#xe653;",
    "url": "",
    "hidden": false,
    "list": [{
        "name": "企业列表",
        "url": "pages_component.html",
    }, {
        "name": "添加企业",
        "url": "pages_model.html"
    },]
}, {
    "name": "会话存档",
    "icon": "&#xe63a;",
    "url": "",
    "hidden": false,
    "list": [{
        "name": "员工维度",
        "url": "user_index.html"
    }, {
        "name": "客户维度",
        "url": "user_add.html"
    }]
},
    {
        "name": "违规监控",
        "icon": "&#xe6b2;",
        "url": "",
        "hidden": false,
        "list": [{
            "name": "设置规则",
            "url": "user_index.html"
        }, {
            "name": "违规记录",
            "url": "user_add.html"
        }]
    }
    ];

const config = {
    name: "msgaudit",
    menu: menu,
};

// module.exports.name = "Qadmin";
// module.exports.menu = menu;
