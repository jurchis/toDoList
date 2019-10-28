package org.fasttrackit.todolist;

import org.fasttrackit.todolist.domain.ToDoItem;
import org.fasttrackit.todolist.persistance.ToDoItemRepository;
import org.fasttrackit.todolist.transfer.CreateToDoItemRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        CreateToDoItemRequest request = new CreateToDoItemRequest();
        request.setDescription("Learn JDBC");
        request.setDeadline(LocalDate.now().plusWeeks(1));

        ToDoItemRepository toDoItemRepository = new ToDoItemRepository();

        toDoItemRepository.createToDoItem(request);

//        toDoItemRepository.updateToDoItem(2,true);
//
//        toDoItemRepository.deleteToDoItem(2);

        List<ToDoItem> toDoItem = toDoItemRepository.getToDoItem();
        System.out.println(toDoItem);

//        for(int i=0;i<toDoItemRepository.getToDoItem().size();i++){
//            System.out.println(toDoItemRepository.getToDoItem().get(i));
//        }
    }
}
