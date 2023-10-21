
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//  Строков В С
//  Список дел.  Приложение должно создавать задачу и добавлять в список. Можно завершить(выполнена)
// Сохранение и считывание задач в файл.
// При запуске считываться из файла данные о невыполненных задачах и добавляться в список.
public class Main {
    private static final List<Task> taskList = new ArrayList<>();
    static String file = "task.txt";


    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        System.out.println("Вас приветствует Список задач.");
        readFromFile(); // Считывание данных из файла
        System.out.println("Введите новую задачу.");
        String input = scr.nextLine();
        Task task1 = new Task(input);
        addTask(task1); // Добавление задачи
        saveToFile(); // Сохраняем.
    }


    private static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while (((line = reader.readLine()) != null)) {
                String[] parts = line.split(";"); // После каждой залачи ;
                String taskName = parts[0];
                boolean taskComplete = Boolean.parseBoolean(parts[1]);
                Task task = new Task(taskName);
                task.setComplete(taskComplete);
                taskList.add(task);
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения");
        }
    }

    private static void saveToFile() {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : taskList) {
                writer.write(task.getName() + ";" ); //+ task.isComplete());
                writer.newLine();

            }
        } catch (IOException e) {
            System.out.println("Ошибка записи. " + e.getMessage());
        }
        System.out.println("Задача записана");
    }

    private static void addTask(Task task) {
        taskList.add(task);
    }

}


class Task  {
    private final String name;
    private boolean complete;

    // Конструктор

    public Task(String name) {
        this.name = name;
        this.complete = false;
    }

    public String getName() {
        return name;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}

//
//    String text = "hi";
//        try(FileOutputStream fileOutputStream = new FileOutputStream(text)) {
//                byte[] buffer = text.getBytes();
//                fileOutputStream.write(buffer, 0, buffer.length);
//                System.out.println("File Write.");
//                }catch (IOException exception){
//                System.out.println(exception.getMessage());
//                }