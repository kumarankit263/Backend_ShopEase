package com.Ecommerce.App.Controller;


import com.Ecommerce.App.Model.Cart;
import com.Ecommerce.App.Service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecom/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add-product")
    public ResponseEntity<Cart> addProductToCart(@RequestParam Integer userId, @RequestParam Integer productId) {
        Cart cart = cartService.addProductToCart(userId, productId);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);

    }

    @DeleteMapping("/remove-product/{cartId}/{productId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Integer cartId, @PathVariable Integer productId) {
        cartService.removeProductFromCart(cartId, productId);
        String msg = "Prodcut is removed from cart";
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

    @GetMapping("/products/{cartId}")
    public ResponseEntity<Cart> getAllCartProducts(@PathVariable Integer cartId) {
        Cart products = cartService.getAllCartProduct(cartId);
        return ResponseEntity.ok(products);
    }
    @DeleteMapping("/empty-Cart/{cartId}")
    public ResponseEntity<String>removeAllProductFromCart(@PathVariable Integer cartId){
        cartService.removeAllProductFromCart(cartId);
        String msg="All product Remove From cart";
        return new ResponseEntity<String>(msg,HttpStatus.OK);
    }

    @PutMapping("/increase-productQty/{cartId}/{productId}")
    public ResponseEntity<Cart> increaseProductQuantity(
            @PathVariable Integer cartId,
            @PathVariable Integer productId

    ) {
        Cart cart = cartService.increaseProductQuantity(cartId, productId);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/decrease-productQty/{cartId}/{productId}")
    public ResponseEntity<Cart> decreaseProductQuantity(
            @PathVariable Integer cartId,
            @PathVariable Integer productId

    ) {
        Cart cart = cartService.decreaseProductQuantity(cartId, productId);
        return ResponseEntity.ok(cart);
    }
}
