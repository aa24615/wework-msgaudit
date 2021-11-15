/*
 * This file is part of the zyan/wework-msgaudit.
 *
 * (c) 读心印 <aa24615@qq.com>
 *
 * This source file is subject to the MIT license that is bundled
 * with this source code in the file LICENSE.
 */

package com.php127.wework.message;

public class Threads extends Thread {
    private Thread thread;
    private String corpid;
    private String secret;
    private String prikey;

    public Threads(String corpid, String secret, String prikey) {
        this.corpid = corpid;
        this.secret = secret;
        this.prikey = prikey;
    }

    public void run() {
        System.out.println("开始运行: " +  corpid );
        try {
            while (true){
                try {
                    Message message = new Message(this.corpid,this.secret,this.prikey);
                    message.getList();
                    Thread.sleep( 5000) ;
                }catch (InterruptedException e){
                    System.out.println("异常: " +  e.getMessage() );
                }
            }
        }catch (UnsatisfiedLinkError e){

            System.out.println(e.getMessage());
            System.out.println("找不到动态库 WeWorkFinanceSdk_Java.so" );
            System.out.println("请使用linux系统,并将动态库拷贝到系统" );
        }catch (Exception e) {
            System.out.println("异常线程: " +  corpid );
            System.out.println(e.getMessage());
        }

        System.out.println("结束线程: " +  corpid );
    }

    public void start () {
        System.out.println("开始线程: " +  this.corpid );
        if (thread == null) {
            thread = new Thread (this);
            thread.start ();
        }
    }
}