import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private Scanner scanner;
    private NoteManager noteApp;

    public Main() {
        this.scanner = new Scanner(System.in);
        this.noteApp = new NoteManager();

    }

    static void main() {
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
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");
            try {

                int choise = scanner.nextInt();
                scanner.nextLine();
                switch (choise) {
                    case 1:
                        noteApp.addNote();
                        break;
                    case 2:
                        noteApp.showAllNotes();
                        break;
                    case 3:
                        noteApp.showNoteById();
                        break;
                    case 4:
                        noteApp.editNote();
                        break;
                    case 5:
                        noteApp.deleteNote();
                        break;
                    case 0:
                        System.exit(0);
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