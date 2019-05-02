package com.codecool.application_process.view;

import java.util.Scanner;

public class View {

    private Scanner scanner = new Scanner(System.in);

    public void printText(String text) {
        System.out.println(text);
    }

    public void printFormattedText(String format, Object... args) {
        System.out.printf(format, args);
    }

    public void displayLogo() {
        printText("       𝕎𝕖𝕝𝕔𝕠𝕞𝕖 𝕥𝕠\n"
                + "           🐘\n"
                + "     𝘼𝙥𝙥𝙡𝙞𝙘𝙖𝙩𝙞𝙤𝙣 𝙋𝙧𝙤𝙘𝙚𝙨𝙨");
    }

    public void displayMenu() {
        printText("\n𝕆𝕡𝕥𝕚𝕠𝕟𝕤:\n"
                + "⓵ ➤ 𝕄𝕖𝕟𝕥𝕠𝕣𝕤\n"
                + "⓶ ➤ 𝔸𝕡𝕡𝕝𝕚𝕔𝕒𝕟𝕥𝕤\n"
                + "⓷ ➤ 𝕊𝕖𝕒𝕣𝕔𝕙\n"
                + "⓪ ➤ 𝔼𝕩𝕚𝕥");
    }

    public void displayMentorsMenu() {
        printText("\n\n𝕄𝕖𝕟𝕥𝕠𝕣 𝕞𝕖𝕟𝕦:\n"
                + "⓵ ➤ 𝔻𝕚𝕤𝕡𝕝𝕒𝕪 𝕞𝕖𝕟𝕥𝕠𝕣𝕤 𝕨𝕚𝕥𝕙 𝕗𝕦𝕝𝕝 𝕟𝕒𝕞𝕖\n"
                + "⓶ ➤ 𝔻𝕚𝕤𝕡𝕝𝕒𝕪 𝕞𝕖𝕟𝕥𝕠𝕣𝕤 𝕟𝕚𝕔𝕜𝕟𝕒𝕞𝕖 𝕓𝕪 𝕔𝕙𝕠𝕤𝕖𝕟 𝕔𝕚𝕥𝕪\n"
                + "⓪ ➤ 𝔼𝕩𝕚𝕥");
    }

    public void displayApplicantsMenu() {
        printText("\n\n𝔸𝕡𝕡𝕝𝕚𝕔𝕒𝕟𝕥 𝕞𝕖𝕟𝕦:\n"
                + "⓵ ➤ 𝔸𝕡𝕡𝕖𝕟𝕕 𝕟𝕖𝕨 𝕒𝕡𝕡𝕝𝕚𝕔𝕒𝕟𝕥\n"
                + "⓶ ➤ 𝔻𝕖𝕝𝕖𝕥𝕖 𝕒𝕡𝕡𝕝𝕚𝕔𝕒𝕟𝕥\n"
                + "⓷ ➤ 𝕌𝕡𝕕𝕒𝕥𝕖 𝕒𝕡𝕡𝕝𝕚𝕔𝕒𝕟𝕥𝕤 𝕡𝕙𝕠𝕟𝕖 𝕟𝕦𝕞𝕓𝕖𝕣\n"
                + "⓸ ➤ 𝔽𝕚𝕟𝕕 𝕒𝕡𝕡𝕝𝕚𝕔𝕒𝕟𝕥 𝕓𝕪 ...\n"
                + "⓪ ➤ 𝔼𝕩𝕚𝕥");
    }

    public void displayFindApplicantMenu() {
        printText("\n\n𝔽𝕚𝕟𝕕 𝕒𝕡𝕡𝕝𝕚𝕔𝕒𝕟𝕥 ...\n"
                + "⓵ ➤ 𝕓𝕪 𝕟𝕒𝕞𝕖 𝕠𝕣 𝕗𝕦𝕝𝕝 𝕟𝕒𝕞𝕖\n"
                + "⓶ ➤ 𝕓𝕪 𝕖𝕞𝕒𝕚𝕝\n"
                + "⓷ ➤ 𝕓𝕪 𝕒𝕡𝕡𝕝𝕚𝕔𝕒𝕥𝕚𝕠𝕟 𝕔𝕠𝕕𝕖\n"
                + "⓪ ➤ 𝔼𝕩𝕚𝕥");
    }

    public int getIntInput() {
        int number;
        while (!scanner.hasNextInt()) {
            printText("Wrong input. Try again.");
            scanner.nextLine();
        }
        number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public String getStringInput() {
        return scanner.nextLine();
    }

    public void clearScreen() {
        printText("\033[H\033[2J");
        System.out.flush();
    }
}