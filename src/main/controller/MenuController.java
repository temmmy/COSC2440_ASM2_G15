package main.controller;

import java.util.Scanner;

public class MenuController implements IMenu {
        private static MenuController instance;
        private Scanner scanner;

        private MenuController() {
                scanner = new Scanner(System.in);
        }

        public static MenuController getInstance() {
                if (instance == null) {
                        instance = new MenuController();
                }
                return instance;
        }

        @Override
        public void displayMenu(Criteria criteria) {
                switch (criteria) {
                        case MAIN:
                                // Display main menu options
                                break;
                        case INSERT:
                                // Display insert menu options
                                break;
                        case REMOVE:
                                // Display remove menu options
                                break;
                        case SEARCH_PLACES:
                                // Display search places menu options
                                break;
                        case DISPLAY:
                                // Display display menu options
                                break;
                        case UPDATE:
                                // Display update menu options
                                break;
                        default:
                                System.out.println("Invalid criteria.");
                }
        }

        private void subMenu(Criteria criteria) {

        }
        @Override
        public void inputBox(String title) {
                System.out.print(title + ": ");
        }

        @Override
        public void displayMessage(String message) {
                System.out.println(message);
        }

        @Override
        public void exit() {
                System.exit(0);
        }

        @Override
        public void load(String filePath) {
                // Load data from the specified file path
        }

        public String getInput() {
                return scanner.nextLine();
        }
}
