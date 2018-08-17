package com.wayfair.labs.pixabay.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photos {

    @SerializedName("totalHits")
    @Expose
    public Integer totalHits;
    @SerializedName("hits")
    @Expose
    public List<Photo> photos = null;
    @SerializedName("total")
    @Expose
    public Integer total;

}
