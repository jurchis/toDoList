package org.fasttrackit.todolist.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.fasttrackit.todolist.domain.ToDoItem;
import org.fasttrackit.todolist.service.ToDoItemService;
import org.fasttrackit.todolist.transfer.CreateToDoItemRequest;
import org.fasttrackit.todolist.transfer.UpdateToDoItemRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


//below annotation is adding functionality
@WebServlet("/to-do-items")
//HttpServlet is not available so we'll get POM.xml modified to add this class
public class ToDoItemServlet extends HttpServlet {
    //this is the place where we define how we receive the requests from the network
    private ToDoItemService toDoItemService = new ToDoItemService();

    //endpoint
    //INSERT
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //with object mapper we read from the request
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        CreateToDoItemRequest request =
                objectMapper.readValue(req.getReader(), CreateToDoItemRequest.class);
        try {
            toDoItemService.createToDoItem(request);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500,"Internal Server Error: " + e.getMessage());
        }
    }


    //DELETE
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        //for each primitive data type there is a wrapper class
        //Long - wrapper class for primitive data type long

        try {
            toDoItemService.deleteToDoItem(Long.parseLong(id));
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500,"Internal Server Error: " + e.getMessage());
        }
    }

    //UPDATE
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id =req.getParameter("id");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        //with the object mapper we convert the JSON from the request from postman
        //we read with object mapper

        UpdateToDoItemRequest request =
                objectMapper.readValue(req.getReader(), UpdateToDoItemRequest.class);

        //we call from service method updateToDoItem

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<ToDoItem> toDoItems = toDoItemService.getToDoItems();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            String response = objectMapper.writeValueAsString(toDoItems);

            resp.getWriter().print(response);

        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(500,"Internal Server Error: " + e.getMessage());
        }
    }
}
