package org.example;

public class Product {
    private final String name;
    private final String photoId;
    private final String description;
    private final String price;
    private final String phoneNumber;
    private Category category;

    public Product(String name, String price, String description, String phoneNumber, String photoId) {
        this.name = name;
        this.photoId = photoId;
        this.description = description;
        this.price = price;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhotoId() {
        return photoId;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Category getCategory() {
        return category;
    }
}

