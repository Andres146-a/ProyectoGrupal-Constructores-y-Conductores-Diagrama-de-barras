package com.example.Controllers;

import com.example.Models.DriverResult;
import com.example.Repositories.DriverResultRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.List;

public class DriverResultsController {
    private TableView<DriverResult> tableView;

    public DriverResultsController() {
        // Constructor por defecto
    }

    public DriverResultsController(TableView<DriverResult> tableView) {
        this.tableView = tableView;
    }

    public List<DriverResult> getDriverResultsByYear(int year) {
        DriverResultRepository repository = new DriverResultRepository();
        return repository.getDriverResultsByYear(year);
    }

    public void updateResults(int year) {
        List<DriverResult> results = getDriverResultsByYear(year);
        ObservableList<DriverResult> data = FXCollections.observableArrayList(results);
        if (tableView != null) {
            tableView.setItems(data);
        }
    }
}
