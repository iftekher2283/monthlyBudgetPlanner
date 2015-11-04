/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monthlybudgetplanner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author iftekher
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TextField incomeAmountField;
    @FXML
    private ComboBox<IncomeSource> incomeSourceBox;
    @FXML
    private DatePicker incomeDateField;
    @FXML
    private TextField expenseAmountField;
    @FXML
    private ComboBox<ExpenseCategory> expenseCategoryBox;
    @FXML
    private DatePicker expenseDateField;
    @FXML
    private TextField totalIncomeField;
    @FXML
    private TextField totalExpenseField;
    @FXML
    private TextField remainingBalanceField;
    @FXML
    private TextField monthNameField;
    @FXML
    private ComboBox<StatementType> statementTypeBox;
    @FXML
    private ListView<String> statementListView;
    private ObservableList<String> statements;
    @FXML
    private ComboBox<StatementMonth> statementMonthBox;
    @FXML
    private TextField totalStatementField;
  //  private ComboBox<StatementMonth> statementMonthBox;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        incomeSourceBox.getItems().addAll(IncomeSource.values());
        expenseCategoryBox.getItems().addAll(ExpenseCategory.values());
        statementTypeBox.getItems().addAll(StatementType.values());
        statementMonthBox.getItems().addAll(StatementMonth.values());
      //  String month = statementMonthBox.getSelectionModel().getSelectedItem() + "";
      //  if(month != null){
        statements = FXCollections.observableArrayList();
        statementListView.setItems(statements);
      //  }
    }    


    @FXML
    private void totalIncomeField(ActionEvent event) {
    }

    @FXML
    private void totalExpenseField(ActionEvent event) {
    }

    @FXML
    private void remainingBalanceField(ActionEvent event) {
    }

    @FXML
    private void monthNameField(ActionEvent event) {
    }

    @FXML
    private void handleAddIncomeAction(ActionEvent event) {
        double amount = Double.parseDouble(incomeAmountField.getText());
        String source = incomeSourceBox.getSelectionModel().getSelectedItem() + "";
        LocalDate date = incomeDateField.getValue();
        String incomeDate = date + "";
        String month = "";
        String tokens[] = incomeDate.split("-");
        int intMonth = Integer.parseInt(tokens[1]);
        switch(intMonth){
            case 1:
                month = "January";
                break;
            case 2:
                month = "February";
                break;
            case 3:
                month = "March";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "June";
                break;
            case 7:
                month = "July";
                break;
            case 8:
                month = "August";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "October";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;
        }
        Income income = new Income(amount, source, incomeDate, month);
        try {
            RandomAccessFile incomes = new RandomAccessFile("incomes.txt", "rw");
            incomes.seek(incomes.length());
            incomes.writeBytes(income.getAmount() + "\n");
            incomes.writeBytes(income.getSource() + "\n");
            incomes.writeBytes(income.getDate() + "\n");
            incomes.writeBytes(income.getMonth() + "\n");
            ArrayList<Double> totalIncome = new ArrayList<>();
            double set_amount = 0.0;
            for(int i = 0; ; i++){
                if(incomes.readLine() == ""){
                    break;
                }
                double get_amount = Double.parseDouble(incomes.readLine());
                String get_source = incomes.readLine();
                String get_income_date = incomes.readLine();
                String get_tokens[] = get_income_date.split("-");
                int get_int_month = Integer.parseInt(tokens[1]);
                String get_month = incomes.readLine();
                Income get_income = new Income(get_amount, get_source, get_income_date, get_month);
                set_amount = totalIncome.get(get_int_month) + get_income.getAmount();
                totalIncome.add(get_int_month, set_amount);
                System.out.printf("%f\n", totalIncome.get(get_int_month));
             //   totalIncome.add(get_int_month, set_amount);
                totalIncomeField.setText(totalIncome.get(intMonth) + "");
                System.out.printf("%f\n", totalIncome.get(intMonth));
            }
           // totalIncomeField.setText(totalIncome.get(intMonth) + "");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  

    @FXML
    private void handleAddExpenseAction(ActionEvent event) {
        double amount = Double.parseDouble(expenseAmountField.getText());
        String category = expenseCategoryBox.getSelectionModel().getSelectedItem() + "";
        LocalDate date = expenseDateField.getValue();
        String expenseDate = date + "";
        String month = "";
        String tokens[] = expenseDate.split("-");
        int intMonth = Integer.parseInt(tokens[1]);
        switch(intMonth){
            case 1:
                month = "January";
                break;
            case 2:
                month = "February";
                break;
            case 3:
                month = "March";
                break;
            case 4:
                month = "April";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "June";
                break;
            case 7:
                month = "July";
                break;
            case 8:
                month = "August";
                break;
            case 9:
                month = "September";
                break;
            case 10:
                month = "October";
                break;
            case 11:
                month = "November";
                break;
            case 12:
                month = "December";
                break;
        }
        Expense expense = new Expense(amount, category, expenseDate, month);
        try {
            RandomAccessFile expenses = new RandomAccessFile("expense.txt", "rw");
            expenses.seek(expenses.length());
            expenses.writeBytes(expense.getAmount() + "\n");
            expenses.writeBytes(expense.getCategory() + "\n");
            expenses.writeBytes(expense.getDate() + "\n");
            expenses.writeBytes(expense.getMonth() + "\n");
            ArrayList<Double> totalExpense = new ArrayList<>();
            for(int i = 0; ; i++){
                if(expenses.readLine() == ""){
                    break;
                }
                double get_amount = Double.parseDouble(expenses.readLine());
                String get_category = expenses.readLine();
                String get_expense_date = expenses.readLine();
                String get_tokens[] = get_expense_date.split("-");
                int get_int_month = Integer.parseInt(get_tokens[1]);
                String get_month = expenses.readLine();
                Expense get_expense = new Expense(get_amount, get_category, get_expense_date, get_month);
                double set_amount = totalExpense.get(get_int_month) + get_expense.getAmount();
                totalExpense.set(get_int_month, set_amount);
             //   totalIncome.add(get_int_month, set_amount); 
            }
            totalIncomeField.setText(totalExpense.get(intMonth) + "");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void handleNewAction(ActionEvent event) {
    }

    @FXML
    private void handleLoadAction(ActionEvent event) {
    }

    @FXML
    private void handlePreviousAction(ActionEvent event) {
    }

    @FXML
    private void handleNextAction(ActionEvent event) {
    }

    @FXML
    private void handleStatementAction(ActionEvent event) {
        statements.remove(0, statements.size());
        
        String type = statementTypeBox.getSelectionModel().getSelectedItem() + "";
        
        
        if ("Income".equals(type)){
            try {
                double totalIncome = 0.0;
                RandomAccessFile incomes = new RandomAccessFile("incomes.txt", "rw");
                String line;
                for(int i = 0; ; i++){
                    line = incomes.readLine();
                    if("".equals(line)){
                        break;
                    }
                    double get_amount = Double.parseDouble(line);
                    line = incomes.readLine();
                    String get_source = line;
                    line = incomes.readLine();
                    String get_income_date = line;
                    line = incomes.readLine();
                    String get_month = line;
                    System.out.printf("%s\n", get_month);
                    Income get_income = new Income(get_amount, get_source, get_income_date, get_month);
                  //  String month = statementMonthBox.getSelectionModel().getSelectedItem() + "";
                  //  if(month.equalsIgnoreCase(get_income.getMonth())){
                 //   System.out.printf("%s\n", get_income.toString());
                    totalIncome = totalIncome + get_income.getAmount();
                    statements.add(i, get_income.toString()); 
                 //   System.out.printf("%f\n", totalIncome);
                    totalStatementField.setText(totalIncome + "");
                 //   }
                }
                //   System.out.printf("%f\n", totalIncome);
                //    totalStatementField.setText(totalIncome + "");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if("Expense".equals(type)){
            try {
                double totalExpense = 0.0;
                RandomAccessFile expenses = new RandomAccessFile("expense.txt", "rw");
                String line;
                ArrayList<Double> totalIncome = new ArrayList<>();
                for(int i = 0; ; i++){
                    line = expenses.readLine();
                    if(line == ""){
                        break;
                    }
                    double get_amount = Double.parseDouble(line);
                    line = expenses.readLine();
                    String get_category = line;
                    line = expenses.readLine();
                    String get_income_date = line;
                    line = expenses.readLine();
                    String get_month = line;
                    Expense get_expense = new Expense(get_amount, get_category, get_income_date, get_month);
                    totalExpense = totalExpense + get_expense.getAmount();
                    statements.add(i, get_expense.toString());
                    totalStatementField.setText(totalExpense + "");
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }

    

}
