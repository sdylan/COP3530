import java.util.*;
import java.io.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class n00448518 extends Application{	
	
	private String input;
	private String output = "";

	public static void main(String[] args){
		launch(args);
	}
		
	@Override
	public void start (Stage primaryStage){

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setHgap(5);
		grid.setVgap(5);

		Label label = new Label("Enter the capacity followed by item weights:");
		GridPane.setConstraints(label, 0, 0);
		grid.getChildren().add(label);

		TextField inField = new TextField();
		inField.setPrefColumnCount(30);
		inField.getText();
		GridPane.setConstraints(inField, 0, 1);
		grid.getChildren().add(inField);

		Button submit = new Button("Submit");
		GridPane.setConstraints(submit, 1, 1);
		grid.getChildren().add(submit);
		
		Button clear = new Button("Clear");
		StackPane cl = new StackPane();
		cl.setAlignment(Pos.TOP_LEFT);
		cl.getChildren().add(clear);	
		GridPane.setConstraints(cl, 1, 2);
		grid.getChildren().add(cl);
		
		TextArea outField = new TextArea();
		outField.setPromptText("Press submit button or enter key to see results.");
		outField.setPrefColumnCount(30);
		outField.setPrefRowCount(15);
		outField.getText();
		GridPane.setConstraints(outField, 0, 2);
		grid.getChildren().add(outField);

		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e){
				if (inField.getText() != null && !inField.getText().isEmpty()){
					input = inField.getText();
					combinations();
					outField.setText(output);
					output = "";
				}
			}
		});	

		clear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e){
				inField.clear();
				outField.clear();
			}			
		});

		inField.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent e){
				if(e.getCode().equals(KeyCode.ENTER)){
					input = inField.getText();
					combinations();
					outField.setText(output);
					output = "";
				}
			}
		});
		
		Scene scene = new Scene(grid);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Knapsack Problem");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void combinations(){
		
		String[] str = input.split("[ \t]+");
		int capacity = Integer.parseInt(str[0]);
		int[] rocks = new int[str.length-1];
		String inSack = "";

		for (int i = 0; i <rocks.length;i++){
			rocks[i] = Integer.parseInt(str[i+1]);
		}
		
		knapsack(capacity, rocks, inSack, 0);

		return;		
	}

	

	public void knapsack(int capacity, int[] rocks, String inSack, int index){
		
		if (capacity == 0){	
			output = output + inSack + "\n";
			return;
		}

		if(index==rocks.length) return;

		knapsack(capacity, rocks, inSack, index+1);		

		if(rocks[index]<=capacity){
			capacity -= rocks[index];
			String sack = inSack + " " + rocks[index];
			knapsack(capacity, rocks, sack, index+1);
		}
	}
}
