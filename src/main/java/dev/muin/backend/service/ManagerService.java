package dev.muin.backend.service;

import dev.muin.backend.domain.Payment.PaymentRepository;
import dev.muin.backend.domain.Product.Category;
import dev.muin.backend.domain.Product.Product;
import dev.muin.backend.domain.Product.ProductRepository;
import dev.muin.backend.domain.Sales.SalesRepository;
import dev.muin.backend.domain.Stock.Stock;
import dev.muin.backend.domain.Stock.StockRepository;
import dev.muin.backend.domain.Store.Store;
import dev.muin.backend.domain.Store.StoreRepository;
import dev.muin.backend.service.dto.*;
import dev.muin.backend.web.request.AddNewStockRequest;
import dev.muin.backend.web.response.AllPaymentsResponse;
import dev.muin.backend.web.response.AllStocksPerStoreResponse;
import dev.muin.backend.web.response.HomeResponse;
import dev.muin.backend.web.response.MyStoreResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ManagerService {

    private final StoreRepository storeRepository;
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;
    private final SalesRepository salesRepository;
    private final String RESPONSE_SUCCESS = "success";

    @Transactional(readOnly = true)
    public List<MyStoreResponse> getMyStores(String userUuid) {
        List<MyStoreResponse> res = storeRepository.findByUserId(userUuid);
        return res;
    }

    // TODO request에 user_uuid 넣어달라고 하기
    @Transactional(readOnly = true)
    public AllStocksPerStoreResponse getStocksPerStore(Short storeId) {
        storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("Store Not Found"));
        List<StockAndProductDto> findRes = stockRepository.findByStoreId(storeId);
        Map<Category, List<StockAndProductDto>> catRes = findRes.stream()
                .collect(Collectors.groupingBy((StockAndProductDto::getCategory)));

        return new AllStocksPerStoreResponse(storeId, catRes);
    }

    @Transactional
    public String updateStock(Short storeId, int stockId, int quantity) throws IllegalArgumentException {
        if (quantity < 0) throw new IllegalArgumentException("Cannot set under zero(0)");
        stockRepository.updateStockByManager(storeId, stockId, quantity);
        return RESPONSE_SUCCESS;
    }

    @Transactional(readOnly = true)
    public List<ProductsGroupedByCategoryDto> getProducts() {
        List<StockAndProductDto> findRes = productRepository.findAllProducts();
        Map<Category, List<StockAndProductDto>> catRes = findRes.stream()
                .collect(Collectors.groupingBy((StockAndProductDto::getCategory)));

        return catRes.entrySet().stream()
                .map(ProductsGroupedByCategoryDto::generateProductDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public String addNewStockToStore(Short storeId, AddNewStockRequest addNewStockRequest) throws IllegalArgumentException {
        if (addNewStockRequest.getQuantity() < 0) throw new IllegalArgumentException("Cannot set under zero(0)");
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("Store Not Found"));
        Product product = productRepository.findById(addNewStockRequest.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product Not Found"));

        Optional<Stock> foundStock = stockRepository.findByStoreIdAndProductId(storeId, addNewStockRequest.getProductId());
        if (foundStock.isPresent()) throw new IllegalArgumentException("Already added in stock");

        Stock stock = new Stock(addNewStockRequest.getQuantity(), store, product);
        stockRepository.save(stock);
        log.info("Add new product(" + store.getName() + ") to store(" + product.getName() + ")");

        return RESPONSE_SUCCESS;
    }

    @Transactional
    public String removeStockToStore(Short storeId, int stockId) throws IllegalArgumentException {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("Store Not Found"));
        Stock stock = stockRepository.findById(stockId).orElseThrow(() -> new IllegalArgumentException("Stock Not Found"));
        if (stock.getStore() == store) stockRepository.delete(stock);
        else throw new IllegalArgumentException("Stock not matches to store"); // FIXME 이럴 일은없을 듯?
        return RESPONSE_SUCCESS;
    }

    @Transactional(readOnly = true)
    public AllPaymentsResponse getRecordOfPayments(Short storeId){
        storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("Store Not Found"));
        List<RecentPaymentDto> res = paymentRepository.findAllOrderByPayTime(storeId);
        return new AllPaymentsResponse(storeId, res);
    }

    @Transactional(readOnly = true)
    public HomeResponse getHomeData(Short storeId) {
        storeRepository.findById(storeId).orElseThrow(() -> new IllegalArgumentException("Store Not Found"));
        List<RecentPaymentDto> recentPaymentDtos = getRecent5Payments(storeId);
        int month = getMonthlySales(storeId);
        int today = getTodaySales(storeId);
        List<MonthPaymentDto> statistics = getLast6MonthPayment(storeId);
        StockStatusDto stockStatusDto = getStockStatus(storeId);
        SalesAnalysisDto salesAnalysisDto = SalesAnalysisDto.builder()
                .recentPayments(recentPaymentDtos)
                .month(month)
                .today(today)
                .statistics(statistics)
                .stockStatus(stockStatusDto)
                .build();
        return new HomeResponse(storeId, salesAnalysisDto);
    }

    private List<RecentPaymentDto> getRecent5Payments(Short storeId) {
        Pageable firstFiveElements = PageRequest.of(0, 5);
        return paymentRepository.find5PaymentByOrderByPayTime(storeId, firstFiveElements);
    }

    private int getMonthlySales(Short storeId) throws NullPointerException {
        Integer res = paymentRepository.findThisMonthByStore(storeId);
        if (res == null) return 0; // No sales has occurred this month
        return res;
    }

    private int getTodaySales(Short storeId) {
        Integer res = paymentRepository.findTodayByStore(storeId);
        if (res == null) return 0; // No sales has occurred today
        return res;
    }

    private List<MonthPaymentDto> getLast6MonthPayment(Short storeId) {
        return salesRepository.findLast6MonthPayment(storeId)
                .stream().map(MonthPaymentDto::new).collect(Collectors.toList());
    }

    private StockStatusDto getStockStatus(Short storeId) {
        Long[] res = stockRepository.countStatusByStoreId(storeId).get(0); //List<Long[]>
        log.info(String.format("매장(id=%d)의 재고 부족:%s/품절:%s",storeId, res[0].toString(),res[1].toString()));
        StockStatusDto stockStatusDto = new StockStatusDto(Long.valueOf(res[0].toString()).intValue(),
                Long.valueOf(res[1].toString()).intValue());
        return stockStatusDto;
    }
}
