package com.ll;

import java.sql.Array;
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

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionWrite();
            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.startsWith("삭제?")) {
                actionRemove(cmd);
            } else if (cmd.startsWith("수정?")) {
                actionModify(cmd);
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

    void actionRemove(String cmd) {
        String[] cmdBits = cmd.split("\\?", 2);
        String action = cmdBits[0];
        String queryString = cmdBits[1];

        String[] queryStringBits = queryString.split("&");

        int id = 0;

        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];

            String[] queryParamStrBits = queryParamStr.split("=");

            String paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            if (paramName.equals("id")) {
                id = Integer.parseInt(paramValue);
            }
        }

        System.out.printf("%d번 명언을 삭제합니다.\n", id);


    }

    void actionModify(String cmd) {
        int id = getParamAsInt(cmd, "id", 0);

        if (id == 0) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        System.out.printf("%d번 명언을 삭제합니다.\n", id);
    }

    int getParamAsInt(String cmd, String paramName, int defaultValue) {

        String[] cmdBits = cmd.split("\\?", 2);
        String queryString = cmdBits[1];

        String[] queryStringBits = queryString.split("&");


        for (int i = 0; i < queryStringBits.length; i++) {
            String queryParamStr = queryStringBits[i];

            String[] queryParamStrBits = queryParamStr.split("=");

            String _paramName = queryParamStrBits[0];
            String paramValue = queryParamStrBits[1];

            if (_paramName.equals(paramName)) {
                try {
                    return Integer.parseInt(paramValue);
                } catch (NumberFormatException e) {
                    return defaultValue;
                }
            }
        }

        return defaultValue;
    }
}

