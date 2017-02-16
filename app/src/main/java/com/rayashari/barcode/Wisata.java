package com.rayashari.barcode;

/**
 * Created by rayasha on 2/13/2017.
 */

public class Wisata {

    private String wisata_title;
    private String wisata_desc;
    private String wisata_image;

    public Wisata(){

    }

    public Wisata(String wisata_title, String wisata_image, String wisata_desc) {
        this.wisata_title = wisata_title;
        this.wisata_image = wisata_image;
        this.wisata_desc = wisata_desc;
    }

    public String getWisata_image() {
        return wisata_image;
    }

    public void setWisata_image(String wisata_image) {
        this.wisata_image = wisata_image;
    }

    public String getWisata_desc() {
        return wisata_desc;
    }

    public void setWisata_desc(String wisata_desc) {
        this.wisata_desc = wisata_desc;
    }

    public String getWisata_title() {
        return wisata_title;
    }

    public void setWisata_title(String wisata_title) {
        this.wisata_title = wisata_title;
    }


}
