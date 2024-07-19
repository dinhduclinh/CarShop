/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author DLCT
 */
import java.util.ArrayList;
import java.util.List;

public class CartDTO {

    private List<CartItem> cartItems;

    public CartDTO() {
        this.cartItems = new ArrayList<>();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public CartItem findItemByProduct(ProductDTO product) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == product.getId()) {
                return item;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    // Các phương thức khác liên quan đến quản lý giỏ hàng
    public void addToCart(ProductDTO product, int quantity) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId() == product.getId()) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                return;
            }
        }
        CartItem cartItem = new CartItem(product, quantity);
        cartItems.add(cartItem);
    }

    public void removeFromCart(ProductDTO product) {
        cartItems.removeIf(cartItem -> cartItem.getProduct().getId() == product.getId());
    }

    public void updateQuantity(ProductDTO product, int newQuantity) {
        CartItem existingItem = findItemByProduct(product);

        if (existingItem != null) {
            // Kiểm tra xem số lượng mới có lớn hơn 0 không
            if (newQuantity > 0) {
                existingItem.setQuantity(newQuantity);
            } else {
                // Nếu số lượng mới là không hợp lệ, xóa sản phẩm khỏi giỏ hàng
                cartItems.remove(existingItem);
            }
        }
    }

    public void clearCart() {
        cartItems.clear();
    }

    @Override
    public String toString() {
        return "CartDTO{" + "cartItems=" + cartItems + '}';
    }

}
