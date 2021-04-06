/*
 * This file is part of the zyan/wework-msgaudit.
 *
 * (c) 读心印 <aa24615@qq.com>
 *
 * This source file is subject to the MIT license that is bundled
 * with this source code in the file LICENSE.
 */

package com.php127.wework;

class ThreadMessage extends Thread {
    private Thread t;
    private String corpid;
    private String secret;
    private String prikey;

    ThreadMessage( String corpid, String secret, String prikey) {
        this.corpid = corpid;
        this.secret = secret;
        this.prikey = prikey;
    }

    public void run() {
        System.out.println("开始运行: " +  corpid );
        try {
            Message message = new Message(this.corpid,this.secret,this.prikey);
            message.getList();
        }catch (Exception e) {
            System.out.println("异常线程: " +  corpid );
            System.out.println(e.getMessage());
        }
        System.out.println("结束线程: " +  corpid );
    }

    public void start () {
        System.out.println("开始线程: " +  this.corpid );
        if (t == null) {
            t = new Thread (this);
            t.start ();
        }
    }
}