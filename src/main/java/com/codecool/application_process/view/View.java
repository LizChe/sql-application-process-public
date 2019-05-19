package com.codecool.application_process.view;

import java.util.List;
import java.util.Scanner;

import com.codecool.application_process.model.Applicant;
import com.codecool.application_process.model.Mentor;

public class View {

    private Scanner scanner;

    public View() {
        scanner = new Scanner(System.in);
    }

    public void printText(String text) {
        System.out.println(text);
    }

    public void displayLogo() {
        printText("\n\n       𝕎𝕖𝕝𝕔𝕠𝕞𝕖 𝕥𝕠\n"
                + "           🐘\n"
                + "     𝘼𝙥𝙥𝙡𝙞𝙘𝙖𝙩𝙞𝙤𝙣 𝙋𝙧𝙤𝙘𝙚𝙨𝙨");
    }

    public void displayMenu() {
        printText("\n𝕆𝕡𝕥𝕚𝕠𝕟𝕤:\n"
                + "⓵ ➤ 𝕄𝕖𝕟𝕥𝕠𝕣𝕤\n"
                + "⓶ ➤ 𝔸𝕡𝕡𝕝𝕚𝕔𝕒𝕟𝕥𝕤\n"
                + "⓷ ➤ 𝕊𝕖𝕒𝕣𝕔𝕙\n"
                + "⓪ ➤ 𝔼𝕩𝕚𝕥\n");
    }

    public void displayMentorsMenu() {
        printText("\n\n𝕄𝕖𝕟𝕥𝕠𝕣 𝕞𝕖𝕟𝕦:\n"
                + "⓵ ➤ 𝔻𝕚𝕤𝕡𝕝𝕒𝕪 𝕞𝕖𝕟𝕥𝕠𝕣𝕤 𝕨𝕚𝕥𝕙 𝕗𝕦𝕝𝕝 𝕟𝕒𝕞𝕖\n"
                + "⓶ ➤ 𝔻𝕚𝕤𝕡𝕝𝕒𝕪 𝕞𝕖𝕟𝕥𝕠𝕣𝕤 𝕟𝕚𝕔𝕜𝕟𝕒𝕞𝕖 𝕓𝕪 𝕔𝕙𝕠𝕤𝕖𝕟 𝕔𝕚𝕥𝕪\n"
                + "⓪ ➤ 𝔼𝕩𝕚𝕥\n");
    }

    public void displayApplicantsMenu() {
        printText("\n\n𝔸𝕡𝕡𝕝𝕚𝕔𝕒𝕟𝕥 𝕞𝕖𝕟𝕦:\n"
                + "⓵ ➤ 𝔸𝕡𝕡𝕖𝕟𝕕 𝕟𝕖𝕨 𝕒𝕡𝕡𝕝𝕚𝕔𝕒𝕟𝕥\n"
                + "⓶ ➤ 𝔻𝕖𝕝𝕖𝕥𝕖 𝕒𝕡𝕡𝕝𝕚𝕔𝕒𝕟𝕥\n"
                + "⓷ ➤ 𝕌𝕡𝕕𝕒𝕥𝕖 𝕒𝕡𝕡𝕝𝕚𝕔𝕒𝕟𝕥𝕤 𝕡𝕙𝕠𝕟𝕖 𝕟𝕦𝕞𝕓𝕖𝕣\n"
                + "⓸ ➤ 𝔽𝕚𝕟𝕕 𝕒𝕡𝕡𝕝𝕚𝕔𝕒𝕟𝕥 𝕓𝕪 ...\n"
                + "⓪ ➤ 𝔼𝕩𝕚𝕥\n");
    }

