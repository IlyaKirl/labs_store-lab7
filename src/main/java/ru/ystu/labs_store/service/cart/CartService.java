package ru.ystu.labs_store.service.cart;import ru.ystu.labs_store.model.Cart;import java.util.List;public interface CartService {	List<Cart> getProductList(String login, Integer id);	List<Cart> getProductListByIdHistory(String login, Integer id);	String getTotalPrice(String login, Integer id);	void addProductToCartById(int idProduct, String login);	void removeProductById(int id);	void decreaseCountProduct(int id);	void increaseCountProduct(int id);}