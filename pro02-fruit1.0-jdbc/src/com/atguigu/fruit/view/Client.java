package com.atguigu.fruit.view;

import com.atguigu.fruit.controller.Menu;

import java.lang.reflect.Field;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Menu menu = new Menu();

        boolean exit = false;

        while (!exit) {
            int slt = 0;
            try {
                slt = menu.showMainMenu();
            } catch (InputMismatchException e) {
                Class<Menu> menuClass = Menu.class;
                Field input = menuClass.getDeclaredField("input");
                input.setAccessible(true);
                input.set(menu, new Scanner(System.in));
                System.out.println("请重新输入...");
                continue;
            }
            switch (slt) {
                case 1:
                    menu.showFruitList();
                    break;
                case 2:
                    menu.addFruit();
                    break;
                case 3:
                    menu.showFruitInfo();
                    break;
                case 4:
                    menu.delFruit();
                    break;
                case 5:
                    exit = menu.exit();
                    break;
                default:
                    System.out.println("请按套路出牌。");
                    break;
            }
        }
        System.out.println("谢谢使用！再见！");
    }
}
