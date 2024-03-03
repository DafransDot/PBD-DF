package com.example.kasirkeymart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialStyledDatePickerDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etinput;

    private TextView tvBon;

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
        tvBon = findViewById((R.id.tvBon));

        btnInput.setOnClickListener(this);
        btnTotal.setOnClickListener(this);

        menu = new ArrayList<>();
        menu.add(new barang(1, "kasur", 100000 ));
        menu.add(new barang(2, "Meja", 75000 ));
        menu.add(new barang(3, "kursi", 50000 ));
        menu.add(new barang(4, "wastafel", 120000 ));
        menu.add(new barang(5, "granit", 25000 ));

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
                Toast.makeText(this, "ID pesanan tidak valid.", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Masukkan ID pesanan yang valid.", Toast.LENGTH_SHORT).show();
        }
    }
    private void calculateTotal() {
        double total = 0;
        double potongan = 0;
        for ( barang brg : pesanan) {
            total += brg.getHarga() + 500 ;
        }

        if (rbMembership.isChecked()) {
            potongan = total * 0.05;
            total -= potongan;
        }

        showReceipt(total, potongan);
    }

    private void showReceipt(double total, double potongan) {
        StringBuilder strukText = new StringBuilder("\bTransaksi Anda\b\n");

        if (!pesanan.isEmpty() ) {
            for (barang barang : pesanan) {
                strukText.append(barang.getNama()).append(" - Rp").append(barang.getHarga()).append("\n");
            }
        } else {
            strukText.append("Tidak ada pesanan.");
        }

        strukText.append("\nDengan Admin perbarang Rp.500/barang \n dengan potongan Membership : Rp. "+potongan +"\nTotal Pembayaran: Rp").append(total);

        // Set teks struk ke dalam TextView atau komponen yang sesuai
        tvBon.setText(strukText.toString());

        // Reset pesanan setelah menampilkan struk
        pesanan.clear();
    }
}