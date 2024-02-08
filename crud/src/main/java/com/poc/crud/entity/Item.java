package com.poc.crud.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Item")
public class Item {

    @Id
    @Column(name = "ITEM_ID")
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "ITEM_DESC")
    private String itemDesc;

    @Column(name = "ITEM_TYPE")
    private String itemType;

    @Column(name = "ITEM_PRICE")
    private Double itemPrice;

    @Column(name = "ITEM_MFG_DATE")
    private Date itemManufacturedDate;

    @Column(name = "ITEM_EXP_DATE")
    private Date itemExpiryDate;

    @Column(name = "ITEM_QUANTITY")
    private Long itemQuantity;

    public Item(Long itemId, String itemName, String itemDesc, String itemType, Double itemPrice, Date itemManufacturedDate, Date itemExpiryDate, Long itemQuantity) {
        super();
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.itemType = itemType;
        this.itemPrice = itemPrice;
        this.itemManufacturedDate = itemManufacturedDate;
        this.itemExpiryDate = itemExpiryDate;
        this.itemQuantity = itemQuantity;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Date getItemManufacturedDate() {
        return itemManufacturedDate;
    }

    public void setItemManufacturedDate(Date itemManufacturedDate) {
        this.itemManufacturedDate = itemManufacturedDate;
    }

    public Date getItemExpiryDate() {
        return itemExpiryDate;
    }

    public void setItemExpiryDate(Date itemExpiryDate) {
        this.itemExpiryDate = itemExpiryDate;
    }

    public Long getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Long itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
