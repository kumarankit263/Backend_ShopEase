package com.Ecommerce.App.ServiceImpl;

import com.Ecommerce.App.Exception.CartException;
import com.Ecommerce.App.Exception.ProductException;
import com.Ecommerce.App.Exception.UserException;
import com.Ecommerce.App.Model.Cart;
import com.Ecommerce.App.Model.CartItem;
import com.Ecommerce.App.Model.Product;
import com.Ecommerce.App.Model.User;
import com.Ecommerce.App.Repository.CartItemRepository;
import com.Ecommerce.App.Repository.CartRepository;
import com.Ecommerce.App.Repository.ProductRepository;
import com.Ecommerce.App.Repository.UserRepository;
import com.Ecommerce.App.Service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final ProductRepository productRepository;

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final UserRepository userRepository;

    public CartServiceImpl(ProductRepository productRepository, CartRepository cartRepository, CartItemRepository cartItemRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Cart addProductToCart(Integer userId, Integer productId) throws CartException {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("Product not available in Stock..."));

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User Not Found In Database"));

        Cart userCart = existingUser.getCart();

        if (userCart != null) {
            List<CartItem> cartItems = userCart.getCartItems();
            if (cartItems != null) {
                for (CartItem cartItem : cartItems) {
                    if (cartItem.getProduct().getProductId().equals(productId)) {
                        throw new CartException("Product Already in the Cart, Please Increase the Quantity");
                    }
                }
            }

            CartItem cartItem = new CartItem();
            cartItem.setProduct(existingProduct);
            cartItem.setQuantity(1);
            cartItem.setCart(userCart);

            userCart.getCartItems().add(cartItem);
            userCart.setTotalAmount(calculateCartTotal(userCart.getCartItems()));

            cartRepository.save(userCart);
            return userCart;

        } else {
            Cart newCart = new Cart();
            newCart.setUser(existingUser);

            CartItem cartItem = new CartItem();
            cartItem.setProduct(existingProduct);
            cartItem.setQuantity(1);
            cartItem.setCart(newCart);

            newCart.getCartItems().add(cartItem);
            newCart.setTotalAmount(calculateCartTotal(newCart.getCartItems()));

            existingUser.setCart(newCart);
            userRepository.save(existingUser);

            return newCart;
        }
    }
    private double calculateCartTotal(List<CartItem>cartItems){
        double total=0.0;
        for(CartItem item:cartItems){
            double itemPrice=item.getProduct().getPrice();
            int itemQuantity=(item.getQuantity());
            total+=itemPrice*itemQuantity;
        }
        return total;
    }

    @Override
    public Cart increaseProductQuantity(Integer userId, Integer productId) throws CartException {
        User existingUser= userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User Not Found in Database"));

        if(existingUser.getCart()==null){
            throw new CartException("Cart not found");
        }

        Cart userCart=existingUser.getCart();
        List<CartItem>cartItems= userCart.getCartItems();

        CartItem cartItemToUpdate=cartItems.stream()
                .filter(item->item.getProduct().getProductId().equals(productId)
                && item.getCart().getCartId().equals(userCart.getCartId())).findFirst()
                .orElseThrow(()->new CartException("cart Item Not found"));

        int quantity=cartItemToUpdate.getQuantity();
        cartItemToUpdate.setQuantity(quantity+1);


        userCart.setCartItems(cartItems);
        userCart.setTotalAmount(calculateCartTotal(cartItems));
        cartRepository.save(userCart);

        return userCart;

    }

    @Override
    public Cart decreaseProductQuantity(Integer userId, Integer productId) throws CartException {
        User existingUser=userRepository.findById(userId)
                .orElseThrow(()->new UserException("User not found"));

        if(existingUser.getCart()==null){
            throw new CartException("Cart not found");
        }

        Cart userCart=existingUser.getCart();
        List<CartItem>cartItems=userCart.getCartItems();
        CartItem cartItemToUpdate=cartItems.stream()
                .filter(item->item.getProduct().getProductId().equals(productId)
                && item.getCart().getCartId().equals(userCart.getCartId())).findFirst()
                .orElseThrow(()->new CartException("Cart Item Not found"));
        int quantity=cartItemToUpdate.getQuantity();

        if(quantity==1){
            throw new CartException("Product can not be further decress");
        }
        if(quantity>1){
            cartItemToUpdate.setQuantity(quantity-1);
            userCart.setCartItems(cartItems);
            userCart.setTotalAmount(calculateCartTotal(cartItems));
            cartRepository.save(userCart);
        }else{
            cartItems.remove(cartItemToUpdate);
            userCart.setCartItems(cartItems);
            userCart.setTotalAmount(calculateCartTotal(cartItems));
            cartRepository.save(userCart);
        }
        return userCart;
    }

    @Override
    public void removeProductFromCart(Integer cartId, Integer productId) throws CartException {
        Cart existingCart=cartRepository.findById(cartId)
                .orElseThrow(() -> new CartException("Cart Not Found"));

        cartItemRepository.removeProductFromCart(cartId,productId);

        List<CartItem>list=existingCart.getCartItems();
        existingCart.setTotalAmount(calculateCartTotal(list));
        cartRepository.save(existingCart);
    }

    @Override
    public void removeAllProductFromCart(Integer cartId) throws CartException {
        Cart existingCart = cartRepository.findById(cartId).orElseThrow(() -> new CartException("Cart Not Found"));

        cartItemRepository.removeAllProductFromCart(cartId);

        existingCart.setTotalAmount(0.0);
        cartRepository.save(existingCart);
    }

    @Override
    public Cart getAllCartProduct(Integer cartId) throws CartException {
        Cart existingCart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartException("Cart Not Found"));

        List<CartItem> cartItems = existingCart.getCartItems();
        if (cartItems == null || cartItems.isEmpty()) {
            throw new CartException("Cart is Empty...");
        }

        return existingCart;
    }
}
