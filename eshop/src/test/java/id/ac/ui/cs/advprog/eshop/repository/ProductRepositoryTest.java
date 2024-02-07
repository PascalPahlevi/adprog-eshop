package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product product1 = new Product();
        product1.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product1.setProductId("Sampo Cap Usep");
        product1.setProductQuantity(50);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId(product1.getProductId());
        product2.setProductName("Apple Vision Pro");
        product2.setProductQuantity(100);
        productRepository.update(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();

        assertEquals(product1.getProductId(), savedProduct.getProductId());
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertEquals(product2.getProductName(), savedProduct.getProductName());
        assertEquals(product2.getProductQuantity(), savedProduct.getProductQuantity());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product1.setProductName("Sampo Cap Usep");
        product1.setProductQuantity(50);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId(product1.getProductId());
        product2.setProductName("Playstation 5");
        product2.setProductQuantity(80);
        productRepository.update(product2);

        Product product3 = new Product();
        product3.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product3.setProductName("Apple Vision Pro");
        product3.setProductQuantity(100);
        productRepository.create(product3);

        Product product4 = new Product();
        product4.setProductId(product3.getProductId());
        product4.setProductName("Metaquest 3");
        product4.setProductQuantity(100);
        productRepository.update(product4);

        Iterator<Product> productIterator = productRepository.findAll();

        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();

        assertEquals(product1.getProductId(), savedProduct.getProductId());
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertEquals(product2.getProductName(), savedProduct.getProductName());
        assertEquals(product2.getProductQuantity(), savedProduct.getProductQuantity());

        assertTrue(productIterator.hasNext());
        savedProduct = productIterator.next();

        assertEquals(product3.getProductId(), savedProduct.getProductId());
        assertEquals(product4.getProductId(), savedProduct.getProductId());
        assertEquals(product4.getProductName(), savedProduct.getProductName());
        assertEquals(product4.getProductQuantity(), savedProduct.getProductQuantity());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProduct() {
        Product product1 = new Product();
        product1.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product1.setProductId("Sampo Cap Usep");
        product1.setProductQuantity(50);
        productRepository.create(product1);

        productRepository.delete(product1);

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteWithMultipleProducts() {
        Product product1 = new Product();
        product1.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product1.setProductName("Sampo Cap Usep");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product2.setProductName("Playstation 5");
        product2.setProductQuantity(30);
        productRepository.create(product2);

        Product product3 = new Product();
        product3.setProductId("a1f8e34-2bc5-547f-82de54fb4f75");
        product3.setProductName("Metaquest 3");
        product3.setProductQuantity(23);
        productRepository.create(product3);

        Product product4 = new Product();
        product4.setProductId(product2.getProductId());
        product4.setProductName("Apple Vision Pro");
        product4.setProductQuantity(999);
        productRepository.create(product4);

        productRepository.delete(product1);
        productRepository.delete(product3);
        Iterator<Product> productIterator = productRepository.findAll();

        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();

        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertEquals(product2.getProductName(), savedProduct.getProductName());
        assertEquals(product2.getProductQuantity(), savedProduct.getProductQuantity());

        assertTrue(productIterator.hasNext());
        savedProduct = productIterator.next();
        assertEquals(product4.getProductId(), savedProduct.getProductId());
        assertEquals(product4.getProductName(), savedProduct.getProductName());
        assertEquals(product4.getProductQuantity(), savedProduct.getProductQuantity());

        assertFalse(productIterator.hasNext());
    }
}
