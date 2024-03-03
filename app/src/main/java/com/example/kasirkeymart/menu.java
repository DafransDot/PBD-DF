package com.example.kasirkeymart;

import java.util.ArrayList;

class barang {
    private  int id;
    private String nama;
    private double harga;

    public barang (int id, String nama,double harga){
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }
    public int getId(){
        return id;
    }

    public String getNama(){
        return nama;
    }

    public double getHarga(){
        return harga;
    }

}

public class menu{
    public static void main(String[] args) {
        ArrayList<barang> menu = new ArrayList<>();

        menu.add(new barang(1, "kasur", 100000));
        menu.add(new barang(2, "Meja", 100000));
        menu.add(new barang(3, "kursi", 100000));
        menu.add(new barang(4, "wastafel", 100000));
        menu.add(new barang(5, "granit", 100000));


    }

}

