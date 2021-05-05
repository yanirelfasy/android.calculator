package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

  @VisibleForTesting
  public SimpleCalculator calculator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (calculator == null) {
      calculator = new SimpleCalculatorImpl();
    }

    TextView calcOutput = findViewById(R.id.textViewCalculatorOutput);
    calcOutput.setText(calculator.output());
    setNumbersListeners(calcOutput);
    setActionsListeners(calcOutput);


  }

  protected void addDigitToOutput(int digit, TextView calcOutput){
    calculator.insertDigit(digit);
    calcOutput.setText(calculator.output());
  }

  protected void setActionsListeners(TextView calcOutput){
    findViewById(R.id.buttonPlus).setOnClickListener(v->{
      calculator.insertPlus();
      calcOutput.setText(calculator.output());
    });

    findViewById(R.id.buttonMinus).setOnClickListener(v->{
      calculator.insertMinus();
      calcOutput.setText(calculator.output());
    });

    findViewById(R.id.buttonEquals).setOnClickListener(v->{
      calculator.insertEquals();
      calcOutput.setText(calculator.output());
    });

    findViewById(R.id.buttonClear).setOnClickListener(v->{
      calculator.clear();
      calcOutput.setText(calculator.output());
    });

    findViewById(R.id.buttonBackSpace).setOnClickListener(v->{
      calculator.deleteLast();
      calcOutput.setText(calculator.output());
    });
  }

  protected void setNumbersListeners(TextView calcOutput){
    findViewById(R.id.button0).setOnClickListener( v -> {
      addDigitToOutput(0, calcOutput);
    });
    findViewById(R.id.button1).setOnClickListener( v -> {
      addDigitToOutput(1, calcOutput);
    });
    findViewById(R.id.button2).setOnClickListener( v -> {
      addDigitToOutput(2, calcOutput);
    });
    findViewById(R.id.button3).setOnClickListener( v -> {
      addDigitToOutput(3, calcOutput);
    });
    findViewById(R.id.button4).setOnClickListener( v -> {
      addDigitToOutput(4, calcOutput);
    });
    findViewById(R.id.button5).setOnClickListener( v -> {
      addDigitToOutput(5, calcOutput);
    });
    findViewById(R.id.button6).setOnClickListener( v -> {
      addDigitToOutput(6, calcOutput);
    });
    findViewById(R.id.button7).setOnClickListener( v -> {
      addDigitToOutput(7, calcOutput);
    });
    findViewById(R.id.button8).setOnClickListener( v -> {
      addDigitToOutput(8, calcOutput);
    });
    findViewById(R.id.button9).setOnClickListener( v -> {
      addDigitToOutput(9, calcOutput);
    });
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("calc_state", calculator.saveState());
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    calculator.loadState(savedInstanceState.getSerializable("calc_state"));
  }
}