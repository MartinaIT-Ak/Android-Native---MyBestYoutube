package com.ita.mybestyoutube.pojos;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "videos")
public class YoutubeVideo implements Serializable {
    //region Properties
    @PrimaryKey
    private long id;
    @ColumnInfo(name = "titre")
    private String titre;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "url")
    private String url;
    @ColumnInfo(name = "categorie")
    private String categorie;
    @ColumnInfo(name = "favori")
    private boolean favori;
    //endregion

    //region Constructor
    public YoutubeVideo() {
    }
    public YoutubeVideo(String titre, String description, String url, String categorie, boolean favori) {
        this.titre = titre;
        this.description = description;
        this.url = url;
        this.categorie = categorie;
        this.favori = favori;
    }
    //endregion

    //region Getters and Setters
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategorie() {
        return categorie;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public boolean isFavori() {
        return favori;
    }
    public void setFavori(boolean favori) {
        this.favori = favori;
    }
    //endregion

    //region Methods
    @Override
    public String toString() {
        return "YoutubeVideo{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", categorie='" + categorie + '\'' +
                ", favori=" + favori +
                '}';
    }
    //endregion
}
