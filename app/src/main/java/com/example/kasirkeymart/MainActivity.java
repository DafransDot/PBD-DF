package com.example.kasirkeymart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialStyledDatePickerDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etinput;

    private Button btnInput, btnTotal;

    private RadioButton rbMembership;
    private ArrayList<barang> menu;

    private ArrayList<barang> pesanan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etinput = findViewById(R.id.etinput);
        btnInput = findViewById(R.id.btnInput);
        btnTotal = findViewById(R.id.btnTotal);
        rbMembership = findViewById(R.id.rbmembership);

        btnInput.setOnClickListener(this);
        btnTotal.setOnClickListener(this);

        menu = new ArrayList<>();
        menu.add(new barang(1, "kasur", 100000 + 500));
        menu.add(new barang(2, "Meja", 75000 + 500));
        menu.add(new barang(3, "kursi", 50000 + 500));
        menu.add(new barang(4, "wastafel", 120000 + 500));
        menu.add(new barang(5, "granit", 25000 + 500));

        pesanan = new ArrayList<>();

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnInput){
            inputPesanan();
        } else if (v.getId()== R.id.btnTotal) {
            calculateTotal();
        }


    }
    private void inputPesanan() {
        String inputText = etinput.getText().toString();

        try {
            int selectedId = Integer.parseInt(inputText);
            barang selectedbarang = null;
            for (barang brg : menu) {
                if (brg.getId() == selectedId) {
                    selectedbarang = brg;
                    break;
                }
            }


            if (selectedbarang != null) {
                pesanan.add(selectedbarang);
                Toast.makeText(this, "pesanan ditambahkan "+ selectedbarang.getNama(), Toast.LENGTH_SHORT).show();
                etinput.setText("");
            } else {
                Toast.makeText(this, "ID makanan tidak valid.", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Masukkan ID makanan yang valid.", Toast.LENGTH_SHORT).show();
        }
    }
    private void calculateTotal() {
        double total = 0;
        for ( barang brg : pesanan) {
            total += brg.getHarga() + 2000 ;
        }

        if (rbMembership.isChecked()) {
            double potongan = total * 0.05;
            total -= potongan;
        }

        showReceipt(total);
    }

    private void showReceipt(double total) {


    }
}