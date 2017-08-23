package io;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by Datartvinci on 2017/1/8.
 */
public class PipeStreamDemo {
    public static void main(String[] args) {
        new PipeStreamDemo();

    }
    public PipeStreamDemo(){
        SendMessage send = new SendMessage();
        ReceiveMessage receive = new ReceiveMessage();
        try {
            send.getOut().connect(receive.getInput());
            Thread t1 = new Thread(send);
            Thread t2 = new Thread(receive);
            t1.start();
            t2.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class SendMessage implements Runnable {
        private PipedOutputStream out = null;

        public PipedOutputStream getOut() {
            return this.out;
        }

        public SendMessage() {
            this.out = new PipedOutputStream();
        }

        @Override
        public void run() {
            try {
                System.out.println("waiting for signal...");
                Thread.sleep(2000);
                send();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void send() {

            String msg = "start";
            try {
                out.write(msg.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                        out = null;
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    class ReceiveMessage implements Runnable {
        private PipedInputStream input = null;

        public PipedInputStream getInput() {
            return this.input;
        }

        public ReceiveMessage() {
            this.input = new PipedInputStream();
        }

        private void receive() {

            byte[] b = new byte[1000];
            int len = 0;
            String msg = "";
            try {
                len = input.read(b);
                System.out.println("??");
                msg = new String(b, 0, len);
                if (msg.equals("start")) {
                    System.out
                            .println("received the start message, receive now can do something......");
                    Thread.interrupted();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (input != null) {
                        input.close();
                        input = null;
                    }
                } catch (Exception e) {
                }
            }
        }

        public void run() {
            try {
                receive();
            } catch (Exception e) {
            }
        }
    }

}
