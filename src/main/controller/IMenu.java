package main.controller;

import main.dataStructure.Rectangle;
import main.model.Place;
public enum Criteria {
    MAIN, INSERT, REMOVE, SEARCH_PLACES, DISPLAY, UPDATE
}
public interface IMenu {
    void displayMenu(Criteria criteria);
//    private void subMenu(Criteria criteria);

    void inputBox(String title);
    void displayMessage(String message);
    void exit();
    void load(String filePath);


}
