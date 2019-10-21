package org.fasttrackit.todolist.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.todolist.service.ToDoItemService;
import org.fasttrackit.todolist.transfer.CreateToDoItemRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


//below annotation is adding functionality
@WebServlet("/to-do-items")
//HttpServlet is not available so we'll get POM.xml modified to add this class
public class ToDoItemServlet extends HttpServlet {
    //this is the place where we define how we receive the requests from the network
    private ToDoItemService toDoItemService = new ToDoItemService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        CreateToDoItemRequest request =
                objectMapper.readValue(req.getReader(), CreateToDoItemRequest.class);
        try {
            toDoItemService.createToDoItem(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500,"Internal Server Error: " + e.getMessage());
        }
    }
}
