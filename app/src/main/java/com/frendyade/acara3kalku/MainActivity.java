package com.frendyade.acara3kalku;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String currentOperator;
    private double firstNumber, secondNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.tvDisplay);

        setNumberButtonListeners();
        setOperatorButtonListeners();
    }

    private void setNumberButtonListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String currentText = display.getText().toString();

                if (currentText.equals("0")) {
                    display.setText(b.getText().toString());
                } else {
                    display.setText(currentText + b.getText().toString());
                }
            }
        };

        findViewById(R.id.btn0).setOnClickListener(listener);
        findViewById(R.id.btn1).setOnClickListener(listener);
        findViewById(R.id.btn2).setOnClickListener(listener);
        findViewById(R.id.btn3).setOnClickListener(listener);
        findViewById(R.id.btn4).setOnClickListener(listener);
        findViewById(R.id.btn5).setOnClickListener(listener);
        findViewById(R.id.btn6).setOnClickListener(listener);
        findViewById(R.id.btn7).setOnClickListener(listener);
        findViewById(R.id.btn8).setOnClickListener(listener);
        findViewById(R.id.btn9).setOnClickListener(listener);
    }

    private void setOperatorButtonListeners() {
        findViewById(R.id.btnAdd).setOnClickListener(new OperatorClickListener("+"));
        findViewById(R.id.btnSubtract).setOnClickListener(new OperatorClickListener("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(new OperatorClickListener("*"));
        findViewById(R.id.btnDivide).setOnClickListener(new OperatorClickListener("/"));

        findViewById(R.id.btnEquals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondNumber = Double.parseDouble(display.getText().toString());
                double result = 0;

                switch (currentOperator) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "*":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        result = firstNumber / secondNumber;
                        break;
                }

                display.setText(String.valueOf(result));
            }
        });

        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display.setText("0");
                firstNumber = 0;
                secondNumber = 0;
                currentOperator = "";
            }
        });
    }

    private class OperatorClickListener implements View.OnClickListener {
        private final String operator;

        OperatorClickListener(String operator) { this.operator = operator; }
        @Override
        public void onClick(View v) {
            firstNumber = Double.parseDouble(display.getText().toString());
            currentOperator = operator;
            display.setText("0");
        }
    }
}
