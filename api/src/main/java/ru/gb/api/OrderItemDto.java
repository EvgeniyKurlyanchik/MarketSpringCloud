package ru.gb.api;





public class OrderItemDto {

    private Long productId;
    private String title;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public OrderItemDto(ProductDto product) {

    }



    public OrderItemDto() {
    }

    public OrderItemDto(Long productId, String title, int quantity, int pricePerProduct, int price) {
        this.productId = productId;
        this.title = title;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }



    public void changeQuantity(int delta) {
        this.quantity += delta;
        this.price = this.quantity * this.pricePerProduct;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(int pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
