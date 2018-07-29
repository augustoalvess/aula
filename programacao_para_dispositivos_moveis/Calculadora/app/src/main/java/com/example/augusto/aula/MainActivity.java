package com.example.augusto.aula;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    private Button zeroBtn;
    private Button oneBtn;
    private Button twoBtn;
    private Button threeBtn;
    private Button fourBtn;
    private Button fiveBtn;
    private Button sixBtn;
    private Button sevenBtn;
    private Button eightBtn;
    private Button nineBtn;
    private Button subBtn;
    private Button addBtn;
    private Button divBtn;
    private Button multBtn;
    private Button equalBtn;
    private Button delBtn;
    private Button clearBtn;
    private TextView calcText;

    private boolean restart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zeroBtn = (Button) findViewById(R.id.zero_btn);
        oneBtn = (Button) findViewById(R.id.one_btn);
        twoBtn = (Button) findViewById(R.id.two_btn);
        threeBtn = (Button) findViewById(R.id.three_btn);
        fourBtn = (Button) findViewById(R.id.four_btn);
        fiveBtn = (Button) findViewById(R.id.five_btn);
        sixBtn = (Button) findViewById(R.id.six_btn);
        sevenBtn = (Button) findViewById(R.id.seven_btn);
        eightBtn = (Button) findViewById(R.id.eight_btn);
        nineBtn = (Button) findViewById(R.id.nine_btn);
        subBtn = (Button) findViewById(R.id.sub_btn);
        addBtn = (Button) findViewById(R.id.add_btn);
        divBtn = (Button) findViewById(R.id.div_btn);
        multBtn = (Button) findViewById(R.id.mult_btn);
        equalBtn = (Button) findViewById(R.id.equal_btn);
        delBtn = (Button) findViewById(R.id.del_btn);
        clearBtn = (Button) findViewById(R.id.clear_btn);
        calcText = (TextView) findViewById(R.id.calc_text);

        zeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRestart();
                calcText.setText(calcText.getText() + "0");
            }
        });

        oneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRestart();
                calcText.setText(calcText.getText() + "1");
            }
        });

        twoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRestart();
                calcText.setText(calcText.getText() + "2");
            }
        });

        threeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRestart();
                calcText.setText(calcText.getText() + "3");
            }
        });

        fourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRestart();
                calcText.setText(calcText.getText() + "4");
            }
        });

        fiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRestart();
                calcText.setText(calcText.getText() + "5");
            }
        });

        sixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRestart();
                calcText.setText(calcText.getText() + "6");
            }
        });

        sevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRestart();
                calcText.setText(calcText.getText() + "7");
            }
        });

        eightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRestart();
                calcText.setText(calcText.getText() + "8");
            }
        });

        nineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRestart();
                calcText.setText(calcText.getText() + "9");
            }
        });

        subBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRestart();
                calcText.setText(calcText.getText() + "-");
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRestart();
                calcText.setText(calcText.getText() + "+");
            }
        });

        divBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRestart();
                calcText.setText(calcText.getText() + "÷");
            }
        });

        multBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRestart();
                calcText.setText(calcText.getText() + "x");
            }
        });

        equalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence calc = calcText.getText();
                if (calc.length() > 0) {
                    restart = true;
                    calc = calc.toString().replace("x", "*").replace("÷", "/");
                    ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
                    try {
                        Object result = engine.eval(calc.toString());
                        calcText.setText(result.toString());
                    } catch (ScriptException err) {
                        calcText.setText(err.getMessage());
                    }
                }
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence calc = calcText.getText();
                if (calc.length() > 0) {
                    calc = calc.toString().substring(0, calc.length() - 1);
                    calcText.setText(calc);
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart = true;
                checkRestart();
            }
        });
    }

    public void checkRestart() {
        if (restart) {
            calcText.setText(null);
            restart = false;
        }
    }
}
