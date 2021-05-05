package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.SimpleCalculatorImpl;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.*;

public class SimpleCalculatorImplTest {

  @Test
  public void when_noInputGiven_then_outputShouldBe0() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_inputIsPlus_then_outputShouldBe0Plus() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertPlus();
    assertEquals("0+", calculatorUnderTest.output());
  }


  @Test
  public void when_inputIsMinus_then_outputShouldBeCorrect() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    String expected = "0-";
    assertEquals(expected, calculatorUnderTest.output());
  }

  @Test
  public void when_callingInsertDigitWithIllegalNumber_then_exceptionShouldBeThrown() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    try {
      calculatorUnderTest.insertDigit(357);
      fail("should throw an exception and not reach this line");
    } catch (RuntimeException e) {
      // good :)
    }
  }


  @Test
  public void when_callingDeleteLast_then_lastOutputShouldBeDeleted() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.deleteLast();
    assertEquals("5", calculatorUnderTest.output());
  }

  @Test
  public void when_callingClear_then_outputShouldBeCleared() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_savingState_should_loadThatStateCorrectly() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    // give some input
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);

    // save current state
    Serializable savedState = calculatorUnderTest.saveState();
    assertNotNull(savedState);

    // call `clear` and make sure calculator cleared
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());

    // load the saved state and make sure state was loaded correctly
    calculatorUnderTest.loadState(savedState);
    assertEquals("5+7", calculatorUnderTest.output());
  }

  @Test
  public void when_savingStateFromFirstCalculator_should_loadStateCorrectlyFromSecondCalculator() {
    SimpleCalculatorImpl firstCalculator = new SimpleCalculatorImpl();
    SimpleCalculatorImpl secondCalculator = new SimpleCalculatorImpl();
    firstCalculator.insertDigit(5);
    firstCalculator.insertPlus();
    firstCalculator.insertDigit(7);
    Serializable savedState = firstCalculator.saveState();
    assertNotNull(savedState);
    secondCalculator.loadState(savedState);
    assertEquals("5+7", secondCalculator.output());
  }

  @Test
  public void when_GettingInputWithDelete_should_keepOutputConsistent() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.deleteLast();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(5);
    assertEquals("5+7-125", calculatorUnderTest.output());
  }

  @Test
  public void when_GettingInputWithTwoClear_should_printRightAnswer() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.clear();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals();
    assertEquals("1", calculatorUnderTest.output());
  }

  @Test
  public void when_GettingChainInput_should_printRightAnswer() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(4);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertEquals();
    assertEquals("4", calculatorUnderTest.output());
  }

  @Test
  public void when_GettingLongInput_should_keepOutputConsistent() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertDigit(9);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertDigit(2);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(3);
    calculatorUnderTest.insertDigit(3);
    assertEquals("-111-333", calculatorUnderTest.output());
  }

  @Test
  public void when_startingNegative_should_haveNegativeOutput() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertEquals();
    assertEquals("-8", calculatorUnderTest.output());
  }

  @Test
  public void when_negativeChain_should_haveNegativeResult() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(7);
    calculatorUnderTest.insertEquals();
    assertEquals("-15", calculatorUnderTest.output());
  }

  @Test
  public void when_clearEmpty_should_haveClearResult() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.clear();
    assertEquals("0", calculatorUnderTest.output());
  }

  @Test
  public void when_twoOperationsAfter_should_RemainRegular() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.clear();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertMinus();
    assertEquals("8-", calculatorUnderTest.output());
  }

  @Test
  public void when_equalAfterOperator_should_RemainRegular() {
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.clear();
    calculatorUnderTest.insertDigit(8);
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertEquals();
    assertEquals("8-", calculatorUnderTest.output());
  }

  @Test
  public void when_negativeFirst_should_flipOperation(){
    SimpleCalculatorImpl calculatorUnderTest = new SimpleCalculatorImpl();
    calculatorUnderTest.insertMinus();
    calculatorUnderTest.insertDigit(1);
    calculatorUnderTest.insertDigit(0);
    calculatorUnderTest.insertEquals();
    calculatorUnderTest.insertPlus();
    calculatorUnderTest.insertDigit(5);
    calculatorUnderTest.insertEquals();
    assertEquals("-5", calculatorUnderTest.output());
  }
}

