# AndroidCalculator - Calculator exercise for Android developers

I pledge the highest level of ethical principles in support of academic excellence.  
I ensure that all of my work reflects my own abilities and not those of someone else.

#   Question
Saying we want to add a cool feature - button "x" to run multiplication.
What code do we need to change/add/remove to support this feature?
Which tests can we run on the calculator? On the activity? On the app?

# Answer
We need to add the following:
    - X button in the UI
    - AddMultiplication method to add X to the string
    - Add multiplication support in out InsertEqual Method.
We will run the following tests:
    - Calculator - check multiplication with a big number as a result.
    - Calculator - check multiplication with different signs (+ and -)
    - Calculator - Check that X added to the output string correctly.
    - Activity - Check that the button X calls the multiplication method.
    - App - Add flowTest  for different sign multiplication
    - App - Add flowTest for 0 multiplication.
    - App - Add flowTest for a long number as a result.