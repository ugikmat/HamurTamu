package com.example.mat.hamurtamu.model;

import android.media.Image;

import java.util.List;

/**
 * Created by Mat on 23/07/2017.
 * Item Kost
 */

public class Kost {
    private String name;
    private int harga;
    private String alamat;
    private List<String> fasilitas;
    private List<Image> images;

    public void addImages(Image image) {
        images.add(image);
    }

    public void addFasilitas(String fasilitas) {
        this.fasilitas.add(fasilitas);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<String> getFasilitas() {
        return fasilitas;
    }
}
