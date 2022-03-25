package com.php127.wework.controller;


import com.php127.wework.Response;
import com.php127.wework.service.CorpService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/corp")
public class CorpController extends BaseController {

    @PostMapping("/list")
    public Object getList() {

        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        if (page == null) {
            page = "1";
        }

        if (limit == null) {
            limit = "10";
        }

        CorpService corp = new CorpService();

        return Response.success(corp.getList(page, limit));
    }

    @PostMapping("/create")
    public Object create() {
        String corpid = request.getParameter("corpid");
        String secret = request.getParameter("secret");
        String corpname = request.getParameter("corpname");
        String limits = request.getParameter("limits");
        String timeout = request.getParameter("timeout");

        CorpService corp = new CorpService();

        boolean res = corp.create(corpid, secret, corpname, limits, timeout);

        if (res) {
            return Response.success();
        }

        return Response.error(corp.getErrorMessage());
    }

    @PostMapping("/update")
    public Object update() {

        String id = request.getParameter("id");
        String corpid = request.getParameter("corpid");
        String secret = request.getParameter("secret");
        String corpname = request.getParameter("corpname");
        String limits = request.getParameter("limits");
        String timeout = request.getParameter("timeout");

        CorpService corp = new CorpService();

        boolean res = corp.update(id, corpid, secret, corpname, limits, timeout);

        if (res) {
            return Response.success();
        }

        return Response.error(corp.getErrorMessage());
    }

    @PostMapping("/update/status")
    public Object updateStatus() {

        String id = request.getParameter("id");
        String status = request.getParameter("status");

        CorpService corp = new CorpService();

        boolean res = corp.updateStatus(id, status);

        if (res) {
            return Response.success();
        }

        return Response.error(corp.getErrorMessage());
    }

    @PostMapping("/delete")
    public Object delete() {

        String id = request.getParameter("id");

        CorpService corp = new CorpService();

        boolean res = corp.delete(id);

        if (res) {
            return Response.success();
        }

        return Response.error();
    }
}
