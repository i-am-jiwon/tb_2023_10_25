package com.ll;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class App {

    int lastQuotationId;
    List<Quotaiton> quotaitons;
    Scanner scanner;


    App(){
        scanner = new Scanner(System.in);
        lastQuotationId = 0;
        quotaitons = new ArrayList<>();
    }

    void run() {
        System.out.println("=== 명언 앱 ===");



        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {
                actionList();
            } else if(cmd.startsWith("삭제?")){
                actionRemove(cmd);
            }


        }
    }

    void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        lastQuotationId++;
        int id = lastQuotationId;

        Quotaiton quotaiton = new Quotaiton(id, content, authorName);

        quotaitons.add(quotaiton);
        //System.out.printf("명언 : %s, 작가 : %s\n", content, authorName);
        System.out.printf("%d번 명언이 등록되었습니다.\n", lastQuotationId);


    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");

        System.out.println("--------------------");

        Quotaiton quotaiton;

        if (quotaitons.isEmpty()) {
            System.out.println("등록된 명언이 없습니다.");
        }
        for (int i = quotaitons.size() - 1; i >= 0; i--) {
            quotaiton = quotaitons.get(i);
            System.out.printf("%d / %s / %s\n", quotaiton.id, quotaiton.authorName, quotaiton.content);
        }
    }

    void actionRemove(String cmd){
        String idStr = cmd.replace("삭제?=","");
        int id =Integer.parseInt(idStr);

        System.out.println(idStr+"번 목록이 삭제되었습니다.");
    }
}
