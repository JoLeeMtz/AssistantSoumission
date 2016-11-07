package com.soumission.assistant.assistantsoumission;

/**
 * Created by 201327549 on 2016-10-21.
 */

public class Items {
    private int _id;
    private String _nameItem;
    private String _price;
    private String _MAJ;

    // Constructeurs
    public Items() {

    }
    public Items(int id, String name, String price, String maj) {
        _id = id;
        _nameItem = name;
        _price = price;
        _MAJ = maj;
    }


    public int get_id() {
        return _id;
    }
    public void set_id(int _id) {
        this._id = _id;
    }


    public String get_nameItem() {
        return _nameItem;
    }
    public void set_nameItem(String _nameItem) {
        this._nameItem = _nameItem;
    }


    public String get_MAJ() {
        return _MAJ;
    }
    public void set_MAJ(String _MAJ) {
        this._MAJ = _MAJ;
    }


    public String get_price() {
        return _price;
    }
    public void set_price(String _price) {
        this._price = _price;
    }
}