    public void displayFindApplicantMenu() {
        printText("\n\n𝔽𝕚𝕟𝕕 𝕒𝕡𝕡𝕝𝕚𝕔𝕒𝕟𝕥 ...\n"
                + "⓵ ➤ 𝕓𝕪 𝕟𝕒𝕞𝕖 𝕠𝕣 𝕗𝕦𝕝𝕝 𝕟𝕒𝕞𝕖\n"
                + "⓶ ➤ 𝕓𝕪 𝕖𝕞𝕒𝕚𝕝\n"
                + "⓷ ➤ 𝕓𝕪 𝕒𝕡𝕡𝕝𝕚𝕔𝕒𝕥𝕚𝕠𝕟 𝕔𝕠𝕕𝕖\n"
                + "⓪ ➤ 𝔼𝕩𝕚𝕥\n");
    }

    public void displayPressAnythingInput() {
        printText("\n\nPress anything to continue.");
        scanner.nextLine();
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

    private String getStringInput() {
        return scanner.nextLine();
    }

    public void clearScreen() {
        printText("\033[H\033[2J");
        System.out.flush();
    }

    public String getNotEmptyInputOf(String column) {
        printText(column);
        String input = getStringInput();
        while (input.isEmpty()) {
            printText("Input cannot be empty. Try again.");
            input = getStringInput();
        }
        return input;
    }

    public int getNotEmptyIntInputOf(String column) {
        printText(column);
        return getIntInput();
    }

    public String[] getValidFullName() {
        printText("Name & Surname:");
        String[] fullName = getStringInput().split(" ");
        while (fullName.length != 2) {
            printText("Wrong Input. Try again");
            fullName = getStringInput().split(" ");
        }
        return fullName;
    }

    public int getInt(String input) {
        return Integer.parseInt(input);
    }

     public boolean isNotEmpty(List list) {
         if (list.isEmpty()) {
             printText("No results.");
             return false;
         }
         return true;
     }

    public boolean isFirstName(String input) {
        String[] name = input.split(" ");
        return (name.length == 1);
    }

    public boolean isInt(String input) {
        return input.matches("\\d+");
    }

    public void printMentorsFullName(List<Mentor> mentors) {
        Table table = new Table();
        table.setHeaders("First Name", "Last Name");
        for (Mentor mentor : mentors) {
            table.addRow(mentor.getFirstName(), mentor.getLastName());
        }
        table.printTable();
    }

    public void printMentorsNickName(List<Mentor> mentors) {
        Table table = new Table();
        table.setHeaders("Nick Name");
        for (Mentor mentor : mentors) {
            table.addRow(mentor.getNickName());
        }
        table.printTable();
    }

    public void printApplicantsFullNamePhoneNumber(List<Applicant> applicants) {
        Table table = new Table();
        table.setHeaders("First Name", "Last Name", "Phone");
        for (Applicant applicant : applicants) {
            table.addRow(applicant.getFirstName(),
                    applicant.getLastName(),
                    applicant.getPhoneNumber());
        }
        table.printTable();
    }

    public void printApplicants(List<Applicant> applicants) {
        Table table = new Table();
        table.setHeaders("ID", "First Name", "Last Name", "Phone", "Email", "Application Code");
        for (Applicant applicant : applicants) {
            table.addRow(String.valueOf(applicant.getID()),
                    applicant.getFirstName(),
                    applicant.getLastName(),
                    applicant.getPhoneNumber(),
                    applicant.getEmail(),
                    String.valueOf(applicant.getApplicationCode()));
        }
        table.printTable();
    }

    public void printMentors(List<Mentor> mentors) {
        Table table = new Table();
        table.setHeaders("ID", "First Name", "Last Name", "Nick Name", "Phone", "Email", "City", "Favourite Number");
        for (Mentor mentor : mentors) {
                table.addRow(String.valueOf(mentor.getID()),
                        mentor.getFirstName(),
                        mentor.getLastName(),
                        mentor.getNickName(),
                        mentor.getPhoneNumber(),
                        mentor.getEmail(),
                        mentor.getCity(),
                        String.valueOf(mentor.getFavouriteNumber()));
        }
        table.printTable();
    }
}