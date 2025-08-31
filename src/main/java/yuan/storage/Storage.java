package yuan.storage;

import yuan.task.Task;
import yuan.tasklist.TaskList;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() {
        TaskList taskList = new TaskList();
        File file = new File(filePath);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return taskList;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                taskList.addTask(Task.fromStorageFormat(line));
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }

        return taskList;
    }

    public void save(TaskList taskList) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            for (Task task : taskList.getTasks()) {
                bw.write(task.toStorageFormat());
                bw.newLine();
            }
            bw.close();

        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

}
