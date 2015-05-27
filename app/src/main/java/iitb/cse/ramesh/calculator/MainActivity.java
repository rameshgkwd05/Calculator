package iitb.cse.ramesh.calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    String inputString;
    int operation; // 1-divide, 2=mult, 3=subtract,4 = add,0=addition
    long num1=0;
    long num2;
    double result;
    boolean isOperationSet;
    boolean isNum1Set;
    boolean isNum2set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isOperationSet = false;
        isNum1Set = false;
        isNum2set = false;
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onDigitClick(View view) {
        Button b = (Button) view;
        String digit = b.getText().toString();

        TextView input = (TextView) findViewById(R.id.input);
        TextView resultView = (TextView) findViewById(R.id.result);
        Button DelButton = (Button) findViewById(R.id.buttonDel);
        DelButton.setText("DEL");
        if(isOperationSet){
            if(!isNum2set)
                isNum2set = true;
            num2=num2*10+Integer.parseInt(digit);
            result = getResult(num1,num2);
            resultView.setText(""+result);

        }else if(isNum1Set){
            num1=num1*10+Integer.parseInt(digit);
        }else{
            num1=Integer.parseInt(digit);
            isNum1Set = true;
        }
        inputString = input.getText().toString();
        inputString+=digit;
        input.setText(inputString);
    }

    public void onOpClick(View view) {
        boolean isDel = false;
        Button b = (Button) view;
        String digit = b.getText().toString();
        if(!isOperationSet)
            isOperationSet = true;
        TextView input = (TextView) findViewById(R.id.input);
        TextView resultView = (TextView) findViewById(R.id.result);
        Button DelButton = (Button) findViewById(R.id.buttonDel);
        if(input.getText().length() == 0)
               inputString = "";
        else
            inputString= input.getText().toString();
        switch(digit){
            case "DEL":
                        if(!inputString.equalsIgnoreCase(""));
                            inputString= inputString.substring(0,inputString.length()-1);
                        input.setText(inputString);
                        isDel = true;
                        break;
            case "CLR": inputString="";
                        isNum1Set=false;
                        isNum2set = false;
                        isOperationSet = false;
                        num1 = 0;
                        num2 = 0;
                        input.setText("");
                        resultView.setText("");
                        DelButton.setText("DEL");
                        isDel = true;
                        break;
            case "/":   operation=1;
                        isOperationSet = true;
                        num2=0;
                        DelButton.setText("DEL");
                        break;
            case "x":   operation=2;
                        isOperationSet = true;
                        num2=0;
                        DelButton.setText("DEL");
                        break;
            case "-":   operation=3;
                        isOperationSet = true;
                        num2=0;
                        DelButton.setText("DEL");
                        break;
            case "+":   operation=4;
                        isOperationSet = true;
                        num2=0;
                        DelButton.setText("DEL");
                        break;
            case "=":
                isDel = true;
                if(isOperationSet && isNum2set) {

                    result = getResult(num1,num2); //compute result
                    num1 = (long) result;       //assign result to num1
                    inputString=""+num1;        //display result as num1 for next computation
                    num2 = 0;                   //reset num2
                    isNum2set = false;          //expect num2 to be set
                    isOperationSet = false;
                    DelButton.setText("CLR");

                }


                input.setText(inputString);
                resultView.setText("");

        }
        if(!isDel){
            inputString+=digit;
            input.setText(inputString);
        }
    }

    private double getResult(long n1,long n2) {
        double res = 0.0;
        switch (operation) {

            case 1:if(n2!=0)
                        res = ((double) n1) / n2;
                    else
                        res = 0.0;
                break;
            case 2:
                res = n1 * n2;
                break;
            case 3:
                res = n1 - n2;
                break;
            case 4:
                res = n1 + n2;

        }
        return res;
    }

}
