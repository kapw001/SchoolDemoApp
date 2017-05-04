package pappayaed.com.demoapp.main;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yasar on 3/5/17.
 */

public class Items implements Parcelable {
    private String price;
    private String itemName;

    public Items() {
    }

    protected Items(Parcel in) {
        price = in.readString();
        itemName = in.readString();
    }

    public static final Creator<Items> CREATOR = new Creator<Items>() {
        @Override
        public Items createFromParcel(Parcel in) {
            return new Items(in);
        }

        @Override
        public Items[] newArray(int size) {
            return new Items[size];
        }
    };

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(price);
        dest.writeString(itemName);
    }
}
