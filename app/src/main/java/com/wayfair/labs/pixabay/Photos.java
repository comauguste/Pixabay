
package com.wayfair.labs.pixabay;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
