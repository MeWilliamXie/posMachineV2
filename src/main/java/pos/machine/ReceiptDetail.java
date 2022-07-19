package pos.machine;

public class ReceiptDetail {
    private int quantity;
    private String name;
    private int unitPrice;
    private int subTotal;
    public ReceiptDetail(int quantity, String name, int unitPrice, int subTotal){
        this.quantity = quantity;
        this.name = name;
        this.unitPrice = unitPrice;
        this.subTotal = subTotal;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
}
