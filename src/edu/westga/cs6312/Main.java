package edu.westga.cs6312;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Main extends Application {
	private TextField currentAge = new TextField();
	private TextField ageAtRetirement = new TextField();
	private TextField salary = new TextField();
	private TextField percentContribution = new TextField();
	private TextField startingBalance = new TextField();
	private TextField expectedReturn = new TextField();
	private Text outputBalance = new Text();
	private Button btCalculate = new Button("Calculate");
	private TextField enterKey = new TextField();
	private Text currentAgeError = new Text();
	private Text ageAtRetirementError = new Text();
	private Text salaryError = new Text();
	private Text percentContributionError = new Text();
	private Text startingBalanceError = new Text();
	private Text expectedReturnError = new Text();

	

	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Set stage size to take up 50% of the viewport
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		BorderPane root = new BorderPane();
		root.setPrefSize(bounds.getWidth() / 2, bounds.getHeight());
		
		//Set header
		Text header = new Text("401K Calculator");
		header.setFont(Font.font("Courier", FontWeight.BOLD, 
			      FontPosture.ITALIC, 15));
		root.setTop(header);
		BorderPane.setAlignment(header, Pos.CENTER);
		
		GridPane form = new GridPane();
		form.setHgap(5);
	    form.setVgap(5);
		form.add(new Label("Current Age:"), 0, 0);
	    form.add(this.currentAge, 1, 0);
	    form.add(this.currentAgeError, 2, 0);
	    form.add(new Label("Age at Retirement:"), 0, 1);
	    form.add(this.ageAtRetirement, 1, 1);
	    form.add(this.ageAtRetirementError, 2, 1);
	    form.add(new Label("Annual Salary:"), 0, 2);
	    form.add(this.salary, 1, 2);
	    form.add(this.salaryError, 2, 2);
	    form.add(new Label("Percent to Contribute:"), 0, 3);
	    form.add(this.percentContribution, 1, 3);
	    form.add(this.percentContributionError, 2, 3);
	    form.add(new Label("Starting 401k Balance:"), 0, 4);
	    form.add(this.startingBalance, 1, 4);
	    form.add(this.startingBalanceError, 2, 4);
	    form.add(new Label("Rate of Expected Return:"), 0, 5);
	    form.add(this.expectedReturn, 1, 5);
	    form.add(this.expectedReturnError, 2, 5);
	    form.add(new Label("401k Balance:"), 0, 6);
	    form.add(this.outputBalance, 1, 6);
	    form.add(this.btCalculate, 1, 7);
	    form.setAlignment(Pos.CENTER);
		root.setCenter(form);
		
		this.btCalculate.setOnAction(e -> this.calculate());
		
		root.setOnKeyPressed(e -> {
		    if (e.getCode() == KeyCode.ENTER) {
		    	this.calculate();
		    }
		});
	    
		Scene mainScene = new Scene(root);
		primaryStage.setTitle("401K Calculator");
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
	
	private void calculate() {
		
	    try {
	    	Integer.parseInt(this.currentAge.getText());
	    	this.currentAgeError.setText("");
	    } catch (NumberFormatException e) {
	    	System.out.println("Current Age is not a number.");
	    	this.currentAgeError.setText("Must be a number");
	    }
		
	    try {
	    	Integer.parseInt(this.ageAtRetirement.getText());
	    	this.ageAtRetirementError.setText("");
	    } catch (NumberFormatException e) {
	    	System.out.println("Age at retirement is not a number.");
	    	this.ageAtRetirementError.setText("Must be a number");
	    }
	    
	    try {
	    	Integer.parseInt(this.salary.getText());
	    	this.salaryError.setText("");
	    } catch (NumberFormatException e) {
	    	System.out.println("Salary is not a number.");
	    	this.salaryError.setText("Must be a number");
	    }
	    
	    try {
	    	Integer.parseInt(this.percentContribution.getText());
	    	this.percentContributionError.setText("");
	    } catch (NumberFormatException e) {
	    	System.out.println("Percent contribution is not a number.");
	    	this.percentContributionError.setText("Must be a number");
	    }
	    
	    try {
	    	Integer.parseInt(this.startingBalance.getText());
	    	this.startingBalanceError.setText("");
	    } catch (NumberFormatException e) {
	    	System.out.println("Starting balance is not a number.");
	    	this.startingBalanceError.setText("Must be a number");
	    }
		
	    try {
	    	Integer.parseInt(this.expectedReturn.getText());
	    	this.expectedReturnError.setText("");
	    } catch (NumberFormatException e) {
	    	System.out.println("Expected Return is not a number.");
	    	this.expectedReturnError.setText("Must be a number");
	    }
	    
	    try {
	    	double p = (Double.parseDouble(this.percentContribution.getText())  * .01) * Double.parseDouble(this.salary.getText());
			double r = Double.parseDouble(this.expectedReturn.getText()) * .01;
			int n = Integer.parseInt(this.ageAtRetirement.getText()) - Integer.parseInt(this.currentAge.getText());
			
			double initialInvestment = Double.parseDouble(this.startingBalance.getText()) + p;
			
			double output = initialInvestment * (Math.pow((1 + r), n) - 1) / r;
			this.outputBalance.setText(String.valueOf(String.format("$%.2f", output)));
	    } catch (NumberFormatException e) {
	    	System.out.println("Unable to calculate output");
	    }
		
	}

}
