package com.ll.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private int lastQuotationId;
    private List<Quotaiton> quotaitons;
    private Scanner scanner;


    public App() {
        scanner = new Scanner(System.in);
        lastQuotationId = 0;
        quotaitons = new ArrayList<>();

        initTestData();

    }

    void initTestData() {
        for (int i = 0; i < 10; i++) {
            write("명언"+i, "작가"+i);
        }
    }

    public void run() {
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
                case "목록":
                    actionList();
                    break;
                case "삭제":
                    actionRemove(rq);
                    break;
                case "수정":
                    actionModify(rq);
                    break;
            }
        }
    }

    private void actionWrite() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        Quotaiton quotaiton = write(content, authorName);

        //System.out.printf("명언 : %s, 작가 : %s\n", content, authorName);
        System.out.printf("%d번 명언이 등록되었습니다.\n", quotaiton.getId());


    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");

        System.out.println("--------------------");

        Quotaiton quotaiton;

        if (quotaitons.isEmpty()) {
            System.out.println("등록된 명언이 없습니다.");
        }
        for (int i = quotaitons.size() - 1; i >= 0; i--) {
            quotaiton = quotaitons.get(i);
            System.out.printf("%d / %s / %s\n", quotaiton.getId(), quotaiton.getAuthorName(), quotaiton.getContent());
        }
    }

    private void actionRemove(Rq rq) {


        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }

        int index = findQuotationIndexById(id);

        if (index == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        quotaitons.remove(index);

        System.out.printf("%d번 명언을 삭제합니다.\n", id);


    }

    private Quotaiton write(String content, String authorName){
        lastQuotationId++;
        int id = lastQuotationId;

        Quotaiton quotaiton = new Quotaiton(id, content, authorName);

        quotaitons.add(quotaiton);

        return quotaiton;
    }


    private int findQuotationIndexById(int id) {
        for (int i = 0; i < quotaitons.size(); i++) {
            Quotaiton quotaiton = quotaitons.get(i);

            if (quotaiton.getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private void actionModify(Rq rq) {
        int id = rq.getParamAsInt("id", 0);

        if (id == 0) {
            System.out.println("id를 정확히 입력해주세요.");
            return;
        }

        int index = findQuotationIndexById(id);

        if (index == -1) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        Quotaiton quotaiton = quotaitons.get(index);

        System.out.printf("명언(기존) : %s\n", quotaiton.getContent());
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.printf("작가(기존) : %s\n", quotaiton.getAuthorName());
        System.out.print("작가 : ");
        String authorName = scanner.nextLine();

        quotaiton.setContent(content);
        quotaiton.setAuthorName(authorName);
        System.out.printf("%d번 명언을 수정했습니다.\n", id);
    }

}

