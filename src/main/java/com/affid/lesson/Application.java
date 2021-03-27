//Мои контакты:
//      ВКонтакте - vk.com/affid.fedorov
//      Inst - @affid.fedorov
//      Почта - aafyodorov@miem.hse.ru
package com.affid.lesson;

import java.util.ArrayList;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        LocalCinemaClient client = new LocalCinemaClient();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Как вас зовут?");
        String name = scanner.nextLine();
        System.out.println("Здравствуйте, " + name +"!");
        printMenu();
        System.out.print("Введите номер действия: ");
        int action = scanner.nextInt();
        scanner.skip("\n");
        while(action != 0) {
            switch (action) {
                case 1:
                    System.out.println(client.getFilms());
                    break;
                case 2:
                    ArrayList<String> genres = new ArrayList<>();
                    System.out.println("Введите искомые жанры. Чтобы закончить ввод отправьте пустую строку");
                    String line = scanner.nextLine();
                    while (!"".equals(line)) {
                        genres.add(line);
                        line = scanner.nextLine();
                    }
                    System.out.println(client.getFilms(genres));
                    break;
                case 3:
                    System.out.println(client.getActors());
                    break;
                case 4:
                    System.out.print("Введите имя Актера: ");
                    String actorName = scanner.nextLine();
                    System.out.println(client.getFilmsByActor(actorName));
                    break;
                case 5:
                    System.out.print("Введите имя Режиссера: ");
                    String directorName = scanner.nextLine();
                    System.out.println(client.getFilms(directorName));
                    break;
                case 6:
                    System.out.print("Введите искомый рейтинг: ");
                    float rate = scanner.nextFloat();
                    System.out.println(client.getFilms(rate));
                    break;
                case 9:
                    printMenu();
                    break;
                default:
                    System.out.println("Такой опции нет!");
                    printMenu();
            }
            System.out.print("Введите номер действия: ");
            action = scanner.nextInt();
            scanner.skip("\n");
        }
        scanner.close();
    }

    /**
     * Вывод меню на консоль.
     *
     * Допишите свои варианты!:)
     */
    public static void printMenu(){
        System.out.println("Возможные действия:");
        System.out.println("1 - Показать все фильмы");
        System.out.println("2 - Показать фильмы по жанру");
        System.out.println("3 - Вывести всех Актеров");
        System.out.println("4 - Показать все фильмы Актера");
        System.out.println("5 - Показать фильмы по режиссеру");
        System.out.println("6 - Показать фильмы с рейтингом");
        System.out.println("9 - Показать меню снова");
        System.out.println("0 - Выйти");
    }

}
