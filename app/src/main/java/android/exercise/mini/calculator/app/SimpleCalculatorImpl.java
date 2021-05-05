package android.exercise.mini.calculator.app;

import java.io.Serializable;



public class SimpleCalculatorImpl implements SimpleCalculator {
  // Constants
  private static final String INITIAL_STRING = "";
  private static final String [] OPERATIONS = {"+", "-"};

  // Props
  private String outputDisplay = INITIAL_STRING;

  // Util methods
  private boolean includes (String[] arr, String toCheck){
    for(String s : arr){
      if(s.equals(toCheck)){
        return true;
      }
    }
    return false;
  }

  private boolean isAllowToUseOperation(){
    if(this.outputDisplay.length() > 0){
      String lastInInput = this.outputDisplay.substring(this.outputDisplay.length() - 1);
      for (String operation : OPERATIONS) {
        if (operation.equals (lastInInput)) {
          return false;
        }
      }
    }
    return true;
  }

  private long applyOperation(long prevResult, String operation, long num){
    switch(operation){
      case "+": return prevResult + num;
      case "-": return prevResult - num;
      default: return prevResult;
    }
  }

  @Override
  public String output() {
    return this.outputDisplay.equals(INITIAL_STRING) ? "0" : this.outputDisplay;
  }

  @Override
  public void insertDigit(int digit) {
    if(digit < 0 || digit > 9){
      throw new RuntimeException("Invalid Input");
    }
    this.outputDisplay += digit;
  }

  @Override
  public void insertPlus() {
    if(this.isAllowToUseOperation()){
      this.outputDisplay += this.outputDisplay.equals(INITIAL_STRING) ? "0+" : "+";
    }
  }

  @Override
  public void insertMinus() {
    if(this.isAllowToUseOperation()){
      this.outputDisplay += this.outputDisplay.equals(INITIAL_STRING) ? "0-" : "-";
    }
  }

  @Override
  public void insertEquals() {
    String[] operations = this.outputDisplay.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
    boolean minusFlag = includes(OPERATIONS, operations[0]);
    if(minusFlag){
      String [] flippedeOperations = new String[operations.length - 1];
      flippedeOperations[0] = operations[3];
      flippedeOperations[1] = operations[0];
      flippedeOperations[2] = operations[1];
      System.arraycopy(operations, 4, flippedeOperations, 3, flippedeOperations.length - 3);
      operations = flippedeOperations;
    }
    long result = Long.parseLong(operations[0]);
    if(!includes(OPERATIONS, operations[operations.length - 1])){
      for(int i = 1; i < operations.length - 1; i+=2){
        result = applyOperation(result, operations[i], Long.parseLong(operations[i + 1]));
      }
      this.outputDisplay = String.valueOf(result);
    }
  }

  @Override
  public void deleteLast() {
    if(this.outputDisplay.length() > 0){
      this.outputDisplay = this.outputDisplay.substring(0, this.outputDisplay.length() - 1);
    }
  }

  @Override
  public void clear() {
    this.outputDisplay = "";
  }

  @Override
  public Serializable saveState() {
    CalculatorState state = new CalculatorState();
    state.setOutputDisplay(this.outputDisplay);
    return state;
  }

  @Override
  public void loadState(Serializable prevState) {
    if (!(prevState instanceof CalculatorState)) {
      return; // ignore
    }
    CalculatorState casted = (CalculatorState) prevState;
    this.outputDisplay = casted.getOutputDisplay();
  }

  private static class CalculatorState implements Serializable {
    private String outputDisplay;

    public CalculatorState(){
      this.outputDisplay = "";
    }

    public void setOutputDisplay(String value){
      this.outputDisplay = value;
    }

    public String getOutputDisplay(){
      return this.outputDisplay;
    }

  }
}
