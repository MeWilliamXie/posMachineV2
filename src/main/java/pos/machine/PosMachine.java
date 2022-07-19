package pos.machine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {
        Map<String, Integer> map = getCartInfo(barcodes);
        List<ReceiptDetail> receiptDetailList = caculateSubTotal(map, barcodes);
        String receipt = getReceipt(receiptDetailList);
        return receipt;
    }

    public Map<String, Integer> getCartInfo(List<String> barcodes) {
        Map<String, Integer> map = new HashMap<>();
        barcodes.forEach(barcode -> map.put(barcode, map.getOrDefault(barcode, 0) + 1));
        return map;
    }


    public List<ReceiptDetail> caculateSubTotal(Map<String, Integer> map, List<String> barcodes) {
        List<ReceiptDetail> receiptDetailList = new ArrayList<>();
        List<ItemInfo> allItemInfos = ItemDataLoader.loadAllItemInfos();
        List<ItemInfo> itemInfos = allItemInfos.stream().filter(itemInfo ->
                map.keySet().stream().anyMatch(barcode -> barcode.equals(itemInfo.getBarcode()))
        ).collect(Collectors.toList());
        barcodes.stream().distinct().forEach(barcode -> {
            ItemInfo item = itemInfos.stream().filter(itemInfo -> itemInfo.getBarcode().equals(barcode)).findFirst().get();
            ReceiptDetail receiptDetail = new ReceiptDetail(map.get(item.getBarcode()),item.getName(),item.getPrice(),item.getPrice() * map.get(item.getBarcode()));
            receiptDetailList.add(receiptDetail);
        });

        return receiptDetailList;
    }

    public String getReceipt(List<ReceiptDetail> receiptDetailList){
        String receipt = "***<store earning no money>Receipt***\n";
        for (int i = 0; i < receiptDetailList.size(); i++) {
            ReceiptDetail receiptDetail = receiptDetailList.get(i);
            receipt += "Name: "+ receiptDetail.getName() +", Quantity: "+ receiptDetail.getQuantity() +", Unit price: "+ receiptDetail.getUnitPrice()+" (yuan), Subtotal: "+receiptDetail.getSubTotal()+" (yuan)\n";

        }
        receipt +=  "----------------------\n" +
                "Total: "+ caculateTotalPrice(receiptDetailList) +" (yuan)\n" +
                "**********************";
        return  receipt;
    }

    public int caculateTotalPrice(List<ReceiptDetail> receiptDetailList){
        int totalPrice = 0;
        for (int i = 0; i < receiptDetailList.size(); i++) {
            totalPrice += receiptDetailList.get(i).getSubTotal();

        }
        return totalPrice;
    }

}