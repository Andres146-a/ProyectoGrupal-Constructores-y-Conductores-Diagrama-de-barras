package com.example.Controllers;

import com.example.Models.ConstructorResult;
import com.example.Repositories.ConstructorResultRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.List;

public class ConstructorResultsController {
    private TableView<ConstructorResult> tableView;

    public ConstructorResultsController() {
        // Constructor por defecto
    }

    public ConstructorResultsController(TableView<ConstructorResult> tableView) {
        this.tableView = tableView;
    }

    public List<ConstructorResult> getConstructorResultsByYear(int year) {
        ConstructorResultRepository repository = new ConstructorResultRepository();
        return repository.getConstructorResultsByYear(year);
    }

    public void updateResults(int year) {
        List<ConstructorResult> results = getConstructorResultsByYear(year);
        ObservableList<ConstructorResult> data = FXCollections.observableArrayList(results);
        if (tableView != null) {
            tableView.setItems(data);
        }
    }
}
