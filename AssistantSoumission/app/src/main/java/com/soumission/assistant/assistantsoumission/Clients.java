package com.soumission.assistant.assistantsoumission;

/**
 * Created by Joaquin on 2016-11-02.
 */

public class Clients {
    private int _id;
    private String _nom;
    private String _prenom;
    private String _ville;
    private String _codePostal;
    private String _adresse;
    private String _telephone;
    private String _cell;
    private String _courriel;

    // Constructeurs
    public Clients() {

    }
    public Clients(int id, String nom, String prenom, String ville, String codePostal, String adresse, String telephone, String cell, String courriel) {
        _id = id;
        _nom = nom;
        _prenom = prenom;
        _ville = ville;
        _codePostal = codePostal;
        _adresse = adresse;
        _telephone = telephone;
        _cell = cell;
        _courriel = courriel;
    }

    public int get_id() {
        return _id;
    }
    public void set_id(int _id) {
        this._id = _id;
    }


    public String get_nom() {
        return _nom;
    }
    public void set_nom(String _nom) {
        this._nom = _nom;
    }


    public String get_prenom() {
        return _prenom;
    }
    public void set_prenom(String _prenom) {
        this._prenom = _prenom;
    }


    public String get_ville() {
        return _ville;
    }
    public void set_ville(String _ville) {
        this._ville = _ville;
    }


    public String get_codePostal() {
        return _codePostal;
    }
    public void set_codePostal(String _codePostal) {
        this._codePostal = _codePostal;
    }


    public String get_adresse() {
        return _adresse;
    }
    public void set_adresse(String _adresse) {
        this._adresse = _adresse;
    }


    public String get_telephone() {
        return _telephone;
    }
    public void set_telephone(String _telephone) {
        this._telephone = _telephone;
    }


    public String get_cell() {
        return _cell;
    }
    public void set_cell(String _cell) {
        this._cell = _cell;
    }


    public String get_courriel() {
        return _courriel;
    }
    public void set_courriel(String _courriel) {
        this._courriel = _courriel;
    }
}
