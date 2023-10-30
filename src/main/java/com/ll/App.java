package com.ll;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class App {

    int lastQuotationId;
    List<Quotaiton> quotaitons;
    Scanner scanner;


    App() {
        scanner = new Scanner(System.in);
        lastQuotationId = 0;
        quotaitons = new ArrayList<>();
    }

    void run() {
        System.out.println("=== 명언 앱 ===");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            Rq rq = new Rq(cmd);


            switch (rq.getAction()) {
                case "종료":
                    return;
                case "등록":
                    actionWrite();
                    break;
                case "목록" :
                    actionList();
                    break;
                case "삭제" :
                    actionRemove(rq);
                    break;
                case "수정" :
                    actionModify(rq);
                    break;
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

    void actionRemove(Rq rq) {


        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }

        int index = getIndexOfQuotaionById(id);

        if (index == -1){
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        quotaitons.remove(index);

        System.out.printf("%d번 명언을 삭제합니다.\n", id);


    }

    int getIndexOfQuotaionById(int id) {
        for (int i = 0; i < quotaitons.size(); i++){
            Quotaiton quotaiton = quotaitons.get(i);

            if (quotaiton.id == id){
                return i;
            }
        }
        return -1;
    }

    void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }

        System.out.printf("%d번 명언을 수정합니다.\n", id);
    }

}

