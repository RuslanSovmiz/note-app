import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private Scanner scanner;
    private NoteManager noteApp;

    public Main() {
        this.scanner = new Scanner(System.in);
        this.noteApp = NoteManager.loadFromFile("Notes.ser");

    }

    public static void main(String[] args) {
        Main app = new Main();
        app.runMenu();

    }

    public void runMenu () {
        while (true) {
            System.out.println("1. Добавить заметку");
            System.out.println("2. Показать все заметки");
            System.out.println("3. Показать заметку по ID");
            System.out.println("4. Редактировать заметку");
            System.out.println("5. Удалить заметку");
            System.out.println("6. Поиск Заметки");
            System.out.println("7. Сортировка");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");
            try {

                int choise = scanner.nextInt();
                scanner.nextLine();
                switch (choise) {
                    case 1:
                        System.out.println("Введите заголовок: ");
                        String title = scanner.nextLine();
                        System.out.println("Введите текст заметки: ");
                        String content = scanner.nextLine();
                        noteApp.addNote(title, content);
                        break;
                    case 2:
                        noteApp.showAllNotes();
                        break;
                    case 3:
                        System.out.print("Введите ID заметки: ");
                        int showNoteId = scanner.nextInt();
                        scanner.nextLine();
                        noteApp.showNoteById(showNoteId);
                        break;
                    case 4:
                        System.out.println("Введите ID заметки: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Введите новый заголовок (Enter - оставить без изменений): ");
                        String editTitle = scanner.nextLine();
                        System.out.println("Введите новый текст (Enter - оставить без изменений): ");
                        String editContent = scanner.nextLine();
                        noteApp.editNote(id, editTitle, editContent);
                        break;
                    case 5:
                        System.out.println("Введите ID заметки: ");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine();
                        noteApp.deleteNote(deleteId);
                        break;
                    case 6:
                        System.out.println("Введите текст для поиска: ");
                        String findText = scanner.nextLine();
                        noteApp.findInText(findText);
                        break;
                    case 7:
                        boolean back = false;
                        while (!back) {
                            System.out.println("1. Сортировка по алфавиту");
                            System.out.println("2. Сортировка по дате создания/изменения");
                            System.out.println("0. Выйти в гланое меню");
                            int choiseSort = scanner.nextInt();
                            scanner.nextLine();
                            switch (choiseSort) {
                                case 1:
                                    noteApp.sortNotesByTitle();
                                    break;
                                case 2:
                                    break;
                                case 0:
                                    back = true;
                                    break;
                            }
                        }
                        break;

                    case 0:
                        try {
                            noteApp.saveToFile("Notes.ser");
                            System.out.println("Сохранение прошло успешно!");
                            return;
                        } catch (IOException e) {
                            System.out.println("Не удалось сохранить файл, повторите попытку!");
                        }
                        break;
                    default:
                        System.out.println("Неверный выбор!");

                }
            } catch (InputMismatchException e) {
                System.out.println("Введите число!");
                scanner.nextLine();
            }

        }

    }

}
