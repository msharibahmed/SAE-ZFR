package com.example.android.zfr.InputActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.android.zfr.ItemActivity.Last2;
import com.example.android.zfr.ItemActivity.PtIntakeItemActivity;
import com.example.android.zfr.Model.Transaction;
import com.example.android.zfr.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class InputActivity extends AppCompatActivity {
    private EditText nameofitem, boughtby, boughtfrom, costofoneitem;
    private Button addtransaction, canceltransaction;
    private TextView date_text, time_text, subitem, additem, total_cost_text, selectdate, selecttime, number_of_items_text;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private FirebaseDatabase database;
    private DatabaseReference transactionDb;
    int numberofitems = 0, totalcost = 1;
    private TextView refresh;
    ImageView inputback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        nameofitem = findViewById(R.id.name_of_item);
        boughtby = findViewById(R.id.bought_by);
        boughtfrom = findViewById(R.id.bought_from);
        costofoneitem = findViewById(R.id.cost_of_one_item);
        subitem = findViewById(R.id.sub_item);
        additem = findViewById(R.id.add_item);
        addtransaction = findViewById(R.id.add_transaction);
        canceltransaction = findViewById(R.id.cancel_transaction);
        date_text = findViewById(R.id.date);
        time_text = findViewById(R.id.time);
        total_cost_text = findViewById(R.id.total_cost);
        number_of_items_text = findViewById(R.id.number_of_items);
        selectdate = findViewById(R.id.date_button);
        selecttime = findViewById(R.id.time_button);
        refresh = findViewById(R.id.refresh);
        inputback = findViewById(R.id.input_back);
        inputback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputActivity.this, Last2.class);
                finish();
                startActivity(intent);
            }
        });


        database = FirebaseDatabase.getInstance();
        transactionDb = database.getReference().child("Steering Items Transaction");


        addtransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToFirebase();
            }
        });
        canceltransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (costofoneitem.getText().toString().isEmpty()) {
                    costofoneitem.setText("0");
                    numberofitems++;
                    number_of_items_text.setText(String.format(Locale.US, "%d", numberofitems));
                    totalcost = totalcost * ((Integer.parseInt(costofoneitem.getText().toString())) * numberofitems);
                    total_cost_text.setText(String.format(Locale.US, "%d", totalcost));
                    totalcost = 1;
                } else {
                    numberofitems++;
                    number_of_items_text.setText(String.format(Locale.US, "%d", numberofitems));
                    totalcost = totalcost * ((Integer.parseInt(costofoneitem.getText().toString())) * numberofitems);
                    total_cost_text.setText(String.format(Locale.US, "%d", totalcost));
                    totalcost = 1;
                }
            }
        });
        subitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberofitems <= 0) {
                    Toast.makeText(InputActivity.this, "Number Of items Cannot Be Less Than 0", Toast.LENGTH_SHORT).show();
                } else if (costofoneitem.getText().toString().isEmpty()) {
                    costofoneitem.setText("0");
                    numberofitems--;
                    number_of_items_text.setText(String.format(Locale.US, "%d", numberofitems));
                    totalcost = totalcost * ((Integer.parseInt(costofoneitem.getText().toString())) * numberofitems);
                    total_cost_text.setText(String.format(Locale.US, "%d", totalcost));
                    totalcost = 1;
                } else {
                    numberofitems--;
                    number_of_items_text.setText(String.format(Locale.US, "%d", numberofitems));
                    totalcost = totalcost * ((Integer.parseInt(costofoneitem.getText().toString())) * numberofitems);
                    total_cost_text.setText(String.format(Locale.US, "%d", totalcost));
                    totalcost = 1;
                }
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (costofoneitem.getText().toString().isEmpty()) {
                    costofoneitem.setText("0");
                    totalcost = totalcost * ((Integer.parseInt(costofoneitem.getText().toString())) * numberofitems);
                    total_cost_text.setText(String.format(Locale.US, "%d", totalcost));
                    totalcost = 1;
                }

                totalcost = totalcost * ((Integer.parseInt(costofoneitem.getText().toString())) * numberofitems);
                total_cost_text.setText(String.format(Locale.US, "%d", totalcost));
                totalcost = 1;
            }
        });
        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();

                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(InputActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener, year, month, day);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                date_text.setText(date);
            }
        };

        selecttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();

                int hour = cal.get(Calendar.HOUR);
                int minute = cal.get(Calendar.MINUTE);

                TimePickerDialog dialog = new TimePickerDialog(InputActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mTimeSetListener, hour, minute, true);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":" + minute;
                time_text.setText(time);
            }
        };

    }

    private void saveToFirebase() {
        String nameofitemstring = nameofitem.getText().toString();
        String boughtbystring = boughtby.getText().toString();
        String boughtfromstring = boughtfrom.getText().toString();
        String costofoneitemstring = costofoneitem.getText().toString();
        String datestring = date_text.getText().toString();
        String timestring = time_text.getText().toString();
        String totalcoststring = total_cost_text.getText().toString();
        String numberofitemstring = number_of_items_text.getText().toString();

        if (!TextUtils.isEmpty(nameofitemstring) &&
                !TextUtils.isEmpty(datestring) && !TextUtils.isEmpty(timestring) && !TextUtils.isEmpty(boughtbystring) &&
                !TextUtils.isEmpty(boughtfromstring) && !TextUtils.isEmpty(costofoneitemstring) &&
                !TextUtils.isEmpty(totalcoststring) && !TextUtils.isEmpty(numberofitemstring)) {
            Transaction transaction = new Transaction(nameofitemstring, boughtbystring, boughtfromstring, costofoneitemstring, datestring, timestring,
                    totalcoststring, numberofitemstring);
            transactionDb.push().setValue(transaction).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(InputActivity.this, " Item Data Added ", Toast.LENGTH_LONG).show();
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(InputActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            Toast.makeText(InputActivity.this, "All fields should be filled", Toast.LENGTH_SHORT).show();
        }
    }

}
