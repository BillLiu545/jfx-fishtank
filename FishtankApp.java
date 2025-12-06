import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.scene.control.cell.*;
import javafx.scene.control.Alert.*;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.TableColumn.*;
import javafx.application.*;
public class FishtankApp extends Application
{
    private final Fishtank tank = new Fishtank();
    private final ObservableList<Fish> fishList = tank.toObservableList();
    public void start(Stage mainStage)
    {
        // Main Layout and Scene
        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root, 450, 480);
        mainStage.setScene(mainScene);
        VBox mainLayout = new VBox();
        mainLayout.setAlignment(Pos.CENTER);
        root.setCenter(mainLayout);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setSpacing(15);
        mainStage.setTitle("Virtual Fish Tank");
        
        // Tableview to display all fish
        TableView<Fish> table = new TableView();
        table.setStyle("-fx-font-size: 15");
        mainLayout.getChildren().add(table);
        table.setItems(fishList);
        
        // Fish type column
        TableColumn typeCol = new TableColumn("Fish Type");
        typeCol.setCellValueFactory(new PropertyValueFactory("type"));
        typeCol.setMinWidth(140);
        table.getColumns().add(typeCol);
        
        // Number of fish column
        TableColumn numCol = new TableColumn("No. of Fish");
        numCol.setCellValueFactory(new PropertyValueFactory("num"));
        numCol.setMinWidth(140);
        table.getColumns().add(numCol);
        
        // Remove any extra columns or rows
        table.setMaxWidth(280);
        table.setMaxHeight(390);
        
        // Row for buttons to add and remove fish from the tank
        HBox buttonRow = new HBox();
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.setSpacing(15);
        buttonRow.setStyle("-fx-font-size: 15px; -fx-font-weight: bold");
        mainLayout.getChildren().add(buttonRow);
        // Add button
        Button addButton = new Button("Add Fish");
        addButton.setOnAction((event)->
        {
            Fish added = tank.add_input();
            if (added != null)
            {
                fishList.clear();
                ObservableList<Fish> otherList = tank.toObservableList();
                Iterator<Fish> iter = otherList.iterator();
                while (iter.hasNext())
                {
                    Fish next = iter.next();
                    fishList.add(next);
                }
            }
            table.refresh();
        });
        
        // Remove button
        Button removeButton = new Button("Remove Fish");
        removeButton.setOnAction((event)->
        {
            Fish removed = tank.remove_input();
            if (removed != null)
            {
                fishList.clear();
                ObservableList<Fish> otherList = tank.toObservableList();
                Iterator<Fish> iter = otherList.iterator();
                while (iter.hasNext())
                {
                    Fish next = iter.next();
                    fishList.add(next);
                }
            }
            table.refresh();
        });
        
        // Add both buttons in buttonRow
        buttonRow.getChildren().addAll(addButton, removeButton);
        
        // Set the menu for functionality
        MenuBar topMenu = new MenuBar();
        topMenu.setStyle("-fx-font-size: 15;");
        root.setTop(topMenu);
        
        // File menu
        Menu fileMenu = new Menu("File");
        topMenu.getMenus().add(fileMenu);
        
        // Menu item - Quit
        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction((e->
        {
            mainStage.close();
            System.exit(0);
        }));
        
        // Show once all elements are added in
        mainStage.show();
    }
}
