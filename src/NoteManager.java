import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NoteManager {
    Scanner scanner = new Scanner(System.in);
    HashMap<Integer, Note> notes = new HashMap<>();


    public void addNote() {
        System.out.println("Введите заголовок: ");
        String title = scanner.nextLine();
        System.out.println("Введите текст заметки: ");
        String text = scanner.nextLine();
        if (title.trim().isEmpty()) {
            System.out.println("Ошибка: заголовок не может быть пустым!");
            return;
        }
        if (text.trim().isEmpty()) {
            System.out.println("Ошибка: заметка не может быть пустой!");
            return;
        } else {
            Note newNote = new Note(title, text);
            notes.put(newNote.getId(), newNote);
            System.out.println("Заметка успешно создана!");
            System.out.println("ID: " + newNote.getId());
        }

    }

    public void showAllNotes() {
        if (notes.isEmpty()) {
            System.out.println("Заметок пока нет!");
        } else {
            for (Note note : notes.values()) {
                System.out.println("ID: " + note.getId() + "\nЗаголовок: " + note.getTitle() +
                        "\nДата создания: " + note.getCreatedAt() + "\n" + note.getContent() + "\n-----------\n");
            }
        }
    }

    public void editNote() {
        while (true) {
            System.out.println("Введите ID заметки: ");
            try {
                int findId = scanner.nextInt();
                scanner.nextLine();
                Note foundNote = notes.get(findId);
                if (foundNote == null) {
                    System.out.println("Заметка с таким ID не найдена!");
                    return;
                } else if (foundNote != null) {
                    System.out.println("Введите новый заголовок (Enter - оставить без изменений): ");
                    String newTitle = scanner.nextLine();
                    if (!newTitle.trim().isEmpty()) {
                        foundNote.setTitle(newTitle);
                    }
                    System.out.println("Введите новый текст (Enter - оставить без изменений): ");
                    String newContent = scanner.nextLine();
                    if (!newContent.trim().isEmpty()) {
                        foundNote.setContent(newContent);
                    }
                    foundNote.updateUpdatedAt();
                    System.out.println("Заметка успешно обновлена!");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Введите число!");
                scanner.nextLine();
            }
        }

    }

    public void deleteNote() {
        while (true) {
            System.out.println("Введите ID заметки: ");
            try {
                int findId = scanner.nextInt();
                scanner.nextLine();
                Note foundNote = notes.get(findId);
                if (foundNote == null) {
                    System.out.println("Заметка с таким ID не найдена!");
                    return;
                } else {
                    notes.remove(findId);
                    System.out.println("Заметка успешно удалена!");
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Введите число!");
                scanner.nextLine();
            }
        }
    }

    public void showNoteById() {

        while (true) {
            System.out.print("Введите ID заметки: ");
            try {
                int findId = scanner.nextInt();
                scanner.nextLine();
                Note foundNote = notes.get(findId);
                if (foundNote !=  null) {
                    System.out.println("ID: " + foundNote.getId());
                    System.out.println("Заголовок : " + foundNote.getTitle());
                    System.out.println("Текст: " + foundNote.getContent());
                    System.out.println("Дата создания: " + foundNote.getCreatedAt());
                    break;
                } else {
                    System.out.println("Заметка с таким ID не найдена!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Введите число!");
                scanner.nextLine();
            }

        }
    }



}


