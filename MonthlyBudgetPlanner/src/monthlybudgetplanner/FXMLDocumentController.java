/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monthlybudgetplanner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import static java.lang.reflect.Array.get;
import java.net.URL;
import static java.time.Clock.system;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        incomeSourceBox.getItems().addAll(IncomeSource.values());
        expenseCategoryBox.getItems().addAll(ExpenseCategory.values());
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
        IncomeSource source = incomeSourceBox.getSelectionModel().getSelectedItem();
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
                double set_amount = totalIncome.get(get_int_month) + get_amount;
                totalIncome.set(get_int_month, set_amount);
             //   totalIncome.add(get_int_month, set_amount); 
            }
            totalIncomeField.setText(totalIncome.get(intMonth) + "");
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
    private void handleAddExpenseAction(ActionEvent event) {
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

}
